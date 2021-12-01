package sanctuary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import house.Isolation;
import house.Enclosures;
import monkey.Monkey;
import monkey.Size;
import monkey.Food;
import monkey.Sex;

/**
 * The sanctuary class is a central manger that coordinate isolation and enclosures class.
 * We fulfil all the needs in this class.
 */
public class Sanctuary {
  private final List<Isolation> isolations;
  private final List<Enclosures> enclosures;
  private boolean[] emptyIsolation;
  private boolean[] emptyEnclosures;
  private final int enclosuresSize;

  /**
   * Construct the sanctuary in terms of isolationNum, enclosuresNum and enclosuresSize.
   *
   * @param isolationNum   how many isolations there are in the sanctuary
   * @param enclosuresNum  how many enclosures there are in the sanctuary
   * @param enclosuresSize the size of each enclosure
   * @throws IllegalArgumentException if input values are negative
   */
  public Sanctuary(int isolationNum, int enclosuresNum, int enclosuresSize) {
    if (isolationNum < 0 || enclosuresNum < 0 || enclosuresSize < 0) {
      throw new IllegalArgumentException("The number of room and size should be positive.");
    }
    this.enclosuresSize = enclosuresSize;
    emptyIsolation = new boolean[isolationNum];
    emptyEnclosures = new boolean[enclosuresNum];
    isolations = new ArrayList<>();
    enclosures = new ArrayList<>();
  }

  /**
   * Put a monkey into the isolation and record its attributes.
   *
   * @param name          the name of the monkey
   * @param species       the species of the monkey
   * @param sex           the sex of the monkey
   * @param size          the size of the monkey
   * @param weight        the weight of the monkey
   * @param age           the age of the monkey
   * @param favouriteFood the favourite food of the monkey
   * @throws IllegalArgumentException if name already exists or is empty or species is empty
   * @throws IllegalStateException    if the isolation is full
   */
  public void setIsolations(String name, String species, Sex sex,
                            int size, double weight, int age, Food favouriteFood) {
    if (name == null || name.length() <= 0) {
      throw new IllegalArgumentException("the name is empty");
    }
    if (species == null || species.length() <= 0) {
      throw new IllegalArgumentException("the species is empty");
    }
    for (Isolation isolation : isolations) {
      if (isolation.getName().get(0).equalsIgnoreCase(name)) {
        throw new IllegalArgumentException("the name already exists in isolation");
      }
    }
    for (Enclosures enclosure : enclosures) {
      for (String str : enclosure.getName()) {
        if (str.equalsIgnoreCase(name)) {
          throw new IllegalArgumentException("the name already exists in enclosure");
        }
      }
    }
    int num = 0;
    for (int i = 0; i < emptyIsolation.length; i++) {
      if (!emptyIsolation[i]) {
        num = i + 1;
        emptyIsolation[i] = true;
        break;
      }
    }
    if (num == 0) {
      throw new IllegalStateException("The isolations are full");
    }
    isolations.add(new Isolation(num, name, species, sex, size, weight, age, favouriteFood));
  }

  /**
   * Put a monkey into the isolation which comes from enclosure.
   *
   * @param monkey the monkey comes from the enclosure
   * @return if the isolation is not full, return the isolation room number, otherwise 0
   */
  private int setIsolations(Monkey monkey) {
    int num = 0;
    for (int i = 0; i < emptyIsolation.length; i++) {
      if (!emptyIsolation[i]) {
        num = i + 1;
        emptyIsolation[i] = true;
        break;
      }
    }
    if (num != 0) {
      isolations.add(new Isolation(num, monkey));
    }
    return num;
  }

  /**
   * Put a monkey into the enclosure which comes from isolation.
   *
   * @param monkey the monkey comes from isolation
   * @return if the enclosure is not full, return the enclosure room number, otherwise 0
   */
  private int setEnclosures(Monkey monkey) {
    int num = 0;
    for (int i = 0; i < emptyEnclosures.length; i++) {
      if (!emptyEnclosures[i]) {
        num = i + 1;
        emptyEnclosures[i] = true;
        break;
      }
    }
    if (num != 0) {
      enclosures.add(new Enclosures(num, enclosuresSize, monkey));
    }
    return num;
  }

