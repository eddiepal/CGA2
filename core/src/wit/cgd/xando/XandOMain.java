package wit.cgd.xando;

import com.badlogic.gdx.Application;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;

import wit.cgd.xando.game.Assets;
import wit.cgd.xando.game.WorldController;
import wit.cgd.xando.game.WorldRenderer;

public class XandOMain extends Game {

	private static final String TAG = XandOMain.class.getName();
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	boolean paused;

	@Override
	public void create() {
		// Set Libgdx log level
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		// Load assets
		Assets.instance.init(new AssetManager());
		// Load preferences for audio settings and start playing music
		System.out.println("VVVVVVV");
		worldController = new WorldController(); // Initialize controller and renderer
		worldRenderer = new WorldRenderer(worldController);

		Gdx.app.debug(TAG, "Playing");
		// Start game at menu screen
		
		paused = false;

	}

	@Override
	public void render() {

		if (!paused) {
			worldController.update(Gdx.graphics.getRawDeltaTime());
		}
		
		Gdx.gl.glClearColor(0,1,1,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		worldRenderer.render();
		//System.out.println("pppppp");

	}
	
	@Override
	public void resume() {
		paused = false;
	}
	
	@Override
	public void pause() {
		paused = true;
	}
	
	
	@Override
	public void dispose() {
        worldRenderer.dispose();
        Assets.instance.dispose();
    }

}