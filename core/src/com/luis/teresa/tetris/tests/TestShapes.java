package com.luis.teresa.tetris.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.luis.teresa.tetris.logic.Block;
import com.luis.teresa.tetris.logic.Shape;

/**
 * This class tests Shape methods
 * @author Luis
 * @author Teresa
 */
public class TestShapes {
	/**
	 * Test Random Shape construction and change of coords
	 */
	@Test
	public void testRandomShapeConstructor() {
		Shape myShape = new Shape();

		assertEquals(myShape.getX_world(), 0);
		assertNotEquals(myShape.getX_world(), 1);
		assertEquals(myShape.getY_world(), 4);
		assertNotEquals(myShape.getY_world(), 2);
	}
	
	/**
	 * Test Specified Shape construction (color and blocks positions)
	 */
	@Test
	public void testSpecificShapeConstructor() {
		Shape myShape = new Shape(Shape.Type.T);

		assertEquals(myShape.getPieceShape().getColor(), "purple");
		assertEquals(myShape.getPieceShape().getLetter(), "T");
	}
	
	/**
	 * Test changes of coordinates
	 */
	@Test
	public void testCoordsChanges() {
		Shape myShape = new Shape();
		
		assertEquals(myShape.getX_world(), 0);
		assertNotEquals(myShape.getX_world(), 1);
		assertEquals(myShape.getY_world(), 4);
		assertNotEquals(myShape.getY_world(), 2);
		
		myShape.setX_world(1);
		myShape.setY_world(1);
		
		assertEquals(myShape.getX_world(), 1);
		assertNotEquals(myShape.getX_world(), 3);
		assertEquals(myShape.getY_world(), 1);
		assertNotEquals(myShape.getY_world(), 2);
		
		myShape.set_newPos(2, 2);
		
		assertEquals(myShape.getNew_x(), 2);
		assertNotEquals(myShape.getNew_x(), 3);
		assertEquals(myShape.getNew_y(), 2);
		assertNotEquals(myShape.getNew_y(), 3);
		
		myShape.update_pos();
		
		assertEquals(myShape.getX_world(), 2);
		assertNotEquals(myShape.getX_world(), 1);
		assertEquals(myShape.getY_world(), 2);
		assertNotEquals(myShape.getY_world(), 1);
	}
	
	/**
	 * Test rotations (left and right)
	 */
	@Test
	public void testShapeRotate() {
		
		// 'O' Block can't rotate
		Shape s = new Shape(Shape.Type.O);

		assertArrayEquals(null, s.getShapeMatrix(), Shape.Type.O.getPattern());
		s.rotateRight();
		assertArrayEquals(null, s.getShapeMatrix(), Shape.Type.O.getPattern());
		
		s = new Shape(Shape.Type.O);
		s.rotateLeft();
		assertArrayEquals(null, s.getShapeMatrix(), Shape.Type.O.getPattern());
		
		
		// 'S' can rotate
		s = new Shape(Shape.Type.S);
		s.rotateRight();
			 
		char matrix_rotate[][] = new char [][] {
		 		{' ','1',' ',' '},
				{' ','1','1',' '},
				{' ',' ','1',' '},
				{' ',' ',' ',' '}
				 };
		assertArrayEquals(s.getShapeMatrix(),matrix_rotate);
		
		s = new Shape(Shape.Type.S);
		s.rotateLeft();
		 
		matrix_rotate= new char [][] {
		 		{' ',' ',' ',' '},
				{' ','1',' ',' '},
				{' ','1','1',' '},
				{' ',' ','1',' '}
				 };
		assertArrayEquals(s.getShapeMatrix(),matrix_rotate);
	}

	/**
	 * Test if some random shape has 4 and only 4 blocks
	 */
	@Test
	public void testNumberOfBlocks() {
		Shape s = new Shape();
		int cont = 0;
		
		for( int i = 0 ; i < s.getMatrix_Block().length ; i++ )
			for( int j = 0 ; j < s.getMatrix_Block().length ; j++ )
				if( s.getBlock(i, j).getColor().equals(s.getPieceShape().getColor()) )
						cont++;
		
		assertEquals(cont,4);
	}
	
	/**
	 * Test the real rotation that we use in Game (vector of coordinates)
	 */
	@Test
	public void testRealRotation() {
		Shape s = new Shape(Shape.Type.S);

		ArrayList<Vector2> v = new ArrayList<>();
		// example of S shape
		v.add(new Vector2(5,5));
		v.add(new Vector2(7,6));
		v.add(new Vector2(7,7));
		v.add(new Vector2(8,8));
		v.add(new Vector2(4,4)); // point of sync
		
		ArrayList<Vector2> rotated_v = new ArrayList<>();
		rotated_v.add(new Vector2(4,5));
		rotated_v.add(new Vector2(5,5));
		rotated_v.add(new Vector2(5,6));
		rotated_v.add(new Vector2(6,6));
		rotated_v.add(new Vector2(4,4)); // point of sync
		
		assertEquals(s.rotate(v), rotated_v);
	}

	/**
	 * Test setting the Blocks manually
	 */
	@Test
	public void testBlockMatrixSetUp() {
		Shape s = new Shape();
		Block [][] b = new Block [][]{

	 		{new Block(s, 0, 0 , "black"),new Block(s, 0, 1, "black"),new Block(s, 0, 2, "black"),new Block(s, 0, 3, "black")},
	 		{new Block(s, 1, 0 , "black"),new Block(s, 1, 1, "green"),new Block(s, 1, 2, "green"),new Block(s, 1, 3, "black")},
	 		{new Block(s, 2, 0 , "green"),new Block(s, 2, 1, "green"),new Block(s, 2, 2, "black"),new Block(s, 2, 3, "black")},
	 		{new Block(s, 3, 0 , "black"),new Block(s, 3, 1, "black"),new Block(s, 3, 2, "black"),new Block(s, 3, 3, "black")}
		};
		s.setMatrix_Block(b);

		assertArrayEquals(b, s.getMatrix_Block());
	}
}