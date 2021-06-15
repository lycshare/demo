package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

import javax.websocket.server.PathParam;


//POJO =Plain old java object
//add annotation to convert it to rest controller component
@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new 	AtomicLong();
	
	//define GreetingComponent variable without initialization
	private GreetingComponent g;
	
	//inject/initialize GreetingComponent in the constructor
	@Autowired
	public GreetingController(GreetingComponent g) {
		this.g = g;
	}
	
	//test the GreetingComponent
	@GetMapping("/testgreeting")
	public ResponseEntity<String> getMessage(){
		return ResponseEntity.ok(g.getMessage());
	}
	
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
		
	}
	
	@GetMapping("/add/{num1}/{num2}")
	public int addTwoNumbers(@PathVariable ("num1") int num1,@PathVariable ("num2") int num2) {
		return num1 + num2;
	}
	
	@GetMapping("/sub/{num1}/{num2}")
	public int subTwoNumbers(@PathVariable ("num1") int num1,@PathVariable ("num2") int num2) {
		return num1 - num2;
	}
	
}
