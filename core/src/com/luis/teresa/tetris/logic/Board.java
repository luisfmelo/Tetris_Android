package com.luis.teresa.tetris.logic;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;


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
	 
	public void setBoard( Block[][] matrix){
		 board=matrix;
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
		//verificar novas coordenadas
		if ( checkCoords(command) )
			insert();
		//else
			//checkRows();
		return true;
		/*}
		else
			return false;*/
	}

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
			board[(int) getMyShape().get(i).x][(int) getMyShape().get(i).y] = shape.getBlock((int) getMyShape().get(i).x - shape.getX_world(),(int) getMyShape().get(i).y-shape.getY_world());
		
		setPieceOnGoing("-");
	}

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
				handleGameOver();
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
	
	public void handleGameOver() {
		gameover  = true;
		System.out.println("Game Over");
	}

	public boolean isGameOver() {
		return gameover;
	}
	public String getPieceOnGoing() {
		return pieceOnGoing;
	}
	public void setPieceOnGoing(String pieceOnGoing) {
		this.pieceOnGoing = pieceOnGoing;
	}
	//For tests
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
	public ArrayList<Vector2> getMyShape() {
		return myShape;
	}
	
}
