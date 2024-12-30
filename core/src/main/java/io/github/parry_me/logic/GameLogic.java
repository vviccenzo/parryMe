package io.github.parry_me.logic;

import io.github.parry_me.entities.Enemy;
import io.github.parry_me.entities.Player;

public class GameLogic {

	private PlayerLogic playerLogic;
	private EnemyLogic enemyLogic;

	public GameLogic(Player player, Enemy enemy) {
		this.playerLogic = new PlayerLogic(player);
		this.enemyLogic = new EnemyLogic(enemy);
	}

	public void update(float deltaTime) {
		playerLogic.update(deltaTime, enemyLogic.getEnemy());
		enemyLogic.update(deltaTime);

		checkCollision();
	}

	private void checkCollision() {
		if (playerLogic.getPlayer().collidesWith(enemyLogic.getEnemy())) {
			playerLogic.setIsFighting(true);

			if (this.enemyLogic.tryDefendAttack() && this.enemyLogic.getEnemy().isAttacking()) {
				this.playerLogic.setIsFighting(false);
			}
		}
	}

	public PlayerLogic getPlayerLogic() {
		return playerLogic;
	}

	public EnemyLogic getEnemyLogic() {
		return enemyLogic;
	}
}
