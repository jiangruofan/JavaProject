package random;

/**
 * This interface represents producing and getting a random number.
 */
public interface RandomInterface {
  /**
   * Get a random value between a certain range.
   *
   * @param low the low boundary of the range
   * @param high the high boundary of the range
   * @return the random value
   */
  int getRandom(int low, int high);
}
