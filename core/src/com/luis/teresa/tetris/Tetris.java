package com.luis.teresa.tetris;

import com.badlogic.gdx.Game;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.LoadAssets;
import com.luis.teresa.tetris.helpers.LoadMusics;
import com.luis.teresa.tetris.screens.IntroScreen;

/**
 * This is the main class of the Tetris Game. 
 * This class is responsible for loading the assets as well as redirect to the first screen.
 * @author Luis
 * @author Teresa
 */
public class Tetris extends Game {
	public static LoadAssets myAssets;
	public static LoadMusics myMusics;
	
	/**
	 * Creation of a Game Instance with configurations loaded (theme & music status)
	 */
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
