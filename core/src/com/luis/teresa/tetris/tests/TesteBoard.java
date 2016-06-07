package com.luis.teresa.tetris.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.luis.teresa.tetris.logic.Block;
import com.luis.teresa.tetris.logic.Board;
import com.luis.teresa.tetris.logic.Shape;
import com.luis.teresa.tetris.logic.Shape.Type;
import com.luis.teresa.tetris.logic.TetrisLogic;


/**
 * This class tests Board methods
 * @author Luis
 * @author Teresa
 */
public class TesteBoard {
	
	/**
	 * Test if the rows are cleaned when filled completely 
	 */
	@Test
	public void testCleanRows() {
		Board b=Board.getInstance();
		int r=b.getRows();
		Block[][] m = new Block[r][b.getCols()];
		
		for (int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				if (  i > 22 || j == 0 || j == 11 ){
					m[i][j] = new Block(i,j);
					m[i][j].setColor("white");
				}
				else
					m[i][j] = new Block(i,j);
			}
		}
		
		for(int i=1; i<b.getCols();i++)
			m[r-3][i].setColor("orange");
		
		TetrisLogic.setROWS_TOLEVELUP(16);
		b.setBoard(m);
		b.checkRows();
		for(int i = 1; i < b.getCols() - 1 ; i++)
			assertEquals(b.getBoard()[r-3][i].getColor(),"black");
	}
	
	/**
	 * Test if the game ends when it should
	 */
	@Test
	public void testGameOver() {
		Board b=Board.getInstance();
		b.handleGameOver();
		assertTrue(b.isGameOver());
	}
	
	/**
	 * Test if a shape doesn't go beyond limits of the board
	 */
	@Test
	public void testCommandWallTest(){
		Board b=Board.getInstance();
		Board.initializeBoard();
		b.setPieceOnGoing(":");
		b.newShape(new Shape(Type.O));
		b.setShapePos(5, 0);
		ArrayList<Vector2> pos=b.getMyShape();
		b.input("a");
		assertEquals(pos,b.getMyShape());
	}
	
	/**
	 * Test if a shape doesn't rotate when it can't
	 */
	@Test
	public void testCommandImpossibleRotation(){
		Board b=Board.getInstance();
		Board.initializeBoard();
		b.setPieceOnGoing(":");

		b.newShape(new Shape(Type.O));
		b.setShapePos(b.getRows()-5, 0);
		b.newShape(new Shape(Type.O));
		b.setShapePos(b.getRows()-5, 5);
		
		b.newShape(new Shape(Type.J));
		b.setShapePos(b.getRows()-5, 2);
		ArrayList<Vector2> pos=b.getMyShape();
		b.rotate();
		assertEquals(pos,b.getMyShape());		
	}
	
	/**
	 * Test the down move of a shape in a board
	 */
	@Test
	public void testCommandDownTest(){
		Board b=getBoard_commandsTest();
		ArrayList<Vector2> pos=b.getMyShape();
		b.input("s");
		assertNotEquals(pos,b.getMyShape());
	}
	
	/**
	 * Test the rotation of a shape in a board 
	 */
	@Test
	public void testCommandRotateTest(){
		Board b=getBoard_commandsTest();
		ArrayList<Vector2> pos=b.getMyShape();
		b.rotate();
		assertNotEquals(pos,b.rotate());
	}
	
	/**
	 * Test the left move of a shape 
	 */
	@Test
	public void testCommandLeftTest(){
		Board b=getBoard_commandsTest();
		ArrayList<Vector2> pos=b.getMyShape();
		
		b.input("a");
		assertNotEquals(pos,b.getMyShape());
	}
	
	/**
	 * Test the right move of a shape 
	 */
	@Test
	public void testCommandRightTest(){
		Board b = getBoard_commandsTest();
		ArrayList<Vector2> pos=b.getMyShape();
		
		b.input("d");
		assertNotEquals(pos,b.getMyShape());	
	}
	
	/**
	 * Test insertion of a shape on the board
	 */
	@Test
	public void testNewShapeOnBoard() {
		Board b=Board.getInstance();
		Board.initializeBoard();
		Shape shape= new Shape();
		b.newShape(shape);
		
		for (int i = 0; i < 4; i++) 
			for (int j = 4; j < 8; j++) 
				assertEquals(b.getBoard()[i][j].getParent(),shape);
	}
	
	/**
	 * Test if a shape it is correctly positioned on top of other shape present in the board 
	 */
	@Test
	public void testAddToTower() {
		Board b=Board.getInstance();
		Board.initializeBoard();
		Shape shape= new Shape(Type.O);
		b.newShape(shape);
		b.setPieceOnGoing(":");
		b.setShapePos(b.getRows()-6, 4);
		b.input("s");
		
		b.newShape(new Shape(Type.O));
		b.setPieceOnGoing(":");
		b.setShapePos(b.getRows()-8, 4);
		b.input("s");
		b.input("s");
		assertNotEquals(b.getBoard()[b.getRows()-5][5].getParent(),b.getBoard()[b.getRows()-4][6].getParent());
		assertFalse(b.input("s"));
	}
	
	/**
	 * Test if the game ends when a shape reaches the top
	 */
	@Test
	public void testTouchTop() throws IOException {
		Board b = Board.getInstance();
		
		Shape s = new Shape();
		b.getBoard()[3][4].setColor("orange");
		b.getBoard()[3][4].setParent(s);
		b.setPieceOnGoing(":");
		b.checkRows();
		
		assertTrue(b.isGameOver());
	}

	/**
	 * Auxiliary method to all commandsTests present in this class
	 * @return board	instance of board modified in the method
	 */
	private Board getBoard_commandsTest() {
		Board b=Board.getInstance();
		Board.initializeBoard();
		b.newShape(new Shape(Type.I));
		b.setPieceOnGoing(":");
		 
		return b;	
	}
}
