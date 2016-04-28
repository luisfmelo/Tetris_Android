package com.luis.teresa.tetris.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

public class SettScreen implements Screen {

	private Stage st;
	private LoadAssets myAssets;
	private TweenManager tweenManager;
	
	private Label titleLabel;
	private Image x;
	private Image theme;
	private Label musicLabel;
	private Image music;
	private Label themeLabel;

	public SettScreen() {
		myAssets = new LoadAssets();
		myAssets.loadSettingsAssets();

		st = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(st);
		
		//Label - Title
		titleLabel = myAssets.getTitleLabel();
		
		//Image - X
		x = myAssets.getX();
		x.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {						
				((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
			}
		});
				
		
		//Label - Music
		musicLabel = myAssets.getMusicLabel();
		
		//Image - Music
		music = myAssets.getImageMusic();
		music.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
						
				Const.soundOn = !Const.soundOn;
				myAssets.setSoundOn(Const.soundOn);
				if (Const.soundOn)
					music.setDrawable(new SpriteDrawable(
										new Sprite(
										new Texture(Gdx.files.internal(Const.THEME + Const.SOUND_PATH)))));
				else
					music.setDrawable(new SpriteDrawable(
										new Sprite(
										new Texture(Gdx.files.internal(Const.THEME + Const.MUTE_PATH)))));
				myAssets.setSoundOn(Const.soundOn);
				
				st = new Stage(new ScreenViewport());
				Gdx.input.setInputProcessor(st);
				((Game) Gdx.app.getApplicationListener()).setScreen(new SettScreen());
			}
		});
		
		//Label - Theme
		themeLabel = myAssets.getThemeLabel();
		
		//Image - theme
		theme = myAssets.getImageTheme();
		theme.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if ( Const.THEME.equals("dracula/") )
				{
					Const.THEME =  "solar/";
					theme.setDrawable(new SpriteDrawable(
							new Sprite(
							new Texture(Gdx.files.internal(Const.THEME + Const.SOLAR_PATH)))));									
				}
				else if ( Const.THEME.equals("solar/") )
				{
					Const.THEME =  "dracula/";
					theme.setDrawable(new SpriteDrawable(
							new Sprite(
							new Texture(Gdx.files.internal(Const.THEME + Const.DRACULA_PATH)))));		
				}
				
				myAssets.setTheme(Const.THEME);

				((Game) Gdx.app.getApplicationListener()).setScreen(new SettScreen());
			}
		});
				
		// creating animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());

	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(Const.BACKGROUND_COLOR[0], 
				Const.BACKGROUND_COLOR[1], Const.BACKGROUND_COLOR[2], Const.BACKGROUND_COLOR[3]);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		st.addActor(titleLabel);
		st.addActor(x);
		st.addActor(musicLabel);
		st.addActor(music);
		st.addActor(themeLabel);
		st.addActor(theme);
		st.draw();
		tweenManager.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		
	}

}
