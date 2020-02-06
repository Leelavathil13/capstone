package com.eatza.restaurantsearch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Message {
	
	
	
	private int id;
	private String content;
	
	public Message(int id,String content) {
		
		this.id = id;
		this.content=content;
		
	}

}