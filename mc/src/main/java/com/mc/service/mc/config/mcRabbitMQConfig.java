package com.mc.service.mc.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mcRabbitMQConfig {
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        String rabbitmqHost = "rabbitmq";
        connectionFactory.setHost(rabbitmqHost);
        // Set other RabbitMQ properties as needed
        connectionFactory.setConnectionTimeout(5000); // Set a timeout value
        return connectionFactory.getRabbitConnectionFactory();
    }
}
