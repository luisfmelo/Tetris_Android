package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.luis.teresa.tetris.logic.TetrisLogic;

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
	private Image leadBtn;
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
	
	//GAME OVER
	private Label gameOverLabel;
	private Label secundaryLabel;
	private Image header;
	private Image home;
	private Image replay;
	private Label scoreLabel;
	private Label score;
	private Label levelLabel;
	private Label level;
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
	
	//BLOCKS
	
	public LoadAssets() {
		atlas = new TextureAtlas(Gdx.files.internal(Const.ATLAS_PATH));
		skin = new Skin(Gdx.files.internal(Const.SKIN_PATH), atlas); 
		prefs = Gdx.app.getPreferences(Const.PREFERENCES);
		im = new Image();
		
	}

    public void loadIntroAssets() {
		//INTRO IMAGE
		intro = new Image(new Texture(Gdx.files.internal(Const.INTRO_PATH)));
		intro.setSize(.5f*Const.w, .5f*Const.h);
		intro.setPosition(.5f*Const.w, .5f*Const.h, 0);
    }


    public void loadMenuAssets() {
		//high Score Image
		trophey = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.TROPHEY_PATH)));
    	trophey.setSize(.15f*Const.w, .15f*Const.h);
		trophey.setPosition(.5f*Const.w, .9f*Const.h, 0);
		
		//high Score Text
		highScore = new Label(Integer.toString(getScores()), skin, Const.THEME + "default");
		highScore.setAlignment(Align.center);
		highScore.setSize(.25f*Const.w, .2f*Const.h);
		highScore.setPosition(.5f*Const.w, .75f*Const.h, 0);
		
		//Play Button
		playBtn = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.PLAYBTN_PATH)));
		playBtn.setSize(.6f*Const.w, .3f*Const.h);
		playBtn.setPosition(.5f*Const.w, .55f*Const.h, 0);

		//LeaderBoard Button
    	leadBtn = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.LEADBTN_PATH)));
    	leadBtn.setSize(.25f*Const.w, .2f*Const.h);
		leadBtn.setPosition(.35f*Const.w, .35f*Const.h, 0);
		
		//Settings Button
    	settBtn = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.SETTBTN_PATH)));
		settBtn.setSize(.25f*Const.w, .15f*Const.h);
		settBtn.setPosition(.65f*Const.w, .35f*Const.h, 0);

		//Footer
    	footer = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.TITLE_PATH)));
		footer.setSize(.3f*Const.w, .05f*Const.h);
		footer.setPosition(.5f*Const.w, .05f*Const.h, 0);

    }


    public void loadGameAssets(TetrisLogic myGame){
		int tam_x = (int) (0.15*Const.w/4);
		int tam_y = (int) (0.15*Const.h/4);
		
    	header = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.TITLE_PATH)));
		header.setSize(.3f*Const.w, .05f*Const.h);
		header.setPosition(.5f*Const.w, .96f*Const.h, 0);
		
		//Label - Score
		scoreLabel = new Label("Score", skin, Const.THEME + "default");
		scoreLabel.setAlignment(Align.center);
		scoreLabel.setSize(tam_x*10, tam_y);
		scoreLabel.setPosition(0.7f*Const.w, 0.5f*Const.h, 0);	
		
		score = new Label(myGame.getScore(), skin, Const.THEME + "default");
		score.setAlignment(Align.center);
		score.setSize(tam_x*10, tam_y);
		score.setPosition(0.7f*Const.w, 0.43f*Const.h , 0);	
		
		//Label - Level
		levelLabel = new Label("Level", skin, Const.THEME + "default");
		levelLabel.setAlignment(Align.center);
		levelLabel.setSize(tam_x*10, tam_y);
		levelLabel.setPosition(0.7f*Const.w, 0.3f*Const.h, 0);	
		
		level = new Label(myGame.getLevel(), skin, Const.THEME + "default");
		level.setAlignment(Align.center);
		level.setSize(tam_x*10, tam_y);
		level.setPosition(0.7f*Const.w, 0.23f*Const.h , 0);	
    }
    
    public void loadSettingsAssets() {
    	
    	//Label Title
    	titleLabel = new Label("Settings", skin, Const.THEME + "big");
		titleLabel.setAlignment(Align.center);
		titleLabel.setSize(0.6f*Const.w, 0.2f*Const.h);
		titleLabel.setPosition(0.5f*Const.w, 0.8f*Const.h, 0);	
		
		//X Image
		x = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.CLOSE_PATH)));
		x.setSize(.1f*Const.w, .1f*Const.h);
		x.setPosition(.9f*Const.w, .9f*Const.h, 0);
		
		//Label - Music
		musicLabel = new Label("Sound", skin, Const.THEME + "small");
		musicLabel.setAlignment(Align.center);
		musicLabel.setSize(0.2f*Const.w, 0.1f*Const.h);
		musicLabel.setPosition(0.5f*Const.w, 0.5f*Const.h, 0);	

		//Image - Music
		if (Const.soundOn)
			music = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.SOUND_PATH)));
		else
			music = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.MUTE_PATH)));
		music.setSize(.15f*Const.w, .15f*Const.h);
		music.setPosition(.65f*Const.w, .5f*Const.h, 0);

		
		//Label - Theme
		themeLabel = new Label("Theme", skin, Const.THEME + "small");
		themeLabel.setAlignment(Align.center);
		themeLabel.setSize(0.2f*Const.w, 0.1f*Const.h);
		themeLabel.setPosition(0.5f*Const.w, 0.3f*Const.h, 0);	
		
		//Image - theme
		if (getTheme().equals("solar/") )
			theme = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.SOLAR_PATH)));
		else
			theme = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.DRACULA_PATH)));
		theme.setSize(.15f*Const.w, .15f*Const.h);
		theme.setPosition(.65f*Const.w, .3f*Const.h, 0);

    }


    public void loadGameOverAssets() {
		//Game Over
    	gameOverLabel = new Label("Game Over", skin, Const.THEME + "default");
		gameOverLabel.setAlignment(Align.center);
		gameOverLabel.setSize(Const.w, 0.2f*Const.h);
		gameOverLabel.setPosition(0.5f*Const.w, 0.85f*Const.h, 0);	
		
		//secondary Label
		secundaryLabel = new Label("", skin, Const.THEME + "small");
		secundaryLabel.setAlignment(Align.center);
		secundaryLabel.setSize(Const.w, 0.2f*Const.h);
		secundaryLabel.setPosition(0.5f*Const.w, 0.75f*Const.h, 0);	
		
		//title bar
    	header = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.TITLE_PATH)));
    	header.setSize(.3f*Const.w, .05f*Const.h);
    	header.setPosition(.5f*Const.w, .95f*Const.h, 0);
		
		//home bar
		home = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.HOME_PATH)));
		home.setSize(.15f*Const.w, .15f*Const.h);
		home.setPosition(.5f*Const.w, .5f*Const.h, 0);
		
		
		//replay bar
		replay = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.REPLAY_PATH)));
		replay.setSize(.15f*Const.w, .15f*Const.h);
		replay.setPosition(.5f*Const.w, .3f*Const.h, 0);		
    }

    
    
    /**
	 * PREFERENCES
	 * @return
	 */
	//HIGHSCORE
	public int getScores() {
		return prefs.getInteger("score");//scores.readString();
	}

	public void setScores(int sc) {
		this.prefs.putInteger("score", sc);
		prefs.flush();
	}
	
	//SOUND ON
	public boolean getSoundOn() {
		return prefs.getBoolean("soundOn");			
	}

	public void setSoundOn(boolean s) {
		this.prefs.putBoolean("soundOn", s);
		prefs.flush();
	}
	
	//THEME
	public String getTheme() {
		return prefs.getString("theme");
	}

	public void setTheme(String s) {
		this.prefs.putString("theme", s);
		prefs.flush();
		if (s != "dracula/")
		{
			// BACKGROUND COLOR 
			Const.setBG_COLOR(1f,1f,1f,1f);
			Const.setTETRIS_COLOR(0f,0f,0f,1f);
		}
		else if (s != "solar/")
		{
			// BACKGROUND COLOR 
			Const.setBG_COLOR(0f,0f,0f,1f);
			Const.setTETRIS_COLOR(1f,1f,1f,1f);
		}
		Const.THEME = s;
	}

    
    
    public static void dispose() {
    	
    }

	public Skin getSkin() {
		return this.skin;
	}
	
	
	// INTRO
	public Image getIntroImg() {
		return intro;
	}
	
	// MENU
	public Image getTrophey() {
		return trophey;
	}

	public Image getPlayBtn() {
		return playBtn;
	}

	public Image getLeaderBtn() {
		return leadBtn;
	}

	public Image getSettBtn() {
		return settBtn;
	}

	public Image getFooter() {
		return footer;
	}

	public Label getHighScore() {
		return this.highScore;
	}

	//Settings
	public Label getTitleLabel() {
		return titleLabel;
	}

	public Image getX() {
		return x;
	}
	
	public Label getMusicLabel() {
		return musicLabel;
	}

	public Image getImageMusic() {
		return music;
	}
	
	public Label getThemeLabel() {
		return themeLabel;
	}

	public Image getImageTheme() {
		return theme;
	}
	

	//GAME
	public Image getGameHeader() {
		return header;
	}

	public Label getScoreLabel() {
		return scoreLabel;
	}

	public Label getScore() {
		return score;
	}

	public Label getLevelLabel() {
		return levelLabel;
	}

	public Label getLevel() {
		return level;
	}


	
	//GAME OVER
	public Label getGameOverLabel() {
		return gameOverLabel;
	}

	public Label getSecundaryLabel() {
		return secundaryLabel;
	}

	public Image getHeader() {
		return header;
	}
	
	public Image getHome() {
		return home;
	}

	public Image getReplay() {
		return replay;
	}

	public void loadBlockImgs() {
		white = new Image(new Texture(Gdx.files.internal(Const.WHITE_BLOCK)));
		black = new Image(new Texture(Gdx.files.internal(Const.BLACK_BLOCK)));
		lightblue = new Image(new Texture(Gdx.files.internal(Const.LIGHTBLUE_BLOCK)));
		darkblue = new Image(new Texture(Gdx.files.internal(Const.DARKBLUE_BLOCK)));
		green = new Image(new Texture(Gdx.files.internal(Const.GREEN_BLOCK)));
		red = new Image(new Texture(Gdx.files.internal(Const.RED_BLOCK)));
		purple = new Image(new Texture(Gdx.files.internal(Const.PURPLE_BLOCK)));
		yellow = new Image(new Texture(Gdx.files.internal(Const.YELLOW_BLOCK)));
		orange = new Image(new Texture(Gdx.files.internal(Const.ORANGE_BLOCK)));
	}

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
