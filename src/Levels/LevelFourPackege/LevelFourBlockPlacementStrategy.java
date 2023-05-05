package Levels.LevelFourPackege;

import Geometry.Point;
import Levels.BlocksPlacementStrategy;
import SpritesAndCollidable.Block;

import java.util.List;

/**
 * this class is the strategy of the Blocks' placement in the forth level.
 * <p>this class is used as part of the Strategy pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @see BlocksPlacementStrategy
 * @since 2022/06/02
 */
public class LevelFourBlockPlacementStrategy implements BlocksPlacementStrategy {

    private List<Block> subList;
    private static final double WIDTH_OF_A_BLOCK = 50;
    private static final double HEIGHT_OF_A_BLOCK = 20;
    private static final int NUM_OF_BLOCKS_IN_A_ROW = 15;
    private static final double STARTING_X_VALUE_OF_A_BLOCK = 25;
    private static final int WIDTH_OF_FRAME_NOT_INCLUDING_BORDERS = 760;
    private static final int NUM_OF_ROWS = 7;
    private static final double Y_VALUE_OF_FIRST_BLOCK_IN_TOP_ROW = 130;

    /**
     * a method that sets Blocks in a row.
     *
     * @param numOfBlocksInThisRow the number of blocks in the row
     * @param xValOfFirstBlock     the x value of the first block in the row from the left
     * @param yValOfFirstBlock     the y value of the first block in the row from the left
     * @see BlocksPlacementStrategy#setARowOfBlocks(int, double, double)
     */
    @Override
    public void setARowOfBlocks(int numOfBlocksInThisRow, double xValOfFirstBlock, double yValOfFirstBlock) {
        int spaceAvailableInThisRow = WIDTH_OF_FRAME_NOT_INCLUDING_BORDERS;
        for (int i = 0; i < numOfBlocksInThisRow; i++) {
            Block b = this.subList.get(i);
            b.getCollisionRectangle().setWidth(WIDTH_OF_A_BLOCK);
            if (spaceAvailableInThisRow - WIDTH_OF_A_BLOCK >= 0) {

                double xAxisMovement = xValOfFirstBlock + ((i) * WIDTH_OF_A_BLOCK);

                Point newUpperLeftP = new Point(xAxisMovement, yValOfFirstBlock);
                b.getCollisionRectangle().moveRectByUpperLeftP(newUpperLeftP);
                spaceAvailableInThisRow -= WIDTH_OF_A_BLOCK;
            } else {
                this.subList.remove(b);
            }
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
        int endOfSubList = NUM_OF_BLOCKS_IN_A_ROW;
        int startOfSubList = 0;
        int jump = NUM_OF_BLOCKS_IN_A_ROW;
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            this.subList =
                    blockList.subList(startOfSubList, Math.min(endOfSubList, blockList.size()));
            double yValueOfFirstBlock =
                    Y_VALUE_OF_FIRST_BLOCK_IN_TOP_ROW + (i * HEIGHT_OF_A_BLOCK);
            setARowOfBlocks(NUM_OF_BLOCKS_IN_A_ROW, STARTING_X_VALUE_OF_A_BLOCK,
                    yValueOfFirstBlock);
            startOfSubList = endOfSubList;
            endOfSubList += jump;
        }
    }
}
