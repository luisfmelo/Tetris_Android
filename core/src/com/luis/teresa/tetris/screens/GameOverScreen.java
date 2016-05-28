package com.luis.teresa.tetris.screens;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
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
	
	//ANIMATION

	   // Time while each frame keeps on screen
    private static float FRAME_DURATION = .1f;
 
    private SpriteBatch batch;
    private TextureAtlas charset;
    private TextureRegion currentFrame;
    private Animation myAnimation;
 
    private float elapsed_time = 0f;
    private float origin_x, origin_y;
    
	private Boolean newHS;   
	
	public GameOverScreen(String score, Boolean newHighScore) throws IOException {
		this.newHS = newHighScore;
		myAssets = new LoadAssets();
		myAssets.loadGameOverAssets();
		
		if (myAssets.getTheme().equals("dracula.")){

			Const.setBG_COLOR(0f,0f,0f,1f);
			Const.setTETRIS_COLOR(1f,1f,1f,1f);
		}
		else {
			Const.setBG_COLOR(1f,1f,1f,1f);
			Const.setTETRIS_COLOR(0f,0f,0f,1f);
		}
	
		st = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(st);
		
		secundaryLabel = myAssets.getSecundaryLabel();
		gameOverLabel = myAssets.getGameOverLabel();
		
		if(newHS)//NEW HIGH SCORE
		{
			createAnimation();
			secundaryLabel.setText("" + Integer.toString(myAssets.getScores()));
			gameOverLabel.setText("");
			st.addActor(secundaryLabel);		
		}
		else //Game Over
		{
			//Game Over
			secundaryLabel.setText("Try Again!");
			gameOverLabel.setText("Game Over!");
		}
		
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
		st.addActor(replay);
		st.addActor(secundaryLabel);
		st.addActor(gameOverLabel);
		st.draw();
		if (newHS)
			renderAnim();
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
	
	
	
	
	private void createAnimation() {
		batch = new SpriteBatch();
		 
        charset = new TextureAtlas( Gdx.files.internal("charset.atlas.txt") );
        Array<AtlasRegion> highScore = charset.findRegions("highScore");
        myAnimation = new Animation(FRAME_DURATION, highScore, PlayMode.LOOP);
        TextureRegion firstTexture = highScore.first();
        
        origin_x = (Gdx.graphics.getWidth()  - firstTexture.getRegionWidth())  / 2;
        origin_y = .85f*Gdx.graphics.getHeight();
	}

	private void renderAnim(){// Elapsed time
        elapsed_time += Gdx.graphics.getDeltaTime();
 
        currentFrame = myAnimation.getKeyFrame(elapsed_time);
     
        // Drawing the frame
        batch.begin();
        batch.draw(currentFrame, origin_x, origin_y);
        batch.end();
	}
}	
