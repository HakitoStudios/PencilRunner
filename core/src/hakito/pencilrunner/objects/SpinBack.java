package hakito.pencilrunner.objects;

import com.badlogic.gdx.math.Vector2;
import hakito.pencilrunner.Assets;

/**
 * Created by Oleg on 20.09.2016.
 */
public class SpinBack extends Bonus {


    public SpinBack(Vector2 pos) {
        super(Assets.spinBack, pos, 0.5f);
    }


    @Override
    protected void activate(Ball ball) {
        ball.body.applyAngularImpulse(0.2f, true);
    }
}
