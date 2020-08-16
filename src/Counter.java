/**
 * The type Counter.
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Counter.
     *
     * @param value the value
     */
    public Counter(int value) {
        this.counter = value;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
// add number to current count.
    void increase(int number) {
        this.counter += number;
    }

    /**
     * Decrease.
     * subtract number from current count.
     * @param number the number
     */
    void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Gets value.
     * get current count.
     * @return the value
     */
    int getValue() {
        return this.counter;
    }
}