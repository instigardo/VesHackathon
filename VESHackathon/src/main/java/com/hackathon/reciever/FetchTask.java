package com.hackathon.reciever;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.hackathon.core.ApiCollector;
import com.hackathon.core.ApiRepository;

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
		for (ApiCollector apiCollector : documents) {
			String uri = apiCollector.getUri();
			if(!settings.getRestUris().contains(uri)){
				
			}
		}
		// TODO Auto-generated method stub
		
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
