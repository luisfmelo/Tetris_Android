package com.luis.teresa.tetris.logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.objects.Board;
import com.luis.teresa.tetris.objects.Shape;
import com.luis.teresa.tetris.screens.MenuScreen;

public class TetrisLogic{
	
	//private Rectangle rect = new Rectangle(0, 0, 17, 12);
	private Board board;
	private float time;
	private boolean shapeInGame;
	private Shape presentShape;
	private Shape futureShape;
	
	public TetrisLogic() {
		time = 0;
		board = new Board();
		board.initializeBoard();
		shapeInGame = false;
		presentShape = new Shape();
		Gdx.input.setInputProcessor(new com.luis.teresa.tetris.helpers.InputHandler(board));
	}

	public char[][] getBoard() {
		return board.getBoard();
	}
	
	public void update(float delta) {
		time += delta;
		if ( time > Const.CYCLE_TIME )
		{
			futureShape = new Shape();
			newCycle();
			time = 0;
			board.checkRows();
			if (board.isGameOver() )
				handleGameOver();
			presentShape = futureShape;
		}	
	}

	private void handleGameOver() {
		Gdx.app.exit();
	}

	private void newCycle() {
		if ( !shapeInGame )
		{
			board.newShape(presentShape);
			shapeInGame = true;
		}
		shapeInGame = board.input("s");
	}

	public int getRows() {
		return board.getRows();
	}

	public int getCols() {
		return board.getCols();
	}
	
	
}
