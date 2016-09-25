package com.hackathon.parser;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.simple.JSONObject;

public class Parser {

	private static final String author = "author";
	private static final String content = "content";
	private static final String link = "link";
	private static final String publishDate = "pubDate";
	private static final String title = "title";
	private static final Boolean enabled = true;
	private static final Boolean upgrade = false;
	private static final Boolean relevant = false;
	private static final float initialScore = 0;
	private static final Long defaultUpdateTS = (long) 0;

	public void parseFetchedData(JSONObject itemJson) {
		ResponseRepository<ResponseCollector> responseRepository;
		ResponseCollector responseCollector = new ResponseCollector();
		responseCollector.setAuthor(itemJson.get(author).toString());
		responseCollector.setContent(itemJson.get(content).toString());
		responseCollector.setLink(itemJson.get(link).toString());
		//responseCollector.setPublishDate(publishDate);
		responseCollector.setTitle(title);
		responseCollector.setEnabled(enabled);
		responseCollector.setUpgrade(upgrade);
		responseCollector.setRelevant(relevant);
		responseCollector.setScore(initialScore);
		responseCollector.setUpdateTimeStamp(defaultUpdateTS);
		DateTimeFormatter parser = ISODateTimeFormat.dateTime();
        DateTime dt = parser.parseDateTime(itemJson.get(publishDate).toString());

        DateTimeFormatter formatter = DateTimeFormat.mediumDateTime();
        System.out.println(formatter.print(dt));
		
	}


}
