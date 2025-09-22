// 212831325 Aya Shmoish
package counter;

/**
 * A Counter class that used for counting things like scores, lives, etc.
 */
public class Counter {
    private int count = 0;

    /**
     * Adds the given number to the current count.
     *
     * @param number the number to be added to the count
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * Subtracts the given number from the current count.
     *
     * @param number the number to be subtracted from the count
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Returns the current count.
     *
     * @return the current count
     */
    public int getValue() {
        return count;
    }
}
