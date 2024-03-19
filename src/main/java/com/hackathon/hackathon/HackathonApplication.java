package com.hackathon.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan("com.hackathon")

@SpringBootApplication
public class HackathonApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HackathonApplication.class, args);
		//String port = context.getEnvironment().getProperty("server.port");
	}

}
