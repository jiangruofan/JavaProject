package house;

import monkey.Monkey;
import monkey.Size;
import monkey.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * The enclosure represents a single place where many monkeys of same species could live there,
 * and it stores the information of the monkey living there.
 * Monkeys can not be directly transferred to enclosures without going to isolations first.
 */
public class Enclosures extends AbstractHouse {
  private int sizeLeft;
  private final List<Monkey> monkeys;

  /**
   * Constructs enclosures in terms of num, sizeLeft, monkey.
   *
   * @param num      the room number of the enclosure
   * @param sizeLeft the left space available to put a monkey in
   * @param monkey   the monkey living in the enclosure
   * @throws IllegalArgumentException if monkey is null
   */
  public Enclosures(int num, int sizeLeft, Monkey monkey) {
    super(num);
    if (monkey == null) {
      throw new IllegalArgumentException("the monkey is null");
    }
    this.sizeLeft = sizeLeft;
    monkeys = new ArrayList<>();
    update(monkey);
  }

  /**
   * Judge whether there is enough space for the potential monkey.
   *
   * @param size the size of the potential monkey
   * @return if there is enough space, return true, otherwise false.
   */
  public boolean judgeSpace(Size size) {
    if (size == Size.small && (sizeLeft - 1) >= 0) {
      return true;
    } else if (size == Size.medium && (sizeLeft - 5) >= 0) {
      return true;
    } else {
      return size == Size.large && (sizeLeft - 10) >= 0;
    }
  }

  /**
   * Update the room information by decreasing the sizeLeft and adding the monkey
   * when there is a monkey coming in.
   *
   * @param monkey the monkey which will be living in this enclosure.
   * @throws IllegalArgumentException if monkey is null
   */
  public void update(Monkey monkey) {
    if (monkey == null) {
      throw new IllegalArgumentException("the monkey is null");
    }
    if (monkey.getSize() == Size.small) {
      sizeLeft -= 1;
    } else if (monkey.getSize() == Size.medium) {
      sizeLeft -= 5;
    } else {
      sizeLeft -= 10;
    }
    monkeys.add(monkey);
  }

  /**
   * Update the room information by increasing the sizeLeft and removing the monkey
   * when there is a monkey transferred to isolation.
   *
   * @param name the name of the monkey transferred to isolation
   * @return the monkey that will be transferred to isolation
   * @throws IllegalArgumentException if name is empty
   */
  public Monkey update(String name) {
    if (name == null || name.length() <= 0) {
      throw new IllegalArgumentException("the name is empty");
    }
    Monkey monkey;
    for (int i = 0; i < monkeys.size(); i++) {
      if (monkeys.get(i).getName().equalsIgnoreCase(name)) {
        monkey = monkeys.get(i).clone();
        if (monkey.getSize() == Size.small) {
          sizeLeft += 1;
        } else if (monkey.getSize() == Size.medium) {
          sizeLeft += 5;
        } else {
          sizeLeft += 10;
        }
        monkeys.remove(i);
        return monkey;
      }
    }
    return null;
  }

  /**
   * Get the current available size of the room.
   *
   * @return the current available size of the room
   */
  public int getSizeLeft() {
    return sizeLeft;
  }

  /**
   * Produce a sign for this enclosure which includes the name, sex and favourite food of monkeys.
   *
   * @return a string list that includes the name, sex and favourite food of monkeys
   */
  public ArrayList<String> produceSign() {
    ArrayList<String> res1 = new ArrayList<>();
    String res;
    for (Monkey monkey : monkeys) {
      res = String.format("%s, %s, %s", monkey.getName(),
              monkey.getSex().name(), monkey.getFavouriteFood().name());
      res1.add(res);
    }
    Collections.sort(res1);
    return res1;
  }

  @Override
  public String getSpecies() {
    return monkeys.get(0).getSpecies();
  }

  @Override
  public ArrayList<String> getName() {
    ArrayList<String> res = new ArrayList<>();
    for (Monkey monkey : monkeys) {
      res.add(monkey.getName());
    }
    return res;
  }

  @Override
  public HashMap<Food, Integer> getFoodAndQuantity() {
    int num;
    HashMap<Food, Integer> foodQuantity = new HashMap<>();
    for (Monkey monkey : monkeys) {
      if (monkey.getSize() == Size.small) {
        num = 100;
      } else if (monkey.getSize() == Size.medium) {
        num = 250;
      } else {
        num = 500;
      }
      if (foodQuantity.containsKey(monkey.getFavouriteFood())) {
        foodQuantity.replace(monkey.getFavouriteFood(),
                foodQuantity.get(monkey.getFavouriteFood()) + num);
      } else {
        foodQuantity.put(monkey.getFavouriteFood(), num);
      }
    }
    return foodQuantity;
  }
}
