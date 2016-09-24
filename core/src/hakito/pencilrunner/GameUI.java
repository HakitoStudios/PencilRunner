package hakito.pencilrunner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;


/**
 * Created by Oleg on 19.09.2016.
 */
public class GameUI {
    Stage stage;
    Label fps, objects, score, ballSpeed;

    public GameUI() {
        stage = new Stage();
        Table root = new Table();
        root.setFillParent(true);
        Table debug = new Table();
        stage.addActor(root);


        fps = new Label("fps", Assets.skin);
        objects = new Label("objects", Assets.skin);
        ballSpeed = new Label("ballSpeed", Assets.skin);
        score = new Label("score", Assets.skin);


        debug.add(fps).left().row();
        debug.add(objects).left().row();
        debug.add(ballSpeed).left().row();
        debug.add(score).left().row();


        root.add(debug).expandX().left();



    }

    public void draw(Controller controller) {
        fps.setText(String.format("FPS: %d", Gdx.graphics.getFramesPerSecond()));
        objects.setText(String.format("Objects count: %d", controller.world.getBodyCount()));
        score.setText(String.format("%04d m", (int)controller.ball.getX()));
        ballSpeed.setText(controller.ball.body.getLinearVelocity().toString());

        stage.act();
        stage.draw();
    }
}
