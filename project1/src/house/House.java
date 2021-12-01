package house;

import monkey.Food;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The house represents the living area of the monkey, and it stores the information of monkeys.
 * and has a room number.
 */
public interface House {
  /**
   * Gets the room number of house.
   *
   * @return the room number of the house.
   */
  int getNumber();

  /**
   * Gets the species living in the house.
   *
   * @return the species living in the house.
   */
  String getSpecies();

  /**
   * Gets the name(s) of the monkey living in the house.
   *
   * @return the name(s) of the monkey living in the house.
   */
  ArrayList<String> getName();

  /**
   * Gets the food of the monkey and the corresponding quantity.
   *
   * @return food and its quantity.
   */
  HashMap<Food, Integer> getFoodAndQuantity();
}
