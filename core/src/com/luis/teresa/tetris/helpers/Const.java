package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.Gdx;

/**
 * This class is responsible to store all the Constants presented in the game.
 * @author Luis
 * @author Teresa
 */
public class Const {
	// TEMPO DE CICLO DO TETRIS 0.5s
	public static double CYCLE_TIME = 0.5 ;
	public static int ROWS_TO_LEVEL_UP = 16;
	public final static String TITLE = "TETRIS";

	//THEMES
	public static float[] TETRIS_COLOR;
	public static float[] BACKGROUND_COLOR;
	public static String THEME = "dracula.";
	public static boolean soundOn;
	
	public static float redColor = 1;
	public static float greenColor = 0;
	public static float blueColor = 0;
	public static float alfa = 1;
	public static String actualImg;
	public static String filename = ".myfile";

	//COMMON
	public static String ATLAS_PATH = "ui/atlas.pack";
	public static String SKIN_PATH = "ui/mySkin.json";
	public final static int w = Gdx.graphics.getWidth();
	public final static int h = Gdx.graphics.getHeight();
	
	// INTRO
	public static final String INTRO_PATH = "intro.png";
	
	//MENU
	public final static String TROPHEY_PATH = "imgs/trophey.png";
	public final static String PLAYBTN_PATH = "buttons/playBtn.png";
	public final static String LEADBTN_PATH = "buttons/leadBtn.png";
	public final static String SETTBTN_PATH = "buttons/settBtn.png";
	public final static String TITLE_PATH = "imgs/title.png";

	//MUSICS
	public static final String INTRO_MUSIC_PATH = "sounds/SFX_SpecialTetris.ogg";
	public static final String MUSIC_LEVELUP_PATH = "sounds/SFX_LevelUp.ogg";
	public static final String MUSIC_PIECEFALL_PATH = "sounds/SFX_PieceFall.ogg";
	public static final String MUSIC_THEME_PATH = "sounds/theme.mp3";
	public static final String MUSIC_FANTASTIC_PATH = "sounds/VO_FANTSTC.ogg";
	public static final String MUSIC_CLEAR1_PATH = "sounds/SFX_SpecialLineClearSingle.ogg";
	public static final String MUSIC_CLEAR2_PATH = "sounds/SFX_SpecialLineClearDouble.ogg";
	public static final String MUSIC_CLEAR3_PATH = "sounds/SFX_SpecialLineClearTriple.ogg";
	public static final String MUSIC_CLEAR4_PATH = "sounds/VO_WOW.ogg";
	public static final String MUSIC_GAMEOVER_PATH = "sounds/SFX_GameOver.ogg";
	public static final String MUSIC_TOUCH_PATH = "sounds/SFX_PieceTouchLR.ogg";
	public static final String PREFERENCES = "My New Preferences";

	//GAME OVER
	public final static String HOME_PATH = "buttons/home.png";
	public final static String REPLAY_PATH = "buttons/replay.png";
	
	//BUTTONS - SETTINGS
	public static final String HEADER_PATH = "imgs/header.png";
	public static final String CLOSE_PATH = "buttons/x.png";
	public static final String SOLAR_PATH = "buttons/sun.png";
	public static final String DRACULA_PATH = "buttons/moon.png";
	public static final String SOUND_PATH = "buttons/sound.png";
	public static final String MUTE_PATH = "buttons/mute.png";
	
	//BLOCK IMAGES
	public static final String WHITE_BLOCK = "blocks/branco.png";
	public static final String BLACK_BLOCK = "blocks/preto.png";
	public static final String LIGHTBLUE_BLOCK = "blocks/azulClaro.png";
	public static final String DARKBLUE_BLOCK = "blocks/azulEscuro.png";
	public static final String GREEN_BLOCK = "blocks/verde.png";
	public static final String RED_BLOCK = "blocks/vermelho.png";
	public static final String PURPLE_BLOCK = "blocks/roxo.png";
	public static final String YELLOW_BLOCK = "blocks/amarelo.png";
	public static final String ORANGE_BLOCK = "blocks/laranja.png";

	
	public static void addLevel(){
		CYCLE_TIME = 0.95 * CYCLE_TIME;
	}

