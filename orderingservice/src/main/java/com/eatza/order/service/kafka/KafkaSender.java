package com.eatza.order.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.eatza.order.model.Message;

import org.springframework.messaging.support.MessageBuilder;
@Service
@EnableBinding(Source.class)
public class KafkaSender {

	/*
	 * @Autowired private KafkaTemplate<String, String> kafkaTemplate;
	 * 
	 * String kafkaTopic = "${kafka-topic}";
	 * 
	 * public void send(String message) {
	 * 
	 * kafkaTemplate.send(kafkaTopic, message); }
	 */
	

	@Autowired
	MessageChannel output;
	
	private static final Logger LOG = LoggerFactory.getLogger(KafkaSender.class);
	
	
	
	public Message sendMessage(@RequestBody Message message){
		output.send(MessageBuilder.withPayload(message).build());
		LOG.info("sending data='{}' to topic='{}'", message);
		return message;
		
	} 
}
