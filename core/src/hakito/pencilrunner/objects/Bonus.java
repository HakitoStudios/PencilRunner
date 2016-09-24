package hakito.pencilrunner.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import hakito.pencilrunner.Controller;
import hakito.pencilrunner.PhysicsHelper;

/**
 Describes sqare shape bonus
 */
public abstract class Bonus extends GameObject {
    private boolean activated;

    public Bonus(TextureRegion region, Vector2 pos, float size) {
        super(region);
        setSize(size, size);
        setPosition(pos.x, pos.y);
        setOrigin(0, 0);
    }

    @Override
    public void init(Controller controller) {
        body = PhysicsHelper.createStaticSensorFromSprite(this, PhysicsHelper.ShapeType.POLYGONE, controller.world);
        super.init(controller);
    }

    public boolean isActivated() {
        return activated;
    }

    @Override
    public void beginContact(GameObject another) {
        if(another instanceof Ball)
        {
            collided((Ball)another);
        }
    }

    public void collided(Ball ball)
    {
        if(activated==true)//already picked
        {
            return;
        }
        removeMe=true;
        activated=true;
        activate(ball);
    }

    protected abstract void activate(Ball ball);
}
