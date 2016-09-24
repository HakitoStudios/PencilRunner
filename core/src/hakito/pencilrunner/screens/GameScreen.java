package hakito.pencilrunner.screens;

import com.badlogic.gdx.Screen;
import hakito.pencilrunner.Controller;
import hakito.pencilrunner.Renderer;

/**
 * Created by Oleg on 18.09.2016.
 */
public class GameScreen implements Screen {

    Controller controller;
    Renderer renderer;

    public GameScreen() {
        renderer = new Renderer();
        controller = new Controller(renderer);

        newGame();
    }

    @Override
    public void show() {

    }

    public void newGame()
    {
        controller.newGame();
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render(controller, delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
