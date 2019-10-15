package gamelogic;

/**
 * The GameLogic.HitNotifier interface indicate that objects that implement it send
 * notifications when they are being hit.
 *
 * @author Yonatan Segal
 * @version 1
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl GameLogic.HitListener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl GameLogic.HitListener.
     */
    void removeHitListener(HitListener hl);
}
