package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

public class LoadAssets {
	
	//COMMON
	private TextureAtlas atlas;
	private Skin skin;
	private float width = Gdx.graphics.getWidth();
	private float height = Gdx.graphics.getHeight();
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
	
	public LoadAssets() {
		atlas = new TextureAtlas(Gdx.files.internal(Const.ATLAS_PATH));
		skin = new Skin(Gdx.files.internal(Const.SKIN_PATH), atlas); 
		prefs = Gdx.app.getPreferences(Const.PREFERENCES);
	}

    public void loadIntroAssets() {
    	
		//INTRO IMAGE
		intro = new Image(new Texture(Gdx.files.internal(Const.INTRO_PATH)));
		intro.setSize(.5f*width, .5f*height);
		intro.setPosition(.5f*width, .5f*height, 0);
    }


    public void loadMenuAssets() {

		//high Score Image
		trophey = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.TROPHEY_PATH)));
    	trophey.setSize(.15f*width, .15f*height);
		trophey.setPosition(.5f*width, .9f*height, 0);
		
		//high Score Text
		highScore = new Label(Integer.toString(getScores()), skin, Const.THEME + "default");
		highScore.setAlignment(Align.center);
		highScore.setSize(.25f*width, .2f*height);
		highScore.setPosition(.5f*width, .75f*height, 0);
		
		//Play Button
		playBtn = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.PLAYBTN_PATH)));
		playBtn.setSize(.6f*width, .3f*height);
		playBtn.setPosition(.5f*width, .55f*height, 0);

		//LeaderBoard Button
    	leadBtn = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.LEADBTN_PATH)));
    	leadBtn.setSize(.25f*width, .2f*height);
		leadBtn.setPosition(.35f*width, .35f*height, 0);
		
		//Settings Button
    	settBtn = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.SETTBTN_PATH)));
		settBtn.setSize(.25f*width, .15f*height);
		settBtn.setPosition(.65f*width, .35f*height, 0);

		//Footer
    	footer = new Image(new Texture(Gdx.files.internal(Const.THEME + Const.TITLE_PATH)));
		footer.setSize(.3f*width, .05f*height);
		footer.setPosition(.5f*width, .05f*height, 0);

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

    
    public static void dispose() {
        // We must dispose of the texture when we are finished.
       // texture.dispose();
    }

	public Skin getSkin() {
		return this.skin;
	}

	//SETTINGS
	
	
	// INTRO
	public Image getIntroImg() {
		return intro;
	}
	
	// MENU
	
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
		return prefs.getString("theme");//scores.readString();
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

	public Music getMusic() {
		Music introMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/SFX_SpecialTetris.ogg"));
		return introMusic;
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

	public void changeTheme() {
		// TODO Auto-generated method stub
		
	}
}
