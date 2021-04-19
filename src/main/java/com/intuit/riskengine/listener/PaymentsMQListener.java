package com.intuit.riskengine.listener;

import com.intuit.riskengine.config.MQPaymentsConfig;
import com.intuit.riskengine.mapper.PaymentMapper;
import com.intuit.riskengine.model.domain.PaymentRequestDmn;
import com.intuit.riskengine.model.dto.PaymentRequestDto;
import com.intuit.riskengine.repository.entity.PaymentEntity;
import com.intuit.riskengine.model.domain.PaymentStatus;
import com.intuit.riskengine.repository.entity.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentsMQListener {
    private final PaymentMapper paymentMapper = PaymentMapper.INSTANCE;
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
