package com.luis.teresa.tetris.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class TetrisLogic {
	private char[][] board = new char[22][12];
	
	private Rectangle rect = new Rectangle(0, 0, 17, 12);

	public void update(float delta) {
		System.out.println("GameWorld - update");
		rect.y++;
		if (rect.y > Gdx.graphics.getHeight()/2) {
			rect.y = 0;
		}
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public char[][] getBoard() {
		return board;
	}
	
	private void initializeBoard(){
		for (int i = 0; i < 22; i++) {
			for (int j = 0; j < 12; j++) {
				if ( i == 0 || i == 21 || j == 0 || j == 11 )
					board[i][j] = 'X';
				else
					board[i][j] = ' ';
			}
		}
	}
}
