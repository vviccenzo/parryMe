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

	private static final float FRAME_ATACK_DURATION = 0.1f;
	private static final float FRAME_DURATION = 0.1f;

	protected Animation<TextureRegion> runAnimation;
	protected Animation<TextureRegion> walkAnimation;
	protected Animation<TextureRegion> protectAnimation;
	protected Animation<TextureRegion> idleAnimation;
	protected Animation<TextureRegion>[] attackAnimations;

	private static final int[] ATTACK_FRAME_COLS = { 4, 5, 4 };

	@SuppressWarnings("unchecked")
	public PlayerAnimation() {
		this.runAnimation = Utils.createAnimation("player/run.png", RUN_FRAME_COLS, RUN_FRAME_ROWS, FRAME_DURATION);
		this.walkAnimation = Utils.createAnimation("player/walk.png", WALK_FRAME_COLS, WALK_FRAME_ROWS, FRAME_DURATION);
		this.protectAnimation = Utils.createAnimation("player/protect.png", PROTECT_FRAME_COLS, PROTECT_FRAME_ROWS, FRAME_DURATION);
		this.idleAnimation = Utils.createAnimation("player/idle.png", IDLE_FRAME_COLS, IDLE_FRAME_ROWS, FRAME_DURATION);

		this.attackAnimations = new Animation[3];
		for (int i = 0; i < 3; i++) {
			attackAnimations[i] = Utils.createAnimation("player/attack" + (i + 1) + ".png", ATTACK_FRAME_COLS[i], ATTACK_FRAME_ROWS, FRAME_ATACK_DURATION);
		}
	}

	public Animation<TextureRegion> getRandomAttackAnimation(int index) {
		return attackAnimations[index];
	}

	public Animation<TextureRegion>[] getAttackAnimations() {
		return this.attackAnimations;
	}
}
