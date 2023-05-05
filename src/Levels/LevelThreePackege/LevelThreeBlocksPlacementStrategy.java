package Levels.LevelThreePackege;

import Geometry.Point;
import Levels.BlocksPlacementStrategy;
import SpritesAndCollidable.Block;

import java.util.List;

/**
 * this class is the strategy of the Blocks' placement in the third level.
 * <p>this class is used as part of the Strategy pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @see BlocksPlacementStrategy
 * @since 2022/06/02
 */
public class LevelThreeBlocksPlacementStrategy implements BlocksPlacementStrategy {

    private static final int NUM_OF_ROWS = 5;
    private static final double WIDTH_OF_A_BLOCK = 50;
    private static final double HEIGHT_OF_A_BLOCK = 20;
    private static final double X_VALUE_OF_FIRST_BLOCK_IN_TOP_ROW = 280;
    private static final double Y_VALUE_OF_FIRST_BLOCK_IN_TOP_ROW = 200;
    private static final int INITIAL_NUM_OF_BLOCKS_IN_A_ROW = 10;
    private static final int INITIAL_JUMP = 9;
    private List<Block> subList;

    /**
     * a method that sets Blocks in a row.
     *
     * @param numOfBlocksInThisRow the number of blocks in the row
     * @param xValOfFirstBlock     the x value of the first block in the row from the left
     * @param yValOfFirstBlock     the y value of the first block in the row from the left
     * @see BlocksPlacementStrategy#setARowOfBlocks(int, double, double)
     */
    @Override
    public void setARowOfBlocks(int numOfBlocksInThisRow, double xValOfFirstBlock,
                                double yValOfFirstBlock) {
        double xAxisMovment = xValOfFirstBlock;
        for (int i = 0; i < numOfBlocksInThisRow; i++) {
            Point upperLeft = new Point(xAxisMovment, yValOfFirstBlock);
            this.subList.get(i).getCollisionRectangle().moveRectByUpperLeftP(upperLeft);
            xAxisMovment += WIDTH_OF_A_BLOCK;
        }
    }

    /**
     * a method that sets the axis values of all the blocks.
     *
     * @param blockList a list of all the blocks in the placement
     * @see BlocksPlacementStrategy#setPlacement(List)
     */
    @Override
    public void setPlacement(List<Block> blockList) {
        int endOfSubList = INITIAL_NUM_OF_BLOCKS_IN_A_ROW;
        int startOfSubList = 0;
        int jump = INITIAL_JUMP;
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            this.subList =
                    blockList.subList(startOfSubList, Math.min(endOfSubList, blockList.size()));
            int numOfBlocksInRow = endOfSubList - startOfSubList;
            double xValueOfFirstBlock =
                    X_VALUE_OF_FIRST_BLOCK_IN_TOP_ROW + (i * WIDTH_OF_A_BLOCK);
            double yValueOfFirstBlock =
                    Y_VALUE_OF_FIRST_BLOCK_IN_TOP_ROW + (i * HEIGHT_OF_A_BLOCK);
            setARowOfBlocks(numOfBlocksInRow, xValueOfFirstBlock, yValueOfFirstBlock);
            startOfSubList = endOfSubList;
            endOfSubList += jump;
            jump--;
        }
    }
}
