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
	private LoadAssets myAssets;
	
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
		
	public void playLevelUp() {
		if (myAssets.getSoundOn())
			levelUp.play();
	}

	public void playPieceFall() {
		if (myAssets.getSoundOn())
			pieceFall.play();
	}
	public void playTheme() {
		if (myAssets.getSoundOn())
		{		
			theme.setLooping(true);
			theme.play();
		}
	}
	public void playFantastic() {
		if (myAssets.getSoundOn())
			fantastic.play();
	}

	public void playClear1() {
		if (myAssets.getSoundOn())
			clear1.play();
	}
	public void playClear2() {
		if (myAssets.getSoundOn())
			clear2.play();
	}

	public void playClear3() {
		if (myAssets.getSoundOn())
			clear3.play();
	}

	public void playClear4() {
		if (myAssets.getSoundOn())
			clear4.play();
	}

	public void playIntro() {
		if (myAssets.getSoundOn())
			intro.play();
	}
	
	public void playTouch() {
		if (myAssets.getSoundOn())
			touch.play();
	}

	public void playGameOver() {
		if (myAssets.getSoundOn())
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