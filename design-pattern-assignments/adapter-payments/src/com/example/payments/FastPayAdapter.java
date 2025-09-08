package com.example.payments;

import java.util.Objects;

/**
 * Adapter that adapts FastPayClient to PaymentGateway interface.
 * Implements the Adapter pattern to bridge incompatible interfaces.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility: Only handles FastPay payment processing
 * - Dependency Inversion: Depends on PaymentGateway abstraction
 * - Open/Closed: Can be extended without modifying existing code
 * - Liskov Substitution: Properly implements PaymentGateway contract
 */
public class FastPayAdapter implements PaymentGateway {
    
    private final FastPayClient fastPayClient;
    
    /**
     * Constructor with dependency injection.
     * 
     * @param fastPayClient The FastPay client to adapt
     * @throws NullPointerException if fastPayClient is null
     */
    public FastPayAdapter(FastPayClient fastPayClient) {
        this.fastPayClient = Objects.requireNonNull(fastPayClient, "fastPayClient cannot be null");
    }
    
    /**
     * Charges a customer using FastPay.
     * 
     * @param customerId The customer ID
     * @param amountCents The amount in cents
     * @return Transaction ID
     * @throws NullPointerException if customerId is null
     * @throws IllegalArgumentException if amountCents is negative
     */
    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId cannot be null");
        if (amountCents < 0) {
            throw new IllegalArgumentException("amountCents cannot be negative");
        }
        
        // Adapt the FastPay API to our PaymentGateway interface
        return fastPayClient.payNow(customerId, amountCents);
    }
}
