package com.Bee.BeeWebserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BeeWebserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeeWebserverApplication.class, args);
	}

}

@RestController
class HelloController{

	@GetMapping("/")
	String hello(){
		return "Hello World";
	}
}
