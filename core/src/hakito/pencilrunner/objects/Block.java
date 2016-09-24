package hakito.pencilrunner.objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import hakito.pencilrunner.Assets;
import hakito.pencilrunner.Controller;

/**
 * Created by Oleg on 22.09.2016.
 */
public class Block extends GameObject {
    public Block(Vector2 pos) {
        super(Assets.block);
        setPosition(pos.x, pos.y);
        setSize(0.6f, 0.6f);
        setOrigin(0, 0);
    }



    @Override
    public void init(Controller controller) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type= BodyDef.BodyType.StaticBody;
        bodyDef.position.set(getX(), getY());
        body = controller.world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(getWidth()/2, getHeight()/2, new Vector2(getWidth()/2, getHeight()/2), 0);
        fixtureDef.shape = polygonShape;
        body.createFixture(fixtureDef);
        super.init(controller);
    }
}
