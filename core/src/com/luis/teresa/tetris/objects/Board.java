package com.luis.teresa.tetris.objects;

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
	
	public Board() {
		board = new char[rows][cols];
	}
	
	public void initializeBoard(){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if ( i == 2 || i == 23 || i == 24 || j == 0 || j == 11 )
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
		myShape = new ArrayList<Vector2>(4);
		this.shape = shape;
		pieceOnGoing = ":";
		
		switch (shape.getPieceShape().getLetter()) {
		case "I": 
			Const.redColor = (float) 0/255;
			Const.greenColor = (float) 240/255;
			Const.blueColor = (float) 240/255;
			break;
		case "S":
			Const.redColor = (float) 0/255;
			Const.greenColor = (float) 240/255;
			Const.blueColor = (float) 0/255;
			break;
		case "Z":
			Const.redColor = (float) 240/255;
			Const.greenColor = (float) 0/255;
			Const.blueColor = (float) 0/255;
			break;
		case "T":
			Const.redColor = (float) 150/255;
			Const.greenColor = (float) 0/255;
			Const.blueColor = (float) 240/255;
			break;
		case "O":
			Const.redColor = (float) 240/255;
			Const.greenColor = (float) 240/255;
			Const.blueColor = (float) 0/255;
			break;
		case "L":
			Const.redColor = (float) 240/255;
			Const.greenColor = (float) 150/255;
			Const.blueColor = (float) 0/255;
			break;
		case "J":
			Const.redColor = (float) 0/255;
			Const.greenColor = (float) 0/255;
			Const.blueColor = (float) 240/255;			
			break;

		default:
			Const.redColor = (float) 0/255;
			Const.greenColor = (float) 0/255;
			Const.blueColor = (float) 0/255;	
			break;
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 4; j < 8; j++) {
				board[i][j] = shape.getChar(i, j - 4);
				if (board[i][j] == '1')
					myShape.add(new Vector2(i,j));
			}
		}
	}

	public boolean input(String command) {	
		newCoords = new ArrayList<Vector2>(4);

		switch (command.toLowerCase()){
		case "s": 
			for (int i = 0; i < 4; i++)
				newCoords.add( new Vector2(myShape.get(i).x + 1, myShape.get(i).y));
			break;
		case "a": 
			for (int i = 0; i < 4; i++)
				newCoords.add( new Vector2(myShape.get(i).x, myShape.get(i).y - 1));
			break;
		case "d": 
			for (int i = 0; i < 4; i++)
				newCoords.add( new Vector2(myShape.get(i).x, myShape.get(i).y + 1));
			break;
		case "w": 
			for (int i = 0; i < 4; i++)
				newCoords.add( new Vector2(myShape.get(i).x - 1, myShape.get(i).y));
			break;
		}
		
		//verificar novas coordenadas
		if ( checkCoords() )
			insert();
		if ( pieceOnGoing == "-" )
			return false;
		return true;
	}

	private boolean checkCoords() {
		for (Vector2 point : newCoords) {
			if ( 	board[(int) point.x][(int) point.y] == 'J'|| 
					board[(int) point.x][(int) point.y] == 'L'|| 
					board[(int) point.x][(int) point.y] == 'I'|| 
					board[(int) point.x][(int) point.y] == 'T'|| 
					board[(int) point.x][(int) point.y] == 'O'|| 
					board[(int) point.x][(int) point.y] == 'S'|| 
					board[(int) point.x][(int) point.y] == 'Z' ||
					point.x == 23)
				{
					//encontrou torre... adiciona a torre
					addToTower();
					return false;
				}

			if ( board[(int) point.x][(int) point.y] != ' ' && board[(int) point.x][(int) point.y] != '1' )
				return false;			
		}
		return true;
	}

	private void addToTower() {
		for (Vector2 point : myShape) {		
			board[(int) point.x][(int) point.y] = shape.getPieceShape().getLetter().charAt(0);
		}
		//printBoard();
		pieceOnGoing = "-";
		
	}

	private void insert() {
		
		//printBoard();
		
		for (int i = 0; i < 4; i++) {
			board[(int) myShape.get(i).x][(int) myShape.get(i).y] = ' ';
		}
		
		for (int i = 0; i < 4; i++) {
			board[(int) newCoords.get(i).x][(int) newCoords.get(i).y] = '1';
		}
		
		myShape = new ArrayList<Vector2>(4);
		
		for (int i = 0; i < 4; i++) {
			myShape.add(new Vector2(newCoords.get(i).x, newCoords.get(i).y));
		}
	}

	public void clearRow() {
		// clear Row if possible
		
	}
	
	public void printBoard(){
		for (int i = 0; i < rows; i++) {
			System.out.println(board[i]);
		}
	}

	public void rotate() {
		// TODO Auto-generated method stub
		
	}


}
