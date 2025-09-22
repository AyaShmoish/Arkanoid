// 212831325 Aya Shmoish
package listeners;

/**
 * A HitNotifier notifies listeners of hit events.
 */
public interface HitNotifier {
    /**
     * Adds hl as a listener to hit events.
     *
     * @param hl the hit listener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Removes hl from the list of listeners to hit events.
     *
     * @param hl the hit listener to be removed
     */
    void removeHitListener(HitListener hl);
}