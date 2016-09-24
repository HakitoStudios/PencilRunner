package hakito.pencilrunner.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import hakito.pencilrunner.MyGdxGame;

public class DesktopLauncher {
	static final boolean pack =true;


	public static void main (String[] arg) {
		if(pack) {
			TexturePacker.Settings settings = new TexturePacker.Settings();
			settings.maxHeight = settings.maxWidth = 2048;
			settings.filterMag = settings.filterMin = Texture.TextureFilter.Linear;
			TexturePacker.process(settings, "images_raw", "images", "images");
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=854;
		config.height=480;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
