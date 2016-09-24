package hakito.pencilrunner.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Pool;
import hakito.pencilrunner.Assets;
import hakito.pencilrunner.Controller;

/**
 * Created by Oleg on 18.09.2016.
 */
public class Line extends GameObject implements Pool.Poolable{

     Vector2 s;
    public Vector2 e;


    public static void applyTo(Vector2 s, Vector2 e, Sprite sprite)
    {
        float len = s.dst(e);
        sprite.setSize(len, 0.07f);
        sprite.setPosition(s.x, s.y);
        sprite.setRotation(e.sub(s).angle());
        sprite.setOrigin(0, sprite.getHeight()/2f);
    }

    public Line() {
        super(Assets.line);
        s= new Vector2();
        e = new Vector2();
    }

    public Line init(Vector2 s, Vector2 e)
    {
        this.s.set(s);
        this.e.set(e);
        applyTo(s, e, this);
        return this;
    }


    @Override
    public void init(Controller controller) {
        BodyDef bodyDef = new BodyDef();

        //bodyDef.position.set(getX(), getY());
        //bodyDef.angle = getRotation() * MathUtils.degreesToRadians;
        bodyDef.type = BodyDef.BodyType.StaticBody;

        body  = controller.world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0.8f;
        EdgeShape shape  = new EdgeShape();
        shape.set(s, e);
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
        super.init(controller);
    }

    @Override
    public void applyTransform(Sprite sprite) {
        return;
    }

    @Override
    public void reset() {

    }
}
