package com.hackathon.reciever;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Strings;
import com.hackathon.core.ApiCollector;
import com.hackathon.core.ApiRepository;
import com.hackathon.parser.Parser;


@Component
public class FetchTask implements Runnable{
    private static final Logger LOGGER = LoggerFactory.getLogger(FetchTask.class);

    private final TaskScheduler taskScheduler;
    private final RestSettings settings;
    private final ApiRepository<ApiCollector> apiRepository;
    
    
    
    @Autowired
    protected FetchTask(TaskScheduler taskScheduler,
    		RestSettings settings,
    		ApiRepository<ApiCollector> apiRepository) {
        this.taskScheduler = taskScheduler;
        this.settings = settings;
        this.apiRepository = apiRepository;
}
    
	@Override
	public void run() {
		Iterable<ApiCollector> documents = apiRepository.findAll();
		List<String> uris = new ArrayList<>();
		for (ApiCollector apiCollector : documents) {
			uris.add(apiCollector.getUri());
		}
		for (String settingUri : settings.getRestUris()) {
			if(!uris.contains(settingUri)){
				saveApi(settingUri);
			}			
		}
		try {
			restCalls();
		} catch (Exception e) {
			LOGGER.error("Rest call exception", e);
		}
	}
	
	private void restCalls() throws UnsupportedEncodingException, URISyntaxException, ParseException {
        String api_endpoint = "http://rss2json.com/api.json?rss_url=";
    	Iterable<ApiCollector> documents = apiRepository.findAll();
    	for (ApiCollector apiCollector : documents) {
        	RestTemplate restTemplate = new RestTemplate();
        	String rss_url = apiCollector.getUri();
    		URI URI = new URI(api_endpoint+URLEncoder.encode(rss_url, "UTF-8"));
            String response = restTemplate.getForObject(URI, String.class);
            JSONParser jsonParser = new JSONParser();
			JSONObject responseJson = (JSONObject) jsonParser.parse(response);
			if("ok".equals(responseJson.get("status"))){
				JSONArray itemsJson = (JSONArray) responseJson.get("items");
				for (Object item : itemsJson) {
					JSONObject itemJson = (JSONObject) item;
					Parser parser = new Parser();
					parser.parseFetchedData(itemJson);
					
				}
				System.out.println(responseJson.get("items"));
			}
		}
		
    	// TODO Auto-generated method stub
		
	}

	private void saveApi(String settingUri) {
		ApiCollector apiCollector = new ApiCollector();
		apiCollector.setUri(settingUri);
		apiCollector.setInsert_timestamp(System.currentTimeMillis());
		apiCollector.setEnabled(true);
		apiRepository.save(apiCollector);
		
	}

	@PostConstruct
    public void onStartup() {
        taskScheduler.schedule(this, new CronTrigger(settings.getCron()));
//        setOnline(true);
    }

    @PreDestroy
    public void onShutdown() {
//        setOnline(false);
    }
//
//    private void setOnline(boolean online) {
//        T collector = getCollectorRepository().findByName(collectorName);
//        if (collector != null) {
//            collector.setOnline(online);
//            getCollectorRepository().save(collector);
//        }
//    }


    protected void log(String marker, long start) {
        log(marker, start, null);
    }

    protected void log(String text, long start, Integer count) {
        long end = System.currentTimeMillis();
        String elapsed = ((end - start) / 1000) + "s";
        String token2 = "";
        String token3;
        if (count == null) {
            token3 = Strings.padStart(" " + elapsed, 35 - text.length(), ' ');
        } else {
            token2 = Strings.padStart(" " + count.toString(), 25 - text.length(), ' ');
            token3 = Strings.padStart(" " + elapsed, 10, ' ');
        }
        LOGGER.info(text + token2 + token3);
    }
    protected void log(String message) {
        LOGGER.info(message);
    }

    protected void logBanner(String bannerHead) {
        LOGGER.info("-----------------------------------");
        LOGGER.info(bannerHead);
        LOGGER.info("-----------------------------------");
}

}
