package com.main.gateway.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configurable
@EnableResourceServer
public class AuthResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource_id";
	@Autowired
	DataSource dataSource;
	
	  @Override public void configure(ResourceServerSecurityConfigurer resources) {
	  resources.resourceId("inventory").tokenStore(getTokenStore()); 
	  }
	  
	  @Override public void configure(HttpSecurity http) throws Exception {
		  http.authorizeRequests().antMatchers("/auth/oauth/token").permitAll().anyRequest().authenticated();
	  }
	  
	  public TokenStore getTokenStore() {
			return new JdbcTokenStore(dataSource);
		}

	 

	/*
	 * @Override public void configure(HttpSecurity http) throws Exception { http
	 * .antMatcher("/**") .authorizeRequests() .antMatchers("/", "/login**")
	 * .permitAll() .anyRequest() .authenticated(); }
	 * 
	 * @Autowired // here is configuration related to spring boot basic
	 * authentication public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { auth.inMemoryAuthentication() // static users
	 * .withUser("zone1").password("mypassword").roles("USER") .and()
	 * .withUser("zone2").password("mypassword").roles("USER") ; }
	 */
}
