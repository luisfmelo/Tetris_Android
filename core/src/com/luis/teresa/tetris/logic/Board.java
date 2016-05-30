package com.luis.teresa.tetris.logic;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.LoadMusics;


public class Board {
	//private char[][] board_char;
	private static Block[][] board;
	private static int rows = 25;
	private static int cols = 12;
	private ArrayList<Vector2> myShape;
	ArrayList<Vector2> newCoords;
	String pieceOnGoing;
	Shape shape;
	boolean tower=false;
	
	private static boolean gameover = false;
	private LoadMusics myMusics;
	
	/**
	 * Singleton
	 */
	private static Board singleton = new Board( );
		   
	/* A private Constructor prevents any other 
	 * class from instantiating.
	 */
	 private Board() {
		board = new Block[rows][cols];
		myMusics = new LoadMusics();
	}
	 /* Static 'instance' method */
	 public static Board getInstance( ) {
		 initializeBoard();
		 gameover = false;
	 		return singleton;
	 }
	public static void initializeBoard(){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (  i >22 || j == 0 || j == 11 ){
					board[i][j] = new Block(i,j);
					board[i][j].setColor("white");
				}
				else
					board[i][j] = new Block(i,j);
			}
		}
	}
	
	public Block[][] getBoard() {
		return board;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public void newShape(Shape shape) {
		tower=false;
		myShape = new ArrayList<Vector2>(5);
		this.shape = shape;
		//pieceOnGoing = ":";
		pieceOnGoing = "-:";
			
		
		for (int i = 0; i < 4; i++) {
			for (int j = 4; j < 8; j++) {
				board[i][j] = shape.getBlock(i, j - 4);
				if (!board[i][j].getColor().equals("black"))
					myShape.add(new Vector2(i,j));
			}
		}
		myShape.add(new Vector2(0,4)); //simboliza o ponto 0,0 da matriz 4x4 da shape
	}

	public boolean input(String command) {	
		newCoords = new ArrayList<Vector2>(5);
		
		if(pieceOnGoing.equals("-:")&& shape.getX_world()>=3)
			pieceOnGoing=":";

		switch (command.toLowerCase()){
		case "s": 
			for (int i = 0; i < myShape.size(); i++)
				newCoords.add( new Vector2(myShape.get(i).x + 1, myShape.get(i).y));
			shape.set_newPos(shape.getX_world()+1,shape.getY_world());
			
			break;
		case "a": 
			for (int i = 0; i < myShape.size(); i++)
				newCoords.add( new Vector2(myShape.get(i).x, myShape.get(i).y - 1));
			shape.set_newPos(shape.getX_world(),shape.getY_world()-1);

			break;
		case "d": 
			for (int i = 0; i < myShape.size(); i++)
				newCoords.add( new Vector2(myShape.get(i).x, myShape.get(i).y + 1));
			shape.set_newPos(shape.getX_world(),shape.getY_world()+1);

			break;
		case "p": 
			 for (int i = 0; i < myShape.size(); i++)
				newCoords.add( new Vector2(myShape.get(i).x, myShape.get(i).y));
			 break;
		}
		if ( pieceOnGoing.equals("-") )
			return false;
		//verificar novas coordenadas
		if ( checkCoords(command) )
			insert();
		//else
			//checkRows();
		return true;
	}

	private boolean checkCoords(String command) {
		int livre=1;
		
		for (int i = 0; i < 4; i++) 
			if (!(board[(int) myShape.get(i).x+1][(int) myShape.get(i).y].getColor().equals("black")) && 
				  (board[(int) myShape.get(i).x+1][(int) myShape.get(i).y].getParent()!=shape)){
				livre=0;
				break;
			}
		
		for (int i = 0; i < 4; i++) {
			if ( 	((board[(int) newCoords.get(i).x][(int) newCoords.get(i).y].getParent()!=shape && 
					  board[(int) newCoords.get(i).x][(int) newCoords.get(i).y].getParent()!=null && 
					!(board[(int) newCoords.get(i).x][(int) newCoords.get(i).y].getColor().equals("black"))
					)|| 
					newCoords.get(i).x == 23) //&&
					//command.toLowerCase().equals("s"))// ||
//					newCoords.get(i).x == 23) && newCoords.get(i).x == max)
					)
				{
					//encontrou torre... adiciona a torre
					if(livre==1)
						return false;
					tower=true;
					addToTower();
					checkRows();
					return false;
				}

			if ( board[(int) newCoords.get(i).x][(int) newCoords.get(i).y].getColor().equals("white") || (board[(int) newCoords.get(i).x][(int) newCoords.get(i).y].getParent()!=shape && board[(int) newCoords.get(i).x][(int) newCoords.get(i).y].getParent()!=null) )
				return false;			
		}
		return true;
	}

	private void addToTower() {
		
		for (int i = 0; i < 4; i++) 
			board[(int) myShape.get(i).x][(int) myShape.get(i).y] = shape.getBlock((int) myShape.get(i).x - shape.getX_world(),(int) myShape.get(i).y-shape.getY_world());
		
		pieceOnGoing = "-";
	}

	private void insert() {

		//printBoard();
		for (int i = 0; i < 4; i++) {
			board[(int) myShape.get(i).x][(int) myShape.get(i).y] = new Block((int) myShape.get(i).x,(int) myShape.get(i).y);
		}
		
		for (int i = 0; i < 4; i++) {
			//System.out.println(myShape.get(i).x + "|" + myShape.get(i).y);
			board[(int) newCoords.get(i).x][(int) newCoords.get(i).y] = shape.getBlock((int)myShape.get(i).x-shape.getX_world(), (int)myShape.get(i).y-shape.getY_world());
		}
		shape.update_pos();
		
		//shape.destroy();
		myShape = new ArrayList<Vector2>(5);
		
		for (int i = 0; i < 5; i++) {
			myShape.add(new Vector2(newCoords.get(i).x, newCoords.get(i).y));
		}
	}

	public void checkRows() {
		boolean clean = true;
		boolean checkTop = false;
		ArrayList<Integer> rowsToClean = new ArrayList<>();
  		
		//percorre todas as linhas... de cima para baixo para ver se é para eliminar
				for (int i = 3; i < rows - 2; i++) {
			for (int j = 1; j < cols - 1; j++) {
				if ( 	board[i][j].getColor().equals("black"))
					clean = false;

				if ( 	i == 3 && board[i][j].getParent()!=null && !(board[i][j].getColor().equals("black")) )
					
						checkTop = true;
			}						
				
	
			if ( clean ){
				rowsToClean.add(i);
				}
			else if ( checkTop && !pieceOnGoing.equals(":-"))
				handleGameOver();
			clean = true;
		}		
				//Score & music
				if(rowsToClean.size() != 0)
				{
					myMusics.playClear(Integer.toString(rowsToClean.size())); 
					TetrisLogic.addScore(Integer.toString(rowsToClean.size()));
				}
					
				//actual cleanning
				for (Integer i : rowsToClean) {
					cleanRow(i);
				}

	}
	
	private void cleanRow(int row) {
		
		
		for (int i = row; i > 4; i--) {
			for (int j = 1; j < cols - 1; j++) {
				board[i][j] = board[i-1][j];
			}
		}	
	}
	public void printBoard(){
		
		for (int i = 0; i < rows; i++) {
			for(int j=0; j<cols ;j++){
				if(board[i][j].getColor().equals("black"))
						System.out.print(" ");
				else if(board[i][j].getColor().equals("white")) 
					System.out.print("X");
				else
					System.out.print("1");}
			System.out.println();
		}
		
		System.out.println();
		
	}

	public void rotate() {
		if(tower)
			return;
		newCoords = new ArrayList<Vector2>(5);
		newCoords = shape.rotate(myShape);
		
		if ( checkCoords("s") ){
			for (int i = 0; i < 4; i++) {
					board[(int) myShape.get(i).x][(int) myShape.get(i).y] = new Block((int)myShape.get(i).x,(int) myShape.get(i).y);
			}
			
			for (int i = 0; i < 4; i++) {				
				myShape.set(i, new Vector2(newCoords.get(i).x,newCoords.get(i).y));
				board[(int) myShape.get(i).x][(int) myShape.get(i).y] = shape.getBlock((int) myShape.get(i).x - shape.getX_world(),(int) myShape.get(i).y-shape.getY_world()); 
			}
			
		}
		else{
			shape.rotateLeft();
			if ( checkCoords("s") )
				insert();
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
