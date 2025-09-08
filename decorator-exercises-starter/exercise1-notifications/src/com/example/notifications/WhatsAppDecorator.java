package com.example.notifications;

/**
 * Adds WhatsApp capability to an existing {@link Notifier}.
 */
public class WhatsAppDecorator extends NotifierDecorator {
    private final String handle;

    public WhatsAppDecorator(Notifier wrapped, String handle) {
        super(wrapped);
        this.handle = handle;
    }

    @Override
    public void notify(String text) {
        // Send WhatsApp first, then delegate to base channel(s)
        System.out.println("[WHATSAPP -> " + handle + "]: " + text);
        super.notify(text);
    }
}


