package com.example.game;

/** Adds a flat bonus to damage and reflects it in attack(). */
public class DamageBoost extends CharacterDecorator {
    private final int bonusDamage;

    public DamageBoost(Character inner, int bonusDamage) {
        super(inner);
        this.bonusDamage = bonusDamage;
    }

    @Override
    public int getDamage() {
        return inner.getDamage() + bonusDamage;
    }

    @Override
    public void attack() {
        System.out.println("Attacking with damage " + getDamage() + " using sprite " + getSprite());
    }
}


