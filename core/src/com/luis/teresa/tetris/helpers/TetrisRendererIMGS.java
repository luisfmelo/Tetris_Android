package com.luis.teresa.tetris.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.luis.teresa.tetris.logic.Block;
import com.luis.teresa.tetris.logic.Shape;
import com.luis.teresa.tetris.logic.TetrisLogic;

/**
 * This class is responsible to render tha game in the Screen
 * @author Luis
 * @author Teresa
 */
public class TetrisRendererIMGS { 
	private TetrisLogic myGame;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private LoadAssets myAssets;
	private Stage stage;
	int w = Gdx.graphics.getWidth();
	int h = Gdx.graphics.getHeight();
	private Image header;
	private Label scoreLabel;
	private Label level;
	private Label levelLabel;
	private Label score;
	private Image img;

	/**
	 * Constructor
	 * @param g			Game instance
	 * @param st		Stage in which we should render the Game.
	 * @param myAssets	Assets pre-loaded
	 */
	public TetrisRendererIMGS(TetrisLogic g, Stage st, LoadAssets myAssets) {
		this.myAssets = myAssets;
		myGame = g;
		cam = new OrthographicCamera();
		cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		stage = st;
		img = myAssets.im;
		
		//Label - Score
		scoreLabel = myAssets.getScoreLabel();	
		score = myAssets.getScore();
		
		//Label - Level
		levelLabel = myAssets.getLevelLabel();	
		level = myAssets.getLevel();
	}
	
	/**
	 * This method is responsible for rendering the Board, 
	 * the Future shape and other important assets
	 */
	public void render() {
		Gdx.gl.glClearColor(Const.BACKGROUND_COLOR[0],Const.BACKGROUND_COLOR[1],Const.BACKGROUND_COLOR[2], Const.BACKGROUND_COLOR[3]);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		header = myAssets.getGameHeader();
		
		renderBoard();
		renderFutureShape();
		renderScore();		
		stage.addActor(header);
		stage.draw();
	}
	
	/**
	 * This method is responsible for rendering the Board and all its blocks
	 */
	private void renderBoard() {
		Block[][] c = myGame.getBoard();

		for (int i = 3; i < myGame.getRows()-1; i++)
			for (int j = 0; j < myGame.getCols(); j++)
				fillCell(i,j,c[i][j]);
	}

	/**
	 * This method is responsible for filling 
	 * each block of the Board with the corresponding image
	 * @param i		Line index in Board matrix
	 * @param j		Column index in Board matrix
	 * @param s		Corresponding block 
	 */
	private void fillCell(int i, int j, Block s) {
		int tam_x = (int) (0.5 * w / 12);
		int tam_y = (int) (0.8 * h / 22);
		int x0 = (int) (0.05 * w);
		int y0 = (int) (0.95 * h);
			
		img =  myAssets.loadOneBlock(s.getColor());
		img.setSize(tam_x, tam_y);
		img.setPosition(x0 + j * tam_x, y0 - i * tam_y, 0);
		stage.addActor(img);
		stage.draw();
	}

	/**
	 * This method is responsible for rendering the Future Shape
	 */
	private void renderFutureShape() {
		Shape s = myGame.getFutureShape();
				
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				fillFutureShapeCell(i,j,s);		
	}
	
	/**
	 * This method is responsible for filling 
	 * each block of the Future Shape with the corresponding image
	 * @param i		Line index in Future Shape matrix
	 * @param j		Column index in Future Shape matrix
	 * @param s		Corresponding block 
	 */
	private void fillFutureShapeCell(int i, int j, Shape s) {
		int tam_x = (int) (0.15*w/4);
		int tam_y = (int) (0.15*h/4);
		int x0 = (int) (0.6*w);
		int y0 = (int) (0.85*h);
		float[] temp;
		
		temp =getColor(s.getBlock(i, j));
		
		img = myAssets.loadOneBlock(Const.transform(temp));
		img.setSize(tam_x, tam_y);
		img.setPosition(x0+j*tam_x, y0-i*tam_y, 0);
		stage.addActor(img);
		stage.draw();
	}

	/**
	 * This method is responsible for rendering the Score of 
	 * the current Game and other assets
	 */
	private void renderScore() {						
		//Label - Score
		scoreLabel = myAssets.getScoreLabel();	
		
		score = myAssets.getScore();
		score.setText(myGame.getScore());
				
		//Label - Level
		levelLabel = myAssets.getLevelLabel();	
		
		level = myAssets.getLevel();
		level.setText(myGame.getLevel());
		
		stage.addActor(score);
		stage.addActor(scoreLabel);
		stage.addActor(level);
		stage.addActor(levelLabel);	
	}
	
	/**
	 * Get the color of some Block
	 * @param c		Block in question
	 * @return		Matching Color
	 */
	private float[] getColor(Block c){
		return Const.getRGB(c.getColor());
	}
}