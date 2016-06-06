package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.luis.teresa.tetris.logic.Board;

/**
 * This class is responsible to answer to input events.
 * For Android version.
 * @author Luis
 * @author Teresa
 */
public class MyGestureListener implements ApplicationListener, GestureListener, InputProcessor {
	private Board board;

	public MyGestureListener(Board board) {
		this.board = board;
	}
	
	/**
	 * When the user swipe, it will move the Shape.
	 * @param velocityX 	velocity on X Axis in seconds
	 * @param velocityY 	velocity on Y Axis in seconds
	 */
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {   	
    	int x = Math.abs((int)velocityX);
    	int y = Math.abs((int)velocityY);

    	x = (x / 600) + 1;
    	y = (y / 1000) + 1;
    	    	
    	if (Math.abs(velocityX) > Math.abs(velocityY))
	    	for (int i = 0; i < Math.abs(x); i++) {
	            if( velocityX > 0 )
	            	board.input("d");
	            else
	            	board.input("a");
	    	}	
    	else
	    	for (int i = 0; i < Math.abs(y); i++)  {
            	board.input("s");
	    	}	

        return true;
    }
	
    /**
     * When the screen is tapped, rotate the shape.
     */
    @Override
    public boolean tap(float x, float y, int count, int button) {
   		board.rotate();
    				
    	return true;
    }
    
    
    
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
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
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void create() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void render() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}