package com.example.payments;

import java.util.Objects;

/**
 * Adapter that adapts SafeCashClient to PaymentGateway interface.
 * Implements the Adapter pattern to bridge incompatible interfaces.
 * 
 * SOLID Principles Applied:
 * - Single Responsibility: Only handles SafeCash payment processing
 * - Dependency Inversion: Depends on PaymentGateway abstraction
 * - Open/Closed: Can be extended without modifying existing code
 * - Liskov Substitution: Properly implements PaymentGateway contract
 */
public class SafeCashAdapter implements PaymentGateway {
    
    private final SafeCashClient safeCashClient;
    
    /**
     * Constructor with dependency injection.
     * 
     * @param safeCashClient The SafeCash client to adapt
     * @throws NullPointerException if safeCashClient is null
     */
    public SafeCashAdapter(SafeCashClient safeCashClient) {
        this.safeCashClient = Objects.requireNonNull(safeCashClient, "safeCashClient cannot be null");
    }
    
    /**
     * Charges a customer using SafeCash.
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
        
        // Adapt the SafeCash API to our PaymentGateway interface
        // SafeCash uses a different method signature, so we adapt it
        SafeCashPayment payment = safeCashClient.createPayment(amountCents, customerId);
        return payment.confirm();
    }
}
