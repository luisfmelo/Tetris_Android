package com.luis.teresa.tetris.logic;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

/**
 * Singleton class which represents the game board. 
 * Each boar is composed of several blocks and can contain shapes
 * @author Luis
 * @author Teresa
 */
public class Board {
	private  static Block[][] board;
	private static int rows = 25;
	private static int cols = 12;
	private ArrayList<Vector2> myShape;
	ArrayList<Vector2> newCoords;
	private String pieceOnGoing;
	Shape shape;
	boolean tower=false;
	
	private static boolean gameover = false;
	 
	
	/**
	 * Singleton 
	 */
	private static Board singleton = new Board( );
		   
	/* A private Constructor prevents any other 
	 * class from instantiating.
	 */
	 private Board() {
		board = new Block[rows][cols];
	}
	 /* Static 'instance' method */
	 public static Board getInstance( ) {
		 initializeBoard();
		 gameover = false;
	 	 return singleton;
	 }
	 
	 /**
	  * Sets the matrix of Blocks that represent the board
	  * @param matrix		matrix of blocks
	 */
	public void setBoard( Block[][] matrix){
		 board=matrix;
	}
	
	/**
	 * Initializes the Board by creating the necessary Blocks 
	 */
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
	
	/**
	 * Gets the matrix of blocks of the Board
	 * @return board		matrix of Blocks
	 */
	public Block[][] getBoard() {
		return board;
	}

	/**
	 * Get the number of rows that compose the Board
	 * @return rows
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * Get the number of columns  that compose the Board
	 * @return cols 	number of columns 
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Inserts a new Shape on the Board
	 * @param shape		the shape to be inserted
	 */
	public void newShape(Shape shape) {
		tower=false;
		myShape=new ArrayList<Vector2>(5);
		this.shape = shape;
		//pieceOnGoing = ":";
		setPieceOnGoing("-:");
			
		
		for (int i = 0; i < 4; i++) {
			for (int j = 4; j < 8; j++) {
				board[i][j] = shape.getBlock(i, j - 4);
				if (!board[i][j].getColor().equals("black"))
					getMyShape().add(new Vector2(i,j));
			}
		}
		getMyShape().add(new Vector2(0,4)); //simboliza o ponto 0,0 da matriz 4x4 da shape
	}

	/**
	 * Creates an array with the desired future coordinates for a shape
	 * given by a certain command
	 * @param command		string with the command inputed by the user
	 * @return true if no error occurs
	 */
	public boolean input(String command) {	
		newCoords = new ArrayList<Vector2>(5);
		
		if(getPieceOnGoing().equals("-:")&& shape.getX_world()>=3)
			setPieceOnGoing(":");

		switch (command.toLowerCase()){
		case "s": 
			for (int i = 0; i < getMyShape().size(); i++)
				newCoords.add( new Vector2(getMyShape().get(i).x + 1, getMyShape().get(i).y));
			shape.set_newPos(shape.getX_world()+1,shape.getY_world());
			
			break;
		case "a": 
			for (int i = 0; i < getMyShape().size(); i++)
				newCoords.add( new Vector2(getMyShape().get(i).x, getMyShape().get(i).y - 1));
			shape.set_newPos(shape.getX_world(),shape.getY_world()-1);

			break;
		case "d": 
			for (int i = 0; i < getMyShape().size(); i++)
				newCoords.add( new Vector2(getMyShape().get(i).x, getMyShape().get(i).y + 1));
			shape.set_newPos(shape.getX_world(),shape.getY_world()+1);

			break;
		default: 
			 for (int i = 0; i < getMyShape().size(); i++)
				newCoords.add( new Vector2(getMyShape().get(i).x, getMyShape().get(i).y));
			 break;
		}
		if ( getPieceOnGoing().equals("-") )
			return false;

		if ( checkCoords(command) )
			insert();
		
		return true;
		
	}

