package com.luis.teresa.tetris.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.luis.teresa.tetris.helpers.Const;

public class TetrisRenderer { 

	private TetrisLogic myGame;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	public TetrisRenderer(TetrisLogic world) {
		myGame = world;
		cam = new OrthographicCamera();
		cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
	}

	public void render() {		
		//renderSquare();
		renderBoard();
		//renderFutureShape();
		
	}

	private void renderBoard() {
		/*
		 * 1. We draw a green background. This prevents flickering.
		 */

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/*
		 * 2. For each cell we draw something
		 */
		char[][] c = myGame.getBoard();
		
		for (int i = 0; i < myGame.getRows(); i++)
			for (int j = 0; j < myGame.getCols(); j++)
				fillCell(i,j,c[i][j]);
	}
	
	private void fillCell(int i, int j, char s) {
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		int tam_x = (int) (0.7*w/12);
		int tam_y = (int) (0.7*h/22);
		int x0 = (int) (0.15*w);
		int y0 = (int) (0.15*h);
				
		//Tells shapeRenderer to begin drawing filled shapes
		shapeRenderer.begin(ShapeType.Filled);
		
		// Chooses RGB Color
		shapeRenderer.setColor(getColor(s)[0], getColor(s)[1], getColor(s)[2], 1);
		
		Rectangle rect = new Rectangle(x0+j*tam_x, y0+i*tam_y, tam_x, tam_y);
		
		// Draws the rectangle from myGame (Using ShapeType.Filled)
		shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);

		// Tells the shapeRenderer to finish rendering
		// We MUST do this every time.
		shapeRenderer.end();

		/*
		 * 3. We draw the rectangle's outline
		 */

		// Tells shapeRenderer to draw an outline of the following shapes
		shapeRenderer.begin(ShapeType.Line);

		// Chooses RGB Color of 255, 109, 120 at full opacity
		shapeRenderer.setColor(255 / 255.0f, 255 / 255.0f, 255 / 255.0f, 1);
		
		// Draws the rectangle from myGame (Using ShapeType.Line)
		shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);

		shapeRenderer.end();		

	}

	private float[] getColor(char c)
	{
		float[] rgb = new float[3];
		switch (c) {
		case '1': 
			rgb[0] = Const.redColor;
			rgb[1] = Const.greenColor;
			rgb[2] = Const.blueColor;
			break;
		case ' ': 
			rgb[0] = (float)255/255;
			rgb[1] = (float)255/255;
			rgb[2] = (float)255/255;
			break;
		case 'I': 
			rgb[0] = 0;
			rgb[1] = (float)240/255;
			rgb[2] = (float)240/255;
			break;
		case 'S':
			rgb[0] = 0;
			rgb[1] = (float)240/255;
			rgb[2] = (float)0/255;
			break;
		case 'Z':
			rgb[0] = (float) 240/255;
			rgb[1] = (float) 0/255;
			rgb[2] = (float) 0/255;
			break;
		case 'T':
			rgb[0] = (float) 150/255;
			rgb[1] = (float) 0/255;
			rgb[2] = (float) 240/255;
			break;
		case 'O':
			rgb[0] = (float) 240/255;
			rgb[1] = (float) 240/255;
			rgb[2] = (float) 0/255;
			break;
		case 'L':
			rgb[0] = (float) 240/255;
			rgb[1] = (float) 150/255;
			rgb[2] = (float) 0/255;
			break;
		case 'J':
			rgb[0] = (float) 0/255;
			rgb[1] = (float) 0/255;
			rgb[2] = (float) 240/255;			
			break;
		default:
			rgb[0] = (float) 0/255;
			rgb[1] = (float) 0/255;
			rgb[2] = (float) 0/255;	
			break;
		}
		
		return rgb;
	}
	
	/*
	private void renderSquare() {
		/*
		 * 1. We draw a black background. This prevents flickering.
		 *

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/*
		 * 2. We draw the Filled rectangle
		 *

		// Tells shapeRenderer to begin drawing filled shapes
		shapeRenderer.begin(ShapeType.Filled);

		// Chooses RGB Color of 87, 109, 120 at full opacity
		shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

		// Draws the rectangle from myGame (Using ShapeType.Filled)
		shapeRenderer.rect(myGame.getRect().x, myGame.getRect().y,
				myGame.getRect().width, myGame.getRect().height);

		// Tells the shapeRenderer to finish rendering
		// We MUST do this every time.
		shapeRenderer.end();

		/*
		 * 3. We draw the rectangle's outline
		 *

		// Tells shapeRenderer to draw an outline of the following shapes
		shapeRenderer.begin(ShapeType.Line);

		// Chooses RGB Color of 255, 109, 120 at full opacity
		shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

		// Draws the rectangle from myGame (Using ShapeType.Line)
		shapeRenderer.rect(myGame.getRect().x, myGame.getRect().y,
				myGame.getRect().width, myGame.getRect().height);

		shapeRenderer.end();		
	}*/
}
