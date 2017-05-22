package com.swe.dev.service;

import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.swe.dev.dao.MessageDao;
import com.swe.dev.model.Message;

public class MessageServiceImplTest {

	@Mock
    MessageDao dao;
	
	@InjectMocks
	MessageServiceImpl messageService;
	
	@Spy
	List<Message> messages = new ArrayList<Message>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		messages = getMessageList();
	}
     
	@Test
    public void findById() {
		Message msg = messages.get(0);
        when(dao.findById(anyString())).thenReturn(msg);
        Assert.assertEquals(messageService.findById(msg.getId()),msg);
    }
 
	@Test
    public void findAllMessages() {
		when(dao.findAllMessages()).thenReturn(messages);
        Assert.assertEquals(messageService.findAllMessages(), messages);
    }
    
    public List<Message> getMessageList(){
    	Message message1 = new Message();
    	message1.setAccount("umurtrky");
    	message1.setCreatedate(new Date());
    	message1.setId("1");
    	message1.setMessage("#goal Ronaldo!");
    	message1.setSentiment(2);
    	message1.setSharedate(new Date());
    	messages.add(message1);
    	Message message2 = new Message();
    	message2.setAccount("cemylmz");
    	message2.setCreatedate(new Date());
    	message2.setId("1");
    	message2.setMessage("#comedy Very funny.");
    	message2.setSentiment(4);
    	message2.setSharedate(new Date());
    	messages.add(message2);
    	return messages;
    }
}
