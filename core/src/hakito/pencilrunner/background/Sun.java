package hakito.pencilrunner.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import hakito.pencilrunner.Assets;
import hakito.pencilrunner.UVSpriteBatch;

/**
 * Created by Oleg on 20.09.2016.
 */
public class Sun {

    private final UVSpriteBatch sunSpriteBatch;
    Sprite sun;

    public Sun() {
        sun = new Sprite(Assets.sun);
        sun.setSize(2, 2);
        sun.setPosition(2, 2);
        sun.setOriginCenter();

        sunSpriteBatch = new UVSpriteBatch(2, new ShaderProgram(Gdx.files.internal("shaders/sun.v"), Gdx.files.internal("shaders/sun.f"))) {
            @Override
            public void begin() {
                super.begin();
                getShader().setUniform1fv("u_ratio", new float[]{sun.getTexture().getWidth() / sun.getTexture().getHeight()}, 0, 1);
            }
        };
    }

    public void draw(Camera camera) {
        sun.rotate(0.1f);
        sunSpriteBatch.setProjectionMatrix(camera.combined);
        sunSpriteBatch.begin();
        sun.draw(sunSpriteBatch);
        sunSpriteBatch.end();
    }
}
