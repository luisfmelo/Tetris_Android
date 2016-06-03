package com.luis.teresa.tetris.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.InitializationError;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.headless.mock.input.MockInput;
import com.badlogic.gdx.math.Vector2;
import com.luis.teresa.tetris.Tetris;
import com.luis.teresa.tetris.logic.Block;
import com.luis.teresa.tetris.logic.Board;
import com.luis.teresa.tetris.logic.Shape;
import com.luis.teresa.tetris.logic.Shape.Type;
import com.luis.teresa.tetris.logic.TetrisLogic;

import mockit.MockUp;

public class TesteBoard {
	
	
	@Test
	public void clean_rows() {
		
		Board b=Board.getInstance();
		int r=b.getRows();
		Block[][] m = new Block[r][b.getCols()];
		
		for (int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				if (  i >22 || j == 0 || j == 11 ){
					m[i][j] = new Block(i,j);
					m[i][j].setColor("white");
				}
				else
					m[i][j] = new Block(i,j);
			}
		}
		
		for(int i=1; i<b.getCols();i++){
			m[r-3][i].setColor("orange");
		}
		TetrisLogic.setROWS_TOLEVELUP(16);
		b.setBoard(m);
		b.checkRows();
		for(int i=1; i<b.getCols()-1;i++)
			assertEquals(b.getBoard()[r-3][i].getColor(),"black");
		
	}
	@Test
	public void gameOver() {
		Board b=Board.getInstance();
		b.handleGameOver();
		assertTrue(b.isGameOver());
		
	}
	
	
	private Board getBoard_commandsTest() {
		Board b=Board.getInstance();
		Board.initializeBoard();
		b.newShape(new Shape(Type.I));
		b.setPieceOnGoing(":");
		 
		return b;
		
	}
	@Test
	public void commandWallTest(){
		Board b=Board.getInstance();
		Board.initializeBoard();
		b.setPieceOnGoing(":");
		//rotate left ->can't rotate
		b.newShape(new Shape(Type.O));
		b.setShapePos(5, 0);
		ArrayList<Vector2> pos=b.getMyShape();
		b.input("a");
		assertEquals(pos,b.getMyShape());
	}
	@Test
	public void commandImpossibleRotation(){
		Board b=Board.getInstance();
		Board.initializeBoard();
		b.setPieceOnGoing(":");
		//rotate left ->can't rotate
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
	@Test
	public void commandDownTest(){
		Board b=getBoard_commandsTest();
		ArrayList<Vector2> pos=b.getMyShape();
		b.input("s");
		assertNotEquals(pos,b.getMyShape());
	}
	@Test
	public void commandRotateTest(){
		Board b=getBoard_commandsTest();
		ArrayList<Vector2> pos=b.getMyShape();
		b.rotate();
		assertNotEquals(pos,b.rotate());
	}
	@Test
	public void commandLeftTest(){
		Board b=getBoard_commandsTest();
		ArrayList<Vector2> pos=b.getMyShape();
		
		b.input("a");
		assertNotEquals(pos,b.getMyShape());
	}
	@Test
	public void commandRightTest(){
		Board b=getBoard_commandsTest();
		ArrayList<Vector2> pos=b.getMyShape();
		
		b.input("d");
		assertNotEquals(pos,b.getMyShape());	
	}
	@Test
	public void newShape_OnBoard() {
		Board b=Board.getInstance();
		Board.initializeBoard();
		Shape shape= new Shape();
		b.newShape(shape);
		
		for (int i = 0; i < 4; i++) 
			for (int j = 4; j < 8; j++) 
				assertEquals(b.getBoard()[i][j].getParent(),shape);
		
	}
	@Test
	public void addToTower() {
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
		//b.printBoard();
		assertNotEquals(b.getBoard()[b.getRows()-5][5].getParent(),b.getBoard()[b.getRows()-4][6].getParent());
		assertFalse(b.input("s"));
		
	}
	@Test
	public void touchTop() throws IOException {

		//TetrisLogic game=new TetrisLogic();
		Board b=Board.getInstance();
		
		Shape s=new Shape();
		b.getBoard()[3][4].setColor("orange");
		b.getBoard()[3][4].setParent(s);
		b.setPieceOnGoing(":");
		b.checkRows();
		
		assertTrue(b.isGameOver());
	}
	


}
