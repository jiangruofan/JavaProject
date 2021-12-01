package dungeon;

import treasure.Treasure;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a package-private class representing the cave or.
 * tunnel depending on whether it has treasure.
 */
class Location {
  private final int num;
  private final List<Integer> neighbors;
  private final List<Treasure> treasures;

  /**
   * Construct Location in terms of num.
   *
   * @param num the number of the location
   */
  Location(int num) {
    this.num = num;
    neighbors = new ArrayList<>();
    treasures = new ArrayList<>();
  }

  /**
   * set the neighbors of the location.
   *
   * @param i the number of the neighbors
   */
  public void setNeighbors(int i) {
    neighbors.add(i);
  }

  /**
   * get the neighbors of the location.
   *
   * @return a list of numbers of neighbors
   */
  public List<Integer> getNeighbors() {
    return neighbors;
  }

  /**
   * set the treasure of the location.
   */
  public void setTreasures() {
    if (treasures.size() == 0) {
      treasures.add(Treasure.DIAMONDS);
    } else if (treasures.size() == 1) {
      treasures.add(Treasure.RUBIES);
    } else if (treasures.size() == 2) {
      treasures.add(Treasure.SAPPHIRES);
    }
  }

  /**
   * get the treasure of the location.
   *
   * @return a list of treasure
   */
  public List<Treasure> getTreasures() {
    return treasures;
  }

  /**
   * judge whether it is a cave or tunnel.
   *
   * @return true if it's cave otherwise false
   */
  public boolean caveOrTunnel() {
    return neighbors.size() != 2;
  }

  /**
   * remove the treasure of the location.
   *
   * @param treasure the treasure you want to remove
   */
  public void updateTreasure(Treasure treasure) {
    if (treasure == null) {
      throw new IllegalArgumentException("invalid input");
    }
    treasures.remove(treasure);
  }

  /**
   * get the number of the location.
   *
   * @return the number of the location
   */
  public int getNum() {
    return num;
  }
}
