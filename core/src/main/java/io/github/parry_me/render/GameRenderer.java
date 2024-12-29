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

	public void render(String playerAction) {
		this.renderShapes();
		this.renderPlayer(playerAction);
		this.renderHUD();
	}

	private void renderShapes() {
		this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		this.renderEnemyShape();
		this.renderStaminaBar();

		this.shapeRenderer.end();
	}

	private void renderEnemyShape() {
		this.shapeRenderer.setColor(Color.RED);
		this.shapeRenderer.rect(this.enemy.getX(), this.enemy.getY(), this.enemy.getSize(), this.enemy.getSize());
	}

	private void renderStaminaBar() {
		this.shapeRenderer.setColor(Color.GREEN);
		float staminaBarWidth = 100 * (this.enemy.getStamina() / 100f);
		this.shapeRenderer.rect(this.enemy.getX(), this.enemy.getY() + 60, staminaBarWidth, 10);
	}

	private void renderPlayer(String playerAction) {
		this.spriteBatch.begin();
		this.player.update(Gdx.graphics.getDeltaTime());
		this.player.render(this.spriteBatch, playerAction);
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
