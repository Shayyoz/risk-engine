package com.intuit.riskengine.repository.entity;

import com.intuit.riskengine.model.domain.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Entity
@Table(name="Payments")
public class PaymentEntity {

    @GeneratedValue
    @Id
    private int paymentId;
    private Double amount;
    private String currency;
    private String userId;
    private String payeeId;
    private String paymentMethodId;
    private PaymentStatus status;
    private Double riskAssessment;

    public PaymentEntity() {
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Double getRiskAssessment() {
        return riskAssessment;
    }

    public void setRiskAssessment(Double riskAssessment) {
        this.riskAssessment = riskAssessment;
    }
}