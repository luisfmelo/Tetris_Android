package com.luis.teresa.tetris.objects;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Shape {

	 enum Type { 
		 NoShape (new int [][] {
			 {0,0,0,0},
			 {0,0,0,0},
			 {0,0,0,0},
			 {0,0,0,0}
		 }) 
		 , 
		 I(new int [][] {
					 {0,1,0,0},
					 {0,1,0,0},
					 {0,1,0,0},
					 {0,1,0,0}
				 }
			 ),
		 S( new int [][] {
					 {0,0,0,0},
					 {0,1,1,0},
					 {1,1,0,0},
					 {0,0,0,0}
				 }),  
		 Z(new int [][] {
					 {0,0,0,0},
					 {0,1,1,0},
					 {0,0,1,1},
					 {0,0,0,0}
			 }
		 ), 
         T(new int [][] {
					 {0,0,0,0},
					 {1,1,1,0},
					 {0,1,0,0},
					 {0,0,0,0}
				 
			 }),
		   
         O(new int [][] {
					 {0,0,0,0},
					 {0,1,1,0},
					 {0,1,1,0},
					 {0,0,0,0}
				 }
			 ),  
         L(new int [][] {
					 {0,1,0,0},
					 {0,1,0,0},
					 {0,1,1,0},
					 {0,0,0,0}
				 }
			 ),  
         J (new int [][] {
					 {0,0,1,0},
					 {0,0,1,0},
					 {0,1,1,0},
					 {0,0,0,0}
			 }
		 );
		 
		 private int [][] pattern;
         private Type(int [][] m){
        	 this.pattern = m;
			 
		 }
	 	};
         
     private int matrix[][];
     private Type pieceShape = Type.NoShape;
     private int size=4;
     //private int pattern[][][];
     
     public Shape() {
         setRandomShape();
     }

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int matrix[][]) {
		this.matrix = matrix;
	}

	public Type getPieceShape() {
		return pieceShape;
	}

	public void setPieceShape(Type pieceShape) {
		this.pieceShape = pieceShape; 
		int [][]pattern=pieceShape.pattern;
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
		int[] x;
		
		if(matrix==null)
			return;
	    
		for (int  i = 0, k = matrix.length - 1; i < k; ++i, --k) {
	        x = matrix[i];
	        matrix[i] = matrix[k];
	        matrix[k] = x;
	    }
		
	}
	private void transpose() {

		int x;
		
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
     
     
}
