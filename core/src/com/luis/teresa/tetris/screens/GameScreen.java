package com.luis.teresa.tetris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.luis.teresa.tetris.helpers.LoadAssets;
import com.luis.teresa.tetris.logic.TetrisLogic;
import com.luis.teresa.tetris.logic.TetrisRendererIMGS;

public class GameScreen implements Screen{
	private TetrisLogic myGame;
	private TetrisRendererIMGS renderer;
	private Stage st;
	private LoadAssets myAssets;
	
	public GameScreen() {
		st = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(st);
		
		myGame = new TetrisLogic(); //inicia novo jogo
		
		myAssets = new LoadAssets();
		myAssets.loadGameAssets(myGame);
		myAssets.loadGameOverAssets();
		myAssets.loadBlockImgs();
		
		renderer = new TetrisRendererIMGS(myGame, st, myAssets); //inicia renderer para imprimir
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
    		//update
	    	myGame.update(delta);
	    	//render
	    	renderer.render();	
    	}
    	//if game is over, render GameOver Screen
    	else
    	{
    		renderer.render();
    		renderer.renderGameOverScreen(myGame.getScore(), TetrisLogic.isNewHighScore());
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

