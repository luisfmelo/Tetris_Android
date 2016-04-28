package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

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
	private static boolean soundOn;
	
	public LoadMusics () {
		theme = Gdx.audio.newMusic(Gdx.files.internal(Const.MUSIC_THEME_PATH)); 
		
		levelUp = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_LEVELUP_PATH));
		pieceFall = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_PIECEFALL_PATH)); //not using
		fantastic = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_FANTASTIC_PATH)); 
		intro = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_INTRO_PATH));
		clear1 = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_CLEAR1_PATH));
		clear2 = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_CLEAR2_PATH));
		clear3 = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_CLEAR3_PATH));
		clear4 = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_CLEAR4_PATH));
		touch = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_TOUCH_PATH));
		gameOver = Gdx.audio.newSound(Gdx.files.internal(Const.MUSIC_GAMEOVER_PATH));
		soundOn = true;
	}

	public static void setSoundTo (boolean bool) {
		soundOn = bool;
	}
		
	public void playLevelUp() {
		if (soundOn)
			levelUp.play();
	}

	public void playPieceFall() {
		if (soundOn)
			pieceFall.play();
	}
	public void playTheme() {
		if (soundOn)
		{		
			theme.setLooping(true);
			theme.play();
		}
	}
	public void playFantastic() {
		if (soundOn)
			fantastic.play();
	}

	public void playClear1() {
		if (soundOn)
			clear1.play();
	}
	public void playClear2() {
		if (soundOn)
			clear2.play();
	}

	public void playClear3() {
		if (soundOn)
			clear3.play();
	}

	public void playClear4() {
		if (soundOn)
			clear4.play();
	}

	public void playIntro() {
		if (soundOn)
			intro.play();
	}
	
	public void playTouch() {
		if (soundOn)
			touch.play();
	}

	public void playGameOver() {
		if (soundOn)
			gameOver.play();
	}
	


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

	public void stopTheme() {
		theme.stop();
	}
}