package com.luis.teresa.tetris.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.luis.teresa.tetris.helpers.Const;
import com.luis.teresa.tetris.helpers.LoadAssets;

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
	
	private void renderBoard() {
		//char[][] c = myGame.getBoard();
		Block[][] c = myGame.getBoard();

		for (int i = 3; i < myGame.getRows()-1; i++)
			for (int j = 0; j < myGame.getCols(); j++)
				fillCell(i,j,c[i][j]);
	}

	private void fillCell(int i, int j, Block s) {
		int tam_x = (int) (0.5*w/12);
		int tam_y = (int) (0.8*h/22);
		int x0 = (int) (0.05*w);
		int y0 = (int) (0.95*h);
			
		img =  myAssets.loadOneBlock(s.getColor());
		img.setSize(tam_x, tam_y);
		img.setPosition(x0+j*tam_x, y0-i*tam_y, 0);
		stage.addActor(img);
		stage.draw();
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
		int y0 = (int) (0.85*h);
		float[] temp;
		
		// Chooses RGB Color
		/*if ( s.getMatrix()[i][j] == '1' )
			temp = getColor(s.getPieceShape().getLetter().charAt(0));
		else
			temp = getColor(s.getMatrix()[i][j]);*/
		temp =getColor(s.getBlock(i, j));
		
		img = myAssets.loadOneBlock(Const.transform(temp));
		img.setSize(tam_x, tam_y);
		img.setPosition(x0+j*tam_x, y0-i*tam_y, 0);
		stage.addActor(img);
		stage.draw();
	}

	private void renderScore() {				
		//myAssets.loadGameAssets(myGame);
		
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
	private float[] getColor(Block c){
		return Const.getRGB(c.getColor());
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
	
	/*public void renderGameOverScreen(String score, boolean newHighScore ) {		
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
		home.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {					
				((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
			}
		});
		//replay bar
		replay = myAssets.getReplay();
		replay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {	
				System.out.println("hey");
				((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
			}
		});
		
		stage.draw();
		
		//top bar
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(150f/255, 150f/255, 150f/255, 0.5f);
		Rectangle rect = new Rectangle(0, 0.1f*h, w, 0.2f*h);
		shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		shapeRenderer.end();
		
		Stage st = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(st);
		st.addActor(home);
		st.addActor(secundaryLabel);
		st.addActor(gameOverLabel);
		st.addActor(replay);
		st.draw();

	}*/
}