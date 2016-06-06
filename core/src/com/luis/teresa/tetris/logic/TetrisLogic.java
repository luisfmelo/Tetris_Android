package com.luis.teresa.tetris.logic;

import com.luis.teresa.tetris.helpers.Const;
import java.io.IOException;


/**
 * Class that logically represents a game 
 * Each instance of this class contains a Board
 * @author Luis
 * @author Teresa
 */ 
public class TetrisLogic{
	private Board board;
	private float time;
	private boolean shapeInGame;
	private Shape presentShape;
	private Shape futureShape;
	private static int level;
	private static int score;
	private boolean gameOver;
	private static int ROWS_TO_LEVEL_UP;
	private static boolean isLevelUp;
	private static int clear;
	
	/**
	 * Constructor for the game
	 */
	public TetrisLogic() {
		time = 0;
		board = Board.getInstance();
		
		level = 1;
		score = 0;
		gameOver = false;
		shapeInGame = false;
		presentShape = new Shape();
		futureShape = new Shape();
	}
	
	/**
	 * Get the matrix of blocks which compose the board of the game
	 * @return		matrix of the board
	 */
	public Block[][] getBoard() {
		return board.getBoard();
	}
	
	/**
	 * Get the instance of Board class of the actual game
	 * @return		board 
	 */
	public Board getBoard_class(){
		return this.board;
	}
	
	/**
	 * Updates and checks the board if the time since last update is greater than the desired CycleTime
	 * @param delta time since the last call of the function
	 */
 	public void update(float delta)  throws IOException {
		time += delta;
		if ( time > Const.CYCLE_TIME )
		{
			newCycle();
			time = 0;
			if (board.isGameOver() )
				gameOver=true;
		}	
	}

	
 	/**
	 * Makes a new game cycle which consists of a down move 
	 * of the actual shape or a insertion of a new shape on the board
	 */
 	
	public void newCycle() {
		if ( !shapeInGame )
		{
			presentShape = new Shape(futureShape.getPieceShape());
			futureShape = new Shape();
			board.newShape(presentShape);
			shapeInGame = true;
		}
		shapeInGame = board.input("s");
	}

	/**
	 * Gets the number of the rows of the board
	 * @return board rows
	 */
	public int getRows() {
		return board.getRows();
	}

	/**
	 * Gets the number of the columns of the board
	 * @return board columns
	 */
	public int getCols() {
		return board.getCols();
	}

	/**
	 * Gets the next Shape that will enter the board
	 * @return futureShape
	 */
	public Shape getFutureShape() {
		return futureShape;
	}

	/**
	 * Gets a string with the current user score
	 * @return score
	 */
	public String getScore() {
		return Integer.toString(score);
	}

	/**
	 * Gets the current user score
	 * @return score
	 */
	public int get_intScore() {
		return score;
	}
	/**
	 * Gets a string with the current user level
	 * @return level
	 */
	public String getLevel() {
		return Integer.toString(level);
	}

	/**
	 * Adds score to the total score and checks if the game should go to next level
	 * @param score to add
	 */
	public static void addScore(String string) {

		if (Integer.parseInt(string) == 1)
			score += 1;
		else if (Integer.parseInt(string) == 2)
			score += 2;
		else if (Integer.parseInt(string) == 3)
			score += 4;
		else if (Integer.parseInt(string) == 4)
			score += 8;
		System.out.println(score);
		if ( score % ROWS_TO_LEVEL_UP == 0){
			level ++;
			setLevelUp(true);
		}

	}

	/**
	 * Checks if the game ended
	 * @return gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * Gets the necessary number of rows cleaned to level up 
	 * @return ROWS_TO_LEVEL_UP
	 */
	public int getROWS_TOLEVELUP() {
		return ROWS_TO_LEVEL_UP;
	}

	/**
	 * Sets the necessary number of rows cleaned to level up 
	 * @param ROWS_TO_LEVEL_UP
	 */
	public static void setROWS_TOLEVELUP(int rOWS_TOLEVELUP) {
		ROWS_TO_LEVEL_UP = rOWS_TOLEVELUP;
	}

	/**
	 * Checks if in the current cycle the levelUp function was called 
	 * @return isLevelUp
	 */
	public static boolean isLevelUp() {
		return isLevelUp;
	}

	/**
	 * Sets variable that is true if in the current cycle the levelUp function was called 
	 * @param isLevelUp
	 */
	public static void setLevelUp(boolean state) {
		isLevelUp = state;
	}
	
	/**
	 * Sets variable that is true if in the current cycle the a row was clear 
	 * @param state of rows
	 */
	public static void setClear(int state){
		clear=state;
	}
	
	/**
	 * Gets variable that is true if in the current cycle the a row was clear  
	 * @return clear
	 */
	public static int getClear(){
		return clear;
	}
	
	/**
	 * Gets the current shape in the board 
	 * @return presentShape
	 */
	public Shape getShape(){
		return presentShape;
	}
	
	/**
	 * Sets a shape as the present shape in the board 
	 * @param shape
	 */
	public void setShapeInGame(boolean shape){
		shapeInGame=shape;
	}
	
}