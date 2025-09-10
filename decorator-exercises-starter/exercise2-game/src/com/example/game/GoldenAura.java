package com.example.game;

/**
 * Cosmetic and minor buffs: swaps sprite and adds small bonuses.
 * Demonstrates open composition and SRP—visual effect concerns are isolated.
 */
public class GoldenAura extends CharacterDecorator {
    private static final String AURA_SPRITE_SUFFIX = " + aura";
    private final int speedBonus;
    private final int damageBonus;

    public GoldenAura(Character inner) {
        this(inner, 1, 2);
    }

    public GoldenAura(Character inner, int speedBonus, int damageBonus) {
        super(inner);
        this.speedBonus = speedBonus;
        this.damageBonus = damageBonus;
    }

    @Override
    public String getSprite() {
        return inner.getSprite() + AURA_SPRITE_SUFFIX;
    }

    @Override
    public int getSpeed() {
        return inner.getSpeed() + speedBonus;
    }

    @Override
    public int getDamage() {
        return inner.getDamage() + damageBonus;
    }

    @Override
    public void move() {
        System.out.println("[GoldenAura] shimmering...");
        System.out.println("Moving at speed " + getSpeed() + " with sprite " + getSprite());
    }

    @Override
    public void attack() {
        System.out.println("[GoldenAura] radiant strike!");
        System.out.println("Attacking with damage " + getDamage() + " using sprite " + getSprite());
    }
}


