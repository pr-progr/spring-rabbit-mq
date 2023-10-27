package com.example.runner;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.RabbitMqAppliicationApplication;
import com.example.receiver.Receiver;

@Component
public class Runner implements CommandLineRunner {
	
	Receiver receiver;
	RabbitTemplate rabbitTemplate;
	
	

	public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
		this.receiver = receiver;
		this.rabbitTemplate = rabbitTemplate;
	}



	@Override
	public void run(String... args) throws Exception {
		
		// To send a message
		rabbitTemplate.convertAndSend(RabbitMqAppliicationApplication.exchange, "car.dd", "TestRabbitMQ");
	//	receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		System.out.println("Sending Message...");
		System.exit(0);
	}

}
