package io.github.parry_me.entities.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import io.github.parry_me.utils.Utils;

public class PlayerAnimation {

	private static final int RUN_FRAME_COLS = 8;
	private static final int RUN_FRAME_ROWS = 1;

	private static final int WALK_FRAME_COLS = 9;
	private static final int WALK_FRAME_ROWS = 1;

	private static final int PROTECT_FRAME_COLS = 8;
	private static final int PROTECT_FRAME_ROWS = 1;

	private static final int ATTACK_FRAME_ROWS = 1;

	private static final int IDLE_FRAME_COLS = 6;
	private static final int IDLE_FRAME_ROWS = 1;

	private static final float FRAME_DURATION = 0.1f;
	private static final float FRAME_ATACK_DURATION = 0.1f;

	private static final int ATTACK_FRAME_COLS = 4;

	protected Animation<TextureRegion> runAnimation;
	protected Animation<TextureRegion> walkAnimation;
	protected Animation<TextureRegion> protectAnimation;
	protected Animation<TextureRegion> idleAnimation;
	protected Animation<TextureRegion> attackAnimations;

	public PlayerAnimation() {
		this.runAnimation = Utils.createAnimation("run.png", RUN_FRAME_COLS, RUN_FRAME_ROWS, FRAME_DURATION);
		this.walkAnimation = Utils.createAnimation("walk.png", WALK_FRAME_COLS, WALK_FRAME_ROWS, FRAME_DURATION);
		this.protectAnimation = Utils.createAnimation("protect.png", PROTECT_FRAME_COLS, PROTECT_FRAME_ROWS, FRAME_DURATION);
		this.idleAnimation = Utils.createAnimation("idle.png", IDLE_FRAME_COLS, IDLE_FRAME_ROWS, FRAME_DURATION);
		this.attackAnimations= Utils.createAnimation("attack1.png", ATTACK_FRAME_COLS, ATTACK_FRAME_ROWS, FRAME_ATACK_DURATION);
	}

}
