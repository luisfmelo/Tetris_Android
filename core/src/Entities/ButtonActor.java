package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ButtonActor extends Image{
	
	public ButtonActor(Texture tex) {
		super(tex);
		setBounds(getX(), getY(), getWidth(), getHeight());
		setTouchable(Touchable.enabled);
		
		//addListener(new InputListener())
	}
	
}
