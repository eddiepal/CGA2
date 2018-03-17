package wit.cgd.xando.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import wit.cgd.xando.game.util.Constants;

public class WorldRenderer implements Disposable {
	
    private static final String TAG = WorldRenderer.class.getName();

    public OrthographicCamera   camera;
    private SpriteBatch         batch;
    private WorldController     worldController;
    
    public WorldRenderer(WorldController worldController) {
    	this.worldController = worldController;
    	init();
    }

	private void init() {
    	batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
	
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	private OrthographicCamera  cameraGUI;
	public void resize(int width, int height) {
        camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / (float) height) * (float) width;
        camera.update();
        cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
        cameraGUI.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT / (float) height) * (float) width;
        cameraGUI.position.set(cameraGUI.viewportWidth / 2, cameraGUI.viewportHeight / 2, 0);
        cameraGUI.update();
	}
	
	public void render() {
        //worldController.cameraHelper.applyTo(camera);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //worldController.level.render(batch);
        batch.end();
	}

	
}
