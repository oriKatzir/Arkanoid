package GameClasses.GameLevelBuilders;

import Levels.BlocksColorStrategy;
import Levels.BlocksPlacementStrategy;
import SpritesAndCollidable.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * A block builder, used to build Block objects with given characteristics.
 * <p>this class used for implementing the Builder pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public class BlockBuilder {
    private List<Block> blockList = new ArrayList<>();
    private int numberOfBlocksToBuild;
    private BlocksPlacementStrategy placementStrategy;
    private BlocksColorStrategy colorStrategy;


    /**
     * A constructor method.
     *
     * @param numberOfBlocksToBuild the number of Blocks that needs to build
     * @param placementStrategy     the Strategy the blocks are going to be placed by
     * @param colorStrategy         the Strategy the blocks are going to be colored by
     */
    public BlockBuilder(int numberOfBlocksToBuild,
                        BlocksPlacementStrategy placementStrategy,
                        BlocksColorStrategy colorStrategy) {
        this.numberOfBlocksToBuild = numberOfBlocksToBuild;
        this.placementStrategy = placementStrategy;
        this.colorStrategy = colorStrategy;

    }

    /**
     * build Block objects with desired characteristics.
     *
     * @return a List of the desired blocks
     */
    public List<Block> build() {
        creatDefaultBlocks();
        this.placementStrategy.setPlacement(this.blockList);
        this.colorStrategy.setColor(this.blockList);
        return this.blockList;
    }

    /**
     * builds a list of default blocks.
     */
    private void creatDefaultBlocks() {
        for (int i = 0; i < this.numberOfBlocksToBuild; i++) {
            this.blockList.add(new Block());
        }
    }
}
