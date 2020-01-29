package com.eatza.restaurantsearch.service.kafka;

import java.io.IOException;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer implements IKafkaConsumer{
	
	    @KafkaListener(topics = "java_in_use_topic", groupId = "group_id")
	    public String consume(String message) throws IOException {
	        return message;
	    }
}
