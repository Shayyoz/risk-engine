package com.intuit.riskengine.listener;

import com.intuit.riskengine.config.MQPaymentsConfig;
import com.intuit.riskengine.mapper.PaymentMapper;
import com.intuit.riskengine.model.domain.PaymentRequest;
import com.intuit.riskengine.repository.entity.PaymentEntity;
import com.intuit.riskengine.repository.entity.PaymentStatus;
import com.intuit.riskengine.repository.entity.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentsMQListener {
    private final PaymentsRepository paymentsRepository;
    private final PaymentMapper paymentMapper = PaymentMapper.INSTANCE;

    @RabbitListener(queues = MQPaymentsConfig.NEW_PAYMENTS_QUEUE)
    public void listener(PaymentRequest request){
        PaymentEntity payment = paymentMapper.dmnToEntityMapper(request);
        payment.setStatus(randomPaymentApproval());
        paymentsRepository.save(payment);
    }

    private PaymentStatus randomPaymentApproval(){
        return Math.random() > 0.3 ? PaymentStatus.APPROVED: PaymentStatus.DECLINED;
    }
}
