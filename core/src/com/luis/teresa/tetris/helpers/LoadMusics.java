package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * This class is responsible to Load all the musics necessary for the game.
 * @author Luis
 * @author Teresa
 */
public class LoadMusics {

	private Music theme;
	
	private Sound levelUp;
	private Sound pieceFall;
	private Sound fantastic;
	private Sound intro;
	private Sound clear1;
	private Sound clear2;
	private Sound clear3;
	private Sound clear4;
	private Sound touch;
	private Sound gameOver;
	private static LoadAssets myAssets;
	
	/**
	 * Constructor.
	 * this method loads the musics for all the screens
	 */
	public LoadMusics () {
		theme = Gdx.audio.newMusic(Gdx.files.internal(Const.MUSIC_THEME_PATH)); 
		myAssets = new LoadAssets();
		
		levelUp = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_LEVELUP_PATH));
		pieceFall = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_PIECEFALL_PATH)); //not using
		fantastic = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_FANTASTIC_PATH)); 
		intro = Gdx.audio.newSound(Gdx.files.internal(Const.INTRO_MUSIC_PATH));
		clear1 = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_CLEAR1_PATH));
		clear2 = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_CLEAR2_PATH));
		clear3 = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_CLEAR3_PATH));
		clear4 = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_CLEAR4_PATH));
		touch = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_TOUCH_PATH));
		gameOver = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_GAMEOVER_PATH));
	}
	
	/**
	 * Play this music when user raise the level
	 */
	public void playLevelUp() {
		if (myAssets.getSoundOn())
			levelUp.play();
	}

	/**
	 * Play this music when a Piece Falls
	 */
	public void playPieceFall() {
		if (myAssets.getSoundOn())
			pieceFall.play();
	}
	
	/**
	 * Play this music in loop during the Game
	 */
	public void playTheme() {
		if (myAssets.getSoundOn())
		{		
			theme.setLooping(true);
			theme.play();
		}
	}
	
	/**
	 * Play this music when user beats high score
	 */
	public void playFantastic() {
		if (myAssets.getSoundOn())
			fantastic.play();
	}

	/**
	 * Play this music during the first Screen
	 */
	public void playIntro() {
		if (myAssets.getSoundOn())
			intro.play();
	}
	
	/**
	 * Play this music when user touch the screen
	 */
	public void playTouch() {
		if (myAssets.getSoundOn())
			touch.play();
	}

	/**
	 * Play this music when user lose
	 */
	public void playGameOver() {
		if (myAssets.getSoundOn())
			gameOver.play();
	}
	
	/**
	 * This method stop the main theme which is in loop.
	 * Use this when user leave the GameScreen
	 */
	public void stopTheme() {
		theme.stop();
	}

	/**
	 * Play this musics when user clears 1,2,3 or 4 horizontal lines (different musics)
	 * @param string	with the number of lines cleared
	 */
	public void playClear(String string) {
		if (!myAssets.getSoundOn())
			return;
		
		if (Integer.parseInt(string) == 1)
			clear1.play();
		else if (Integer.parseInt(string) == 2)
			clear2.play();
		else if (Integer.parseInt(string) == 3)
			clear3.play();
		else if (Integer.parseInt(string) == 4)
			clear4.play();
	}
	
	/**
	 * Dispose all musics
	 */
	public void dispose () {
		levelUp.dispose();
		pieceFall.dispose();
		theme.dispose();
		fantastic.dispose();
		intro.dispose();
		clear1.dispose();
		clear2.dispose();
		clear3.dispose();
		clear4.dispose();
		touch.dispose();
		gameOver.dispose();
	}
}