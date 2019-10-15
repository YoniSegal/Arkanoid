package gamelogic;

import gameobjects.Block;

import java.util.Map;

/**
 * Class assigns symbols for blockmakers and spacers.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockMaker> blockCreators;

    /**
     * Blank constructor.
     */
    public BlocksFromSymbolsFactory() {

    }

    /**
     * returns true if 's' is a valid space symbol.
     *
     * @param s String.
     * @return boolean.
     */
    public boolean isSpaceSymbol(String s) {
        return (spacerWidths.containsKey(s));
    }

    /**
     * returns true if 's' is a valid block symbol.
     *
     * @param s String.
     * @return boolean.
     */
    public boolean isBlockSymbol(String s) {
        return (blockCreators.containsKey(s));
    }

    /**
     * Return a block according to the definitions associated with symbol s.
     * The block will be located at position (xpos, ypos).
     *
     * @param s    String.
     * @param xpos int.
     * @param ypos int.
     * @return Block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s String.
     * @return int.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * Returns the width associated with the given block-symbol.
     *
     * @param s String.
     * @return int.
     */
    public int getBlockWidth(String s) {
        return this.blockCreators.get(s).getWidth();
    }

    /**
     * Method sets blockcreator.
     *
     * @param blockMakerMap map.
     */
    public void setBlockCreators(Map<String, BlockMaker> blockMakerMap) {
        this.blockCreators = blockMakerMap;
    }

    /**
     * Method sets spacerwidths.
     *
     * @param stringIntegerMap map.
     */
    public void setSpacerWidths(Map<String, Integer> stringIntegerMap) {
        this.spacerWidths = stringIntegerMap;
    }

    /**
     * method returns a map from symbols to blockcreators.
     *
     * @return map.
     */
    public Map<String, BlockMaker> getBlockCreators() {
        return blockCreators;
    }

    /**
     * Method returns map from symbols to speacerwidths.
     *
     * @return map.
     */
    public Map<String, Integer> getSpacerWidths() {
        return spacerWidths;
    }
}
