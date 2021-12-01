package random;

import java.util.Random;

/**
 * This class represents getting a true random number.
 */
public class RealRandom implements RandomInterface {
  private final Random random;

  /**
   * Construct RealRandom in terms of random.
   */
  public RealRandom() {
    this.random = new Random();
  }

  @Override
  public int getRandom(int low, int high) {
    return random.nextInt(high - low + 1) + low;
  }
}
