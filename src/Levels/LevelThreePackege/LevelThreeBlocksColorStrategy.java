package Levels.LevelThreePackege;

import Levels.BlocksColorStrategy;
import SpritesAndCollidable.Block;

import java.awt.Color;
import java.util.List;

/**
 * this class is the strategy of the Blocks' coloring in the third level.
 * <p>this class is used as part of the Strategy pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @see BlocksColorStrategy
 * @since 2022/06/02
 */
public class LevelThreeBlocksColorStrategy implements BlocksColorStrategy {

    private static final int NUM_OF_BLOCKS_UNTIL_FIRST_ROW = 10;
    private static final int NUM_OF_BLOCKS_UNTIL_SECOND_ROW = 19;
    private static final int NUM_OF_BLOCKS_UNTIL_THIRD_ROW = 27;
    private static final int NUM_OF_BLOCKS_UNTIL_FORTH_ROW = 34;
    private static final int NUM_OF_BLOCKS_IN_TOTAL = 40;

    /**
     * a method that sets the colors of all the blocks in the given list.
     *
     * @param blockList a list of blocks to set the color to
     * @see BlocksColorStrategy#setColor(List)
     */
    @Override
    public void setColor(List<Block> blockList) {
        for (int i = 0; i < blockList.size(); i++) {
            Block b = blockList.get(i);
            if (i < NUM_OF_BLOCKS_UNTIL_FIRST_ROW) {
                b.setColor(Color.GRAY);
            } else if (i < NUM_OF_BLOCKS_UNTIL_SECOND_ROW) {
                b.setColor(Color.RED);
            } else if (i < NUM_OF_BLOCKS_UNTIL_THIRD_ROW) {
                b.setColor(Color.YELLOW);
            } else if (i < NUM_OF_BLOCKS_UNTIL_FORTH_ROW) {
                b.setColor(Color.BLUE);
            } else if (i < NUM_OF_BLOCKS_IN_TOTAL) {
                b.setColor(Color.WHITE);
            }
        }
    }
}
