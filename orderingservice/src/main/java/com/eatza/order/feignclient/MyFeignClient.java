package com.eatza.order.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eatza.order.dto.ItemFetchDto;

@FeignClient(value="restaurant-search-item",url="http://localhost:8084/item/id/")
public interface MyFeignClient {
	
	@GetMapping("/{id}")
	ItemFetchDto feignclientResponse(@PathVariable("id") Long id);

}
