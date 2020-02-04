package com.eatza.orderingservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.eatza.order.controller.OrderController;
import com.eatza.order.dto.OrderRequestDto;
import com.eatza.order.dto.OrderUpdateDto;
import com.eatza.order.dto.OrderUpdateResponseDto;
import com.eatza.order.dto.OrderedItemsDto;
import com.eatza.order.exception.InvalidTokenException;
import com.eatza.order.model.Order;
import com.eatza.order.model.OrderedItem;
import com.eatza.order.service.orderservice.OrderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/*import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
*/
@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class)
public class OrderControllerTest {

	
	  @Autowired private MockMvc mockMvc;
	  
	  @MockBean private OrderServiceImpl orderService;
	  
	  @Autowired private ObjectMapper objectMapper;
	  
	  private String obtainAccessToken(String username, String password) throws Exception {
		  
		    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    params.add("grant_type", "password");
		    params.add("client_id", "user");
		    params.add("username", username);
		    params.add("password", password);
		 
		    ResultActions result 
		      = mockMvc.perform(post("/oauth/token")
		        .params(params)
		        .with(httpBasic("fooClientIdPassword","secret"))
		        .accept("application/json;charset=UTF-8"))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType("application/json;charset=UTF-8"));
		 
		    String resultString = result.andReturn().getResponse().getContentAsString();
		 
