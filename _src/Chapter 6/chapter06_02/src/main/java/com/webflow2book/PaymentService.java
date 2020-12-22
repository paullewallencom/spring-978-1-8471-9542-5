package com.webflow2book;

/**
 * Interface for a payment service
 * @author Sven
 */
public interface PaymentService {

    boolean checkPayment(Payment payment);
}
