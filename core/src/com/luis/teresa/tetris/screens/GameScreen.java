package com.luis.teresa.tetris.screens;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.luis.teresa.tetris.Tetris;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.MyGestureListener;
import com.luis.teresa.tetris.helpers.TetrisRendererIMGS;
import com.luis.teresa.tetris.logic.TetrisLogic;

/**
 * This class implements a libGDX Screen that shows up when a game is running
 * @author Luis
 * @author Teresa
 */
public class GameScreen implements Screen{
	private TetrisLogic myGame;
	private TetrisRendererIMGS renderer;
	private Stage st;
	private boolean newHighScore=false;
	
	/**
	 * Constructor of the GameScreen
	 * This method loads the necessary assets and sets the input processor
	 */
	public GameScreen() {
		
		st = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(st);
		
		myGame = new TetrisLogic(); //inicia novo jogo
		TetrisLogic.setROWS_TOLEVELUP(Const.ROWS_TO_LEVEL_UP);
		
		Tetris.myMusics.playTheme();
		Tetris.myAssets.loadGameAssets(myGame);
		//Desktop
				//Gdx.input.setInputProcessor(new com.luis.teresa.tetris.helpers.InputHandler(myGame.getBoard_class()));
		//Android
		Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener(myGame.getBoard_class())));

		renderer = new TetrisRendererIMGS(myGame, st, Tetris.myAssets); //inicia renderer para imprimir
		//renderer = new TetrisRenderer(myGame); //inicia renderer para imprimir
	}
	
	/**
	 * Sets a Color to Fill the Screen with (RGB = 10, 15, 230), Opacity of 1 (100%)
	 */
	@Override
	public void show() {
	    Gdx.gl.glClearColor(10/255.0f, 215/255.0f, 15/255.0f, 1f);
	}

	
	@Override
	public void render(float delta) {
	
	    // Fills the screen with the selected color
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	    //update & render if game is not over
    	if( !myGame.isGameOver() )
    	{
    		if(TetrisLogic.getClear() >0){
    			Tetris.myMusics.playClear(Integer.toString(TetrisLogic.getClear()));
    			TetrisLogic.setClear(0);
    			
    		}
    		if(TetrisLogic.isLevelUp()){
    			TetrisLogic.setLevelUp(false);
    			Const.addLevel();
    			Tetris.myMusics.playLevelUp();
    		}
    		
    		try {
				myGame.update(delta);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//render
	    	renderer.render();	
    	}
    	//if game is over, render GameOver Screen
    	else
    	{
    		renderer.render();
    		try {
				if(myGame.get_intScore() > Tetris.myAssets.getScores()){
					newHighScore = true;
					
					Tetris.myAssets.setScores(myGame.get_intScore());
					
					Tetris.myMusics.playFantastic();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		Tetris.myMusics.stopTheme();
    		Tetris.myMusics.playGameOver();
    		
    		try {
				((Game) Gdx.app.getApplicationListener()).setScreen(new GameOverScreen(myGame.getScore(), newHighScore));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	st.draw();

	    
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