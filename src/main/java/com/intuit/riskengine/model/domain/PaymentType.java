package com.intuit.riskengine.model.domain;

public enum PaymentType {
    TRANSFER("TRANSFER"),
    CHARGE("CHARGE");

    private String value;

    PaymentType(String value){this.value = value;}

    public String getValue(){return value;}

    @Override
    public String toString(){ return String.valueOf(value);}

    public static PaymentType fromValue(String value){
        for(PaymentType s : PaymentType.values()){
            if(s.value.equals(value)){
                return s;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

}
