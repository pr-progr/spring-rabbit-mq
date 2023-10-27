package com.example.receiver;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	public void receiveMessage(String message) {
		System.out.println("Message Received " + message);
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}
}
