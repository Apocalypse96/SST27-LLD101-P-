package com.example.game;

/** Adds a flat bonus to speed and reflects it in move(). */
public class SpeedBoost extends CharacterDecorator {
    private final int bonusSpeed;

    public SpeedBoost(Character inner, int bonusSpeed) {
        super(inner);
        this.bonusSpeed = bonusSpeed;
    }

    @Override
    public int getSpeed() {
        return inner.getSpeed() + bonusSpeed;
    }

    @Override
    public void move() {
        System.out.println("Moving at speed " + getSpeed() + " with sprite " + getSprite());
    }
}


