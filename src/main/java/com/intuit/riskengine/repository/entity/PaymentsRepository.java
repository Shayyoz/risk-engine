package com.intuit.riskengine.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<PaymentEntity,Integer> {
}
