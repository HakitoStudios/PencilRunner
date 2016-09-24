package hakito.pencilrunner.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import hakito.pencilrunner.Assets;

/**
 * Created by Oleg on 18.09.2016.
 */
public class Pencil extends GameObject {
    private float offsetX;
private Vector3 t = new Vector3();

    public Pencil(float offsetX) {
        super(Assets.pencil);
        this.offsetX = offsetX;
        setSize(1, 2);
    }

    public void updatePos(float ballX, Camera camera)
    {

        setX(ballX + offsetX);
        if(Gdx.input.isTouched())
        {
            t.set(0, Gdx.input.getY(), 0);
            camera.unproject(t);
            setY(MathUtils.lerp(getY(), t.y, 0.1f));

        }

    }

    @Override
    public void update(float d) {
        super.update(d);
    }
}
