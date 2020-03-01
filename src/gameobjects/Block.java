package gameobjects;

import gamelogic.*;
import geometry.Point;
import geometry.Rectangle;
import levels.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * gameobjects.Block class implements gameobjects.Collidable and gameobjects.Sprite interfaces. It has a rectangle
 * and keeps track of the number of times it's hit.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class Block implements Visitable, Collidable, HitNotifier {
    private Rectangle rectangle;
    private Color colour;
    private List<HitListener> hitListeners = new ArrayList<>();
    private Counter hitsRemaining;
//    private Map<String, ColourBackground> colourBackgroundMap = new HashMap<>();
//    private Map<String, ImageBackground> imageBackgroundMap = new HashMap<>();
    private Map<Integer, Background> fill;
    private Color stroke;
    private String symbol;


    /**
     * Constructor assigns an upper left geometry.Point, width and height to a block.
     *
     * @param upperLeft geometry.Point - upper left.
     * @param width     Double - width of block.
     * @param height    Double - height of gameobjects.Block.
     */
    public Block(Point upperLeft, double width, double height) {
        this.rectangle = new Rectangle(upperLeft, width, height);
    }

    /**
     * Method sets colour for a given block.
     *
     * @param color Colour or a block.
     */
    public void setColour(Color color) {
        this.colour = color;
    }

    /**
     * Method returns a blocks colour.
     *
     * @return Colour.
     */
    public Color getColour() {
        return colour;
    }

    /**
     * Constructor assigns a rectangle to a block.
     *
     * @param block geometry.Rectangle - belongs to block.
     */
    public Block(Rectangle block) {
        this.rectangle = block;
    }

    /**
     * Method sets the number of hits incurred by a block.
     *
     * @param hit int - number of hits incurred.
     */
    public void setHits(int hit) {
        this.hitsRemaining = new Counter(hit);
    }

    /**
     * Method returns the "collision shape" of the object.
     *
     * @return geometry.Rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Method assigns new velocity and updates its number of hits.
     *
     * @param collisionPoint  geometry.Point - point of collision.
     * @param currentVelocity GameLogic.Velocity - current velocity.
     * @param hitter          Ball.
     * @return GameLogic.Velocity - new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = getNewVelocity(collisionPoint, currentVelocity);
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * Method notifies all hitlisteners that a hit occurred.
     *
     * @param hitter Ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Method updates the number of hits a block has incurred.
     */
    public void addHit() {
        if (this.getHits().getValue() != 0) {
            this.hitsRemaining.decrease(1);
        }
    }

    public Color getStroke() {
        return stroke;
    }

    /**
     * Method returns the number of hits a block can incur.
     *
     * @return Counter.
     */
    public Counter getHits() {
        return this.hitsRemaining;
    }

    /**
     * Method assigns new velocity.
     *
     * @param collisionPoint  geometry.Point - point of collision.
     * @param currentVelocity GameLogic.Velocity - current velocity.
     * @return GameLogic.Velocity - new velocity.
     */
    public Velocity getNewVelocity(Point collisionPoint, Velocity
            currentVelocity) {
        boolean hitsBottom = this.getCollisionRectangle().getBottom().pointOnLine(collisionPoint);
        boolean hitsTop = this.getCollisionRectangle().getTop().pointOnLine(collisionPoint);
        boolean hitsLeft = this.getCollisionRectangle().getLeft().pointOnLine(collisionPoint);
        boolean hitsRight = this.getCollisionRectangle().getRight().pointOnLine(collisionPoint);
        Velocity v = currentVelocity;
        //If there is a horizontal collision.
        if (hitsBottom || hitsTop) {
            v = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        }
        //If there is a vertical collision.
        if (hitsLeft || hitsRight) {
            v = new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
        }
        return v;
    }

    /**
     * Method adds block as a gameobjects.Sprite and a gameobjects.Collidable to the gameLevel.
     *
     * @param gameLevel GameLogic.GameLevel - gameLevel being played.
     */
    public void addToGame(GameLevel gameLevel) {
//        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
        gameLevel.addVisitable(this);
    }

    /**
     * Method removed a block from the game.
     *
     * @param gameLevel Gamelevel.
     */
    public void removeFromGame(GameLevel gameLevel) {
        //gameLevel.removeSprite(this);
        gameLevel.removeVisitable(this);
        gameLevel.removeCollidable(this);

    }

    /**
     * Method updates block that time has passed.
     *
     * @param dt change in time.
     */
    public void timePassed(double dt) {

    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl GameLogic.HitListener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl GameLogic.HitListener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

//    /**
//     * Method sets Map.
//     *
//     * @param c map.
//     */
//    public void setColourBackgroundMap(Map<String, ColourBackground> c) {
//        this.colourBackgroundMap = c;
//    }
//
//    /**
//     * Method sets map.
//     *
//     * @param i map.
//     */
//    public void setImageBackgroundMap(Map<String, ImageBackground> i) {
//        this.imageBackgroundMap = i;
//    }

    /**
     * Method sets fill.
     *
     * @param f map.
     */
    public void setFill(Map<Integer, Background> f) {
        this.fill = f;
    }

    /**
     * method gets map.
     *
     * @return map.
     */
    public Map<Integer, Background> getFill() {
        return fill;
    }

    /**
     * Method sets stroke.
     *
     * @param s Color.
     */
    public void setStroke(Color s) {
        this.stroke = s;
    }

    /**
     * Method sets symbol.
     *
     * @param s String.
     */
    public void setSymbol(String s) {
        this.symbol = s;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
