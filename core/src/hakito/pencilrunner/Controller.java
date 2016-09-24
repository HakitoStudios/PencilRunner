package hakito.pencilrunner;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import hakito.pencilrunner.objects.*;
import hakito.pencilrunner.spawners.BlocksSpawner;
import hakito.pencilrunner.spawners.BonusSpawner;
import hakito.pencilrunner.spawners.LineSpawner;

import java.util.Iterator;

import static hakito.pencilrunner.Assets.line;

/**
 * Created by Oleg on 18.09.2016.
 */
public class Controller {

    class MyContactListene implements ContactListener
    {

        @Override
        public void beginContact(Contact contact) {
            GameObject a=((GameObject)contact.getFixtureA().getBody().getUserData()),
                    b=((GameObject)contact.getFixtureB().getBody().getUserData());
            a.beginContact(b);
            b.beginContact(a);

        }

        @Override
        public void endContact(Contact contact) {

        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {

        }
    }

    Vector2 t = new Vector2();
    public World world;
    Ball ball;
    Pencil pencil;
   public Array<Line> lines;
    Array<Bonus> bonuses;
    Array<Block> blocks;
    Line appendix;
    LineSpawner lineSpawner;

    BlocksSpawner blocksSpawner;
    BonusSpawner bonusSpawner;

    Renderer renderer;
    GameUI gameUI;


    public Controller(Renderer renderer) {
        gameUI = new GameUI();
        this.renderer = renderer;
        world = new World(new Vector2(0, -9), false);
        world.setContactListener(new MyContactListene());
        lines = new Array<Line>();
        blocks = new Array<Block>();
        bonuses = new Array<Bonus>();
        lineSpawner = new LineSpawner();
        bonusSpawner = new BonusSpawner();
        blocksSpawner = new BlocksSpawner();
        appendix = new Line();
        appendix.init(Vector2.Zero, Vector2.Zero);
        appendix.init(this);

    }

    private void clearAll() {
        lineSpawner.clear();
        blocks.clear();
        bonusSpawner.clear();
        lines.clear();
        bonuses.clear();
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body b : bodies) {
            world.destroyBody(b);
        }
    }

    public void newGame() {
        clearAll();

        ball = new Ball(2, 3);
        ball.init(this);

        pencil = new Pencil(3);

        lines.add(new Line().init(new Vector2(0, 2), new Vector2(3, 1)));

        lines.add(new Line().init(new Vector2(3, 1), new Vector2(7, 0)));
        for (Line l:lines){l.init(this);};
    }

    void updateAll(Array<? extends GameObject> array, float d) {
        for (GameObject o : array) {
            o.update(d);
        }
    }

    void removeToLeft(Iterable<? extends GameObject> iterable)
    {
        for (Iterator<? extends GameObject> l = iterable.iterator(); l.hasNext(); ) {
            GameObject g = l.next();
            if (g.isRemoveMe() || g.getX() < ball.getX() - 5) {
                l.remove();
                if(g.body!=null) {
                    world.destroyBody(g.body);
                }
            }
        }
    }

    public void update(float d) {

        if (ball.getY() < -10 || ball.body.getLinearVelocity().x < 0.1) {
            newGame();
        }
        world.step(1 / 60f, 5, 5);

        t.set(ball.getX(), ball.getY());
        blocksSpawner.spawn(t, this, blocks );
        bonusSpawner.spawn(t, this, bonuses);



        updateAll(blocks, d);
        updateAll(lines, d);
        updateAll(bonuses, d);


        lineSpawner.removeToLeft(ball.getX(), lines);
        removeToLeft(bonuses);

        removeToLeft(blocks);

        ball.update(d);

        pencil.updatePos(ball.getX(), renderer.camera);
        pencil.update(d);
        {
            Line t = lineSpawner.update(pencil, this);
        }

        if (!lineSpawner.last.isZero()) {
            Line.applyTo(lineSpawner.last, t.set(pencil.getX(), pencil.getY()), appendix);
        } else {
            appendix.setSize(0, 0);
        }


    }
}
