package GameClasses;

/**
 * An object that uses as a counter.
 *
 * @author ori katzir
 * @version ass6
 * @since 2022/06/02
 */
public class Counter {
    private int counter = 0;

    /**
     * add number to current count.
     * <p>you can't increase With a negative number</p>
     *
     * @param number the number to add to this counter
     */
    public void increase(int number) {
        if (number > 0) {
            this.counter += number;
        }
    }

    /**
     * subtract number from current count.
     *
     * @param number the number to subtract from this counter
     */
    public void decrease(int number) {
        this.counter -= number;
        if (this.counter < 0) {
            this.counter = 0;
        }
    }

    /**
     * this method reset the counter back to zero.
     */
    public void reset() {
        this.counter = 0;
    }

    /**
     * A getter for this counter value.
     *
     * @return this counter value
     */
    public int getValue() {
        return this.counter;
    }
}