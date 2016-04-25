package com.luis.teresa.tetris.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.luis.teresa.tetris.accessors.ActorAccessor;
import com.luis.teresa.tetris.helpers.LoadAssets;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class MenuScreen extends ApplicationAdapter implements Screen {
	
	private Stage stage;
	private Skin skin;
	private LoadAssets myAssets;
	private TweenManager tweenManager;
	
	@Override
	public void show() {
		
		myAssets = new LoadAssets();
		myAssets.loadMenuAssets();
		
		skin = myAssets.getSkin();
		
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		Image trophey = myAssets.getTrophey();
		Label highScore = myAssets.getHighScore();
		Image playBtn = myAssets.getPlayBtn();
		playBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Timeline.createParallel().beginParallel()
						.setCallback(new TweenCallback() {
							@Override
							public void onEvent(int type, BaseTween<?> source) {
								((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
							}
						})
						.end().start(tweenManager);
			}
		});
		Image leadBtn = myAssets.getLeaderBtn();
		Image settBtn = myAssets.getSettBtn();
		Image footer = myAssets.getFooter();

		stage.addActor(trophey);
		stage.addActor(highScore);
		stage.addActor(playBtn);
		stage.addActor(leadBtn);
		stage.addActor(settBtn);
		stage.addActor(footer);
		
		// creating animations
				tweenManager = new TweenManager();
				Tween.registerAccessor(Actor.class, new ActorAccessor());

		
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta); //update
		stage.draw();
		tweenManager.update(delta);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void resume() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

}
