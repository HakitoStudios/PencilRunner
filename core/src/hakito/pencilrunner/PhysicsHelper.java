package hakito.pencilrunner;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Oleg on 20.09.2016.
 */
public class PhysicsHelper {
    public enum ShapeType
    {
        POLYGONE, CIRCLE
    }

    private static final Vector2 tmp = new Vector2();

    public static Body createStaticSensorFromSprite(Sprite s, ShapeType shapeType, World world)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(s.getX(), s.getY());

        Body b = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        Shape shape=null;
        switch (shapeType) {
            case POLYGONE:
                shape = new PolygonShape();
                ((PolygonShape)shape).setAsBox(s.getWidth()/2, s.getHeight()/2, tmp.set(s.getWidth()/2, s.getHeight()/2), 0);
                break;
            case CIRCLE:
                shape = new CircleShape();
                ((CircleShape)shape).setPosition(tmp.set(s.getWidth()/2, s.getHeight()/2));
                shape.setRadius(Math.max(s.getWidth()/2, s.getHeight()/2));
                break;
        }
        fixtureDef.shape=shape;
        b.createFixture(fixtureDef);
        return b;
    }
}
