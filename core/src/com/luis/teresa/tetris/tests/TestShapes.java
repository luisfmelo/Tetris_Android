package com.luis.teresa.tetris.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.luis.teresa.tetris.logic.Shape;

/**
 * 
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
	
	public void testRotations() {
		Shape myShape = new Shape(Shape.Type.O);
		
		char[][] matrix = myShape.getPieceShape().getPattern();
		
		myShape.rotateLeft();
		// falta fazer
	}
	
	
}
