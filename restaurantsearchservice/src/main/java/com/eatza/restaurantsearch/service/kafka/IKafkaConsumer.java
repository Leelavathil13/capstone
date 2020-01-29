package com.eatza.restaurantsearch.service.kafka;

import java.io.IOException;

public interface IKafkaConsumer {
	public String consume(String message) throws IOException;

}
