package wit.cgd.xando.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

import wit.cgd.xando.game.util.Constants;

public class Assets implements Disposable, AssetErrorListener {

    private static final String TAG = WorldRenderer.class.getName();

    public static final Assets instance = new Assets();
    private AssetManager assetManager;


    public Asset board;
    public Asset x;
    public Asset o;



    public class Asset {

        public final AtlasRegion region;

        public Asset(TextureAtlas atlas, String imageName) {
            region = atlas.findRegion(imageName);
        }
    }
   

    private Assets() {
    }

    public void init(AssetManager assetManager) {
    	

        this.assetManager = assetManager;
        
        // set asset manager error handler
        this.assetManager.setErrorListener(this);
        
        // load texture atlas
        this.assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        // start loading assets and wait until finished
        this.assetManager.finishLoading();

        Gdx.app.debug(TAG, "# of assets loaded: " + this.assetManager.getAssetNames().size);
        for (String a: this.assetManager.getAssetNames())
            Gdx.app.debug(TAG, "asset: " + a);

        TextureAtlas atlas = this.assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
        
        // enable texture filtering for pixel smoothing
        for (Texture t: atlas.getTextures())
            t.setFilter(TextureFilter.Linear, TextureFilter.Linear);



        // build game resource objects
        board = new Asset(atlas, "board");
        x = new Asset(atlas, "x");
        o = new Asset(atlas, "o");

        this.assetManager.finishLoading();



    }

    @Override
    public void dispose() {

        assetManager.dispose();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {

        Gdx.app.error(TAG, "Couldn't load asset '" + asset + "'", (Exception) throwable);
    }
}