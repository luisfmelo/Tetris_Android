package com.luis.teresa.tetris.logic;

import com.badlogic.gdx.Gdx;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.LoadAssets;
import com.luis.teresa.tetris.helpers.LoadMusics;
import com.luis.teresa.tetris.objects.Board;
import com.luis.teresa.tetris.objects.Shape;

public class TetrisLogic{
	private Board board;
	private float time;
	private boolean shapeInGame;
	private Shape presentShape;
	private Shape futureShape;
	private static int level;
	private static int score;
	private LoadAssets myAssets;
	private static LoadMusics myMusics;
	
	public TetrisLogic() {
		time = 0;
		board = new Board();
		board.initializeBoard();
		level = 1;
		score = 0;
		shapeInGame = false;
		presentShape = new Shape();
		futureShape = new Shape();
		myAssets = new LoadAssets();
		myMusics = new LoadMusics();
		Gdx.input.setInputProcessor(new com.luis.teresa.tetris.helpers.InputHandler(board));
	}

	public char[][] getBoard() {
		return board.getBoard();
	}
	
	public void update(float delta) {
		time += delta;
		if ( time > Const.CYCLE_TIME )
		{
			newCycle();
			time = 0;
			board.checkRows();
			if (board.isGameOver() )
				handleGameOver();
		}	
	}

	private void handleGameOver() {
		int highScore = myAssets.getScores();
		System.out.println("GAME OVER--->" + highScore);
		myMusics.playGameOver();
		
		if (score > highScore)
		{
			myAssets.setScores(score);
			myMusics.playFantastic();
		}
		//Gdx.app.exit();
	}

	private void newCycle() {
		if ( !shapeInGame )
		{
			presentShape = futureShape;
			futureShape = new Shape();
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

	public Shape getFutureShape() {
		return futureShape;
	}

	public String getScore() {
		return Integer.toString(score);
	}

	public String getLevel() {
		return Integer.toString(level);
	}

	public static void levelUp() {
		level ++;
		Const.addLevel();
		myMusics.playLevelUp();
	}

	public static void addScore() {
		score ++;
		if ( score % 10 == 0)
			levelUp();

	}
	
	
}
