package com.luis.teresa.tetris.helpers;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.luis.teresa.tetris.logic.TetrisLogic;

/**
 * This class is responsible to Load all the assets necessary for the game.
 * @author Luis
 * @author Teresa
 */
public class LoadAssets {
	
	//COMMON
	private TextureAtlas atlas;
	private Skin skin;
	private Preferences prefs;
	
	//INTRO
	private Image intro;
	
	//MENU
	private Image trophey;
	private Label highScore;
	private Image playBtn;
	private Image settBtn;
	private Image footer;
	
	//SETTINGS
	private Label titleLabel;
	private Image x;
	private Label musicLabel;
	private Image theme;
	private Label themeLabel;
	private Image music;
	
	//GAME
	private Label scoreLabel;
	private Label score;
	private Label levelLabel;
	private Label level;
	private Image header;

	//GAME OVER
	private Label gameOverLabel;
	private Label secundaryLabel;
	private Image home;
	private Image replay;

	public Image orange;
	public Image yellow;
	public Image purple;
	public Image red;
	public Image green;
	public Image darkblue;
	public Image lightblue;
	public Image black;
	public Image white;
	public Image im;
	
	private TextureAtlas myPackage;
	private TextureAtlas myBlocks;
	
	/**
	 * Constructor.
	 * this method loads important assets for all the screens like skin, preferences, theme,...
	 */
	public LoadAssets() {
		atlas = new TextureAtlas(Gdx.files.internal(Const.ATLAS_PATH));
		skin = new Skin(Gdx.files.internal(Const.SKIN_PATH), atlas); 
		prefs = Gdx.app.getPreferences(Const.PREFERENCES);
		Const.THEME = getTheme();
		im = new Image();
		
		myPackage = new TextureAtlas("imgsPkg.txt");		
		myBlocks = new TextureAtlas("blocksPkg.txt");
	}

	/**
	 * This method loads important assets for the proper functioning 
	 * of the screen: IntroScreen
	 */
    public void loadIntroAssets() {
		//INTRO IMAGE
		intro = new Image(new Texture(Gdx.files.internal(Const.INTRO_PATH)));
		intro.setSize(.5f*Const.w, .3f*Const.h);
		intro.setPosition(.5f*Const.w, .5f*Const.h, 0);
    }

	/**
	 * This method loads important assets for the proper functioning 
	 * of the screen: MenuScreen
	 */
    public void loadMenuAssets() throws IOException {
		//high Score Image
    	trophey = new Image(myPackage.findRegion(Const.THEME + "trophey"));
    	trophey.setSize(.15f*Const.w, .15f*Const.h);
		trophey.setPosition(.5f*Const.w, .9f*Const.h, 0);
		
		//high Score Text
		highScore = new Label(Integer.toString(getScores()), skin, Const.THEME + "default");
		highScore.setAlignment(Align.center);
		highScore.setSize(.25f*Const.w, .2f*Const.h);
		highScore.setPosition(.5f*Const.w, .75f*Const.h, 0);
		//highScore.setFontScale(3);
		
		//Play Button
		playBtn = new Image(myPackage.findRegion(Const.THEME + "playBtn"));
		playBtn.setSize(.4f*Const.w, .2f*Const.h);
		playBtn.setPosition(.5f*Const.w, .55f*Const.h, 0);
		
		//Settings Button
    	settBtn = new Image(myPackage.findRegion(Const.THEME + "settBtn"));
    	settBtn.setSize(.4f*Const.w, .2f*Const.h);
		settBtn.setPosition(.5f*Const.w, .3f*Const.h, 0);

		//Footer
		footer = new Image(myPackage.findRegion(Const.THEME + "title"));
		footer.setSize(.3f*Const.w, .08f*Const.h);
		footer.setPosition(.5f*Const.w, .05f*Const.h, 0);
    }

