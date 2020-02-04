package com.eatza.restaurantsearch.service.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.eatza.restaurantsearch.service.menuservice.MenuServiceImpl;

@Service
public class KafkaConsumer implements IKafkaConsumer{
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
		
	    @KafkaListener(topics = "${kafka-topic}", groupId = "group_id")
	    public void consume(String message) throws IOException {
	        logger.info("Kafka messgae:"+message);
	    }
}
