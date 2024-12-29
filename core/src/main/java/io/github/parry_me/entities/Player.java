package io.github.parry_me.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.parry_me.entities.animation.PlayerAnimation;

public class Player extends PlayerAnimation {

	private float x;
	private float y;
	private float size;
	private float stateTime = 0f;

	private int level = 1;
	private String currentAction = "idle";

	private boolean isAttacking = false;

	public Player(float x, float y, float size) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
	}

	public void update(float deltaTime) {
		stateTime += deltaTime;

		if ("attack".equalsIgnoreCase(currentAction) && attackAnimations.isAnimationFinished(stateTime)) {
			isAttacking = false;
		}
	}

	public void render(SpriteBatch spriteBatch, String action) {
		if (!currentAction.equalsIgnoreCase(action)) {
			if ("attack".equalsIgnoreCase(action) && !isAttacking) {
				stateTime = 0f;
				isAttacking = true;
			}

			currentAction = action;
		}

		Animation<TextureRegion> currentAnimation = getCurrentAnimation(action);

		TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

		spriteBatch.draw(currentFrame, x, y, size, size);
	}

	private Animation<TextureRegion> getCurrentAnimation(String action) {
		switch (action.toLowerCase()) {
		case "run":
			return runAnimation;
		case "walk":
			return walkAnimation;
		case "attack":
			return attackAnimations;
		default:
			return idleAnimation;
		}
	}

	public void move(float dx, float dy) {
		this.x += dx;
		this.y += dy;
	}

	public void resetPosition() {
		this.x = 100f;
		this.y = 100f;
	}

	public boolean collidesWith(Enemy enemy) {
		return this.x < enemy.getX() + enemy.getSize() && this.x + this.size > enemy.getX()
				&& this.y < enemy.getY() + enemy.getSize() && this.y + this.size > enemy.getY();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
}
