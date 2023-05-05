package Levels.LevelOnePackage;

import Levels.BlocksColorStrategy;
import SpritesAndCollidable.Block;

import java.awt.Color;
import java.util.List;

/**
 * this class is the strategy of the Blocks' coloring in the first level.
 * <p>this class is used as part of the Strategy pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @see BlocksColorStrategy
 * @since 2022/06/02
 */
public class LevelOneBlocksColorStrategy implements BlocksColorStrategy {
    /**
     * a method that sets the colors of all the blocks in the given list.
     *
     * @param blockList a list of blocks to set the color to
     * @see BlocksColorStrategy#setColor(List)
     */
    @Override
    public void setColor(List<Block> blockList) {
        blockList.get(0).setColor(Color.RED);
    }
}
