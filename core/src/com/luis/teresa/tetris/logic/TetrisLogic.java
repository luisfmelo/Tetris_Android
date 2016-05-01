package com.luis.teresa.tetris.logic;

import com.badlogic.gdx.Gdx;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.LoadAssets;
import com.luis.teresa.tetris.helpers.LoadMusics;

public class TetrisLogic{
	private Board board;
	private float time;
	private boolean shapeInGame;
	private Shape presentShape;
	private Shape futureShape;
	private static int level;
	private static int score;
	private static LoadAssets myAssets;
	private boolean gameOver;
	private static LoadMusics myMusics;
	private static boolean newHighScore;
	
	public TetrisLogic() {
		time = 0;
		board = new Board();
		board.initializeBoard();
		level = 1;
		score = 0;
		gameOver = false;
		shapeInGame = false;
		presentShape = new Shape();
		futureShape = new Shape();
		myAssets = new LoadAssets();
		myMusics= new LoadMusics();
		myMusics.playTheme();
		newHighScore = false;
		Gdx.input.setInputProcessor(new com.luis.teresa.tetris.helpers.InputHandler(board));
		
	}

	public char[][] getBoard() {
		return board.getBoard();
	}
	
	public void update(float delta) {
		board.printBoard();
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
		myMusics.stopTheme();
		myMusics.playGameOver();
		gameOver = true;
		if (score > highScore)
		{
			newHighScore = true;
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
		myMusics.dispose();
	}

	public static void addScore() {
		score ++;
		if ( score % 10 == 0)
			levelUp();

	}

	public boolean isGameOver() {
		return gameOver;
	}

	public static boolean isNewHighScore() {
		return newHighScore;
	}

}