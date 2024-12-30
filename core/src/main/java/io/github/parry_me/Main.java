package io.github.parry_me;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import io.github.parry_me.entities.Enemy;
import io.github.parry_me.entities.Player;
import io.github.parry_me.entities.constant.EnemyConstant;
import io.github.parry_me.logic.GameLogic;
import io.github.parry_me.render.GameRenderer;

public class Main extends ApplicationAdapter {

	private GameRenderer renderer;

	private GameLogic logic;

	@Override
	public void create() {
		Player player = new Player(100f, 100f, 150f);
		Enemy enemy = new Enemy(EnemyConstant.POSITION_X_INITIAL_ENEMY, EnemyConstant.POSITION_Y_INITIAL_ENEMY, 150f);

		this.logic = new GameLogic(player, enemy);
		this.renderer = new GameRenderer(player, enemy);
	}

	@Override
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();

		this.logic.update(deltaTime);

		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.renderer.render(logic.getPlayerLogic().getCurrentAction(), logic.getEnemyLogic().getCurrentAction());
	}

	@Override
	public void dispose() {
		this.renderer.dispose();
	}
}
