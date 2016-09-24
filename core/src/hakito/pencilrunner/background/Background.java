package hakito.pencilrunner.background;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import hakito.pencilrunner.Assets;
import hakito.pencilrunner.Constants;

/**
 * Created by Oleg on 08.08.2016.
 */
public class Background {
    static class ParallaxLayer {


        static class ParallaxData {
            TextureRegion[] textureRegion;
            Rectangle bounds;
            float w, h;
            float k = 1;

            public void setTextureRegion(TextureRegion... textureRegion) {
                this.textureRegion = textureRegion;
            }

            public void setBounds(Rectangle bounds) {
                this.bounds = bounds;
            }

            public void setSize(float w, float h) {
                this.w = w;
                this.h = h;
            }

            public void setK(float k) {
                this.k = k;
            }
        }


        Array<Sprite> sprites;
        private ParallaxData data;

        public ParallaxLayer(ParallaxData data) {

            this.data = data;
            int n;//count of sprites
            n = (int) (Math.ceil(data.bounds.width / data.w) + 1);
            sprites = new Array<Sprite>(n);
            for (int i = 0; i < n; i++) {
                Sprite s = new Sprite(data.textureRegion[0]);
                s.setSize(data.w, data.h);
                s.setPosition(data.bounds.x + i * data.w, data.bounds.y);
                sprites.add(s);
            }
        }

        void updateViewverX(float viewerX) {
            float o = -viewerX * data.k;//offset with k
            float offset;//real offset. We can offset not more than w
            offset = o % data.w;
            int i = 0;
            for (Sprite s : sprites) {
                s.setX(data.bounds.x + i * data.w+offset);
                i++;
            }
        }

        void draw(SpriteBatch batch, float viewerX) {
            updateViewverX(viewerX);

            for (Sprite s : sprites) {
                s.draw(batch);
            }
        }
    }

    OrthographicCamera camera;
    Sprite sky;
    Sun sun;
    ParallaxLayer mountains;

    public Background() {
        camera = new OrthographicCamera();
        sky = new Sprite(Assets.sky);
        sun = new Sun();
    }

    public void resize(float w, float h) {
        camera.setToOrtho(false, Constants.WIDTH, Constants.WIDTH*h/w);
        sky.setSize(camera.viewportWidth, camera.viewportHeight);
        ParallaxLayer.ParallaxData data = new ParallaxLayer.ParallaxData();
        data.setTextureRegion(Assets.mountains);
        float hk = 0.3f;
        float sh = hk * camera.viewportHeight;
        data.setBounds(new Rectangle(0, 0, camera.viewportWidth, sh));
        data.setSize(sh * 2.56f, sh);
        data.setK(0.6f);

        mountains = new ParallaxLayer(data);
    }

    public void draw(SpriteBatch spriteBatch, float viewerX) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        sky.draw(spriteBatch);
        mountains.draw(spriteBatch, viewerX);
        spriteBatch.end();

        sun.draw(camera);
    }
}
