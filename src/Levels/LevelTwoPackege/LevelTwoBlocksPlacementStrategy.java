package Levels.LevelTwoPackege;

import Geometry.Point;
import Levels.BlocksPlacementStrategy;
import SpritesAndCollidable.Block;

import java.util.List;

/**
 * this class is the strategy of the Blocks' placement in the second level.
 * <p>this class is used as part of the Strategy pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @see BlocksPlacementStrategy
 * @since 2022/06/02
 */
public class LevelTwoBlocksPlacementStrategy implements BlocksPlacementStrategy {

    private List<Block> blockList;
    private static final double WIDTH_OF_A_BLOCK = 50;
    private static final int WIDTH_OF_FRAME_NOT_INCLUDING_BORDERS = 760;
    private static final int X_VALUE_OF_FIRST_BLOCK = 25;
    private static final int Y_VALUE_OF_FIRST_BLOCK = 200;

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
            Block b = blockList.get(i);
            b.getCollisionRectangle().setWidth(WIDTH_OF_A_BLOCK);
            if (spaceAvailableInThisRow - WIDTH_OF_A_BLOCK >= 0) {

                double xAxisMovement = xValOfFirstBlock + ((i) * WIDTH_OF_A_BLOCK);

                Point newUpperLeftP = new Point(xAxisMovement, yValOfFirstBlock);
                b.getCollisionRectangle().moveRectByUpperLeftP(newUpperLeftP);
                spaceAvailableInThisRow -= WIDTH_OF_A_BLOCK;
            } else {
                this.blockList.remove(b);
                i++;
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
        this.blockList = blockList;
        setARowOfBlocks(blockList.size(),
                X_VALUE_OF_FIRST_BLOCK, Y_VALUE_OF_FIRST_BLOCK);
    }
}
