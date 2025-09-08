package com.example.notifications;

/**
 * Adds SMS capability to an existing {@link Notifier}.
 */
public class SmsDecorator extends NotifierDecorator {
    private final String phoneNumber;

    public SmsDecorator(Notifier wrapped, String phoneNumber) {
        super(wrapped);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void notify(String text) {
        // Send SMS first, then delegate to base channel(s)
        System.out.println("[SMS   -> " + phoneNumber + "]: " + text);
        super.notify(text);
    }
}


