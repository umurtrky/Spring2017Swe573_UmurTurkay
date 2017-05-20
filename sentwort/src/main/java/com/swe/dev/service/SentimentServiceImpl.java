package com.swe.dev.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.swe.dev.model.Message;
import com.swe.dev.model.SentimentReport;

@Service("sentimentService")
@Transactional
public class SentimentServiceImpl implements SentimentService {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
    MessageService messageService;
	
	private String sendPostRequest(String requestParameter){
		String result = null;
		try{
			String url = "http://www.sentiment140.com/api/bulkClassifyJson?appid=umur.turkay@gmail.com";
	
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);
			
			StringEntity postingString = new StringEntity(requestParameter);
			post.setEntity(postingString);
			post.setHeader("Content-type", "application/json");
	
			HttpResponse response = client.execute(post);
	
			BufferedReader rd = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));
	
			StringBuffer resultBuffer = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				resultBuffer.append(line);
			}
			result = resultBuffer.toString();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	private String createRequestString(List<Message> nonAnalyzedMessages){
		String dataToAnalyze = "{\"data\": [";
		for(int i=0;i<nonAnalyzedMessages.size();i++){
			String messageBody = nonAnalyzedMessages.get(i).getMessage();
			String messageid = nonAnalyzedMessages.get(i).getId();
			if(messageBody.contains("\"")){
				messageBody = messageBody.replace("\"", "");
			}
			dataToAnalyze += "{\"text\":\"" + messageBody + "\", \"id\":" + messageid + "}";
			if(i != nonAnalyzedMessages.size()-1){
				dataToAnalyze += ",";
			}
			else{
				dataToAnalyze += "]}";
			}
		}
		dataToAnalyze = dataToAnalyze.replace("\n", " ");
		return dataToAnalyze;
	}
	
	public void analyze(List<Message> nonAnalyzedMessages){
		String dataToAnalyze = createRequestString(nonAnalyzedMessages);
		String result = sendPostRequest(dataToAnalyze);
		JsonParser jparser = new JsonParser();
		JsonObject jobject = jparser.parse(result).getAsJsonObject();
		JsonArray messageArray = jobject.get("data").getAsJsonArray();
		for(JsonElement element : messageArray){
	    	JsonObject jsonMessage = jparser.parse(element.toString()).getAsJsonObject();
	    	Message messageToUpdate = messageService.findById(jsonMessage.get("id").toString());
	    	Integer sentiment = jsonMessage.get("polarity").getAsInt();
	    	messageService.updateSentiment(messageToUpdate, sentiment);
	    }
	}
	
	public List<SentimentReport> getReport(List<Integer> hashtagIds){
    	List<SentimentReport> messages = new ArrayList<SentimentReport>();
		
		Query<SentimentReport> query = sessionFactory.getCurrentSession().createQuery("SELECT new com.swe.dev.model.SentimentReport(m.id, "
				+ "m.message, m.account, m.sentiment, m.links, m.sharedate, ht.hashtagname) from Message m, MessageHashtag mht, Hashtag ht "
				+ "where mht.id.messageid=m.id and mht.id.hashtagid=ht.id and mht.id.hashtagid in :hashtagids order by m.sharedate desc",SentimentReport.class);
		query.setParameter("hashtagids", hashtagIds);
		
		messages.addAll(query.list());
		return messages;
    }
	
	public List<SentimentReport> getFilteredReport(List<Integer> hashtagIds, Date startdate, Date enddate, List<Integer> sentiment){
		List<SentimentReport> messages = new ArrayList<SentimentReport>();
		
		Query<SentimentReport> query = sessionFactory.getCurrentSession().createQuery("SELECT new com.swe.dev.model.SentimentReport(m.id, "
				+ "m.message, m.account, m.sentiment, m.links, m.sharedate, ht.hashtagname) from Message m, MessageHashtag mht, Hashtag ht "
				+ "where mht.id.messageid=m.id and mht.id.hashtagid=ht.id and mht.id.hashtagid in :hashtagids and m.sharedate between :startdate and :enddate "
				+ "and m.sentiment in :sentiment order by m.sharedate desc",SentimentReport.class);
		query.setParameter("hashtagids", hashtagIds);
		query.setParameter("startdate", startdate);
		query.setParameter("enddate", enddate);
		query.setParameter("sentiment", sentiment);
		
		messages.addAll(query.list());
		return messages;
	}
}
