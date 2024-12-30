package io.github.parry_me.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.parry_me.entities.Enemy;
import io.github.parry_me.entities.Player;

public class GameRenderer {

	private ShapeRenderer shapeRenderer;
	private BitmapFont font;
	private Player player;
	private Enemy enemy;
	private SpriteBatch spriteBatch;

	public GameRenderer(Player player, Enemy enemy) {
		this.player = player;
		this.enemy = enemy;
		this.font = new BitmapFont();
		this.font.setColor(Color.BLACK);

		this.shapeRenderer = new ShapeRenderer();
		this.spriteBatch = new SpriteBatch();
	}

	public void render(String playerAction, String enemyAction) {

		this.renderPlayer(playerAction);
		this.renderEnemy(enemyAction);

		this.renderHUD();
	}

	private void renderPlayer(String playerAction) {
		this.spriteBatch.begin();
		this.player.update(Gdx.graphics.getDeltaTime());
		this.player.render(this.spriteBatch, playerAction);
		this.spriteBatch.end();
	}

	private void renderEnemy(String enemyAction) {
		this.spriteBatch.begin();
		this.enemy.update(Gdx.graphics.getDeltaTime());
		this.enemy.render(this.spriteBatch, enemyAction);
		this.spriteBatch.end();
	}

	private void renderHUD() {
		this.spriteBatch.begin();
		this.renderEnemyStamina();
		this.renderPlayerLevel();
		this.spriteBatch.end();
	}

	private void renderEnemyStamina() {
		this.font.draw(this.spriteBatch, "Estamina do inimigo: " + this.enemy.getStamina(), 10, 20);
	}

	private void renderPlayerLevel() {
		this.font.draw(this.spriteBatch, "Fase: " + this.player.getLevel(), 10, 40);
	}

	public void dispose() {
		this.shapeRenderer.dispose();
		this.spriteBatch.dispose();
		this.font.dispose();
	}
}
