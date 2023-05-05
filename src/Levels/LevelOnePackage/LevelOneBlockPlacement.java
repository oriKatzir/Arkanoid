package Levels.LevelOnePackage;

import Geometry.Point;
import Levels.BlocksPlacementStrategy;
import SpritesAndCollidable.Block;

import java.util.List;

/**
 * this class is the strategy of the Blocks' placement in the first level.
 * <p>this class is used as part of the Strategy pattern</p>
 *
 * @author ori katzir
 * @version ass6
 * @see BlocksPlacementStrategy
 * @since 2022/06/02
 */
public class LevelOneBlockPlacement implements BlocksPlacementStrategy {

    private List<Block> blockList;
    private static final int X_AXIS_POSITION = 375;
    private static final int Y_AXIS_POSITION = 225;
    private static final double WIDTH_OF_A_BLOCK = 50;
    private static final double HEIGHT_OF_A_BLOCK = 50;

    /**
     * a method that sets Blocks in a row.
     *
     * @param numOfBlocksInThisRow the number of blocks in the row
     * @param xValOfFirstBlock     the x value of the first block in the row from the left
     * @param yValOfFirstBlock     the y value of the first block in the row from the left
     * @see BlocksPlacementStrategy#setARowOfBlocks(int, double, double)
     */
    @Override
    public void setARowOfBlocks(int numOfBlocksInThisRow,
                                double xValOfFirstBlock,
                                double yValOfFirstBlock) {
        Point newUpperLeft = new Point(xValOfFirstBlock, yValOfFirstBlock);
        this.blockList.get(0).getCollisionRectangle().moveRectByUpperLeftP(newUpperLeft);
        this.blockList.get(0).getCollisionRectangle().setWidth(WIDTH_OF_A_BLOCK);
        this.blockList.get(0).getCollisionRectangle().setHeight(HEIGHT_OF_A_BLOCK);
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
        setARowOfBlocks(blockList.size(), X_AXIS_POSITION, Y_AXIS_POSITION);
    }
}
