package test;

import controller.Features;
import view.IView;

import java.util.List;
import java.util.Map;

/**
 * mock the view to help test the controller.
 */
public class IViewMock implements IView {
  private final StringBuffer stringBuffer;

  /**
   * set up the field.
   */
  public IViewMock() {
    stringBuffer = new StringBuffer();
  }

  /**
   * when this method is called, it will add a string to the stringBuffer.
   *
   * @param f the set of feature callbacks as a Features object
   */
  @Override
  public void setFeatures(Features f) {
    stringBuffer.append("setFeatures");
  }

  /**
   * when this method is called, it will add a string to the stringBuffer.
   *
   * @param row the row of the dungeon
   * @param col the column of the dungeon
   */
  @Override
  public void createDungeon(int row, int col) {
    stringBuffer.append("createDungeon");
  }

  /**
   * when this method is called, it will add a string to the stringBuffer.
   */
  @Override
  public void createDungeonFail() {
    stringBuffer.append("fail");
  }

  /**
   * when this method is called, it will add a string to the stringBuffer.
   *
   * @param loc       the location of the new room
   * @param direction the neighbors of the new room
   */
  @Override
  public void updateDungeon(int loc, String direction) {
    stringBuffer.append("update");
  }

  /**
   * when this method is called, it will add a string to the stringBuffer.
   */
  @Override
  public void resetFocus() {
    stringBuffer.append("focus");
  }

  /**
   * when this method is called, it will add a string to the stringBuffer.
   *
   * @param i 2 if player reached the end and 3 if player is eaten
   */
  @Override
  public void gameEnd(int i) {
    stringBuffer.append("end");
  }

  /**
   * when this method is called, it will add a string to the stringBuffer.
   *
   * @param list list of items in the room
   */
  @Override
  public void showObject(List<Integer> list) {
    stringBuffer.append("show");
  }

  /**
   * when this method is called, it will add a string to the stringBuffer.
   *
   * @param i location of the player
   * @param j the smell of the monster
   */
  @Override
  public void setJLabel(int[] i, int j) {
    stringBuffer.append("setJLabel");
  }

  /**
   * when this method is called, it will add a string to the stringBuffer.
   *
   * @param input the treasure the player has collected
   */
  @Override
  public void playerInformation(Map<String, Integer> input) {
    stringBuffer.append("playerInformation");
  }

  /**
   * when this method is called, it will add a string to the stringBuffer.
   *
   * @param i 0 if player has no arrow, 1 if players don't hit monster otherwise 2
   */
  @Override
  public void shoot(int i) {
    stringBuffer.append("shoot");
  }

  /**
   * used to return stringBuffer.
   *
   * @return the stringBuffer
   */
  public String check() {
    return stringBuffer.toString();
  }
}
