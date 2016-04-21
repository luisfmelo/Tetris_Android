package com.luis.teresa.tetris;

import com.badlogic.gdx.Game;
import com.luis.teresa.tetris.screens.IntroScreen;

public class Tetris extends Game {

	@Override
	public void create() {
		setScreen(new IntroScreen());
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
	
}
