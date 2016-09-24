package hakito.pencilrunner.spawners;

import com.badlogic.gdx.math.Vector2;
import hakito.pencilrunner.Controller;
import hakito.pencilrunner.objects.Block;

/**
 * Created by Oleg on 22.09.2016.
 */
public class BlocksSpawner extends ObjectSpawner<Block> {
    public BlocksSpawner() {
        super(10, 5, 3);
    }

    @Override
    Block getNewObject(Vector2 pos, Controller controller) {
        Block b = new Block(pos);
        b.init(controller);
        return b;
    }
}
