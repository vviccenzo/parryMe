package io.github.parry_me.entities;

public class Enemy {
    private float x;
    private float y;
    private final float size;
    private int stamina = 100;

    public Enemy(float x, float y, float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void resetPosition() {
        this.x = 500f;
        this.y = 100f;
    }

    public void reduceStamina(int amount) {
        stamina -= amount;
        if (stamina < 0) stamina = 0; // Garante que a estamina não fique negativa
    }

    public int getStamina() {
        return stamina;
    }

    public boolean isAttacking() {
        return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
}
