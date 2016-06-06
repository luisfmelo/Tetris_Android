package com.luis.teresa.tetris.logic;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;

/**
 * This class is a piece. 
 * One piece consists of 4 blocks and has a color and a shape
 * @author Luis
 * @author Teresa
 */
public class Shape {
	 public enum Type { 
		 NoShape (new char [][] {
			 {' ' ,' ' ,' ' ,' '},
			 {' ' ,' ' ,' ' ,' '},
			 {' ' ,' ' ,' ' ,' '},
			 {' ' ,' ' ,' ' ,' '}
		 }, "black"), 
		 I(new char [][] {
	 		{' ','1',' ',' '},
			{' ','1',' ',' '},
			{' ','1',' ',' '},
			{' ','1',' ',' '}
		 }, "lightblue"),
		 S( new char [][] {
		 		{' ',' ',' ',' '},
				{' ','1','1',' '},
				{'1','1',' ',' '},
				{' ',' ',' ',' '}
				 }, "green"),  
		 Z(new char [][] {
	 		{' ',' ',' ',' '},
			{' ','1','1',' '},
			{' ',' ','1','1'},
			{' ',' ',' ',' '}
		 }, "red"), 
         T(new char [][] {
	 		{' ',' ',' ',' '},
			{'1','1','1',' '},
			{' ','1',' ',' '},
			{' ',' ',' ',' '}
         }, "purple"), 
         O(new char [][] {
	 		{' ',' ',' ',' '},
			{' ','1','1',' '},
			{' ','1','1',' '},
			{' ',' ',' ',' '}
		 }, "yellow"),  	
         L(new char [][] {
		 		{' ','1',' ',' '},
				{' ','1',' ',' '},
				{' ','1','1',' '},
				{' ',' ',' ',' '}
				 }, "orange"), 
         J (new char [][] {
	 		{' ',' ','1',' '},
			{' ',' ','1',' '},
			{' ','1','1',' '},
			{' ',' ',' ',' '}
         }, "darkblue");
		 private char[][] pattern;
		 private String color;
         
		 /**
		  * Constructor of a Type
		  * @param m	pattern of that type
		  * @param c	color of that type
		  */
		 private Type(char [][] m, String c){
        	 this.pattern = m;
        	 this.color = c;
		 }
         
		 /**
		  * Get the letter which represents the type
		  * @return		String of the letter
		  */
		 public String getLetter(){
        	 return this.name();
         }
         
		 /**
		  * Get the color of that type
		  * @return		String of the color e question
		  */
		 public String getColor(){
        	 return this.color;
         }
         
		 /**
		  * Get the pattern of that type
		  * @return		2D array of char for the pattern
		  */
		 public char[][] getPattern(){
        	 return this.pattern;
         }
         
	 };
         
     private Type pieceShape = Type.NoShape;
     private int size=4;
     private Block[][] matrix_block;
     private int x_world, y_world, new_x, new_y;
     
     /**
      * Constructor for Random Shape
     */
     public Shape() {
         setRandomShape();
         x_world=0;
         y_world=4;
         new_x=0;
         new_y=4;
     }
     
     /**
     	* Constructor for specified Shape
     	* @param t		Type of the desired shape
      */
     public Shape(Type t) {
         setPieceShape(t);
         x_world=0;
         y_world=4;
         new_x=0;
         new_y=4;
     }
     
     /**
      * Update position of the shape
      */
	public void update_pos(){
		x_world=new_x;
		y_world=new_y;
		
	}
   
	/**
	 * Gets the matrix of blocks of the shape
	 * @return	matrix_block	Array of Blocks
	 */
	public Block[][] getMatrix_Block() {
		return matrix_block;
	}

	/**
	 * Sets the matrix of blocks of the shape
	 * @param	matrix	Array of Blocks to be setted
	 */
	public void setMatrix_Block(Block[][] matrix) {
		this.matrix_block = matrix;
	}

	/**
	 * Gets the type of the shape
	 * @return pieceShape
	 */
	public Type getPieceShape() {
		return pieceShape;
	}
	
	/**
	 * Sets the shape Type
	 * @param pieceShape
	 */
	public void setPieceShape(Type pieceShape) {
		this.pieceShape = pieceShape; 
		char [][] pattern = pieceShape.pattern;
		//setMatrix(pattern);
		matrix_block = new Block [size][size]; 
		for (int i = 0; i <pattern.length; i++) {
            for (int j = 0; j < pattern.length; j++) {
                if(pattern[i][j]=='1')
                	matrix_block[i][j]= new Block(this,i,j,this.pieceShape.color);
                else
                	matrix_block[i][j]= new Block(this,i,j,"black");
            }
        }
	}
    