  /**
   * Transfer an ill monkey from the enclosure to the isolation.
   *
   * @param name the name of the monkey
   * @return the isolation room number where the monkey will be transferred
   * @throws IllegalArgumentException if name does not exist or is empty
   * @throws IllegalStateException    if the isolation is full
   */
  public String transferToIsolation(String name) {
    if (name == null || name.length() <= 0) {
      throw new IllegalArgumentException("the name is empty");
    }
    Monkey monkey;
    for (Enclosures enclosure : enclosures) {
      monkey = enclosure.update(name);
      if (monkey != null) {
        if (enclosure.getSizeLeft() == enclosuresSize) {
          emptyEnclosures[enclosure.getNumber() - 1] = false;
          enclosures.remove(enclosure);
        }
        int num = setIsolations(monkey);
        if (num == 0) {
          throw new IllegalStateException("The isolations are full and "
                  + "you need to find out another solution");
        } else {
          return String.format("%s is successfully transferred to Isolation %d", name, num);
        }
      }
    }
    throw new IllegalArgumentException("the monkey does not exit.");
  }

  /**
   * Transfer a monkey from isolation to enclosure.
   *
   * @param name the name of the monkey
   * @return where the monkey should be transferred to
   * @throws IllegalArgumentException if name does not exist or is empty
   */
  public String transferToEnclosure(String name) {
    if (name == null || name.length() <= 0) {
      throw new IllegalArgumentException("the name is empty");
    }
    Monkey monkey = null;
    for (int i = 0; i < isolations.size(); i++) {
      Isolation isolation = isolations.get(i);
      if (isolation.getName().get(0).equalsIgnoreCase(name)) {
        monkey = isolation.getMonkey();
        isolations.remove(i);
        emptyIsolation[isolation.getNumber() - 1] = false;
        break;
      }
    }
    if (monkey == null) {
      throw new IllegalArgumentException("the monkey does not exist.");
    }
    if ((monkey.getSize() == Size.small && enclosuresSize < 1)
            || (monkey.getSize() == Size.medium && enclosuresSize < 5)
            || (monkey.getSize() == Size.large && enclosuresSize < 10)) {
      return "The monkey is too large and there is no room big enough to put it,"
              + " it will be transferred to other sanctuary";
    }
    for (Enclosures enclosure : enclosures) {
      if (enclosure.getSpecies().equalsIgnoreCase(monkey.getSpecies())) {
        if (enclosure.judgeSpace(monkey.getSize())) {
          enclosure.update(monkey);
          return String.format("%s is successfully transferred to Enclosures %d",
                  name, enclosure.getNumber());
        } else {
          int num = setEnclosures(monkey);
          if (num == 0) {
            return "there is no room available for the monkey in this sanctuary"
                    + " and will be transferred to other sanctuaries";
          } else {
            return String.format("%s is successfully transferred to Enclosures %d", name, num);
          }
        }
      }
    }
    int num = setEnclosures(monkey);
    if (num == 0) {
      return "there is no room available for the monkey in this sanctuary"
              + " and will be transferred to other sanctuaries";
    } else {
      return String.format("%s is successfully transferred to Enclosures %d", name, num);
    }
  }

  /**
   * Get all the species and their living place.
   *
   * @return a string list of where they live
   */
  public ArrayList<String> reportSpecies() {
    ArrayList<String> res = new ArrayList<>();
    for (Isolation isolation : isolations) {
      res.add(String.format("%s is in isolation:%d",
              isolation.getSpecies(), isolation.getNumber()));
    }
    for (Enclosures enclosure : enclosures) {
      res.add(String.format("%s is in enclosures:%d",
              enclosure.getSpecies(), enclosure.getNumber()));
    }
    Collections.sort(res);
    return res;
  }

