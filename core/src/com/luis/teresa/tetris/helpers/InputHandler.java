package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.luis.teresa.tetris.objects.Board;

public class InputHandler implements InputProcessor{
	Board board;
	boolean dragging;
	int previousX, previousY;
	public InputHandler(Board board) {
		this.board = board;
		dragging = false;
		previousX = 0;
		previousY = 0;		
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
		case Input.Keys.LEFT: board.input("a"); break;
		case Input.Keys.A: board.input("a"); break;
		case Input.Keys.RIGHT: board.input("d");break;
		case Input.Keys.D: board.input("d");break;
		case Input.Keys.DOWN: board.input("s");break;
		case Input.Keys.S: board.input("s");break;
		case Input.Keys.SPACE: board.rotate();break;
		}
		
		return true;
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
		board.rotate();
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		dragging = false;
		previousX = 0;
		previousY = 0;	
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		if (dragging == false)
		{
			previousX = screenX;
			previousY = screenY;
			dragging = true;
			return true;
		}
		if (screenX >= previousX)
			board.input("d");
		else if (screenX < previousX)
			board.input("a");
		if (screenY > previousY)
			board.input("s");

		previousX = screenX;
		previousY = screenY;
				
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		Gdx.app.log("input", "scrolled" + amount);
		return false;
	}

}
