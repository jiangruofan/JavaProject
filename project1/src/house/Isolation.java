package house;

import monkey.Monkey;
import monkey.Size;
import monkey.Food;
import monkey.Sex;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The isolation represents a single place where only one monkey could live there, and it stores the
 * information of the monkey living there.
 * When monkeys comes to the sanctuary, they will first go to the isolation to get medical attention
 * and attributes recorded.
 */
public class Isolation extends AbstractHouse {
  private final Monkey monkey;

  /**
   * Constructs the isolation in terms of num, name, species, sex, size,
   * weight, age and favouriteFood.
   *
   * @param num           the room number
   * @param name          the name of the monkey
   * @param species       the species of the monkey
   * @param sex           the sex of the monkey
   * @param size          the size of the monkey
   * @param weight        the weight of the monkey
   * @param age           the age of the monkey
   * @param favouriteFood the favouriteFood of the monkey
   * @throws IllegalArgumentException if name or species is empty
   */
  public Isolation(int num, String name, String species, Sex sex,
                   int size, double weight, int age, Food favouriteFood) {
    super(num);
    if (name == null || name.length() <= 0) {
      throw new IllegalArgumentException("the name is empty");
    }
    if (species == null || species.length() <= 0) {
      throw new IllegalArgumentException("the species is empty");
    }
    Size size1;
    if (size < 10) {
      size1 = Size.small;
    } else if (size <= 20) {
      size1 = Size.medium;
    } else {
      size1 = Size.large;
    }
    monkey = new Monkey(name, species, sex, size1, weight, age, favouriteFood);
  }

  /**
   * Constructs the isolation in terms of num and monkey.
   *
   * @param num    the room number of the isolation
   * @param monkey the monkey living in the isolation
   * @throws IllegalArgumentException if monkey is null
   */
  public Isolation(int num, Monkey monkey) {
    super(num);
    if (monkey == null) {
      throw new IllegalArgumentException("the monkey is null");
    }
    this.monkey = monkey;
  }

  @Override
  public ArrayList<String> getName() {
    ArrayList<String> res = new ArrayList<>();
    res.add(monkey.getName());
    return res;
  }

  /**
   * Get a clone of the monkey.
   *
   * @return a clone of the monkey
   */
  public Monkey getMonkey() {
    return monkey.clone();
  }

  @Override
  public String getSpecies() {
    return monkey.getSpecies();
  }

  @Override
  public HashMap<Food, Integer> getFoodAndQuantity() {
    int num;
    if (monkey.getSize() == Size.small) {
      num = 100;
    } else if (monkey.getSize() == Size.medium) {
      num = 250;
    } else {
      num = 500;
    }
    HashMap<Food, Integer> foodQuantity = new HashMap<>();
    foodQuantity.put(monkey.getFavouriteFood(), num);
    return foodQuantity;
  }
}
