package com.luis.teresa.tetris.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

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
		System.out.println("GameRenderer - render");
		
		//renderSquare();
		renderBoard();
		
	}

	private void renderBoard() {
		/*
		 * 1. We draw a green background. This prevents flickering.
		 */

		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/*
		 * 2. For each cell we draw something
		 */
		char[][] c = myGame.getBoard();
		
		for (int i = 0; i < 22; i++) {
			for (int j = 0; j < 12; j++) {
				if ( c[i][j] == 'X' )
					fillCell(i,j,0);
				else
					fillCell(i,j,1);
			}
		}

			}

	private void fillCell(int i, int j, int s) {
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		int tam_x = (int) (0.7*w/12);
		int tam_y = (int) (0.7*h/22);
		int x0 = (int) (0.15*w);
		int y0 = (int) (0.15*h);
				
		//Tells shapeRenderer to begin drawing filled shapes
		shapeRenderer.begin(ShapeType.Filled);

		if (s == 1)
		// Chooses RGB Color
			shapeRenderer.setColor(1 / 255.0f, 1 / 255.0f, 255 / 255.0f, 1);
		else
			shapeRenderer.setColor(255 / 255.0f, 1 / 255.0f, 1 / 255.0f, 1);
		
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
		if (s == 0)
			// Chooses RGB Color
				shapeRenderer.setColor(255 / 255.0f, 1 / 255.0f, 1 / 255.0f, 1);
			else
				shapeRenderer.setColor(1 / 255.0f, 1 / 255.0f, 1 / 255.0f, 1);
		// Draws the rectangle from myGame (Using ShapeType.Line)
		shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);

		shapeRenderer.end();		

	}

	private void renderSquare() {
		/*
		 * 1. We draw a black background. This prevents flickering.
		 */

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/*
		 * 2. We draw the Filled rectangle
		 */

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
		 */

		// Tells shapeRenderer to draw an outline of the following shapes
		shapeRenderer.begin(ShapeType.Line);

		// Chooses RGB Color of 255, 109, 120 at full opacity
		shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

		// Draws the rectangle from myGame (Using ShapeType.Line)
		shapeRenderer.rect(myGame.getRect().x, myGame.getRect().y,
				myGame.getRect().width, myGame.getRect().height);

		shapeRenderer.end();		
	}
}
