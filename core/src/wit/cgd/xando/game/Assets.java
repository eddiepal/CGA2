package wit.cgd.xando.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import wit.cgd.xando.game.util.Constants;



public class Assets implements Disposable, AssetErrorListener {

    public static final String  TAG         = Assets.class.getName();

    public static final Assets  instance    = new Assets();
    private AssetManager        assetManager;

    public AssetBoard            board;  
    public AssetX                x;
    public AssetO                o;
    
    private Assets() {}
    
    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        // set asset manager error handler
        assetManager.setErrorListener(this);
        // load texture atlas
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        // start loading assets and wait until finished
        assetManager.finishLoading();

        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
        for (String a : assetManager.getAssetNames())
            Gdx.app.debug(TAG, "asset: " + a);

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);

        // enable texture filtering for pixel smoothing
        for (Texture t : atlas.getTextures())
            t.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // create game resource objects

        board = new AssetBoard(atlas);
        x = new AssetX(atlas);
        o = new AssetO(atlas);
    }
    
    public class AssetBoard {
        public final AtlasRegion    board;

        public AssetBoard(TextureAtlas atlas) {
            board = atlas.findRegion("board");
        }
    }
    
    public class AssetX {
        public final AtlasRegion    playerX;
        
        public AssetX(TextureAtlas atlas) {
            playerX = atlas.findRegion("playerX");
        }
    }
    
    public class AssetO {
        public final AtlasRegion    playerO;

        public AssetO(TextureAtlas atlas) {
            playerO = atlas.findRegion("playerO");
        }
    }

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
