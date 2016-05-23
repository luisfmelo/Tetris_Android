package com.luis.teresa.tetris.logic;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.LoadAssets;
import com.luis.teresa.tetris.helpers.LoadMusics;
import com.luis.teresa.tetris.helpers.MyGestureListener;

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
		board = Board.getInstance( );
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
		//Desktop
		Gdx.input.setInputProcessor(new com.luis.teresa.tetris.helpers.InputHandler(board));
		//Android
		//Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener(board)));
	}

	public char[][] getBoard() {
		return board.getBoard();
	}
	
	public void update(float delta) throws IOException {
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

	private void handleGameOver() throws IOException {
		int highScore = myAssets.getScores();
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
	}

	public static void addScore(String string) {

		if (Integer.parseInt(string) == 1)
			score += 1;
		else if (Integer.parseInt(string) == 2)
			score += 2;
		else if (Integer.parseInt(string) == 3)
			score += 4;
		else if (Integer.parseInt(string) == 4)
			score += 8;

		if ( score % Const.ROWS_TO_LEVEL_UP == 0)
			levelUp();

	}

	public boolean isGameOver() {
		return gameOver;
	}

	public static boolean isNewHighScore() {
		return newHighScore;
	}

}