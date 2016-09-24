package hakito.pencilrunner.spawners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import hakito.pencilrunner.Controller;
import hakito.pencilrunner.objects.GameObject;
import hakito.pencilrunner.objects.Line;
import hakito.pencilrunner.objects.Pencil;

import java.util.Iterator;

/**
 * Created by Oleg on 18.09.2016.
 */
public class LineSpawner {
    public final Vector2 last = new Vector2();
    private Pool<Line> pool;

    Vector2 tmp = new Vector2(), tmp2 = new Vector2();
    Vector3 tmp3 = new Vector3();

    public LineSpawner() {
        pool = new Pool<Line>() {
            @Override
            protected Line newObject() {
                Line l = new Line();
                return l;
            }
        };
    }

    public void clear() {
        last.setZero();
    }


    public void removeToLeft(float ballX, Array<Line> lines)
    {
        for (Iterator<Line> l = lines.iterator(); l.hasNext(); ) {
            Line g = l.next();
            if (g.isRemoveMe() || g.getX() < ballX - 5) {
                l.remove();
                pool.free(g);
                if(g.body!=null) {
                    g.body.getWorld().destroyBody(g.body);
                }
            }
        }
    }

    public Line update(Pencil pencil, Controller controller) {

            tmp.set(pencil.getX(), pencil.getY());
            if (Math.abs(tmp.x - last.x) + Math.abs(tmp.y - last.y) > 0.3f) {
                tmp2.set(last);
                last.set(tmp);
                if (!tmp2.isZero()) {

                    Line l = pool.obtain();
                    l.init(tmp2, tmp);
                    l.init(controller);
                    controller.lines.add(l);
                    return l;
                }
            }

        return null;
    }
}
