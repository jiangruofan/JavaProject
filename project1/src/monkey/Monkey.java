package monkey;


import java.util.Objects;

/**
 * This monkey class represents monkey which has many attributes.
 */
public class Monkey implements Cloneable {
  private final String name;
  private final String species;
  private final Sex sex;
  private final Size size;
  private final double weight;
  private final int age;
  private final Food favouriteFood;

  /**
   * Constructs a monkey in terms of name, species, sex, size, weight, age, favouriteFood.
   *
   * @param name          the name of the monkey
   * @param species       the species of the monkey
   * @param sex           the sex of the monkey
   * @param size          the size of the monkey
   * @param weight        the weight of the monkey
   * @param age           the age of the monkey
   * @param favouriteFood the favouriteFood of the monkey
   */
  public Monkey(String name, String species, Sex sex, Size size,
                double weight, int age, Food favouriteFood) {
    this.name = name;
    this.species = species;
    this.sex = sex;
    this.size = size;
    this.weight = weight;
    this.age = age;
    this.favouriteFood = favouriteFood;
  }

  /**
   * Gets the name of the monkey.
   *
   * @return the name of the monkey
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the species of the monkey.
   *
   * @return the species of the monkey
   */
  public String getSpecies() {
    return species;
  }

  /**
   * Gets the sex of the monkey.
   *
   * @return the sex of the monkey
   */
  public Sex getSex() {
    return sex;
  }

  /**
   * Gets the size of the monkey.
   *
   * @return the size of the monkey
   */
  public Size getSize() {
    return size;
  }

  /**
   * Gets the weight of the monkey.
   *
   * @return the weight of the monkey
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Gets the age of the monkey.
   *
   * @return the age of the monkey
   */
  public int getAge() {
    return age;
  }

  /**
   * Gets the favouriteFood of the monkey.
   *
   * @return the favouriteFood of the monkey
   */
  public Food getFavouriteFood() {
    return favouriteFood;
  }

  @Override
  public Monkey clone() {
    try {
      return (Monkey) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Monkey monkey = (Monkey) o;
    return Double.compare(monkey.weight, weight) == 0
            && age == monkey.age && name.equals(monkey.name)
            && species.equals(monkey.species) && sex == monkey.sex
            && size == monkey.size && favouriteFood == monkey.favouriteFood;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, species, sex, size, weight, age, favouriteFood);
  }

}
