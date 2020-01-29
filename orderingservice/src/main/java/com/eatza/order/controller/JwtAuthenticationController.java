package com.eatza.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);
	/*
	 * @Autowired JwtAuthenticationService authenticationService;
	 */

	/*
	 * @PostMapping("/login") public ResponseEntity<String> login(@RequestBody
	 * UserDto user) throws UnauthorizedException {
	 * 
	 * logger.debug("Calling authentication service to verify user"); // String
	 * token = authenticationService.authenticateUser(user);
	 * logger.debug("User verified, returning back token"); return ResponseEntity
	 * .status(HttpStatus.OK) .body(token);
	 * 
	 * 
	 * }
	 */
}
