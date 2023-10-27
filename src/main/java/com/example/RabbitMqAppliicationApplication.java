package com.example;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.example.receiver.Receiver;

//@ComponentScan(value = "com.example.runner")
@SpringBootApplication
public class RabbitMqAppliicationApplication {
	
	 final static String queue = "spring-queue";
	public final static String exchange = "spring-exchang";


	
	// in Order Configuration RabbitMq
	
	// Queueu
	@Bean
	public Queue queue(){
		return new Queue(queue, false);
	}
	// Exchange
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	
	// Binding
	@Bean
	public Binding binding(Queue queue,TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("car.#");
	}
	
	// Creeate Message Listen Adapter for Receiver
	@Bean
	public MessageListenerAdapter adpter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
		
	}
	
	//Container
	@Bean
	SimpleMessageListenerContainer cointaine(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		// Connection RabbitMq
		container.setConnectionFactory(connectionFactory);
			
		//QueueuName
		container.addQueueNames(queue);
			
		//SetMessageListne
		container.setMessageListener(messageListenerAdapter);
				
		return container;
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitMqAppliicationApplication.class, args);
	}

 
}
