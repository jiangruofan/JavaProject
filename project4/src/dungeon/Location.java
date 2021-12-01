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
  private int monsterLife;
  private boolean arrow;

  /**
   * Construct Location in terms of num.
   *
   * @param num the number of the location
   */
  Location(int num) {
    this.num = num;
    neighbors = new ArrayList<>();
    treasures = new ArrayList<>();
    monsterLife = 0;
    arrow = false;
  }

  /**
   * put a monster in this location.
   */
  public void setMonsterLife() {
    monsterLife = 2;
  }

  /** get the monster life.
   *
   * @return monster life
   */
  public int getMonsterLife() {
    return monsterLife;
  }

  /**
   * if the monster is hit, decrease its life by 1.
   *
   */
  public void updateMonsterLife() {
    if (monsterLife != 0) {
      monsterLife -= 1;
    }
  }

  /**
   * put an arrow in this location.
   */
  public void increaseArrow() {
    if (arrow) {
      throw new IllegalStateException("There is one arrow");
    }
    arrow = true;
  }

  /**
   * if an arrow is picked up by player, there will be no arrow in this location.
   */
  public void decreaseArrow() {
    if (!arrow) {
      throw new IllegalStateException("there is no arrow");
    }
    arrow = false;
  }

  /**
   * judge whether there is an arrow.
   * @return
   */
  public boolean getArrow() {
    return arrow;
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
