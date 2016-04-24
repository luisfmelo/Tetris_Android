package com.luis.teresa.tetris.helpers;

public class Const {
	/**
	 * TEMPO DE CICLO DO TETRIS 1s
	 */
	public static double CYCLE_TIME = 0.1 ;
	
	public final static String TITLE = "TETRIS ULTIMATE";
	
	public static float redColor = 1;
	public static float greenColor = 0;
	public static float blueColor = 0;
	public static float alfa = 1;

	public static String ATLAS_PATH = "ui/atlas.pack";
	public static String SKIN_PATH = "ui/menuSkin.json";

	public final static String TROPHEY_PATH = "imgs/trophey.png";
	public final static String LEADBTN_PATH = "buttons/leadBtn.png";
	public final static String PLAYBTN_PATH = "buttons/playBtn.png";
	public final static String SETTBTN_PATH = "buttons/settBtn.png";

	
	public final static String COLLIDES_PATH = "sound/wallCollision2.wav";
	public final static String LASERISH_PATH = "sound/laserish.wav";
	public final static String HURT_PATH = "sound/hurt.wav";
	
	public void addLevel(){
		CYCLE_TIME -= 50 * 10e9;
	}
}
