package com.luis.teresa.tetris.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.luis.teresa.tetris.accessors.ActorAccessor;
import com.luis.teresa.tetris.accessors.SpriteAccessor;
import com.luis.teresa.tetris.helpers.LoadAssets;
import com.luis.teresa.tetris.helpers.LoadMusics;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * This class implements a libGDX Screen that shows up in the laucher of the application
 * @author Luis
 * @author Teresa
 */
public class IntroScreen implements Screen {

	private SpriteBatch batch;
	private Image introImg;
	private TweenManager tweenManager;
	private LoadAssets myAssets;
	private Stage stage;
	private LoadMusics myMusics;

	/**
	 * This method loads the necessary resources to show the intro screen
	 */
	@Override
	public void show() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		myAssets = new LoadAssets();
		myAssets.loadIntroAssets();
		
		batch = new SpriteBatch();
		
		myMusics = new LoadMusics();
		myMusics.playIntro();
			
		stage = new Stage(new ScreenViewport());

		introImg = myAssets.getIntroImg();

		stage.addActor(introImg);
		
		// creating animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());

		Tween.set(introImg, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(introImg, SpriteAccessor.ALPHA, 1.5f).target(1).repeatYoyo(1, .5f).setCallback(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
				}
		}).start(tweenManager);

		tweenManager.update(Float.MIN_VALUE); // update once avoid short flash of splash before animation
	}

/**
 * This method renders the screen
 * @param delta		time in seconds since the last call of the function
 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta); //update
		stage.draw();
		tweenManager.update(delta);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	/**
	 * Called when this screen should the Sprite resource
	 */
	@Override
	public void dispose() {
		batch.dispose();
	}
}