package com.luis.teresa.tetris.objects;

public class Board {
	private char[][] board;
	private int rows = 23;
	private int cols = 12;
	
	public Board() {
		board = new char[23][12];
	}
	
	public void initializeBoard(){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if ( i == 0 || i == 21 || i == 22 || j == 0 || j == 11 )
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
}