	/**
	 * This method loads important assets for the proper functioning 
	 * of the screen: GameScreen
	 */
    public void loadGameAssets(TetrisLogic myGame){
		int tam_x = (int) (0.15*Const.w/4);
		int tam_y = (int) (0.15*Const.h/4);
		
    	//header = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.TITLE_PATH)));
		header = new Image(myPackage.findRegion(Const.THEME + "title"));
		header.setSize(.3f*Const.w, .05f*Const.h);
		header.setPosition(.5f*Const.w, .96f*Const.h, 0);
		
		//Label - Score
		scoreLabel = new Label("Score", skin, Const.THEME + "default");
		scoreLabel.setAlignment(Align.center);
		scoreLabel.setSize(tam_x*5, tam_y);
		scoreLabel.setPosition(0.7f*Const.w, 0.5f*Const.h , 0);
		//scoreLabel.setFontScale(3);
		
		score = new Label(myGame.getScore(), skin, Const.THEME + "default");
		score.setAlignment(Align.center);
		score.setSize(tam_x*10, tam_y);
		score.setPosition(0.7f*Const.w, 0.43f*Const.h , 0);	
		//score.setFontScale(3);
		
		//Label - Level
		levelLabel = new Label("Level", skin, Const.THEME + "default");
		levelLabel.setAlignment(Align.center);
		levelLabel.setSize(tam_x*10, tam_y);
		levelLabel.setPosition(0.7f*Const.w, 0.3f*Const.h, 0);	
		//levelLabel.setFontScale(3);
		
		level = new Label(myGame.getLevel(), skin, Const.THEME + "default");
		level.setAlignment(Align.center);
		level.setSize(tam_x*10, tam_y);
		level.setPosition(0.7f*Const.w, 0.23f*Const.h , 0);	
		//level.setFontScale(3);
    }

	/**
	 * This method loads important assets for the proper functioning 
	 * of the screen: SettingsScreen
	 */
    public void loadSettingsAssets() {
    	//Label Title
    	titleLabel = new Label("Settings", skin, Const.THEME + "big");
		titleLabel.setAlignment(Align.center);
		titleLabel.setSize(0.6f*Const.w, 0.2f*Const.h);
		titleLabel.setPosition(0.5f*Const.w, 0.8f*Const.h, 0);
		//titleLabel.setFontScale(3);
		
		//X Image
		//x = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.CLOSE_PATH)));
		x = new Image(myPackage.findRegion(Const.THEME + "x"));
		x.setSize(.1f*Const.w, .1f*Const.h);
		x.setPosition(.9f*Const.w, .9f*Const.h, 0);
		
		//Label - Music
		musicLabel = new Label("Sound", skin, Const.THEME + "small");
		musicLabel.setAlignment(Align.center);
		musicLabel.setSize(0.2f*Const.w, 0.1f*Const.h);
		musicLabel.setPosition(0.5f*Const.w, 0.5f*Const.h, 0);	
		//musicLabel.setFontScale(3);

		//Image - Music
		if (Const.soundOn)
			//music = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.SOUND_PATH)));
			music = new Image(myPackage.findRegion(Const.THEME + "sound"));
		else
			//music = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.MUTE_PATH)));
			music = new Image(myPackage.findRegion(Const.THEME + "mute"));
		music.setSize(.15f*Const.w, .15f*Const.h);
		music.setPosition(.65f*Const.w, .5f*Const.h, 0);

		
		//Label - Theme
		themeLabel = new Label("Theme", skin, Const.THEME + "small");
		themeLabel.setAlignment(Align.center);
		themeLabel.setSize(0.2f*Const.w, 0.1f*Const.h);
		themeLabel.setPosition(0.5f*Const.w, 0.3f*Const.h, 0);	
		//themeLabel.setFontScale(3);
		
		//Image - theme
		if (getTheme().equals("solar.") )
			//theme = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.SOLAR_PATH)));
			theme = new Image(myPackage.findRegion(Const.THEME + "sun"));
		else
			//theme = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.DRACULA_PATH)));
			theme = new Image(myPackage.findRegion(Const.THEME + "moon"));
		theme.setSize(.15f*Const.w, .15f*Const.h);
		theme.setPosition(.65f*Const.w, .3f*Const.h, 0);

    }

