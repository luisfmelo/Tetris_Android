package com.luis.teresa.tetris.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class Shape {

	 enum Type { 
		 NoShape (new char [][] {
			 {' ' ,' ' ,' ' ,' '},
			 {' ' ,' ' ,' ' ,' '},
			 {' ' ,' ' ,' ' ,' '},
			 {' ' ,' ' ,' ' ,' '}
		 }), 
		 I(new char [][] {
	 		{' ','1',' ',' '},
			{' ','1',' ',' '},
			{' ','1',' ',' '},
			{' ','1',' ',' '}
		 }),
		 S( new char [][] {
		 		{' ',' ',' ',' '},
				{' ','1','1',' '},
				{'1','1',' ',' '},
				{' ',' ',' ',' '}
				 }),  
		 Z(new char [][] {
	 		{' ',' ',' ',' '},
			{' ','1','1',' '},
			{' ',' ','1','1'},
			{' ',' ',' ',' '}
		 }), 
         T(new char [][] {
	 		{' ',' ',' ',' '},
			{'1','1','1',' '},
			{' ','1',' ',' '},
			{' ',' ',' ',' '}
         }), 
         O(new char [][] {
	 		{' ',' ',' ',' '},
			{' ','1','1',' '},
			{' ','1','1',' '},
			{' ',' ',' ',' '}
		 }),  	
         L(new char [][] {
		 		{' ','1',' ',' '},
				{' ','1',' ',' '},
				{' ','1','1',' '},
				{' ',' ',' ',' '}
				 }), 
         J (new char [][] {
	 		{' ',' ','1',' '},
			{' ',' ','1',' '},
			{' ','1','1',' '},
			{' ',' ',' ',' '}
         });
		 private char[][] pattern;
         private Type(char [][] m){
        	 this.pattern = m;
		 }
         public String getLetter(){
        	 return this.name();
         }
	 	};
         
     private char matrix[][];
     private Type pieceShape = Type.NoShape;
     private int size=4;
     //private int pattern[][][];
     
     public Shape() {
         setRandomShape();
     }

	public char[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(char matrix[][]) {
		this.matrix = matrix;
	}

	public Type getPieceShape() {
		return pieceShape;
	}

	public void setPieceShape(Type pieceShape) {
		this.pieceShape = pieceShape; 
		char [][] pattern = pieceShape.pattern;
		setMatrix(pattern);
	}
    
	public void setRandomShape() {
		Random r = new Random();
        Type[] values = Type.values(); 
       
        //+1 para não contar com o NoShape
        int index = Math.abs(r.nextInt()) %  (values.length-1) + 1;
        
        setPieceShape(values[index]);		
	}
	
	public  void swapRows() {
		char[] x;
		
		if(matrix==null)
			return;
	    
		for (int  i = 0, k = size - 1; i < k; ++i, --k) {
	        x = matrix[i];
	        matrix[i] = matrix[k];
	        matrix[k] = x;
	    }
		
	}
	private void transpose() {
		char x;
		
		if(matrix==null)
			return;
        
		for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                x= matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = x;
            }
        }
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

	public char getChar(int i, int j) {
		return matrix[i][j];
	}

	/*public ArrayList<Vector2> rotate(ArrayList<Vector2> v) {
		ArrayList<Vector2> myVec = new ArrayList<Vector2>(4);
		this.print();
		this.rotateRight();
		this.print();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if ( this.matrix[i][j] == '1')
					myVec.add( new Vector2(i+v.get(i).x,j+v.get(i).y  ) );
			}
		}
		
		return myVec;
	}*/
     
     
}
