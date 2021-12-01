package random;

/**
 * This class represents getting a value from a preset array.
 */
public class PredictableRandom implements RandomInterface {
  private final int[] preset;
  private int i;

  /**
   * Construct PredictableRandom in terms of preset.
   *
   * @param preset a preset array of numbers
   * @throws IllegalArgumentException if input is null
   */
  public PredictableRandom(int... preset) {
    if (preset == null) {
      throw new IllegalArgumentException("input could not be null.");
    }
    this.preset = preset;
    i = 0;
  }

  @Override
  public int getRandom(int low, int high) {
    return preset[i++];
  }
}
