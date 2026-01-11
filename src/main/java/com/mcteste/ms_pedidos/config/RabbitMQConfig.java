package com.mcteste.ms_pedidos.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.processamento.name}")
    private String queue;

    @Bean
    public JacksonJsonMessageConverter messageConverter(JsonMapper jsonMapper){

        return new JacksonJsonMessageConverter(jsonMapper);
    }




}
