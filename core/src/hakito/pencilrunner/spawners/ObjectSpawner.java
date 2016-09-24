package hakito.pencilrunner.spawners;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import hakito.pencilrunner.Controller;
import hakito.pencilrunner.objects.GameObject;

/**
 * Created by Oleg on 22.09.2016.
 */
public abstract class ObjectSpawner<T extends GameObject> {
    private Vector2 lastSpawn;
    private Vector2 tmp = new Vector2();
    private float offset;
    private float step;
    private float dy;

    public ObjectSpawner(float offset, float step, float dy) {
        this.offset = offset;
        this.step = step;
        this.dy = dy;
        lastSpawn = new Vector2();
    }

    public void clear() {
        lastSpawn.setZero();
    }

    abstract T getNewObject(Vector2 pos, Controller controller);

    public T spawn(Vector2 ballPos, Controller controller, Array<T> array) {
        if (ballPos.x - lastSpawn.x > step) {
            tmp.set(ballPos.x + offset, MathUtils.random(-dy, dy));


            lastSpawn.set(tmp);
            T t = getNewObject(tmp, controller);
            array.add(t);
            return t;
        }
        return null;
    }

}
