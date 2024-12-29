package io.github.parry_me.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import io.github.parry_me.entities.Enemy;
import io.github.parry_me.entities.Player;

public class GameLogic {

	private Player player;
	private Enemy enemy;

	private boolean isRunning = false;

	private static final float DASH_SPEED = 500f;
	private static final float DASH_DURATION = 1f;
	private static final float ATTACK_DURATION = 0.5f; // duração da animação de ataque em segundos

	private float attackTimer = 0f;

	private float dashTimer = 0f;

	private String currentAction = "idle";

	public GameLogic(Player player, Enemy enemy) {
		this.player = player;
		this.enemy = enemy;
	}

	public void update(float deltaTime) {
		this.handlePlayerMovement(deltaTime);
		this.handlePlayerAttack();

	    if ("attack".equalsIgnoreCase(this.currentAction)) {
	        this.attackTimer -= deltaTime;

	        if (this.attackTimer <= 0) {
	            this.currentAction = "idle";
	        }
	    }

		this.checkPlayerEnemyCollision();
	}

    private void handlePlayerMovement(float deltaTime) {
    	if("attack".equalsIgnoreCase(this.currentAction)) {
    		return;
    	}

        if (this.player.collidesWith(this.enemy)) {
            this.currentAction = "idle";
            return;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
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

	private void handlePlayerAttack() {
		if (this.player.collidesWith(this.enemy) && Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			this.executeAttack();
		}
	}

	private void checkPlayerEnemyCollision() {
		if (this.enemy.isAttacking() && this.player.collidesWith(this.enemy)) {
			this.resetGame();
		}
	}

	private void initiateRunningState() {
		this.isRunning = true;
		this.dashTimer = DASH_DURATION;
		this.currentAction = "run";
	}

	private void resetRunningState() {
		this.isRunning = false;
		this.currentAction = "idle";
	}

	private void switchToWalking() {
		this.isRunning = false;
		this.currentAction = "walk";
	}

	private void executeAttack() {
	    this.enemy.reduceStamina(10);

	    if (this.enemy.getStamina() <= 0) {
	        this.resetGameForNextLevel();
	    } else {
	        this.currentAction = "attack";
	        this.attackTimer = ATTACK_DURATION;
	    }
	}

	private void resetGame() {
		this.player.resetPosition();
		this.enemy.resetPosition();
		this.dashTimer = 0f;
	}

	private void resetGameForNextLevel() {
		this.player.resetPosition();
		this.enemy.resetPosition();

		this.enemy.setStamina(100);
		this.player.setLevel(this.player.getLevel() + 1);
	}

	public String getCurrentAction() {
		return this.currentAction;
	}
}
