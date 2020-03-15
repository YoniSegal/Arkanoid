package levels;

import gameobjects.Background;
import gameobjects.Block;
import geometry.Point;

import java.awt.Color;
import java.util.Map;

/**
 * BlockMaker class creates blocks.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class BlockMaker implements BlockCreator {
    private int width;
    private int height;
    private int hitPoints;
    private Map<Integer, Background> fill;
    private Color stroke;
    private String symbol;

    /**
     * Blank constructor.
     */
    public BlockMaker() {
    }

    /**
     * Create a block at the specified location.
     *
     * @param xpos - int.
     * @param ypos 0 int.
     * @return Block.
     */
    @Override
    public Block create(int xpos, int ypos) {
        geometry.Point point = new Point(xpos, ypos);
        Block block = new Block(point, this.width, this.height);
        block.setHits(this.hitPoints);
        block.setFill(this.fill);
        block.setStroke(this.stroke);
        block.setSymbol(this.symbol);
        return block;
    }

    /**
     * Method sets the blockMaker's width.
     *
     * @param w int.
     */
    public void setWidth(int w) {
        this.width = w;
    }

    /**
     * Method sets the number of hitpoints.
     *
     * @param hp int.
     */
    public void setHitPoints(int hp) {
        this.hitPoints = hp;
    }

    /**
     * Method sets the stroke.
     *
     * @param s color.
     */
    public void setStroke(Color s) {
        this.stroke = s;
    }

    /**
     * Method sets the symbol.
     *
     * @param s Stirng.
     */
    public void setSymbol(String s) {
        this.symbol = s;
    }

    /**
     * Method sets the height.
     *
     * @param h int.
     */
    public void setHeight(int h) {
        this.height = h;
    }

    /**
     * Method returns a Map from ints to Backgrounds.
     *
     * @return Map.
     */
    public Map<Integer, Background> getFill() {
        return fill;
    }

    /**
     * Method gets a symbol.
     *
     * @return String.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Method gets height.
     *
     * @return height - int.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Method gets the number of hitpoints.
     *
     * @return int - hitpoints.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Method returns the width.
     *
     * @return int.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Method returns the stroke.
     *
     * @return colour.
     */
    public Color getStroke() {
        return stroke;
    }

    /**
     * Method sets a map from ints to backgrounds.
     *
     * @param f map.
     */
    public void setFill(Map<Integer, Background> f) {
        this.fill = f;
    }
}