	/**
	 * Checks if the present shape future 
	 * coordinates of a given command are valid and can be reached
	 * @param command		string with the command given
	 * @return	true if the future coordinates can be reache
	 */
	private boolean checkCoords(String command) {
		int livre=1;
		
		for (int i = 0; i < 4; i++) 
			if (!(board[(int) getMyShape().get(i).x+1][(int) getMyShape().get(i).y].getColor().equals("black")) && 
				  (board[(int) getMyShape().get(i).x+1][(int) getMyShape().get(i).y].getParent()!=shape)){
				livre=0;
				break;
			}
		
		for (int i = 0; i < 4; i++) {
			if ( 	((board[(int) newCoords.get(i).x][(int) newCoords.get(i).y].getParent()!=shape && 
					  board[(int) newCoords.get(i).x][(int) newCoords.get(i).y].getParent()!=null && 
					!(board[(int) newCoords.get(i).x][(int) newCoords.get(i).y].getColor().equals("black"))
					)|| 
					newCoords.get(i).x == 23) 
					)
				{
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

	/**
	 * Adds the present shape to a tower of shapes when it can't reach the floor
	 */
	private void addToTower() {
		
		for (int i = 0; i < 4; i++) 
			board[(int) getMyShape().get(i).x][(int) getMyShape().get(i).y] = shape.getBlock((int) getMyShape().get(i).x - shape.getX_world(),(int) getMyShape().get(i).y-shape.getY_world());
		
		setPieceOnGoing("-");
	}

	/**
	 * Makes a shape movement by changing its coordinates in the board
	 * @param command	string of the command called to move the shape 
	 * @return	true if the input is successfully 
	 */
	private void insert() {

		//printBoard();
		for (int i = 0; i < 4; i++) {
			board[(int) getMyShape().get(i).x][(int) getMyShape().get(i).y] = new Block((int) getMyShape().get(i).x,(int) getMyShape().get(i).y);
		}
		
		for (int i = 0; i < 4; i++) 
			board[(int) newCoords.get(i).x][(int) newCoords.get(i).y] = shape.getBlock((int)getMyShape().get(i).x-shape.getX_world(), (int)getMyShape().get(i).y-shape.getY_world());
		
		shape.update_pos();
	
		myShape=new ArrayList<Vector2>(5);
		
		for (int i = 0; i < 5; i++) {
			getMyShape().add(new Vector2(newCoords.get(i).x, newCoords.get(i).y));
		}
	}

	/**
	 * Checks if there are rows to deleted or a shape reached the top
	 */
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
			else if ( checkTop && !getPieceOnGoing().equals(":-"))
				gameover=true;
			clean = true;
		}		
				//Score & music
				if(rowsToClean.size() != 0)
				{	
						TetrisLogic.addScore(Integer.toString(rowsToClean.size()));
						TetrisLogic.setClear(rowsToClean.size());
					
				}
					
				//actual cleanning
				for (Integer i : rowsToClean) {
					cleanRow(i);
				}

	}
	
	/**
	 * Cleans a row
	 * @param row	row to delete
	 */
	private void cleanRow(int row) {
		
		
		for (int i = row; i > 4; i--) {
			for (int j = 1; j < cols - 1; j++) {
				board[i][j] = board[i-1][j];
			}
		}	
		
	}
	/*public void printBoard(){
		
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
		
	}*/

	/**
	 * Rotates the present shape to the right if possible
	 * @return		true if it is possible to rotate, false if it isn't
	 */
	public boolean rotate() {
		if(tower)
			return false;
		newCoords = new ArrayList<Vector2>(5);
		newCoords = shape.rotate(getMyShape());
		
		if ( checkCoords("s") ){
			for (int i = 0; i < 4; i++) {
					board[(int) getMyShape().get(i).x][(int) getMyShape().get(i).y] = new Block((int)getMyShape().get(i).x,(int) getMyShape().get(i).y);
			}
			
			for (int i = 0; i < 4; i++) {				
				getMyShape().set(i, new Vector2(newCoords.get(i).x,newCoords.get(i).y));
				board[(int) getMyShape().get(i).x][(int) getMyShape().get(i).y] = shape.getBlock((int) getMyShape().get(i).x - shape.getX_world(),(int) getMyShape().get(i).y-shape.getY_world()); 
			}
			return true;
			
		}
		else{
			shape.rotateLeft();
			if ( checkCoords("s") )
				insert();
			return false;
		}
	}
	
	/**
	 * check if the game already ended
	 * @return gameover		true if the game ended, false otherwise
	 */

	public boolean isGameOver() {
		return gameover;
	}
	
	/**
	 * Gets the state of the shape on board
	 * @return	pieceOnGoin		state of the present shape (if it is going down or entering the board)
	 */
	public String getPieceOnGoing() {
		return pieceOnGoing;
	}
	/**
	 * Handles the gameOver
	 */
	public void handleGameOver(){
		gameover=true;
	}
	/**
	 * Sets the state of the present shape
	 * @param pieceOnGoing
	 */
	public void setPieceOnGoing(String pieceOnGoing) {
		this.pieceOnGoing = pieceOnGoing;
	}

	/**
	 * Sets the present shape to a given position
	 * @param x		X coordinate
	 * @param y		Y coordinate
	 */
	public void setShapePos(int x, int y){
		int cont=0;
		for (int i = 0; i < 4; i++) 
			for(int j=0; j<4;j++){
				if(board[x+i][y+j].getColor().equals("white") || (board[x+i][y+j].getParent()!=null&& !board[x+i][y+j].getColor().equals("black")  ))
					continue;
				if(!shape.getBlock(i,j).getColor().equals("black")){
					getMyShape().set(cont,new Vector2(x+i,y+j) );
					cont++;
				}
				board[x+i][y+j] = shape.getBlock(i,j); 
			}
		getMyShape().set(4,new Vector2(x,y));
		shape.setX_world(x);
		shape.setY_world(y);
	}
	
	/**
	 * Gets the array with the coordinates of the shape blocks
	 * @return	myShape		coordinates of the shape blocks with filled cells
	 */
	public ArrayList<Vector2> getMyShape() {
		return myShape;
	}
	
}
