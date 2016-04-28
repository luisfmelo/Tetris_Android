package com.luis.teresa.tetris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.luis.teresa.tetris.Tetris;
import com.luis.teresa.tetris.helpers.Const;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Const.TITLE;
		//config.width = Tetris.WIDTH;
		//config.height = Tetris.HEIGHT;
		new LwjglApplication(new Tetris(), config);
	}
}
