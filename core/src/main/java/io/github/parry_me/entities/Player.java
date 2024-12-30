package io.github.parry_me.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.parry_me.entities.animation.PlayerAnimation;

public class Player extends PlayerAnimation {

	private float x;
	private float y;
	private float size;
	private float stateTime = 0f;

	private int attackNumber = 0;
	private int level = 1;

	private String currentAction = "idle";

	private final Random random = new Random();

	private boolean isAttacking = false;

	public Player(float x, float y, float size) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
	}

	public void update(float deltaTime) {
		this.stateTime += deltaTime;

		if ("attack".equalsIgnoreCase(this.currentAction) && this.attackAnimations[this.attackNumber].isAnimationFinished(this.stateTime)) {
			this.isAttacking = false;
			this.attackNumber = 0;
		}
	}

	public void render(SpriteBatch spriteBatch, String action) {
		if (!this.currentAction.equalsIgnoreCase(action)) {
			if ("attack".equalsIgnoreCase(action) && !this.isAttacking) {
				this.stateTime = 0f;
				this.isAttacking = true;
				this.attackNumber = this.random.nextInt(this.attackAnimations.length);
			}

			this.currentAction = action;
		}

		Animation<TextureRegion> currentAnimation = this.getCurrentAnimation(action);

		TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

		spriteBatch.draw(currentFrame, x, y, size, size);
	}

	private Animation<TextureRegion> getCurrentAnimation(String action) {
		switch (action.toLowerCase()) {
		case "run":
			return this.runAnimation;
		case "walk":
			return this.walkAnimation;
		case "attack":
			return this.getRandomAttackAnimation(1);
		default:
			return this.idleAnimation;
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
		float collisionMargin = 10f; // Margem para ajustar a proximidade da colisão

		// Define os limites reduzidos do jogador
		float playerLeftBoundary = this.x + collisionMargin;
		float playerRightBoundary = this.x + this.size - collisionMargin;
		float playerTopBoundary = this.y + collisionMargin;
		float playerBottomBoundary = this.y + this.size - collisionMargin;

		// Define os limites reduzidos do inimigo
		float enemyLeftBoundary = enemy.getX() + collisionMargin;
		float enemyRightBoundary = enemy.getX() + enemy.getSize() - collisionMargin;
		float enemyTopBoundary = enemy.getY() + collisionMargin;
		float enemyBottomBoundary = enemy.getY() + enemy.getSize() - collisionMargin;

		// Verifica se os limites se sobrepõem
		boolean isCollidingHorizontally = playerLeftBoundary < enemyRightBoundary
				&& playerRightBoundary > enemyLeftBoundary;
		boolean isCollidingVertically = playerTopBoundary < enemyBottomBoundary
				&& playerBottomBoundary > enemyTopBoundary;

		// Retorna true se houver colisão tanto horizontal quanto verticalmente
		return isCollidingHorizontally && isCollidingVertically;
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
