package com.luis.teresa.tetris.screens;

import java.io.IOException;

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
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.LoadAssets;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * This class implements a libGDX Screen that represents the Main Menu of the application  
 * @author Luis
 * @author Teresa
 */
public class MenuScreen extends ApplicationAdapter implements Screen {
	
	private Stage stage;
	private Skin skin;
	private LoadAssets myAssets;
	private TweenManager tweenManager;
	Stage st = new Stage(new ScreenViewport());
	
	/**
	 * This method loads the necessary resources to show the Menu screen
	 */
	@Override
	public void show() {
		myAssets = new LoadAssets();
		try {
			myAssets.loadMenuAssets();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (myAssets.getTheme().equals("dracula.")){
			Const.setBG_COLOR(0f,0f,0f,1f);
			Const.setTETRIS_COLOR(1f,1f,1f,1f);
		}
		else {
			Const.setBG_COLOR(1f,1f,1f,1f);
			Const.setTETRIS_COLOR(0f,0f,0f,1f);
		}
		
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
		//add listener 
		Image settBtn = myAssets.getSettBtn();
		settBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new SettScreen());
			}
		});
		
		Image footer = myAssets.getFooter();
		
		stage.addActor(trophey);
		stage.addActor(highScore);
		stage.addActor(playBtn);
		stage.addActor(settBtn);
		stage.addActor(footer);
		
		// creating animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
	}

	/**
	 * Called when this screen is no longer the current screen for a Game
	 * Releases its resources
	 */
	@Override
	public void hide() {
		dispose();
	}

	/**
	 * This method renders the screen
	 * @para delta		Time in seconds since the last call of the function
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(Const.BACKGROUND_COLOR[0], 
							Const.BACKGROUND_COLOR[1], 
							Const.BACKGROUND_COLOR[2], 
							Const.BACKGROUND_COLOR[3]);
		
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

	/**
	 * Called when this screen is no longer the current screen for a Game
	 * Releases the stage and skin resources
	 */
	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}
}