  /**
   * Look up a certain species.
   *
   * @param species the species we want to look up
   * @return if the species exists, return its place otherwise report the fact
   * @throws IllegalArgumentException if species is empty
   */
  public ArrayList<String> lookUpSpecies(String species) {
    if (species == null || species.length() <= 0) {
      throw new IllegalArgumentException("the name is empty");
    }
    ArrayList<String> res = new ArrayList<>();
    for (Isolation isolation : isolations) {
      if (isolation.getSpecies().equalsIgnoreCase(species)) {
        res.add(String.format("isolation:%d", isolation.getNumber()));
      }
    }
    for (Enclosures enclosure : enclosures) {
      if (enclosure.getSpecies().equalsIgnoreCase(species)) {
        res.add(String.format("enclosures:%d", enclosure.getNumber()));
      }
    }
    if (res.size() == 0) {
      res.add("there is no such species");
    }
    return res;
  }

  /**
   * Produce a sign for a certain enclosure.
   *
   * @param num the room number of the enclosure
   * @return if the enclosure is not empty, return the sign otherwise report the fact
   */
  public ArrayList<String> produceSign(int num) {
    for (Enclosures enclosure : enclosures) {
      if (enclosure.getNumber() == num) {
        return enclosure.produceSign();
      }
    }
    ArrayList<String> res = new ArrayList<>();
    res.add("the enclosure is empty");
    return res;
  }

  /**
   * Produce the name list for all monkeys and their living area.
   *
   * @return a list of the names and living space of monkeys
   */
  public ArrayList<String> produceNameList() {
    ArrayList<String> res = new ArrayList<>();
    for (Isolation isolation : isolations) {
      res.add(String.format("%s in isolation:%d",
              isolation.getName().get(0), isolation.getNumber()));
    }
    for (Enclosures enclosure : enclosures) {
      for (String i : enclosure.getName()) {
        res.add(String.format("%s in enclosure:%d", i, enclosure.getNumber()));
      }
    }
    Collections.sort(res);
    return res;
  }

  /**
   * Produce a shopping list of the monkeys' food.
   *
   * @return a hashmap of the food and its quantity
   */
  public HashMap<Food, Integer> produceShoppingList() {
    HashMap<Food, Integer> res = new HashMap<>();
    for (Isolation isolation : isolations) {
      for (Food key : isolation.getFoodAndQuantity().keySet()) {
        if (res.containsKey(key)) {
          res.replace(key, res.get(key) + isolation.getFoodAndQuantity().get(key));
        } else {
          res.put(key, isolation.getFoodAndQuantity().get(key));
        }
      }
    }
    for (Enclosures enclosure : enclosures) {
      for (Food key : enclosure.getFoodAndQuantity().keySet()) {
        if (res.containsKey(key)) {
          res.replace(key, res.get(key) + enclosure.getFoodAndQuantity().get(key));
        } else {
          res.put(key, enclosure.getFoodAndQuantity().get(key));
        }
      }
    }
    return res;
  }

  /**
   * Expand the isolation and enclosure.
   *
   * @param isolationNum  new size of the isolation
   * @param enclosuresNum new size of the enclosures
   * @return a list of the old and new size of the isolation and enclosure
   * @throws IllegalArgumentException if new sizes are smaller than old sizes
   */
  public int[] expand(int isolationNum, int enclosuresNum) {
    if (isolationNum < emptyIsolation.length || enclosuresNum < emptyEnclosures.length) {
      throw new IllegalArgumentException("new size should be larger than old size");
    }
    boolean[] a = new boolean[isolationNum];
    boolean[] b = new boolean[enclosuresNum];
    System.arraycopy(emptyIsolation, 0, a, 0, emptyIsolation.length);
    System.arraycopy(emptyEnclosures, 0, b, 0, emptyEnclosures.length);
    int[] res = {emptyIsolation.length, emptyEnclosures.length, a.length, b.length};
    emptyIsolation = a;
    emptyEnclosures = b;
    return res;
  }
}
