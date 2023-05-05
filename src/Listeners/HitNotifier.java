package Listeners;

/**
 * An interface with the characteristics of a Listeners.HitNotifier object.
 * <p>this class is used for implementing the Observer pattern</p>
 *
 * @author ori katzir
 * @version ass5
 * @since 2022/05/22
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl listener that have an effect when gets informed by this Listeners.HitNotifier
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl listener that have an effect when gets informed by this Listeners.HitNotifier
     */
    void removeHitListener(HitListener hl);
}