package com.eatza.restaurantsearch.service.kafka;

import java.io.IOException;

import com.eatza.restaurantsearch.model.Message;

public interface IKafkaConsumer {
	public void receiveMessage(Message message)  throws IOException;

}
