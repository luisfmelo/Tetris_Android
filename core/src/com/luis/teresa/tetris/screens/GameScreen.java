package com.luis.teresa.tetris.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.luis.teresa.tetris.logic.TetrisLogic;
import com.luis.teresa.tetris.logic.TetrisRenderer;

public class GameScreen implements Screen{
	private TetrisLogic myGame;
	private TetrisRenderer renderer;
	
	public GameScreen() {
       
		myGame = new TetrisLogic(); //inicia novo jogo
		renderer = new TetrisRenderer(myGame); //inicia renderer para imprimir
		
		//Gdx.input.setInputProcessor(new InputHandler());
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
	    // Covert Frame rate to String, print it
	    //Gdx.app.log("GameScreen FPS", (1/delta) + "");
	    
	    //update
	    myGame.update(delta);
	    //render
	    renderer.render();
	    
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

