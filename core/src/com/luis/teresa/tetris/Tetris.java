package com.luis.teresa.tetris;

import com.badlogic.gdx.Game;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.LoadAssets;
import com.luis.teresa.tetris.screens.IntroScreen;

/**
 * This class is the main class which is responsible to start the first Screen
 * @author Luis
 * @author Teresa
 */
public class Tetris extends Game {
	/**
	 * Method responsible to create the Game and redirect the use to Splash screen, 
	 * using the theme pre-defined in the preferences
	 * This method load some assets.
	 */
	@Override
	public void create() {
		LoadAssets myAssets = new LoadAssets();
		Const.soundOn = myAssets.getSoundOn();
				
		if ( myAssets.getTheme().equals("solar.") || myAssets.getTheme().equals("dracula.") )
			myAssets.setTheme(myAssets.getTheme());
		else
			myAssets.setTheme("solar.");

			setScreen(new IntroScreen());
	}
	
	/**
	 * Method responsible to dispose the Game and the assets loaded
	 */
	@Override
	public void dispose() {
        super.dispose();
        LoadAssets.dispose();
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	/**
	 * Method responsible to adjust the screen 
	 */
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
