
  package com.eatza.restaurantsearchservice;
  
  import org.junit.Test; import org.junit.runner.RunWith; import
  org.springframework.boot.test.context.SpringBootTest; import
  org.springframework.context.annotation.ComponentScan; import
  org.springframework.test.context.junit4.SpringRunner;

import com.netflix.discovery.shared.Application;
  
  @RunWith(SpringRunner.class)
  
  @ComponentScan(basePackages={"com.eatza.*"})
  
  @SpringBootTest(classes=Application.class) public class
  RestaurantsearchserviceApplicationTests {
  
  @Test public void contextLoads() { }
  
  }
 