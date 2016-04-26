package com.luis.teresa.tetris.helpers;

public class Const {
	/**
	 * TEMPO DE CICLO DO TETRIS 1s
	 */
	public static double CYCLE_TIME = 1 ;
	public final static String TITLE = "TETRIS";
	
	public static float redColor = 1;
	public static float greenColor = 0;
	public static float blueColor = 0;
	public static float alfa = 1;

	//COMMON
	public static String ATLAS_PATH = "ui/atlas.pack";
	public static String SKIN_PATH = "ui/menuSkin.json";

	// INTRO
	public static final String INTRO_PATH = "intro.png";
	
	//MENU
	public final static String TROPHEY_PATH = "imgs/trophey.png";
	public static final String LOCAL_SCORE_PATH = "scores";
	public final static String PLAYBTN_PATH = "buttons/playBtn.png";
	public final static String LEADBTN_PATH = "buttons/leadBtn.png";
	public final static String SETTBTN_PATH = "buttons/settBtn.png";
	public final static String FOOTER_PATH = "imgs/footer.png";

	//MUSICS
	public final static String COLLIDES_PATH = "sound/wallCollision2.wav";
	public final static String LASERISH_PATH = "sound/laserish.wav";
	public final static String HURT_PATH = "sound/hurt.wav";

	public static final String MUSIC_LEVELUP_PATH = "sounds/SFX_LevelUp.ogg";
	public static final String MUSIC_PIECEFALL_PATH = "sounds/SFX_PieceFall.ogg";
	public static final String MUSIC_THEME_PATH = "sounds/theme.mp3";
	public static final String MUSIC_FANTASTIC_PATH = "sounds/VO_FANTSTC.ogg";
	public static final String MUSIC_INTRO_PATH = "sounds/SFX_SpecialTetris.ogg";
	public static final String MUSIC_CLEAR1_PATH = "sounds/SFX_SpecialLineClearSingle.ogg";
	public static final String MUSIC_CLEAR2_PATH = "sounds/SFX_SpecialLineClearDouble.ogg";
	public static final String MUSIC_CLEAR3_PATH = "sounds/SFX_SpecialLineClearTriple.ogg";
	public static final String MUSIC_CLEAR4_PATH = "sounds/VO_WOW.ogg";
	public static final String MUSIC_GAMEOVER_PATH = "sounds/SFX_GameOver.ogg";
	public static final String MUSIC_TOUCH_PATH = "sounds/SFX_PieceTouchLR.ogg";


	
	public static void addLevel(){
		CYCLE_TIME = 0.9 * CYCLE_TIME;
	}
}
