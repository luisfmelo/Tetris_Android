package com.luis.teresa.tetris;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.LoadAssets;
import com.luis.teresa.tetris.helpers.LoadMusics;
import com.luis.teresa.tetris.screens.GameOverScreen;

public class Tetris extends Game {
	public static LoadAssets myAssets;
	public static LoadMusics myMusics;
	
	@Override
	public void create() {
		myAssets = new LoadAssets();
		myAssets.loadGameOverAssets();
		myAssets.loadBlockImgs();
		myMusics= new LoadMusics();

		Const.soundOn = myAssets.getSoundOn();
				
		if ( myAssets.getTheme().equals("solar.") || myAssets.getTheme().equals("dracula.") )
			myAssets.setTheme(myAssets.getTheme());
		else
			myAssets.setTheme("solar.");

		try {
			setScreen(new GameOverScreen("1", false));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
