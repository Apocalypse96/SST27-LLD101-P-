package com.example.notifications;

/**
 * Adds Slack capability to an existing {@link Notifier}.
 */
public class SlackDecorator extends NotifierDecorator {
    private final String channel;

    public SlackDecorator(Notifier wrapped, String channel) {
        super(wrapped);
        this.channel = channel;
    }

    @Override
    public void notify(String text) {
        // Send Slack message first, then delegate to base channel(s)
        System.out.println("[SLACK -> #" + channel + "]: " + text);
        super.notify(text);
    }
}


