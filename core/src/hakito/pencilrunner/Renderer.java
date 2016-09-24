package hakito.pencilrunner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import hakito.pencilrunner.background.Background;
import hakito.pencilrunner.objects.GameObject;

/**
 * Created by Oleg on 18.09.2016.
 */
public class Renderer {
    SpriteBatch spriteBatch;
    OrthographicCamera camera;
    Background background;


    public Renderer() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        background = new Background();


    }

    void drawAll(Array<? extends GameObject> array, Batch batch)
    {
        for (GameObject o:array)
        {
            o.draw(batch);
        }
    }

    public void render(Controller controller, float d)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        controller.gameUI.draw(controller);

        camera.position.set(controller.ball.getX()+2, 0, 0);
        camera.update();

        background.draw(spriteBatch, camera.position.x);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        drawAll(controller.blocks, spriteBatch);
        drawAll(controller.lines, spriteBatch);
        drawAll(controller.bonuses, spriteBatch);

        controller.appendix.draw(spriteBatch);
        controller.ball.draw(spriteBatch);
        controller.pencil.draw(spriteBatch);
        spriteBatch.end();
        //new Box2DDebugRenderer().render(controller.world, camera.combined);
    }

    public void resize(float w, float h)
    {
        background.resize(w, h);
        camera.setToOrtho(false,Constants.WIDTH, Constants.WIDTH * Gdx.graphics.getHeight()/Gdx.graphics.getWidth());
    }
}
