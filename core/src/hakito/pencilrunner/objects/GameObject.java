package hakito.pencilrunner.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import hakito.pencilrunner.Controller;

/**
 * Created by Oleg on 18.09.2016.
 */
public abstract class GameObject extends Sprite implements BodyContactListener{
    public Body body;
    protected boolean removeMe;

    public GameObject(TextureRegion region) {
        super(region);

    }

    public void init(Controller controller){
        if(body!=null)
        {
            body.setUserData(this);
        }
    }

    @Override
    public void beginContact(GameObject another) {

    }

    public boolean isRemoveMe() {
        return removeMe;
    }

    public void applyTransform(Sprite sprite)
    {
        if(body!=null)
        {
            Vector2 pos = body.getPosition();
            sprite.setPosition(pos.x - sprite.getOriginX(), pos.y-sprite.getOriginY());
            sprite.setRotation(body.getAngle() * MathUtils.radDeg);
        }
    }

    public void update(float d)
    {

        applyTransform(this);
    }

}
