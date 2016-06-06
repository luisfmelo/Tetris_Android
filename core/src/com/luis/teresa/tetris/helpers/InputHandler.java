package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.luis.teresa.tetris.logic.Board;

/**
 * This class is responsible to answer to input events.
 * For desktop version only
 * @author Luis
 * @author Teresa
 */
public class InputHandler implements InputProcessor{

	private Board board;
	/**
	 * Constructor
	 * @param board			Board to be handled
	 */
	public InputHandler(Board board) {
		this.board = board;	
	}

	/**
	 * This method handles keyboard events.
	 */
	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
		case Input.Keys.LEFT: board.input("a"); break;
		case Input.Keys.A: board.input("a"); break;
		case Input.Keys.RIGHT: board.input("d");break;
		case Input.Keys.D: board.input("d");break;
		case Input.Keys.DOWN: board.input("s");break;
		case Input.Keys.S: board.input("s");break;
		case Input.Keys.SPACE: while(board.input("s"));break;//board.rotate();break;
		case Input.Keys.UP:
		case Input.Keys.SHIFT_LEFT:
		case Input.Keys.SHIFT_RIGHT:board.rotate();break;
		default: board.input(".");break;	
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
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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

}
