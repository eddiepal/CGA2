package wit.cgd.xando.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import wit.cgd.xando.game.XandOMain;

public class DesktopLauncher {
	//test
	private static boolean  rebuildAtlas        = true;
	private static boolean  drawDebugOutline    = true;
	
    public static void main(String[] args) {
        if (rebuildAtlas) {
            Settings settings = new Settings();
            settings.maxWidth = 1024;
            settings.maxHeight = 1024;
            settings.debug = drawDebugOutline;
            TexturePacker.process(settings, "assets-raw/images", "../android/assets/images",
                    "board.atlas");
        }
		
		
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "X and O";
        config.width = 800;
        config.height = 480;
        new LwjglApplication(new XandOMain(), config);
    }
}

