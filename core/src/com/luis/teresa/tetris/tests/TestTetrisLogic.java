package com.luis.teresa.tetris.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.luis.teresa.tetris.logic.Shape;
import com.luis.teresa.tetris.logic.TetrisLogic;

/**
 * This class tests the TetrisLogic Class
 * @author Luis
 * @author Teresa
 */
public class TestTetrisLogic {
	/**
	 * Tests if level is updated when scores reaches the desired rows to level Up
	 */
	@Test
	public void testLevelUp() {
		TetrisLogic game=new TetrisLogic();
		TetrisLogic.setROWS_TOLEVELUP(16);
		TetrisLogic.addScore("4");
		TetrisLogic.addScore("4");
		assertEquals(game.get_intScore(),16);
		assertEquals(game.getLevel(),"2");
	}
	/**
	 * Tests if the future shape implementation is correct
	 */
	@Test
	public void testFutureShape_Ok() throws IOException{
		TetrisLogic g = new TetrisLogic();
		g.newCycle();
		Shape s = g.getFutureShape();
		g.setShapeInGame(false);
		g.newCycle();
		assertEquals(s.getPieceShape(),g.getShape().getPieceShape());
	}
}
