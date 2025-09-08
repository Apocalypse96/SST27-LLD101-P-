package com.example.notifications;

/**
 * Base decorator that wraps a {@link Notifier} and forwards calls to it.
 * Concrete decorators extend this to add channels while adhering to OCP.
 */
public abstract class NotifierDecorator implements Notifier {
    private final Notifier wrapped;

    protected NotifierDecorator(Notifier wrapped) {
        if (wrapped == null) {
            throw new IllegalArgumentException("Wrapped notifier must not be null");
        }
        this.wrapped = wrapped;
    }

    protected Notifier getWrapped() {
        return wrapped;
    }

    @Override
    public void notify(String text) {
        // Default behavior: delegate to the wrapped notifier.
        wrapped.notify(text);
    }
}


