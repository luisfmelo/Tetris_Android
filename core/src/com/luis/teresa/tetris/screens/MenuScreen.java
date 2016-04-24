package com.luis.teresa.tetris.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.luis.teresa.tetris.accessors.ActorAccessor;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.LoadAssets;

import Entities.ButtonActor;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class MenuScreen extends ApplicationAdapter implements Screen {
	
	private Stage stage;
	private Skin skin;
	private Table table;
	private LoadAssets myAssets;
	private float width = Gdx.graphics.getWidth();
	private float height = Gdx.graphics.getHeight();
	private Label highScore;
	private TweenManager tweenManager;
	
	SpriteBatch batch;
	Texture img;

	@Override
	public void show() {
		batch = new SpriteBatch();
		img = new Texture("imgs/trophey.png");
		
		myAssets = new LoadAssets();
		myAssets.loadMenuAssets();
		skin = myAssets.getSkin();
		
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		ButtonActor trophey = new ButtonActor(new Texture(Const.TROPHEY_PATH));
		trophey.setSize(.15f*width, .15f*height);
		trophey.setPosition(.5f*width, .9f*height, 0);

		highScore = new Label("1000p", skin, "b_default");
		highScore.setSize(.25f*width, .2f*height);
		highScore.setPosition(.5f*width, .70f*height, 0);

		ButtonActor playBtn = new ButtonActor(new Texture(Const.PLAYBTN_PATH));
		playBtn.setSize(.3f*width, .2f*height);
		playBtn.setPosition(.5f*width, .55f*height, 0);
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
		
		ButtonActor leadBtn = new ButtonActor(new Texture(Const.LEADBTN_PATH));
		leadBtn.setSize(.3f*width, .2f*height);
		leadBtn.setPosition(.5f*width, .35f*height, 0);
		
		ButtonActor settBtn = new ButtonActor(new Texture(Const.SETTBTN_PATH));
		settBtn.setSize(.3f*width, .2f*height);
		settBtn.setPosition(.5f*width, .15f*height, 0);

		stage.addActor(trophey);
		stage.addActor(highScore);
		stage.addActor(playBtn);
		stage.addActor(leadBtn);
		stage.addActor(settBtn);
		// creating animations
				tweenManager = new TweenManager();
				Tween.registerAccessor(Actor.class, new ActorAccessor());
		/*


		table = new Table(skin);
		table.setFillParent(true);
		table.setPosition(0, 0);
		stage.addActor(table);
		table.debug();
		table.debugTable();
			
		 20% TROFEU
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/gfx/packed.atlas"));
		Texture titleImage = new Texture(Gdx.files.internal("buttons/leadBtn.png"));
		stage.addActor(titleImage);
		table.add(titleImage).padBottom(200f);
		table.row();*/

		// 10% PONTOS

		// 60% BOTOES : PLAY LEAD, SETT

		// 10% TITULO

		
		
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void render(float delta) {
		/*Gdx.gl.glClearColor(0, 0, 0, 1);

		stage.act(delta);
		stage.draw();
		
		batch.begin();
		batch.draw(img, (float) (width/2 - 0.15*width), height/2, (float)0.3*width, (float)0.1*height);
		batch.end();*/
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
