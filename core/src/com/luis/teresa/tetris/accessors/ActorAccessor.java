package com.luis.teresa.tetris.accessors;

import com.badlogic.gdx.scenes.scene2d.Actor;

import aurelienribon.tweenengine.TweenAccessor;

public class ActorAccessor implements TweenAccessor<Actor> {

	public static final int ALPHA = 0; //definido pelo tweenType
	public static final int RGB = 1;
	public static final int Y = 2;
	
	@Override
	public int getValues(Actor target, int tweenType, float[] returnValues) {
		
		switch(tweenType){	
		case ALPHA:
			returnValues[0] = target.getColor().a;
			return 1;
		case RGB:
			returnValues[0] = target.getColor().r;
			returnValues[1] = target.getColor().g;
			returnValues[2] = target.getColor().b;
			return 3;
		case Y:
			returnValues[0] = target.getY();
			return 1;
		default:
			assert false; //se nos é passado um tweenType que nos nao conhecemos -> dá "erro" 
			return -1;    //-> este caso nao deve acontecer... mais vale prevenir do que remediar
		}	
		
		//o retorno vai ser o numero de valores que eu vou por no returnValues
	}

	@Override
	public void setValues(Actor target, int tweenType, float[] newValues) {
		
		switch(tweenType){	
		case ALPHA:
			target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]); //por causa do returnValues[0]
			break;
		case RGB:
			target.setColor(newValues[0],newValues[1],newValues[2], target.getColor().a); //por causa do returnValues[0]
			break;
		case Y:
			target.setY(newValues[0]); // faz update do Y
			break;
		default:
			assert false; //se nos é passado um tweenType que nos nao conhecemos -> dá "erro" 
			              //-> este caso nao deve acontecer... mais vale prevenir do que remediar
		}	
	}
}
