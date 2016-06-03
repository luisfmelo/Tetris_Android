package com.luis.teresa.tetris.logic;

import com.luis.teresa.tetris.helpers.Const;
import java.io.IOException;


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
	
	public TetrisLogic() {
		time = 0;
		board = Board.getInstance();
		
		level = 1;
		score = 0;
		gameOver = false;
		shapeInGame = false;
		presentShape = new Shape();
		futureShape = new Shape();
		/*myAssets = new LoadAssets();
		myMusics= new LoadMusics();
		myMusics.playTheme();*/
		//newHighScore = false;
		//Desktop
		//Gdx.input.setInputProcessor(new com.luis.teresa.tetris.helpers.InputHandler(board));
		//Android
		//Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener(board)));
	}
	

	public Block[][] getBoard() {
		return board.getBoard();
	}
	
	public Board getBoard_class(){
		return this.board;
	}
 	public void update(float delta)  throws IOException {
		//board.printBoard();
 		//System.out.println(score);
		time += delta;
		if ( time > Const.CYCLE_TIME )
		{
			newCycle();
			time = 0;
			//board.checkRows();
			if (board.isGameOver() )
				gameOver=true;
		}	
	}

	

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

	public int get_intScore() {
		return score;
	}
	public String getLevel() {
		return Integer.toString(level);
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
		System.out.println(score);
		if ( score % ROWS_TO_LEVEL_UP == 0){
			level ++;
			setLevelUp(true);
		}

	}

	public boolean isGameOver() {
		return gameOver;
	}

	public int getROWS_TOLEVELUP() {
		return ROWS_TO_LEVEL_UP;
	}

	public static void setROWS_TOLEVELUP(int rOWS_TOLEVELUP) {
		ROWS_TO_LEVEL_UP = rOWS_TOLEVELUP;
	}

	public static boolean isLevelUp() {
		return isLevelUp;
	}

	public static void setLevelUp(boolean state) {
		isLevelUp = state;
	}
	public static void setClear(int state){
		clear=state;
	}
	public static int getClear(){
		return clear;
	}
	
	public Shape getShape(){
		return presentShape;
	}
	public void setShapeInGame(boolean shape){
		shapeInGame=shape;
	}
	
}