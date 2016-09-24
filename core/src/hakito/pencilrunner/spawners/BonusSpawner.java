package hakito.pencilrunner.spawners;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import hakito.pencilrunner.Controller;
import hakito.pencilrunner.objects.Bonus;
import hakito.pencilrunner.objects.Boost;
import hakito.pencilrunner.objects.SpinBack;

/**
 * Created by Oleg on 20.09.2016.
 */
public class BonusSpawner extends ObjectSpawner<Bonus> {


    public BonusSpawner() {
        super(10, 4, 1);
    }

    @Override
    Bonus getNewObject(Vector2 pos, Controller controller) {
        Bonus b = MathUtils.randomBoolean() ? new SpinBack(pos) : new Boost(pos);
        b.init(controller);
        return b;
    }
}
