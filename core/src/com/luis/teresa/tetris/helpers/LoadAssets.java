package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
	
	//INTRO
	private Image intro;
	
	//MENU
	private Image trophey;
	private FileHandle scores;
	private Label highScore;
	private Image playBtn;
	private Image leadBtn;
	private Image settBtn;
	private Image footer;
	
	public LoadAssets() {
		atlas = new TextureAtlas(Gdx.files.internal(Const.ATLAS_PATH));
		skin = new Skin(Gdx.files.internal(Const.SKIN_PATH), atlas);
	}

    public void loadIntroAssets() {
    	
		//INTRO IMAGE
		intro = new Image(new Texture(Gdx.files.internal(Const.INTRO_PATH)));
		intro.setSize(.5f*width, .5f*height);
		intro.setPosition(.5f*width, .5f*height, 0);
    }


    public void loadMenuAssets() {

		//high Score Image
		trophey = new Image(new Texture(Gdx.files.internal(Const.TROPHEY_PATH)));
    	trophey.setSize(.15f*width, .15f*height);
		trophey.setPosition(.5f*width, .9f*height, 0);
		
		//high Score Text
		scores = Gdx.files.internal(Const.LOCAL_SCORE_PATH);
		highScore = new Label(getScores(), skin, "b_default");
		highScore.setAlignment(Align.center);
		highScore.setSize(.25f*width, .2f*height);
		highScore.setPosition(.5f*width, .75f*height, 0);
		
		//Play Button
		playBtn = new Image(new Texture(Gdx.files.internal(Const.PLAYBTN_PATH)));
		playBtn.setSize(.6f*width, .3f*height);
		playBtn.setPosition(.5f*width, .55f*height, 0);

		//LeaderBoard Button
    	leadBtn = new Image(new Texture(Gdx.files.internal(Const.LEADBTN_PATH)));
    	leadBtn.setSize(.25f*width, .2f*height);
		leadBtn.setPosition(.35f*width, .35f*height, 0);
		
		//Settings Button
    	settBtn = new Image(new Texture(Gdx.files.internal(Const.SETTBTN_PATH)));
		settBtn.setSize(.25f*width, .15f*height);
		settBtn.setPosition(.65f*width, .35f*height, 0);

		//Footer
    	footer = new Image(new Texture(Gdx.files.internal(Const.FOOTER_PATH)));
		footer.setSize(.3f*width, .05f*height);
		footer.setPosition(.5f*width, .05f*height, 0);

    }

    
    public static void dispose() {
        // We must dispose of the texture when we are finished.
       // texture.dispose();
    }

	public Skin getSkin() {
		return this.skin;
	}

	
	// INTRO
	public Image getIntroImg() {
		return intro;
	}
	
	// MENU
	public String getScores() {
		System.out.println(scores.readString());
		return scores.readString();
	}

	public void setScores(String s) {
		this.scores.writeString(s, false);;
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


}
