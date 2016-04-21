package com.luis.teresa.tetris.accessors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import aurelienribon.tweenengine.TweenAccessor;

/**
 * Esta classe serve para dar efeitos do tipo fade in fade out
 * Ela vai aceder ao alpha (opacidade) da imagem e afins... 
 * Ver a documentação pra perceber melhor
 * @author luism
 */
public class SpriteAccessor implements TweenAccessor<Sprite> {

	public static final int ALPHA = 0; //definido pelo tweenType
	
	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
		
		switch(tweenType){	
		case ALPHA:
			returnValues[0] = target.getColor().a;
			return 1;
		default:
			assert false; //se nos é passado um tweenType que nos nao conhecemos -> dá "erro" 
			return -1;    //-> este caso nao deve acontecer... mais vale prevenir do que remediar
		}	
		
		//o retorno vai ser o numero de valores que eu vou por no returnValues
	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		
		switch(tweenType){	
		case ALPHA:
			target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]); //por causa do returnValues[0]
			break;
		default:
			assert false; //se nos é passado um tweenType que nos nao conhecemos -> dá "erro" 
			              //-> este caso nao deve acontecer... mais vale prevenir do que remediar
		}	
	}

}
