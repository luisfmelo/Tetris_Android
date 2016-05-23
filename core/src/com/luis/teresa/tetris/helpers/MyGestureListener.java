package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.luis.teresa.tetris.logic.Board;

public class MyGestureListener implements GestureListener{
	
	private boolean dragging;
	private int previousX;
	private int previousY;
	private Board board;

	public MyGestureListener(Board board) {
		this.board = board;
		dragging = false;
		previousX = 0;
		previousY = 0;		
	}
	
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
    	board.rotate();
    	previousX = 0;
		previousY = 0;
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
    public boolean fling(float velocityX, float velocityY, int button) {

    	Gdx.app.log("Vx = ", Double.toString(velocityX));
    	Gdx.app.log("Vy = ", Double.toString(velocityY));
    	
    	int x = Math.abs((int)velocityX);
    	int y = Math.abs((int)velocityY);

    	x = (x / 600) + 1;
    	y = (y / 1000) + 1;
    	
    	System.out.println(x);
    	
    	for (int i = 0; i < Math.abs(x); i++) {
            if( velocityX > 0 )
            	board.input("d");
            else
            	board.input("a");
    	}
    	
    	for (int i = 0; i < Math.abs(y); i++) 
            if( velocityY < 0 )
            	board.input("s");   	

        return true;
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
}