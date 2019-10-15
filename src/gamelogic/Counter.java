package gamelogic;

/**
 * Class holds an int and can increase, decrease and return its count.
 *
 * @author Yonatan Segal
 * @version 1
 */
public class Counter {
    private int count;

    /**
     * Constructor assigns the number of blocks needed to be removed.
     *
     * @param remainingBlocks int.
     */
    public Counter(int remainingBlocks) {
        this.count = remainingBlocks;
    }

    /**
     * Method increases the counter.
     *
     * @param number int.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Method decreases the counter.
     *
     * @param number int.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Method returns the current count.
     *
     * @return int - count.
     */
    public int getValue() {
        return this.count;
    }
}