	/**
	 * Set Background color
	 * @param r		Red component of Background color
	 * @param g		Green component of Background color
	 * @param b		Blue component of Background color
	 * @param a		Opacity of Background color
	 */
	public static void setBG_COLOR(float r, float g, float b, float a) {
		BACKGROUND_COLOR = new float[4];
		BACKGROUND_COLOR[0] = r;
		BACKGROUND_COLOR[1] = g;
		BACKGROUND_COLOR[2] = b;
		BACKGROUND_COLOR[3] = a;
	}

	/**
	 * Set Tetris color
	 * @param r		Red component of Tetris color
	 * @param g		Green component of Tetris color
	 * @param b		Blue component of Tetris color
	 * @param a		Opacity of Tetris color
	 */
	public static void setTETRIS_COLOR(float r, float g, float b, float a) {
		TETRIS_COLOR = new float[4];
		TETRIS_COLOR[0] = r;
		TETRIS_COLOR[1] = g;
		TETRIS_COLOR[2] = b;
		TETRIS_COLOR[3] = a;
	}

	/**
	 * Transform color in RGB format to String format
	 * @param rgb		rgb values stored in array of floats
	 * @return 			string of the correspondent value
	 */
	public static String transform(float[] rgb) {
		
		if (rgb[0] == 255f/255 && rgb[1] == 255f/255 && rgb[2] == 255f/255)
			return "white";
		else if (rgb[0] == 0f/255 && rgb[1] == 0f/255 && rgb[2] == 0f/255)
			return "black";
		else if (rgb[0] == 0f/255 && rgb[1] == 240f/255 && rgb[2] == 240f/255)
			return "lightblue";
		else if (rgb[0] == 0f/255 && rgb[1] == 240f/255 && rgb[2] == 0f/255)
			return "green";
		else if (rgb[0] == 240f/255 && rgb[1] == 0f/255 && rgb[2] == 0f/255)
			return "red";
		else if (rgb[0] == 150f/255 && rgb[1] == 0f/255 && rgb[2] == 240f/255)
			return "purple";
		else if (rgb[0] == 240f/255 && rgb[1] == 240f/255 && rgb[2] == 0f/255)
			return "yellow";
		else if (rgb[0] == 240f/255 && rgb[1] == 150f/255 && rgb[2] == 0f/255)
			return "orange";
		else if (rgb[0] == 0f/255 && rgb[1] == 0f/255 && rgb[2] == 240f/255)
			return "darkblue";
		return "";

	}
	
	/**
	 * Transform color in string format to RGB format
	 * @param rgb		name of the color in string format
	 * @return 			rgb values of the correspondent color stored in array of floats
	 */
	public static float[] getRGB(String color) {
		float [] rgb = new float[3];
		
		switch (color){
			case "white":
				rgb[0] = 255f/255;
				rgb[1] = 255f/255;
				rgb[2] = 255f/255;
				break;
			case "black":
				rgb[0] = 0f/255;
				rgb[1] = 0f/255;
				rgb[2] = 0f/255;
				break;
			case "lightblue":
				rgb[0] = 0f/255;
				rgb[1] = 240f/255;
				rgb[2] = 240f/255;
				break;			
			case "green":
				rgb[0] = 0f/255;
				rgb[1] = 240f/255;
				rgb[2] = 0f/255;
				break;
			case "red": 
				rgb[0] = 240f/255;
				rgb[1] = 0f/255;
				rgb[2] = 0f/255;
				break;
			case "purple":
				rgb[0] = 150f/255;
				rgb[1] = 0f/255;
				rgb[2] = 240f/255;
				break;
			case "yellow":
				rgb[0] = 240f/255;
				rgb[1] = 240f/255;
				rgb[2] = 0f/255;
				break;
			case "orange":
				rgb[0] = 240f/255;
				rgb[1] = 150f/255 ;
				rgb[2] = 0f/255;
				break;
			case "darkblue":
				rgb[0] = 0f/255;
				rgb[1]= 0f/255;
				rgb[2] = 240f/255;
				break;
			default:
				rgb=null;
		}
		return rgb;

	}
}
