package com.luis.teresa.tetris.logic;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;


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
         private Type(char [][] m, String c){
        	 this.pattern = m;
        	 this.color =c;
		 }
         public String getLetter(){
        	 return this.name();
         }
         public String getColor(){
        	 return this.color;
         }
         
	 	};
         
     private char[][] matrix;
     private Type pieceShape = Type.NoShape;
     private int size=4;
     private Block[][] matrix_block;
   //  private ArrayList <Block<T>> Blocks;
     private int x_world, y_world,new_x,new_y;
     
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
     	* @param s
      */
     public Shape(Type t) {
         setPieceShape(t);
	     x_world=0;
	     y_world=4;
	     new_x=0;
	     new_y=4;
     }
	public void update_pos(){
		x_world=new_x;
		y_world=new_y;
		
	}
   
	public Block[][] getMatrix_Block() {
		return matrix_block;
	}

	public void setMatrix_Block(Block[][] matrix) {
		this.matrix_block = matrix;
	}

	public Type getPieceShape() {
		return pieceShape;
	}
	/*private void updateBlocks(){
		for (int i = 0; i <matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j]=='1')
                	Blocks.add(new Block(this,i,j,this.pieceShape.color));
                else
                	Blocks.add(new Block(this,i,j,"black"));
            }
        }
	}*/
	public void setPieceShape(Type pieceShape) {
		this.pieceShape = pieceShape; 
		char [][] pattern = pieceShape.pattern;
		//setMatrix(pattern);
		matrix_block = new Block [size][size]; //ou array list?
		for (int i = 0; i <pattern.length; i++) {
            for (int j = 0; j < pattern.length; j++) {
                if(pattern[i][j]=='1')
                	matrix_block[i][j]= new Block(this,i,j,this.pieceShape.color);
                else
                	matrix_block[i][j]= new Block(this,i,j,"black");
            }
        }
		//this.Blocks = new ArrayList <Block>();
		
		/*for (int i = 0; i <matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j]=='1')
                	Blocks.add(new Block(this,i,j,this.pieceShape.color));
                else
                	Blocks.add(new Block(this,i,j,"black"));
            }
        }*/
			}
    
	public void setRandomShape() {
		Random r = new Random();
        Type[] values = Type.values(); 
       
        //+1 para n�o contar com o NoShape
        int index = Math.abs(r.nextInt()) %  (values.length-1) + 1;
        
        setPieceShape(values[index]);		
	}
	
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
	
	private void transpose() {
		Block x;
		
		if(matrix_block==null)
			return;
        
		for (int i = 0; i < matrix_block.length; i++) {
            for (int j = i; j < matrix_block[0].length; j++) {
                x= matrix_block[i][j];
                matrix_block[i][j] = matrix_block[j][i];
                matrix_block[j][i] = x;
            }
        }
		update_block_pos();
    }
	
	public void rotateLeft() 
    {
        if (pieceShape == Type.O)
            return;
        
        transpose();
        swapRows();

        
    }

    public void rotateRight()
    {
    	if (pieceShape == Type.O)
            return;
    	
          swapRows();
    	  transpose();

    }
    
    public void print(){
    	
    	for(int i=0; i<size;i++){
    		for(int j=0;j<size;j++)
    			System.out.print(matrix[i][j]);
			System.out.println("");

    	}
		System.out.println("");

    }

	public Block getBlock(int i, int j) {
		return matrix_block[i][j];
	}

	public ArrayList<Vector2> rotate(ArrayList<Vector2> v) {
		ArrayList<Vector2> myVec = new ArrayList<Vector2>(5);

		this.rotateRight();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if ( !matrix_block[i][j].getColor().equals("black"))
					myVec.add( new Vector2( i + v.get(4).x  ,  j + v.get(4).y ) );
					//System.out.println("%" + v.get(i).x + "|" + v.get(i).y );
			}
		}
		myVec.add( new Vector2(v.get(4).x,v.get(4 ).y ) );
		
		return myVec;
	}

	public int getX_world() {
		return x_world;
	}
	public void set_newPos(int x,int y){
		new_x=x;
		new_y=y;
	}
	public void setX_world(int x_world) {
		this.x_world = x_world;
	}

	public int getY_world() {
		return y_world;
	}

	public void setY_world(int y_world) {
		this.y_world = y_world;
	}
     
    public void destroy(){
    	for (int i=0; i<size;i++)
    		for(int j=0; j<size;j++)
    			matrix_block[i][j].reachedFloor();
    }
    
    public void update_block_pos(){
    	for (int i=0; i< size; i++)
    		for(int j=0; j<size;j++){
    			matrix_block[i][j].setX_shape(i);
    			matrix_block[i][j].setY_shape(j);
    			matrix_block[i][j].setX_global(i + x_world);;
    			matrix_block[i][j].setY_global(j + y_world);
    		}
    }
    
}
