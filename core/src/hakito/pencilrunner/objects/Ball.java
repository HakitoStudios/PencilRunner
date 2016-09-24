package hakito.pencilrunner.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import hakito.pencilrunner.Assets;
import hakito.pencilrunner.Controller;

/**
 * Created by Oleg on 18.09.2016.
 */
public class Ball extends GameObject {
    private static final float RADIUS=0.5f;
    private static Vector2 tmp = new Vector2();
    private Sprite face, lights;


    public Ball(float x, float y) {
        super(Assets.ball);
        face = new Sprite(Assets.ball_face);
        lights = new Sprite(Assets.ball_lights);

        initSprite(face, x, y);
        initSprite(lights, x, y);
        initSprite(this, x, y);
    }

    private void initSprite(Sprite s,float x, float y)
    {
        s.setPosition(x, y);
        s.setSize(RADIUS*2, RADIUS*2);
        s.setOriginCenter();
    }

    @Override
    public void init(Controller controller) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(), getY());
        bodyDef.bullet=true;
        bodyDef.linearDamping=0.05f;
        body = controller.world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(RADIUS);
        fixtureDef.shape = circleShape;
        fixtureDef.restitution = 0.1f;
        fixtureDef.friction=0.8f;

        fixtureDef.density = 3;

        body.createFixture(fixtureDef);
        body.setLinearVelocity(1, 0);
        body.setAngularVelocity(-10);
        super.init(controller);
    }

    @Override
    public void update(float d) {
        super.update(d);
        {
            if(Gdx.input.isTouched()) {
                float dx = Gdx.input.getDeltaX() / 10f;
                if(dx>0)
                {
                    body.applyTorque(-dx, true);
                }
            }
        }
        body.applyTorque(-0.1f, true);
        applyTransform(face);
        applyTransform(lights);
        face.setRotation(0);
        lights.setRotation(0);

        if(body.getLinearVelocity().x < 1f)
        {
            body.applyForceToCenter(Gdx.input.getAccelerometerY() / 100, 0, true);
        }
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        face.draw(batch);
        lights.draw(batch);
    }
}
