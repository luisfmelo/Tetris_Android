package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.luis.teresa.tetris.logic.Board;

public class MyGestureListener implements ApplicationListener, GestureListener, InputProcessor {
	private Board board;

	public MyGestureListener(Board board) {
		this.board = board;
	}
	

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			
		return false;
	}
	
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
    	Gdx.app.log("Vx = ", Double.toString(velocityX));
    	Gdx.app.log("Vy = ", Double.toString(velocityY));
    	
    	int x = Math.abs((int)velocityX);
    	int y = Math.abs((int)velocityY);

    	x = (x / 600) + 1;
    	y = (y / 1000) + 1;
    	
    	System.out.println(x);
    	
    	if (Math.abs(velocityX) > Math.abs(velocityY))
	    	for (int i = 0; i < Math.abs(x); i++) {
	            if( velocityX > 0 )
	            	board.input("d");
	            else
	            	board.input("a");
	    	}	
    	else
	    	for (int i = 0; i < Math.abs(y); i++)  {
	            if( velocityY < 0 )
	            	board.rotate();
	            else
	            	board.input("s");
	    	}	

        return true;
    }
	
	
	
	
	
	
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {

        return false;
    }



    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {	
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean zoom (float originalDistance, float currentDistance){

       return false;
    }

    @Override
    public boolean pinch (Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer){

       return false;
    }

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}