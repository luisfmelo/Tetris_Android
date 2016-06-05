package com.luis.teresa.tetris.screens;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.luis.teresa.tetris.Tetris;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.TetrisRendererIMGS;
import com.luis.teresa.tetris.logic.TetrisLogic;

public class GameScreen implements Screen{
	private TetrisLogic myGame;
	private TetrisRendererIMGS renderer;
	private Stage st;
	private boolean newHighScore=false;
	//private LoadAssets myAssets;
	
	public GameScreen() {
		
		st = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(st);
		
		myGame = new TetrisLogic(); //inicia novo jogo
		TetrisLogic.setROWS_TOLEVELUP(Const.ROWS_TO_LEVEL_UP);
		
		Tetris.myMusics.playTheme();
		Tetris.myAssets.loadGameAssets(myGame);
		//Desktop
				Gdx.input.setInputProcessor(new com.luis.teresa.tetris.helpers.InputHandler(myGame.getBoard_class()));
		//Android
		//Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener(myGame.getBoard_class())));

		renderer = new TetrisRendererIMGS(myGame, st, Tetris.myAssets); //inicia renderer para imprimir
		//renderer = new TetrisRenderer(myGame); //inicia renderer para imprimir
	}
	
	@Override
	public void show() {
	    // Sets a Color to Fill the Screen with (RGB = 10, 15, 230), Opacity of 1 (100%)
	    Gdx.gl.glClearColor(10/255.0f, 215/255.0f, 15/255.0f, 1f);
	}

	@Override
	public void render(float delta) {
	
	    // Fills the screen with the selected color
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	    //update & render if game is not over
    	if( !myGame.isGameOver() )
    	{
    		System.out.println("Level "+myGame.getLevel());
    		if(TetrisLogic.getClear() >0){
    			Tetris.myMusics.playClear(Integer.toString(TetrisLogic.getClear()));
    			TetrisLogic.setClear(0);
    			
    		}
    		if(TetrisLogic.isLevelUp()){
    			TetrisLogic.setLevelUp(false);
    			Const.addLevel(Integer.parseInt(myGame.getLevel()));
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
    		Const.CYCLE_TIME=0.18;
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
    		
    		
    		//renderer.renderGameOverScreen(myGame.getScore(), TetrisLogic.isNewHighScore());
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