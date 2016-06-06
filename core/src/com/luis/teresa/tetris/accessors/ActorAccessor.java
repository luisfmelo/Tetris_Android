package com.luis.teresa.tetris.accessors;

import com.badlogic.gdx.scenes.scene2d.Actor;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * This class add effects to Actors by changing 
 * the color values (RGB) or opacity (ALFA)
 * @author Luis
 * @author Teresa
 */
public class ActorAccessor implements TweenAccessor<Actor> {

	public static final int ALPHA = 0; //defined by tweenType
	public static final int RGB = 1;

	/**
	 * Method responsible to get the current value of the <code>tweenType</code>
	 * @param actor 		actor who will suffer the change
	 * @param tweenType		type of change (RGB or alfa parameters)
	 * @param vals			current values of the parameter
	 */
	@Override
	public int getValues(Actor actor, int tweenType, float[] vals) {
		
		switch(tweenType){	
		case ALPHA:
			vals[0] = actor.getColor().a;
			return 1;
		case RGB:
			vals[0] = actor.getColor().r;
			vals[1] = actor.getColor().g;
			vals[2] = actor.getColor().b;
			return 3;
		default:
			assert false; // if we do not know the tweenType -> error 
		}
		return -1;    // this case should not happen ... better safe than sorry
	}

	/**
	 * Method responsible to set the new value for the <code>tweenType</code> specified
	 * @param actor 		actor who will suffer the change
	 * @param tweenType		type of change (RGB or alfa parameters)
	 * @param vals			new values for those parameter
	 */
	@Override
	public void setValues(Actor actor, int tweenType, float[] vals) {
		
		switch(tweenType){	
		case ALPHA:
			actor.setColor(actor.getColor().r, actor.getColor().g, actor.getColor().b, vals[0]); // update opacity
			break;
		case RGB:
			actor.setColor(vals[0],vals[1],vals[2], actor.getColor().a); // update rgb color
			break;
		default:
			assert false; 
		}	
	}
}
