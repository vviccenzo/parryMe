package io.github.parry_me.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Utils {

	Utils() {
		throw new IllegalAccessError("Utility class!");
	}

	public static Animation<TextureRegion> createAnimation(String filePath, int frameCols, int frameRows, float frameDuration) {
		Texture sheet = new Texture(Gdx.files.internal(filePath));
		TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth() / frameCols, sheet.getHeight() / frameRows);

		TextureRegion[] frames = new TextureRegion[frameCols * frameRows];
		int index = 0;
		for (int i = 0; i < frameRows; i++) {
			for (int j = 0; j < frameCols; j++) {
				frames[index++] = tmp[i][j];
			}
		}

		return new Animation<>(frameDuration, frames);
	}

}
