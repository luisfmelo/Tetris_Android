package com.luis.teresa.tetris.logic;

import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.objects.Board;
import com.luis.teresa.tetris.objects.Shape;

public class TetrisLogic {
	
	//private Rectangle rect = new Rectangle(0, 0, 17, 12);
	private Board board;
	private float time;
	private boolean shapeInGame;
	private Shape presentShape;
	private Shape futureShape;
	
	public TetrisLogic() {
		board = new Board();
		board.initializeBoard();
		shapeInGame = false;
		presentShape = new Shape();
	}

	public char[][] getBoard() {
		return board.getBoard();
	}
	
	public void update(float delta) {
		time += System.nanoTime();
		if ( time > Const.CYCLE_TIME )
			newCycle();
		
	}

	private void newCycle() {
		
	}

	public int getRows() {
		return board.getRows();
	}

	public int getCols() {
		return board.getCols();
	}
	
	
	/*public void update(float delta) {
		System.out.println("GameWorld - update");
		rect.y++;
		if (rect.y > Gdx.graphics.getHeight()/2) {
			rect.y = 0;
		}
	}*/
	
	/*public Rectangle getRect() {
		return rect;
	}*/
	
	
	
	
}
