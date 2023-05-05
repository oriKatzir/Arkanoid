package Levels.LevelTwoPackege;

import Levels.BlocksColorStrategy;
import SpritesAndCollidable.Block;

import java.awt.Color;
import java.util.List;

/**
 * this class is the strategy of the Blocks' coloring in the second level.
 * <p>this class is used as part of the Strategy pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @see BlocksColorStrategy
 * @since 2022/06/02
 */
public class LevelTwoBlocksColorStrategy implements BlocksColorStrategy {

    private static final int RED = 0;
    private static final int ORANGE = 1;
    private static final int YELLOW = 2;
    private static final int BLUE = 3;
    private static final int PINK = 4;
    private static final int FIRST_PART = 6;
    private static final int SECOND_PART = 8;

    /**
     * a method that sets the colors of all the blocks in the given list.
     *
     * @param blockList a list of blocks to set the color to
     * @see BlocksColorStrategy#setColor(List)
     */
    @Override
    public void setColor(List<Block> blockList) {
        boolean shouldDecrease = true;
        int j = 0;
        for (int i = 0; i < blockList.size(); i++) {
            if (i < FIRST_PART || i > SECOND_PART) {
                switch (j) {
                    case RED:
                        blockList.get(i).setColor(Color.RED);
                        break;
                    case ORANGE:
                        blockList.get(i).setColor(Color.ORANGE);
                        break;
                    case YELLOW:
                        blockList.get(i).setColor(Color.YELLOW);
                        break;
                    case BLUE:
                        blockList.get(i).setColor(Color.BLUE);
                        if (shouldDecrease) {
                            j--;
                            shouldDecrease = false;
                        }
                        break;
                    case PINK:
                        blockList.get(i - 1).setColor(Color.PINK);
                        blockList.get(i).setColor(Color.PINK);
                        break;
                    default:
                        blockList.get(i - 1).setColor(Color.CYAN);
                        blockList.get(i).setColor(Color.CYAN);
                        break;
                }
                if (i % 2 != 0) {
                    j++;
                }
            } else {
                blockList.get(i).setColor(Color.GREEN);
            }
        }
    }
}
