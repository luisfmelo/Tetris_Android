package com.luis.teresa.tetris.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.LoadAssets;

public class TetrisRenderer { 

	private TetrisLogic myGame;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private LoadAssets myAssets;
	Stage stage = new Stage(new ScreenViewport());
	int w = Gdx.graphics.getWidth();
	int h = Gdx.graphics.getHeight();
	private Label secundaryLabel;
	private Label gameOverLabel;
	private Image home;
	private Image header;
	private Image GOheader;
	private Image replay;

	public TetrisRenderer(TetrisLogic g) {
		myGame = g;
		cam = new OrthographicCamera();
		cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		myAssets = new LoadAssets();
		myAssets.loadGameAssets(myGame);
	}

	public void render() {
		
		Gdx.gl.glClearColor(Const.BACKGROUND_COLOR[0],Const.BACKGROUND_COLOR[1],Const.BACKGROUND_COLOR[2], Const.BACKGROUND_COLOR[3]);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		header = myAssets.getGameHeader();
		
		stage.addActor(header);		
		renderBoard();
		renderFutureShape();
		renderScore();		
	}
	
	private void renderBoard() {
		char[][] c = myGame.getBoard();
		
		for (int i = 3; i < myGame.getRows()-1; i++)
			for (int j = 0; j < myGame.getCols(); j++)
				fillCell(i,j,c[i][j]);
	}

	private void fillCell(int i, int j, char s) {
		int tam_x = (int) (0.5*w/12);
		int tam_y = (int) (0.8*h/22);
		int x0 = (int) (0.05*w);
		int y0 = (int) (0.05*h);
				
		//Tells shapeRenderer to begin drawing filled shapes
		shapeRenderer.begin(ShapeType.Filled);
		
		// Chooses RGB Color
		shapeRenderer.setColor(getColor(s)[0], getColor(s)[1], getColor(s)[2], Const.TETRIS_COLOR[3]);
		
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
		shapeRenderer.setColor(Const.TETRIS_COLOR[0], Const.TETRIS_COLOR[1], Const.TETRIS_COLOR[2], .5f);
		
		// Draws the rectangle from myGame (Using ShapeType.Line)
		shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);

		shapeRenderer.end();		

	}

	private void renderFutureShape() {
		Shape s = myGame.getFutureShape();
				
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				fillFutureShapeCell(i,j,s);		
	}
	
	private void fillFutureShapeCell(int i, int j, Shape s) {
		int tam_x = (int) (0.15*w/4);
		int tam_y = (int) (0.15*h/4);
		int x0 = (int) (0.6*w);
		int y0 = (int) (0.15*h);
				
		//Tells shapeRenderer to begin drawing filled shapes
		shapeRenderer.begin(ShapeType.Filled);
		
		// Chooses RGB Color
		if ( s.getMatrix()[i][j] == '1' )
			shapeRenderer.setColor( getColor(s.getPieceShape().getLetter().charAt(0))[0], 
									getColor(s.getPieceShape().getLetter().charAt(0))[1], 
									getColor(s.getPieceShape().getLetter().charAt(0))[2], 1);
		else
			shapeRenderer.setColor( getColor(s.getMatrix()[i][j])[0], 
									getColor(s.getMatrix()[i][j])[1], 
									getColor(s.getMatrix()[i][j])[2], 1);
		
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
		shapeRenderer.setColor(Const.TETRIS_COLOR[0], Const.TETRIS_COLOR[1], Const.TETRIS_COLOR[2], .5f);
		
		// Draws the rectangle from myGame (Using ShapeType.Line)
		shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);

		shapeRenderer.end();			
	}

	private void renderScore() {
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		int tam_x = (int) (0.15*w/4);
		int tam_y = (int) (0.15*h/4);
				
		//Label - Score
		Label scoreLabel = new Label("Score", myAssets.getSkin(), Const.THEME + "default");
		scoreLabel.setAlignment(Align.center);
		scoreLabel.setSize(tam_x*10, tam_y);
		scoreLabel.setPosition(0.7f*w, 0.5f*h, 0);	
		
		Label score = new Label(myGame.getScore(), myAssets.getSkin(), Const.THEME + "default");
		score.setAlignment(Align.center);
		score.setSize(tam_x*10, tam_y);
		score.setPosition(0.7f*w, 0.43f*h , 0);	
		
		//Label - Level
		Label levelLabel = new Label("Level", myAssets.getSkin(), Const.THEME + "default");
		levelLabel.setAlignment(Align.center);
		levelLabel.setSize(tam_x*10, tam_y);
		levelLabel.setPosition(0.7f*w, 0.3f*h, 0);	
		
		Label level = new Label(myGame.getLevel(), myAssets.getSkin(), Const.THEME + "default");
		level.setAlignment(Align.center);
		level.setSize(tam_x*10, tam_y);
		level.setPosition(0.7f*w, 0.23f*h , 0);	
		
		stage.addActor(score);
		stage.addActor(scoreLabel);
		stage.addActor(level);
		stage.addActor(levelLabel);
//		st.draw();
		
		
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
			rgb[0] = Const.BACKGROUND_COLOR[0];
			rgb[1] = Const.BACKGROUND_COLOR[1];
			rgb[2] = Const.BACKGROUND_COLOR[2];
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
			rgb[0] = Const.TETRIS_COLOR[0];// (float) 0/255;
			rgb[1] = Const.TETRIS_COLOR[1];// (float) 0/255;
			rgb[2] = Const.TETRIS_COLOR[2];// = (float) 0/255;	
			break;
		}
		
		return rgb;
	}
	
	public void renderGameOverScreen(String score, boolean newHighScore ) {
		myAssets.loadGameOverAssets();
		
		//top bar
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(150f/255, 150f/255, 150f/255, 0.5f);
		Rectangle rect = new Rectangle(0, 0.1f*h, w, 0.2f*h);
		shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		shapeRenderer.end();
		
		//Game Over
		gameOverLabel = myAssets.getGameOverLabel();
		secundaryLabel = myAssets.getSecundaryLabel();
		
		if(newHighScore)//NEW HIGH SCORE
			secundaryLabel.setText("New High Score: " + Integer.toString(myAssets.getScores()));
		else //Game Over
			secundaryLabel.setText("Try Again!");
		
		//title bar
    	//GOheader = myAssets.getHeader();
		//home bar
		home = myAssets.getHome();
		//replay bar
		replay = myAssets.getReplay();

		stage.addActor(home);
		stage.addActor(secundaryLabel);
		stage.addActor(gameOverLabel);
		stage.addActor(replay);
	}
}