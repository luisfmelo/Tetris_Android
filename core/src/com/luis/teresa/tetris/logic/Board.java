package com.luis.teresa.tetris.logic;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.luis.teresa.tetris.helpers.Const;

public class Board {
	private char[][] board;
	private int rows = 25;
	private int cols = 12;
	private ArrayList<Vector2> myShape;
	ArrayList<Vector2> newCoords;
	String pieceOnGoing;
	Shape shape;
	
	private boolean gameover = false;
	
	public Board() {
		board = new char[rows][cols];
	}
	
	public void initializeBoard(){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if ( i == 2 || i >22 || j == 0 || j == 11 )
					board[i][j] = 'X';
				else
					board[i][j] = ' ';
			}
		}
	}
	
	public char[][] getBoard() {
		return board;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public void newShape(Shape shape) {
		myShape = new ArrayList<Vector2>(5);
		this.shape = shape;
		pieceOnGoing = ":";
		
		switch (shape.getPieceShape().getLetter()) {
		case "I": 
			Const.redColor = 0f/255;
			Const.greenColor = 240f/255;
			Const.blueColor = 240f/255;
			break;
		case "S":
			Const.redColor = 0f/255;
			Const.greenColor = 240f/255;
			Const.blueColor = 0f/255;
			break;
		case "Z":
			Const.redColor = 240f/255;
			Const.greenColor = 0f/255;
			Const.blueColor = 0f/255;
			break;
		case "T":
			Const.redColor = 150f/255;
			Const.greenColor = 0f/255;
			Const.blueColor = 240f/255;
			break;
		case "O":
			Const.redColor = 240f/255;
			Const.greenColor = 240f/255;
			Const.blueColor = 0f/255;
			break;
		case "L":
			Const.redColor = 240f/255;
			Const.greenColor = 150f/255;
			Const.blueColor = 0f/255;
			break;
		case "J":
			Const.redColor = 0f/255;
			Const.greenColor = 0f/255;
			Const.blueColor = 240f/255;			
			break;

		default:
			Const.redColor = 0f/255;
			Const.greenColor = 0f/255;
			Const.blueColor = 0f/255;	
			break;
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 4; j < 8; j++) {
				board[i][j] = shape.getChar(i, j - 4);
				if (board[i][j] == '1')
					myShape.add(new Vector2(i,j));
			}
		}
		myShape.add(new Vector2(0,4)); //simboliza o ponto 0,0 da matriz 4x4 da shape
	}

	public boolean input(String command) {	
		newCoords = new ArrayList<Vector2>(5);
		switch (command.toLowerCase()){
		case "s": 
			for (int i = 0; i < myShape.size(); i++)
				newCoords.add( new Vector2(myShape.get(i).x + 1, myShape.get(i).y));
			break;
		case "a": 
			for (int i = 0; i < myShape.size(); i++)
				newCoords.add( new Vector2(myShape.get(i).x, myShape.get(i).y - 1));
			break;
		case "d": 
			for (int i = 0; i < myShape.size(); i++)
				newCoords.add( new Vector2(myShape.get(i).x, myShape.get(i).y + 1));
			break;
		}
		if ( pieceOnGoing.equals("-") )
			return false;
		//verificar novas coordenadas
		if ( checkCoords(command) )
			insert();
		return true;
	}

	private boolean checkCoords(String command) {
		//int max = 0;
		/*for (int i = 0; i < 4; i++) {
			if (newCoords.get(i).x > max)
				max = (int) newCoords.get(i).x;
		}*/
		for (int i = 0; i < 4; i++) {
			if ( 	(board[(int) newCoords.get(i).x][(int) newCoords.get(i).y] == 'J'|| 
					board[(int) newCoords.get(i).x][(int) newCoords.get(i).y] == 'L'|| 
					board[(int) newCoords.get(i).x][(int) newCoords.get(i).y] == 'I'|| 
					board[(int) newCoords.get(i).x][(int) newCoords.get(i).y] == 'T'|| 
					board[(int) newCoords.get(i).x][(int) newCoords.get(i).y] == 'O'|| 
					board[(int) newCoords.get(i).x][(int) newCoords.get(i).y] == 'S'|| 
					board[(int) newCoords.get(i).x][(int) newCoords.get(i).y] == 'Z'||
					newCoords.get(i).x == 23) &&
					command.toLowerCase().equals("s"))// ||
//					newCoords.get(i).x == 23) && newCoords.get(i).x == max)
				{
					//encontrou torre... adiciona a torre
					addToTower();
					return false;
				}

			if ( board[(int) newCoords.get(i).x][(int) newCoords.get(i).y] != ' ' && board[(int) newCoords.get(i).x][(int) newCoords.get(i).y] != '1' )
				return false;			
		}
		return true;
	}

	private void addToTower() {
		for (int i = 0; i < 4; i++) 
			board[(int) myShape.get(i).x][(int) myShape.get(i).y] = shape.getPieceShape().getLetter().charAt(0);

		//printBoard();
		pieceOnGoing = "-";
	}

	private void insert() {
		
		//printBoard();
		
		for (int i = 0; i < 4; i++) {
			board[(int) myShape.get(i).x][(int) myShape.get(i).y] = ' ';
		}
		
		for (int i = 0; i < 4; i++) {
			//System.out.println(myShape.get(i).x + "|" + myShape.get(i).y);
			board[(int) newCoords.get(i).x][(int) newCoords.get(i).y] = '1';
		}
		
		myShape = new ArrayList<Vector2>(5);
		
		for (int i = 0; i < 5; i++) {
			myShape.add(new Vector2(newCoords.get(i).x, newCoords.get(i).y));
		}
	}

	public void checkRows() {
		boolean clean = true;
		boolean checkTop = false;
		
		for (int i = 3; i < rows - 2; i++) {
			for (int j = 1; j < cols - 1; j++) {
				if ( 	board[i][j] != 'I' &&
						board[i][j] != 'J' &&
						board[i][j] != 'L' &&
						board[i][j] != 'O' &&
						board[i][j] != 'T' &&
						board[i][j] != 'S' &&
						board[i][j] != 'Z')
					clean = false;				
				if ( 	i == 3 &&
						(board[i][j] == 'I' ||
						board[i][j] == 'J' ||
						board[i][j] == 'L' ||
						board[i][j] == 'O' ||
						board[i][j] == 'T' ||
						board[i][j] == 'S' ||
						board[i][j] == 'Z'))
					checkTop = true;
				
			}
			if ( clean )
				cleanRow(i);
			else if ( checkTop )
				handleGameOver();
			clean = true;
		}		
	}
	
	private void cleanRow(int row) {
		for (int i = row; i > 3; i--) {
			for (int j = 1; j < cols - 1; j++) {
				board[i][j] = board[i-1][j];
			}
		}	
		TetrisLogic.addScore();
	}

	public void printBoard(){
		for (int i = 0; i < rows; i++) {
			System.out.println(board[i]);
		}
	}

	public void rotate() {
		newCoords = new ArrayList<Vector2>(5);
		newCoords = shape.rotate(myShape);
		
		if ( checkCoords("s") )
			for (int i = 0; i < 4; i++) {
				board[(int) myShape.get(i).x][(int) myShape.get(i).y] = ' ';
				myShape.set(i, new Vector2(newCoords.get(i).x,newCoords.get(i).y));
				board[(int) myShape.get(i).x][(int) myShape.get(i).y] = '1';
			}
	}
	
	public void handleGameOver() {
		gameover  = true;
		System.out.println("Game Over");
	}

	public boolean isGameOver() {
		return gameover;
	}


}
