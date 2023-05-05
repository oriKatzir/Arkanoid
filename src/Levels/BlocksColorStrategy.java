package Levels;

import SpritesAndCollidable.Block;

import java.util.List;

/**
 * an interface that sets the characteristic of a Blocks coloring strategy.
 * <p>this class used for implementing the Strategy pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public interface BlocksColorStrategy {

    /**
     * a method that sets the colors of all the blocks in the given list.
     *
     * @param blockList a list of blocks to set the color to
     * @see BlocksColorStrategy#setColor(java.util.List)
     */
    void setColor(List<Block> blockList);
}
