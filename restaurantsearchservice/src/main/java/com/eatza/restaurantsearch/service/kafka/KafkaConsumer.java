package com.eatza.restaurantsearch.service.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.eatza.restaurantsearch.model.Message;

@Service
@EnableBinding(Sink.class) 
public class KafkaConsumer implements IKafkaConsumer{
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	/*
	 * @KafkaListener(topics = "${kafka-topic}", groupId = "group_id") public void
	 * consume(String message) throws IOException {
	 * logger.info("Kafka messgae:"+message); }
	 */
	   
		@StreamListener("input")
		public void receiveMessage(Message message) {
			logger.info("Kafka messgae:" + message);
		}
	    
	    
}