    /**
	 * This method loads important assets for the proper functioning 
	 * of the screen: GameOverScreen
	 */
    public void loadGameOverAssets() {
		//Game Over Label
    	gameOverLabel = new Label("Game Over", skin, Const.THEME + "default");
		gameOverLabel.setAlignment(Align.center);
		gameOverLabel.setSize(Const.w, 0.2f*Const.h);
		gameOverLabel.setPosition(0.5f*Const.w, 0.85f*Const.h, 0);	
		//gameOverLabel.setFontScale(3);
		
		//secondary Label
		secundaryLabel = new Label("", skin, Const.THEME + "small");
		secundaryLabel.setAlignment(Align.center);
		secundaryLabel.setSize(Const.w, 0.2f*Const.h);
		secundaryLabel.setPosition(0.5f*Const.w, 0.75f*Const.h, 0);	
		//secundaryLabel.setFontScale(3);
		
		//title bar
		header = new Image(myPackage.findRegion(Const.THEME + "title"));
    	header.setSize(.3f*Const.w, .05f*Const.h);
    	header.setPosition(.5f*Const.w, .95f*Const.h, 0);
		
		//home bar
		home = new Image(myPackage.findRegion(Const.THEME + "home"));
		home.setSize(.3f*Const.w, .15f*Const.h);
		home.setPosition(.5f*Const.w, .6f*Const.h, 0);
		
		//replay bar
		replay = new Image(myPackage.findRegion(Const.THEME + "replay"));
		replay.setSize(.3f*Const.w, .15f*Const.h);
		replay.setPosition(.5f*Const.w, .35f*Const.h, 0);		
    }
    

    /** 
     * Get the current value of HighScore
     * @return 		The HighScore value
     */
	public int getScores() throws IOException {
		return prefs.getInteger("score");
	}

    /** 
     * Save a new value for HighScore
     * @param sc 	new HighScore value
     */
	public void setScores(int sc) throws IOException {
		this.prefs.putInteger("score", sc);
		prefs.flush();
	}
	
    /** 
     * Get the current value of Sound Preference
     * @return 		false if it is muted. true otherwise.
     */
	public boolean getSoundOn() {
		return prefs.getBoolean("soundOn");			
	}

    /** 
     * Save new Sound Preference
     * @param s 	false if we want to mute the sound. true otherwise.
     */
	public void setSoundOn(boolean s) {
		this.prefs.putBoolean("soundOn", s);
		prefs.flush();
	}
	
    /** 
     * Get the current value of the Theme Preference
     * @return 		the name of the theme that is being used.
     */
	public String getTheme() {
		Const.THEME = prefs.getString("theme");
		return prefs.getString("theme");
	}

	/**
	 * Save new Theme Preference: solar. or dracula.
	 * @param s
	 */
	public void setTheme(String s) {
		this.prefs.putString("theme", s);
		prefs.flush();
		if (s != "dracula.")
		{
			// BACKGROUND COLOR 
			Const.setBG_COLOR(1f,1f,1f,1f);
			Const.setTETRIS_COLOR(0f,0f,0f,1f);
		}
		else if (s != "solar.")
		{
			// BACKGROUND COLOR 
			Const.setBG_COLOR(0f,0f,0f,1f);
			Const.setTETRIS_COLOR(1f,1f,1f,1f);
		}
		Const.THEME = s;
	}

    /** 
     * Get the skin currently in use
     * @return 		skin.
     */
	public Skin getSkin() {
		return this.skin;
	}
	
	
	// INTRO
    /** 
     * Get the image for splash Screen
     * @return 		Image for introScreen.
     */
	public Image getIntroImg() {
		return intro;
	}
	
	// MENU
    /** 
     * Get HighScore Image
     * @return 		Trophey Image.
     */
	public Image getTrophey() {
		return trophey;
	}

    /** 
     * Get Play Image
     * @return 		Play Image.
     */
	public Image getPlayBtn() {
		return playBtn;
	}

