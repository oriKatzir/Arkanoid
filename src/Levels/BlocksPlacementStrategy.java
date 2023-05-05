package Levels;

import SpritesAndCollidable.Block;

import java.util.List;

/**
 * an interface that sets the characteristic of a Blocks' placement strategy.
 * <p>this class used for implementing the Strategy pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public interface BlocksPlacementStrategy {

    /**
     * a method that sets Blocks in a row.
     *
     * @param numOfBlocksInThisRow the number of blocks in the row
     * @param xValOfFirstBlock     the x value of the first block in the row from the left
     * @param yValOfFirstBlock     the y value of the first block in the row from the left
     * @see BlocksPlacementStrategy#setARowOfBlocks(int, double, double)
     */
    void setARowOfBlocks(int numOfBlocksInThisRow, double xValOfFirstBlock,
                         double yValOfFirstBlock);

    /**
     * a method that sets the axis values of all the blocks.
     *
     * @param blockList a list of all the blocks in the placement
     * @see BlocksPlacementStrategy#setPlacement(java.util.List)
     */
    void setPlacement(List<Block> blockList);

}
