package com.luis.teresa.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.luis.teresa.tetris.helpers.LoadAssets;
import com.luis.teresa.tetris.screens.GameScreen;

public class Tetris extends Game {

	public static final String TITLE = "Tetris";
	//public static final int WIDTH = 1080/4;
	//public static final int HEIGHT = 1920/4;
	
	@Override
	public void create() {
		//setScreen(new IntroScreen());
		setScreen(new GameScreen());
		//LoadAssets.load();
	}
	
	@Override
	public void dispose() {
        super.dispose();
        LoadAssets.dispose();
	}

	@Override
	public void render() {
		super.render();
	}
	

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Gdx.app.log("Tetris", "resized");
	}

	@Override
	public void pause() {
		super.pause();
		Gdx.app.log("Tetris", "paused");
	}

	@Override
	public void resume() {
		super.resume();
		Gdx.app.log("Tetris", "resumed");
	}
	
}
