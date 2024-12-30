package io.github.parry_me.logic;

import java.util.Random;

import io.github.parry_me.entities.Enemy;

public class EnemyLogic {

	private Enemy enemy;

	private String currentAction = "idle";

	private boolean isAttackDefendable = false;

	private final Random random = new Random();

	public EnemyLogic(Enemy enemy) {
		this.enemy = enemy;
	}

	public void update(float deltaTime) {
		if ("attack".equalsIgnoreCase(currentAction)) {
			enemy.setIsAttacking(true);
			isAttackDefendable = random.nextFloat() < 0.5;
		} else {
			enemy.setIsAttacking(false);
		}
	}

	public void resetPosition() {
		enemy.resetPosition();
		currentAction = "idle";
	}

	public void startAttack() {
		currentAction = "attack";
	}

	public boolean tryDefendAttack() {
		return random.nextFloat() < 0.3; // 30% de chance de o inimigo se defender
	}

	public String getCurrentAction() {
		return currentAction;
	}

	public Enemy getEnemy() {
		return enemy;
	}
	
	public boolean isAttackDefendable() {
		return this.isAttackDefendable;
	}
}