	/**
	 * Sets a random Type to the shape
	 */
	public void setRandomShape() {
		Random r = new Random();
        Type[] values = Type.values(); 
        int index = Math.abs(r.nextInt()) %  (values.length - 1) + 1;
        
        setPieceShape(values[index]);		
	}
	
	/**
	 * Swaps the rows of the shape's matrix 
	 */
	public  void swapRows() {
		Block[] x;
		
		if(matrix_block==null)
			return;
	  
		for (int  i = 0, k = size - 1; i < k; ++i, --k) {
			x = matrix_block[i];
	        matrix_block[i] = matrix_block[k];
	        matrix_block[k] = x;
	    }
		update_block_pos();
	}
	
	/**
	 * Transpose the shape's matrix
	 */
	private void transpose() {
		Block x;
		
		if(matrix_block==null)
			return;
        
		for (int i = 0; i < matrix_block.length; i++) {
            for (int j = i; j < matrix_block[0].length; j++) {
                x = matrix_block[i][j];
                matrix_block[i][j] = matrix_block[j][i];
                matrix_block[j][i] = x;
            }
        }
		update_block_pos();
    }
	
	/**
	 * Performs a shape rotation to the left
	 */
	public void rotateLeft() 
    {
        if (this.pieceShape == Type.O)
            return;        
        transpose();
        swapRows();
    }

	/**
	 * Performs a shape rotation to the right
	 */
    public void rotateRight()
    {
    	if (this.pieceShape == Type.O)
            return;
    	
          swapRows();
    	  transpose();
    }
    
    /**
     * Gets the Block in the given coordinates
     * @param i		x Coordinate	
     * @param j		y Coordinate
     * @return	matrix_block[i][j]
     */
	public Block getBlock(int i, int j) {
		return matrix_block[i][j];
	}

	/**
	 * Performs a rotation to the right changing the filled blocks position
	 * @param v		vector with the current coordinates of the blocks
	 * @return		new vector with new coordinates after rotation
	 */
	public ArrayList<Vector2> rotate(ArrayList<Vector2> v) {
		ArrayList<Vector2> myVec = new ArrayList<Vector2>(5);

		this.rotateRight();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if ( !matrix_block[i][j].getColor().equals("black"))
					myVec.add( new Vector2( i + v.get(4).x  ,  j + v.get(4).y ) );

			}
		}
		myVec.add( new Vector2(v.get(4).x,v.get(4 ).y ) );
		
		return myVec;
	}
   
	/**
	 * Updates the position of the blocks of the matrix
	 */
    public void update_block_pos(){
    	for (int i=0; i< size; i++)
    		for(int j=0; j<size;j++){
    			matrix_block[i][j].setX_shape(i);
    			matrix_block[i][j].setY_shape(j);
    			matrix_block[i][j].setX_global(i + getX_world());;
    			matrix_block[i][j].setY_global(j + getY_world());
    		}
    }

	/**
	 * Sets the future position of the shape
	 * @param x		x coordinate
	 * @param y		y coordinate
	 */
	public void set_newPos(int x,int y){
		new_x=x;
		new_y=y;
	}

	/**
	 * Gets future Y coordinate 
	 * @return new_y
	 */
	public int getNew_y() {
		return new_y;
	}
 /**
  * gets the future X coordinate
  * @return new_x
  */
	public int getNew_x() {
		return new_x;
	}
/**
 * Gets the y coordinate of the shape on the board reference
 * @return y_world
 */
	public int getY_world() {
		return y_world;
	}

	/**
	 * Sets the y coordinate of the shape on the board reference
	 * @param y_world
	 */
	public void setY_world(int y_world) {
		this.y_world = y_world;
	}

	/**
	 * Gets the x coordinate of the shape on the board reference
	 * @return x_world
	 */
	public int getX_world() {
		return x_world;
	}

	/**
	 * Sets the x coordinate of the shape on the board reference
	 * @param x_world
	 */
	public void setX_world(int x_world) {
		this.x_world = x_world;
	}
    
	/**
	 * Gets the character matrix of the shape
	 * @return	matrix of chars
	 */
	public char [][] getShapeMatrix(){
    	char matrix[][]= new char[size][size];
    	
    	for(int i=0;i<size;i++){
    		for(int j=0;j<size;j++)
    			if(!matrix_block[i][j].getColor().equals("black"))
    				matrix[i][j]='1';
    			else
    				matrix[i][j]=' ';
    	}
    	return matrix;
    }
}