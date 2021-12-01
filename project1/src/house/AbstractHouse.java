package house;

/**
 * Abstract base class for implementations of {@link House}. This class
 * contains a field and a method definitions that are common to the concrete
 * implementations of the {@link House} interface. A new implementation of
 * the interface has the option of extending this class, or directly
 * implementing all the methods.
 */
public abstract class AbstractHouse implements House {
  protected int number;

  protected AbstractHouse(int number) {
    this.number = number;
  }

  @Override
  public int getNumber() {
    return number;
  }
}
