package com.intuit.riskengine.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MQPaymentsConfig {

    public static final String NEW_PAYMENTS_QUEUE = "new_payments_queue";
    public static final String NEW_PAYMENTS_EXCHANGE = "new_payments_exchange";
    public static final String NEW_PAYMENT_ROUTING_KEY = "new_payment_routing_key";

    public static final String PAYMENTS_RESPONSE_QUEUE = "payments_response_queue";
    public static final String PAYMENTS_RESPONSE_EXCHANGE = "payments_response_exchange";
    public static final String PAYMENTS_RESPONSE_ROUTING_KEY = "payments_response_routing_key";

//    @Bean
//    public Queue queue(){
//        return new Queue(NEW_PAYMENTS_QUEUE);
//    }

    @Bean
    Queue queue1() {
        return new Queue(NEW_PAYMENTS_QUEUE, false);
    }

    @Bean
    Queue queue2() {
        return new Queue(PAYMENTS_RESPONSE_QUEUE, false);
    }

    @Bean
    public TopicExchange exchange1(){
        return new TopicExchange(NEW_PAYMENTS_EXCHANGE);
    }

    @Bean
    public TopicExchange exchange2(){
        return new TopicExchange(PAYMENTS_RESPONSE_EXCHANGE);
    }

//    @Bean
//    public Binding binding(Queue queue,TopicExchange exchange){
//        return BindingBuilder
//                .bind(queue)
//                .to(exchange)
//                .with(NEW_PAYMENT_ROUTING_KEY);
//    }

    @Bean
    Binding binding1(@Qualifier("queue1") Queue queue,@Qualifier("exchange1")  TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(NEW_PAYMENT_ROUTING_KEY);
    }

    @Bean
    Binding binding2(@Qualifier("queue2") Queue queue, @Qualifier("exchange2") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(PAYMENTS_RESPONSE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }


}