		    JacksonJsonParser jsonParser = new JacksonJsonParser();
		    return jsonParser.parseMap(resultString).get("access_token").toString();
		}
	  
	  
	  private RequestPostProcessor httpBasic(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}


	private static final long EXPIRATIONTIME = 900000; 
	  
	 
	  
	  @Test public void placeOrder() throws Exception { OrderRequestDto
	  orderRequestDto = new OrderRequestDto(); 
	  String accessToken = obtainAccessToken("user", "password");
	  Order order = new Order(1L,
	  "CREATED", 1L); OrderedItemsDto orderedItems = new OrderedItemsDto(1L, 1);
	  List<OrderedItemsDto> orderedItemsList = new ArrayList<>();
	  orderedItemsList.add(orderedItems); orderRequestDto.setCustomerId(1L);
	  orderRequestDto.setRestaurantId(1L);
	  orderRequestDto.setItems(orderedItemsList);
	  when(orderService.placeOrder(any(OrderRequestDto.class))).thenReturn(order);
	  RequestBuilder request = MockMvcRequestBuilders.post( "/order")
	  .contentType(MediaType.APPLICATION_JSON)
	  .content(objectMapper.writeValueAsString((orderRequestDto)))
	  .header("Authorization", "Bearer " + accessToken); mockMvc.perform(request)
	  .andExpect(status().is(200)) .andReturn(); }
	  
	
	  @Test public void cancelOrder() throws Exception {
	  
		  String accessToken = obtainAccessToken("user", "password");
	  when(orderService.cancelOrder(anyLong())).thenReturn(true); RequestBuilder
	  request = MockMvcRequestBuilders.put( "/order/cancel/1")
	  .accept(MediaType.ALL) .header("Authorization", "Bearer " + accessToken);
	  mockMvc.perform(request) .andExpect(status().isOk()) .andReturn(); }
	  
	  @Test public void cancelOrder_failed() throws Exception {
	  
		  String accessToken = obtainAccessToken("user", "password");
	  when(orderService.cancelOrder(anyLong())).thenReturn(false); RequestBuilder
	  request = MockMvcRequestBuilders.put( "/order/cancel/1")
	  .accept(MediaType.ALL) .header("Authorization", "Bearer " + accessToken);
	  mockMvc.perform(request) .andExpect(status().is(400)) .andReturn();
	  
	  }
	  
	  @Test public void getOrderAmountByOrderId_positive() throws Exception {
		  String accessToken = obtainAccessToken("user", "password");
	  when(orderService.getOrderAmountByOrderId(anyLong())).thenReturn(100.0);
	  RequestBuilder request = MockMvcRequestBuilders.get( "/order/value/1")
	  .accept(MediaType.ALL) .header("Authorization", "Bearer " + accessToken);
	  mockMvc.perform(request) .andExpect(status().is(200)) .andReturn();
	  
	  }
	  
	  @Test public void getOrderAmountByOrderId_negative() throws Exception {
		  String accessToken = obtainAccessToken("user", "password");
	  when(orderService.getOrderAmountByOrderId(anyLong())).thenReturn(0.0);
	  RequestBuilder request = MockMvcRequestBuilders.get( "/order/value/1")
	  .accept(MediaType.ALL) .header("Authorization", "Bearer " + accessToken);
	  mockMvc.perform(request) .andExpect(status().is(400)) .andReturn();
	  
	  }
	  
	  @Test public void getOrderById() throws Exception {
		  String accessToken = obtainAccessToken("user", "password");
	  Optional<Order> order = Optional.of(new Order(1L, "CREATED", 1L));
	  when(orderService.getOrderById(anyLong())).thenReturn(order); RequestBuilder
	  request = MockMvcRequestBuilders.get( "/order/1") .accept(MediaType.ALL)
			  .header("Authorization", "Bearer " + accessToken); mockMvc.perform(request)
	  .andExpect(status().is(200)) .andReturn();
	  
	  
	  
	  }
	  
	  @Test public void getOrderById_negative() throws Exception {
		  String accessToken = obtainAccessToken("user", "password");
	  Optional<Order> order = Optional.empty();
	  when(orderService.getOrderById(anyLong())).thenReturn(order); RequestBuilder
	  request = MockMvcRequestBuilders.get( "/order/1") .accept(MediaType.ALL)
			  .header("Authorization", "Bearer " + accessToken); mockMvc.perform(request)
	  
	  .andExpect(status().is(400)) .andReturn();
	  
	  }
	  
	  @Test public void updateOrder() throws Exception{ List<OrderedItem>
	  orderedList = new ArrayList<>(); List<OrderedItemsDto> orderedDto = new
	  ArrayList<>();
	  String accessToken = obtainAccessToken("user", "password");
	  OrderUpdateDto orderUpdateDto = new OrderUpdateDto(1L, 1L, orderedDto, 1L);
	  when(orderService.updateOrder(any(OrderUpdateDto.class))).thenReturn(new
	  OrderUpdateResponseDto(1L, 1L, "UPDATED", 1L,orderedList )); RequestBuilder
	  request = MockMvcRequestBuilders.put( "/order")
	  .contentType(MediaType.APPLICATION_JSON)
	  .content(objectMapper.writeValueAsString((orderUpdateDto)))
	  .header("Authorization", "Bearer " + accessToken);  mockMvc.perform(request)
	  .andExpect(status().is(200)) .andReturn();
	  
	  }
	  
	  @Test(expected=InvalidTokenException.class) public void updateOrder_invalid()
	  throws Exception{ List<OrderedItem> orderedList = new ArrayList<>();
	  String accessToken = obtainAccessToken("user", "password");
	  List<OrderedItemsDto> orderedDto = new ArrayList<>(); 
	  OrderUpdateDto orderUpdateDto = new
	  OrderUpdateDto(1L, 1L, orderedDto, 1L);
	  when(orderService.updateOrder(any(OrderUpdateDto.class))).thenReturn(new
	  OrderUpdateResponseDto(1L, 1L, "UPDATED", 1L,orderedList )); RequestBuilder
	  request = MockMvcRequestBuilders.put( "/order")
	  .contentType(MediaType.APPLICATION_JSON)
	  .content(objectMapper.writeValueAsString((orderUpdateDto)))
	  .header("Authorization", "Bearer " + accessToken);  mockMvc.perform(request)
	  .andExpect(status().is(401)) .andReturn();
	  
	  }
	  
	  @Test(expected=InvalidTokenException.class) public void
	  updateOrder_null_token() throws Exception{ List<OrderedItem> orderedList =
	  new ArrayList<>(); 
	  String accessToken = obtainAccessToken("user", "password");
	  List<OrderedItemsDto> orderedDto = new ArrayList<>(); 
	  OrderUpdateDto orderUpdateDto = new
	  OrderUpdateDto(1L, 1L, orderedDto, 1L);
	  when(orderService.updateOrder(any(OrderUpdateDto.class))).thenReturn(new
	  OrderUpdateResponseDto(1L, 1L, "UPDATED", 1L,orderedList )); RequestBuilder
	  request = MockMvcRequestBuilders.put( "/order")
	  .contentType(MediaType.APPLICATION_JSON)
	  .content(objectMapper.writeValueAsString((orderUpdateDto)))
	  .header(HttpHeaders.AUTHORIZATION, " help"); mockMvc.perform(request)
	  .andExpect(status().is(401)) .andReturn();
	  
	  }
	  
	  
	 
	  
	 

}
