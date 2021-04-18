package com.intuit.riskengine.mapper;

import com.intuit.riskengine.model.domain.PaymentRequest;
import com.intuit.riskengine.repository.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentEntity dmnToEntityMapper(PaymentRequest paymentRequest);

}
