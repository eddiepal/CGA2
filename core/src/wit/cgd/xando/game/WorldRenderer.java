package wit.cgd.xando.game;

import wit.cgd.xando.game.WorldController;
import wit.cgd.xando.game.util.Constants;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable {

	@SuppressWarnings("unused")
	private static final String	TAG	= WorldRenderer.class.getName(); 


	public OrthographicCamera	camera;
	public OrthographicCamera	cameraGUI;

	private SpriteBatch			batch;
	private WorldController		worldController;

    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
        init();
    }
    
    private void init() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();
		cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		cameraGUI.position.set(0, 0, 0);
		cameraGUI.setToOrtho(true); // flip y-axis
		cameraGUI.update();
    }
    
    public void resize(int width, int height) {
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / (float) height) * (float) width;
		camera.update();
		worldController.viewportWidth = camera.viewportWidth;
		worldController.width = width;
		worldController.height = height;
		cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
		cameraGUI.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT / (float) height) * (float) width;
		cameraGUI.position.set(cameraGUI.viewportWidth / 2, cameraGUI.viewportHeight / 2, 0);
		cameraGUI.update();
     }

	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		worldController.board.render(batch);
		batch.end();
		
		// GUI rendering
		
		batch.setProjectionMatrix(cameraGUI.combined);
		batch.begin();
		if (worldController.board.gameState != Board.GameState.PLAYING) {
			float x = cameraGUI.viewportWidth / 2;
			float y = cameraGUI.viewportHeight / 2;
			BitmapFont fontGameOver = Assets.instance.fonts.defaultBig;
			fontGameOver.setColor(1, 0.75f, 0.25f, 1);
			String message = "X Won";
			if (worldController.board.gameState== worldController.board.gameState.O_WON) 
				message = "O Won";
			else if (worldController.board.gameState== worldController.board.gameState.DRAW)
				message = "Draw";
			fontGameOver.draw(batch, message, x, y, 0, Align.center, true);
			fontGameOver.setColor(1, 1, 1, 1);
		}
//			if (board.gameState != Board.GameState.PLAYING) {
//				timeLeftGameOverDelay -= deltaTime;
//				if (timeLeftGameOverDelay < 0) {
//					backToMenu();
//				}
//			}
		batch.end();
	}

    
    @Override public void dispose() {
        batch.dispose();
    }
}
