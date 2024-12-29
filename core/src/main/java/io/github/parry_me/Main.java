package io.github.parry_me;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import io.github.parry_me.entities.Enemy;
import io.github.parry_me.entities.Player;
import io.github.parry_me.logic.GameLogic;
import io.github.parry_me.render.GameRenderer;

public class Main extends ApplicationAdapter {
	private GameRenderer renderer;
	private GameLogic logic;

	@Override
	public void create() {
		Player player = new Player(100f, 100f, 150f);
		Enemy enemy = new Enemy(500f, 100f, 50f);

		logic = new GameLogic(player, enemy);
		renderer = new GameRenderer(player, enemy);
	}

	@Override
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();

		logic.update(deltaTime);

		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.render(logic.getCurrentAction());
	}

	@Override
	public void dispose() {
		renderer.dispose();
	}
}
