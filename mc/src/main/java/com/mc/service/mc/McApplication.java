package com.mc.service.mc;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class McApplication {

	public static void main(String[] args) {
		SpringApplication.run(McApplication.class, args);
	}

}
