package com.example.websocketdemo.controller;

import com.example.websocketdemo.configuration.ConnectionHandler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/")
	public String hello() {
		return "Say hello to the demo controller.";
	}
	
	@GetMapping("/sendAll/{value}")
	public String sendNotification(
			@PathVariable(name = "value", required = false) String message) {

		ConnectionHandler.sendMessage(message);
		return "Notifications successfully sent to Angular !";
	}

	@GetMapping("/send/{id}/{value}")
	public String sendNotification(
			@PathVariable(name = "id", required = false) String id,
			@PathVariable(name = "value", required = false) String message) {

		ConnectionHandler.sendMessage(id, message);
		return "Notifications successfully sent to Angular !";
	}
}
