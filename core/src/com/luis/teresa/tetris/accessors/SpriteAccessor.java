package com.luis.teresa.tetris.accessors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import aurelienribon.tweenengine.TweenAccessor;

/**
 * This class add effects to Sprite by changing the color values (RGB)
 * @author Luis
 * @author Teresa
 */
public class SpriteAccessor implements TweenAccessor<Sprite> {

	public static final int ALPHA = 0; //defined by tweenType
	
	/**
	 * Method responsible to get the current value of the <code>tweenType</code>
	 * @param sprite 		sprite who will suffer the change
	 * @param tweenType		type of change (RGB parameters)
	 * @param vals			current values of RGB color
	 */
	@Override
	public int getValues(Sprite sprite, int tweenType, float[] vals) {
		
		switch(tweenType){	
		case ALPHA:
			vals[0] = sprite.getColor().a;
			return 1;
		default:
			assert false; // if we do not know the tweenType -> error 
		}	
		return -1;    // this case should not happen ... better safe than sorry
	}

	/**
	 * Method responsible to set the new value for the <code>tweenType</code> specified
	 * @param sprite 		sprite who will suffer the change
	 * @param tweenType		type of change (RGB parameters)
	 * @param vals			new values for RGB color
	 */
	@Override
	public void setValues(Sprite sprite, int tweenType, float[] vals) {
		
		switch(tweenType){	
		case ALPHA:
			sprite.setColor(sprite.getColor().r, sprite.getColor().g, sprite.getColor().b, vals[0]); //por causa do vals[0]
			break;
		default:
			assert false;
		}	
	}

}