    /** 
     * Get Settings Image
     * @return 		Settings Image.
     */
	public Image getSettBtn() {
		return settBtn;
	}

    /** 
     * Get Footer Image
     * @return 		Footer Image.
     */
	public Image getFooter() {
		return footer;
	}

    /** 
     * Get HighScore Label
     * @return 		HighScore Label.
     */
	public Label getHighScore() {
		return this.highScore;
	}

	//Settings
    /** 
     * Get Title Label
     * @return 		Title Label.
     */
	public Label getTitleLabel() {
		return titleLabel;
	}

    /** 
     * Get Close (x) Image
     * @return 		Close(x) Image.
     */
	public Image getX() {
		return x;
	}
	
    /** 
     * Get Music Status Label
     * @return 		Music Status Label.
     */
	public Label getMusicLabel() {
		return musicLabel;
	}

    /** 
     * Get Music Status Image
     * @return 		Music Status Image.
     */
	public Image getImageMusic() {
		return music;
	}
	
    /** 
     * Get Theme Label
     * @return 		Theme Label.
     */
	public Label getThemeLabel() {
		return themeLabel;
	}

    /** 
     * Get Theme Image
     * @return 		Theme Image.
     */
	public Image getImageTheme() {
		return theme;
	}
	

	//GAME
    /** 
     * Get Game Header Image
     * @return 		Game Header Image.
     */
	public Image getGameHeader() {
		return header;
	}

    /** 
     * Get Score Label
     * @return 		Score Label.
     */
	public Label getScoreLabel() {
		return scoreLabel;
	}

    /** 
     * Get Score number in Label
     * @return 		Score number Label.
     */
	public Label getScore() {
		return score;
	}

    /** 
     * Get Level Label
     * @return 		Level Label.
     */
	public Label getLevelLabel() {
		return levelLabel;
	}

    /** 
     * Get Level number in Label
     * @return 		Level number Label.
     */
	public Label getLevel() {
		return level;
	}


	
	//GAME OVER
    /** 
     * Get Game Over Label
     * @return 		Game Over Label.
     */
	public Label getGameOverLabel() {
		return gameOverLabel;
	}

    /** 
     * Get Info Label
     * @return 		Secundary(info) Label.
     */
	public Label getSecundaryLabel() {
		return secundaryLabel;
	}

    /** 
     * Get Header Image
     * @return 		Header Image.
     */
	public Image getHeader() {
		return header;
	}
	
    /** 
     * Get Home Btn Image
     * @return 		Home Btn Image.
     */
	public Image getHome() {
		return home;
	}

    /** 
     * Get Replay Btn Image
     * @return 		Replay Btn Image.
     */
	public Image getReplay() {
		return replay;
	}

	/**
	 * Load all blocks images
	 */
	public void loadBlockImgs() {
		white = new Image(myBlocks.findRegion("branco"));
		black = new Image(myBlocks.findRegion("preto"));
		lightblue = new Image(myBlocks.findRegion("azulClaro"));
		darkblue = new Image(myBlocks.findRegion("azulEscuro"));
		green = new Image(myBlocks.findRegion("verde"));
		red = new Image(myBlocks.findRegion("vermelho"));
		purple = new Image(myBlocks.findRegion("roxo"));
		yellow = new Image(myBlocks.findRegion("amarelo"));
		orange = new Image(myBlocks.findRegion("laranja"));
	}

	/**
	 * Get the Block image we want to use. All the blocks are pre-loaded
	 * @param color		String with color of the block
	 * @return			Image of the desired block
	 */
	public Image loadOneBlock(String color) {
		switch(color)
		{
		case "white":
			return white;
		case "black":
			return black;
		case "lightblue":
			return lightblue;
		case "darkblue":
			return darkblue;
		case "green":
			return green;
		case "red":
			return red;
		case "purple":
			return purple;
		case "yellow":
			return yellow;
		case "orange":
			return orange;
		}
		return orange;
	}
}