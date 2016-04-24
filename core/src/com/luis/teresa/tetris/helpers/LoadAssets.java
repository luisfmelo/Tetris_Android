package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LoadAssets {
	private TextureAtlas atlas;
	private Skin skin;
	
	private Sprite trophey;
	private Sprite playBtn;
	private Sprite leadBtn;
	private Sprite settBtn;
	private FileHandle scores;
	

    public void loadMenuAssets() {
		atlas = new TextureAtlas(Gdx.files.internal(Const.ATLAS_PATH));
		skin = new Skin(Gdx.files.internal(Const.SKIN_PATH), atlas);
    	//trophey = new Sprite(Gdx.files.internal(Const.TROPHEY_PATH));
    	//playBtn = new Sprite(Gdx.files.internal(Const.PLAYBTN_PATH));
    	//leadBtn = new Sprite(Gdx.files.internal(Const.LEADBTN_PATH));
    	//settBtn = new Sprite(Gdx.files.internal(Const.SETTBTN_PATH));
    	
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
       // texture.dispose();
    }

	public Skin getSkin() {
		return this.skin;
	}

}
