package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Audio {

	private final float LOUD_VOLUME = 1.0f;
	private final float SOFTER_VOLUME = 0.5f;
	private Sound soundCollides;
	private Sound soundLaserish;
	private Sound soundHurt;
	private boolean soundOn;
	private boolean musicOn;

	public Audio () {
		soundCollides = Gdx.audio.newSound(Gdx.files.internal(Const.COLLIDES_PATH));
		soundLaserish = Gdx.audio.newSound(Gdx.files.internal(Const.LASERISH_PATH));
		soundHurt = Gdx.audio.newSound(Gdx.files.internal(Const.HURT_PATH));
	}

	public void playCollides () {
		if (soundOn) 
			soundCollides.play(LOUD_VOLUME);
	}

	public void playLaserish () {
		if (soundOn) 
			soundLaserish.play(SOFTER_VOLUME);
	}

	public void playHurt () {
		if (soundOn)
			soundHurt.play(SOFTER_VOLUME);
	}

	public void dispose () {
		soundCollides.dispose();
		soundLaserish.dispose();
		soundHurt.dispose();
	}

	public void setSoundTo (boolean soundOn) {
		this.soundOn = soundOn;
	}

	public void setMusicTo (boolean musicOn) {
		this.musicOn = musicOn;
	}
}
