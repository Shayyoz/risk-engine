package com.intuit.riskengine.listener;

import com.intuit.riskengine.config.MQPaymentsConfig;
import com.intuit.riskengine.model.domain.PaymentRequestDmn;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentsMQListener {
    @Autowired
    private RabbitTemplate template;

    @RabbitListener(queues = MQPaymentsConfig.NEW_PAYMENTS_QUEUE)
    public void listener(PaymentRequestDmn request){
        template.convertAndSend(MQPaymentsConfig.PAYMENTS_RESPONSE_EXCHANGE,
                MQPaymentsConfig.PAYMENTS_RESPONSE_ROUTING_KEY, riskAssessmentProcess(request));
    }

    private PaymentRequestDmn riskAssessmentProcess(PaymentRequestDmn request){
        request.setRiskAssessment(Math.random());
        return request;
    }
}
