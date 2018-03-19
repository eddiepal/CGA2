package wit.cgd.xando;

import com.badlogic.gdx.Application;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import wit.cgd.xando.game.Assets;
import wit.cgd.xando.game.WorldController;
import wit.cgd.xando.game.WorldRenderer;

public class XandOMain extends Game {

	private static final String TAG = XandOMain.class.getName();
	private WorldController worldController;
	private WorldRenderer worldRenderer;

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

	}

	@Override
	public void render() {
		// Update game world by the time that has passed since last rendered frame.
		// worldController.update(Gdx.graphics.getDeltaTime());
		// Render game world to screen
		System.out.println("yooooo");
		worldRenderer.render();
		System.out.println("pppppp");

	}
	
/*    public void dispose() {
        worldRenderer.dispose();
        Assets.instance.dispose();
    }*/

}