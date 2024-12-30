package io.github.parry_me.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.parry_me.entities.animation.EnemyAnimation;

import java.util.Random;

public class Enemy extends EnemyAnimation {

	private float x;
	private float y;
	private float size;
	private float stateTime = 0f;
	private int stamina = 100;
	private boolean isAttacking;

	private String currentAction = "idle";
	private final Random random = new Random();

	public Enemy(float x, float y, float size) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
	}

	public void update(float deltaTime) {
		stateTime += deltaTime;
	}

	public void render(SpriteBatch spriteBatch, String action) {
		if (!this.currentAction.equalsIgnoreCase(action)) {
			currentAction = action;
			stateTime = 0f;
		}

		Animation<TextureRegion> currentAnimation = this.getCurrentAnimation(action);

		TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

		spriteBatch.draw(currentFrame, x, y, size, size);
	}

	private Animation<TextureRegion> getCurrentAnimation(String action) {
		switch (action.toLowerCase()) {
		case "protect":
			return protectAnimation;
		case "attack":
			return attackAnimations[random.nextInt(attackAnimations.length)];
		default:
			return idleAnimation;
		}
	}

	public void move(float dx, float dy) {
		this.x += dx;
		this.y += dy;
	}

	public void resetPosition() {
		this.x = 450f;
		this.y = 100f;
	}

	public void reduceStamina(int amount) {
		stamina -= amount;
		if (stamina < 0) {
			stamina = 0;
		}
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

	public float getStamina() {
		return this.stamina;
	}

	public boolean isAttacking() {
		return this.isAttacking;
	}

	public void setIsAttacking(boolean state) {
		this.isAttacking = state;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	
	public boolean isAttackDefendable() {
		return random.nextBoolean();
	}
}
