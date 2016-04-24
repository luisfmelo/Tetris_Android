package com.luis.teresa.tetris.helpers;

public class Const {
	/**
	 * TEMPO DE CICLO DO TETRIS 1s
	 */
	public static double CYCLE_TIME = 0.1 ;
	public static float redColor = 1;
	public static float greenColor = 0;
	public static float blueColor = 0;
	public static float alfa = 1;
	
	public void addLevel(){
		CYCLE_TIME -= 50 * 10e9;
	}
}
