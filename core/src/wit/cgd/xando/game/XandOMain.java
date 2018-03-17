package wit.cgd.xando.game;

import com.badlogic.gdx.Application;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*import ie.wit.cgd.bunnyhop.game.Assets;
import ie.wit.cgd.bunnyhop.game.WorldController;
import ie.wit.cgd.bunnyhop.game.WorldRenderer;*/

public class XandOMain extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);             // Set Libgdx log level to DEBUG

        Assets.instance.init(new AssetManager());               // Load assets

/*        worldController = new WorldController();                // Initialize controller and renderer
        worldRenderer = new WorldRenderer(worldController);

        paused = false;                                         // Game world is active on start
*/	}

	@Override
	public void render () {
/*		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
		

	        // Update game world by the time that has passed since last rendered frame.
	        // worldController.update(Gdx.graphics.getDeltaTime());

	        // Sets the clear screen color to: Cornflower Blue
	        Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);

	        // Clears the screen
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	        // Render game world to screen
/*	        worldRenderer.render();
	        
	        if (!paused) {           // Do not update game world when paused.
	            // Update game world by the time that has passed since last rendered frame.
	            worldController.update(Gdx.graphics.getDeltaTime());
	        }*/
	    }
	
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
