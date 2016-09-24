package hakito.pencilrunner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Oleg on 18.09.2016.
 */
public class Assets {

    public static TextureRegion ball, ball_lights, ball_face, pencil, line, sharp, cloud, cloud1, cloud2, sun, mountains, mountains2, sky, boost, spinBack, block;
    public static Skin skin;

    public static void load()
    {
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

        TextureAtlas atlas = new TextureAtlas("images/images.atlas");
        ball_face = atlas.findRegion("ball_face");
        ball_lights = atlas.findRegion("ball_lights");
        block = atlas.findRegion("block");
        spinBack = atlas.findRegion("spinBack");
        boost = atlas.findRegion("boost");
        sharp = atlas.findRegion("sharp");
        ball = atlas.findRegion("ball");
        line = atlas.findRegion("line");
        pencil = atlas.findRegion("pencil");
        cloud = atlas.findRegion("cloud");
        cloud1 = atlas.findRegion("cloud1");
        cloud2 = atlas.findRegion("cloud2");
        sun = atlas.findRegion("sun");
        mountains = atlas.findRegion("mountains");
        mountains2 = atlas.findRegion("mountains2");
        sky = atlas.findRegion("sky");
    }
}
