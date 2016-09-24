package hakito.pencilrunner.objects;

import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import hakito.pencilrunner.Assets;

/**
 * Created by Oleg on 20.09.2016.
 */
public class Boost extends Bonus {


    public Boost(Vector2 pos) {
        super(Assets.boost, pos, 0.5f);
    }


    @Override
    protected void activate(Ball ball) {
        ball.body.applyForceToCenter(200, 0.0f, true);

    }
}
