package com.luis.teresa.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.LoadAssets;
import com.luis.teresa.tetris.helpers.LoadMusics;
import com.luis.teresa.tetris.screens.MenuScreen;

public class Tetris extends Game {
	@Override
	public void create() {
		LoadAssets myAssets = new LoadAssets();
		Const.soundOn = myAssets.getSoundOn();
		LoadMusics.setSoundTo( myAssets.getSoundOn() );
				
		if ( myAssets.getTheme().equals("solar/") || myAssets.getTheme().equals("dracula/") )
			Const.THEME = myAssets.getTheme();
		else
			Const.THEME = "solar/";

		setScreen(new MenuScreen());
		//setScreen(new GameScreen());
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
