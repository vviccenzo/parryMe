package io.github.parry_me.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import io.github.parry_me.entities.Enemy;
import io.github.parry_me.entities.Player;

public class PlayerLogic {

	private static final float DASH_SPEED = 500f;
	private static final float DASH_DURATION = 1f;
	private static final float ATTACK_DURATION = 0.5f;

	private Player player;

	private float attackTimer = 0f;
	private float dashTimer = 0f;

	private boolean isRunning = false;
	private boolean isFighting = false;
	private boolean isDefending = false;

	private String currentAction = "idle";

	public PlayerLogic(Player player) {
		this.player = player;
	}

	public void update(float deltaTime, Enemy enemy) {
		this.handleMovement(deltaTime);
		this.handleAttack(enemy);
		this.handleDefense(deltaTime, enemy);

		if ("attack".equalsIgnoreCase(currentAction)) {
			this.attackTimer -= deltaTime;
			if (this.attackTimer <= 0) {
				this.currentAction = "idle";
				this.attackTimer = 0;
			}
		}
	}

	private void handleMovement(float deltaTime) {
		if ("attack".equalsIgnoreCase(currentAction)) {
			return;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.A) && !this.isFighting) {
			this.initiateRunningState();
		} else {
			this.resetRunningState();
		}

		if (this.isRunning) {
			this.dashTimer -= deltaTime;
			this.player.move(DASH_SPEED * deltaTime, 0);

			if (this.dashTimer <= 0) {
				this.switchToWalking();
			}
		}
	}

	private void handleDefense(float deltaTime, Enemy enemy) {
		if (enemy.isAttacking() && Gdx.input.isKeyJustPressed(Input.Keys.F)) {
			this.isDefending = true;

			if (enemy.isAttackDefendable() && player.collidesWith(enemy)) {
				this.currentAction = "defend";

				enemy.setIsAttacking(false);

				this.executeAttack(enemy);
			} else {
				this.currentAction = "hurt";
			}
		} else {
			this.isDefending = false;
		}
	}

	private void handleAttack(Enemy enemy) {
		if (this.attackTimer > 0) {
			return;
		}

		if (this.player.collidesWith(enemy) && Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			this.executeAttack(enemy);
		}
	}

	public void initiateRunningState() {
		this.isRunning = true;
		this.dashTimer = DASH_DURATION;
		this.currentAction = "run";
	}

	public void resetRunningState() {
		this.isRunning = false;
		this.currentAction = "idle";
	}

	public void switchToWalking() {
		this.isRunning = false;
		this.currentAction = "walk";
	}

	public void executeAttack(Enemy enemy) {
		enemy.reduceStamina(10);

		this.currentAction = "attack";
		this.attackTimer = ATTACK_DURATION;
	}

	public void resetPosition() {
		this.player.resetPosition();
		this.currentAction = "idle";
	}

	public String getCurrentAction() {
		return this.currentAction;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setIsFighting(boolean state) {
		this.isFighting = state;
	}

	public boolean getIsFighting() {
		return this.isFighting;
	}

	public boolean isDefending() {
		return isDefending;
	}

	public void setDefending(boolean isDefending) {
		this.isDefending = isDefending;
	}
}
