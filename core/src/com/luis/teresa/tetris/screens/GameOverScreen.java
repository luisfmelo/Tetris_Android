package com.luis.teresa.tetris.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.bullet.linearmath.int4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.luis.teresa.tetris.accessors.ActorAccessor;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.LoadAssets;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class GameOverScreen implements Screen{

	private Stage st;
	private LoadAssets myAssets;
	private TweenManager tweenManager;

	private Image home;
	private Label gameOverLabel;
	private Image replay;
	private Label secundaryLabel;
	private ShapeRenderer shapeRenderer;
	
	public GameOverScreen(String score, Boolean newHighScore) {
		myAssets = new LoadAssets();
		myAssets.loadGameOverAssets();
	
		st = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(st);
		
		//Game Over
		gameOverLabel = myAssets.getGameOverLabel();
		secundaryLabel = myAssets.getSecundaryLabel();
		
		if(newHighScore)//NEW HIGH SCORE
			secundaryLabel.setText("New High Score: " + Integer.toString(myAssets.getScores()));
		else //Game Over
			secundaryLabel.setText("Try Again!");
		
		//title bar
    	//GOheader = myAssets.getHeader();
		//home bar
		home = myAssets.getHome();
		home.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {					
				((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
			}
		});
		//replay bar
		replay = myAssets.getReplay();
		replay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {	
				System.out.println("hey");
				((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
			}
		});
				
		//top bar
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(150f/255, 150f/255, 150f/255, 0.5f);
		Rectangle rect = new Rectangle(0, 0.1f*Const.h, Const.w, 0.2f*Const.h);
		shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		shapeRenderer.end();
		


		st = new Stage(new ScreenViewport());

		// creating animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
	
	}
	
	@Override
	public void show() {
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(Const.BACKGROUND_COLOR[0], 
				Const.BACKGROUND_COLOR[1], Const.BACKGROUND_COLOR[2], Const.BACKGROUND_COLOR[3]);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Gdx.input.setInputProcessor(st);
		st.addActor(home);
		st.addActor(secundaryLabel);
		st.addActor(gameOverLabel);
		st.addActor(replay);
		st.draw();
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
	
	@Override
	public void dispose() {
	}
}
