package com.example.servicelayertest;

import java.math.BigDecimal;
import java.util.Optional;

public interface PaymentGateway {
    PaymentResult charge(BigDecimal amount, PaymentDetails paymentDetails) 
        throws PaymentTimeoutException;
    
    RefundResult refund(String transactionId, BigDecimal amount);
}
