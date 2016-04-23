package com.luis.teresa.tetris.helpers;

public class Const {
	/**
	 * TEMPO DE CICLO DO TETRIS 1s
	 */
	public static double CYCLE_TIME = 1 * 10e9;
	
	public void addLevel(){
		CYCLE_TIME -= 50 * 10e9;
	}
}
