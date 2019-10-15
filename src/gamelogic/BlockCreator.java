package gamelogic;

import gameobjects.Block;

/**
 * BlockCreater interface holds a method to create blocks.
 *
 * @author Yonatan Segal
 * @version 1
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     *
     * @param xpos - int.
     * @param ypos 0 int.
     * @return Block.
     */
    Block create(int xpos, int ypos);
}
