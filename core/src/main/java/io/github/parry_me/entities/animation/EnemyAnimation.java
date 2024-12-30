package io.github.parry_me.entities.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.parry_me.utils.Utils;

public class EnemyAnimation {

	private static final int PROTECT_FRAME_COLS = 2;
	private static final int PROTECT_FRAME_ROWS = 1;

	private static final int IDLE_FRAME_COLS = 5;
	private static final int IDLE_FRAME_ROWS = 1;

	private static final int ATTACK_FRAME_ROWS = 1;

	private static final float FRAME_DURATION = 0.1f;
	private static final float FRAME_ATTACK_DURATION = 0.15f;

	protected Animation<TextureRegion> idleAnimation;
	protected Animation<TextureRegion> protectAnimation;
	protected Animation<TextureRegion>[] attackAnimations;

	private static final int[] ATTACK_FRAME_COLS = { 5 };

	@SuppressWarnings("unchecked")
	public EnemyAnimation() {
		this.idleAnimation = Utils.createAnimation("enemy/idle.png", IDLE_FRAME_COLS, IDLE_FRAME_ROWS, FRAME_DURATION);
		this.protectAnimation = Utils.createAnimation("enemy/protect.png", IDLE_FRAME_COLS, PROTECT_FRAME_COLS, PROTECT_FRAME_ROWS);

		this.attackAnimations = new Animation[ATTACK_FRAME_COLS.length];
		for (int i = 0; i < ATTACK_FRAME_COLS.length; i++) {
			attackAnimations[i] = Utils.createAnimation("enemy/attack" + (i + 1) + ".png", ATTACK_FRAME_COLS[i], ATTACK_FRAME_ROWS, FRAME_ATTACK_DURATION);
		}
	}

	public Animation<TextureRegion> getIdleAnimation() {
		return idleAnimation;
	}

	public Animation<TextureRegion> getAttackAnimation(int index) {
		return attackAnimations[index];
	}

	public Animation<TextureRegion>[] getAttackAnimations() {
		return this.attackAnimations;
	}
}
