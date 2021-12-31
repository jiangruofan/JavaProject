package controller;

import manager.Manager;
import manager.ManagerInterface;
import random.RandomInterface;
import random.RealRandom;
import view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * this is the controller for GUI.
 */
public class ControllerView implements Features {
  private ManagerInterface model;
  private IView view;
  private int row;
  private int col;
  private int interconnectivity;
  private boolean wrap;
  private double percent;
  private int monsterNum;
  private final RandomInterface random;

  /**
   * Constructor.
   *
   * @param m the model to use
   */
  public ControllerView(ManagerInterface m) {
    random = new RealRandom();
    model = m;
    row = 4;
    col = 4;
    interconnectivity = 0;
    wrap = false;
    percent = 50;
    monsterNum = 2;
  }

  /**
   * Mutator for the view.
   *
   * @param v the view to use
   */
  @Override
  public void setView(IView v) {
    view = v;
    // give the feature callbacks to the view
    view.setFeatures(this);
  }

  /**
   * get what is in the current room.
   *
   * @return a list of integer representing treasure and arrows
   */
  private List<Integer> getObject() {
    List<Integer> res = new ArrayList<>();
    if (model.getLocDescription().contains("DIAMONDS")) {
      res.add(1);
    }
    if (model.getLocDescription().contains("SAPPHIRES")) {
      res.add(2);
    }
    if (model.getLocDescription().contains("RUBIES")) {
      res.add(3);
    }
    if (model.getLocDescription().contains("1 arrow")) {
      res.add(4);
    }
    return res;
  }

  @Override
  public void begin() {
    try {
      model = new Manager(row, col, interconnectivity, wrap, percent, random, monsterNum);

      StringBuffer res = new StringBuffer();
      if (model.getLocDescription().contains("east")) {
        res.append("E");
      }
      if (model.getLocDescription().contains("south")) {
        res.append("S");
      }
      if (model.getLocDescription().contains("west")) {
        res.append("W");
      }
      if (model.getLocDescription().contains("north")) {
        res.append("N");
      }

      view.createDungeon(row, col);
      view.updateDungeon(model.getPlayerLoc1(), res.toString());
      view.showObject(getObject());
      view.setJLabel(model.getPlayerLoc(), model.detectMonster());
      view.playerInformation(model.getPlayerDescription());
    } catch (IllegalArgumentException e) {
      view.createDungeonFail();
    }
    view.resetFocus();
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void setRow(int row) {
    this.row = row;
    view.resetFocus();
  }

  @Override
  public void setCol(int col) {
    this.col = col;
    view.resetFocus();
  }

  @Override
  public void setInterconnectivity(int interconnectivity) {
    this.interconnectivity = interconnectivity;
    view.resetFocus();
  }

  @Override
  public void setWrap(boolean wrap) {
    this.wrap = wrap;
    view.resetFocus();
  }

  @Override
  public void setPercent(int percent) {
    this.percent = percent;
    view.resetFocus();
  }

  @Override
  public void setMonsterNum(int num) {
    this.monsterNum = num;
    view.resetFocus();
  }

  @Override
  public void move(String str) {
    boolean move = model.move(str);
    if (move) {
      StringBuffer res = new StringBuffer();
      if (model.getLocDescription().contains("east")) {
        res.append("E");
      }
      if (model.getLocDescription().contains("south")) {
        res.append("S");
      }
      if (model.getLocDescription().contains("west")) {
        res.append("W");
      }
      if (model.getLocDescription().contains("north")) {
        res.append("N");
      }
      view.updateDungeon(model.getPlayerLoc1(), res.toString());
      view.showObject(getObject());
      int i = model.judgeEnd();
      if (i == 2) {
        view.gameEnd(2);
      } else if (i == 3) {
        view.gameEnd(3);
      }
      if (i == 1) {
        view.setJLabel(model.getPlayerLoc(), model.detectMonster());
      }
    }
    view.resetFocus();
  }

  @Override
  public void pickUp(int i) {
    switch (i) {
      case 1:
        if (model.whetherHaveTreasure()) {
          if (model.pickUpTreasure("diamonds") == 3) {
            model.updateTreasure("diamonds");
            view.playerInformation(model.getPlayerDescription());
          }
        }
        break;
      case 2:
        if (model.whetherHaveTreasure()) {
          if (model.pickUpTreasure("SAPPHIRES") == 3) {
            model.updateTreasure("SAPPHIRES");
            view.playerInformation(model.getPlayerDescription());
          }
        }
        break;
      case 3:
        if (model.whetherHaveTreasure()) {
          if (model.pickUpTreasure("rubies") == 3) {
            model.updateTreasure("rubies");
            view.playerInformation(model.getPlayerDescription());
          }
        }
        break;
      case 4:
        if (model.judgeWhetherHaveArrow()) {
          model.pickUpArrow("yes");
          view.playerInformation(model.getPlayerDescription());
        }
        break;
      default:
        throw new IllegalArgumentException("error");
    }
    view.showObject(getObject());
  }

  @Override
  public void shoot(String direction, int distance) {
    String input = String.format("%s,%d", direction, distance);
    int i = model.shot(input);
    if (i == 1) {
      view.shoot(2);
    } else if (i == 2) {
      view.shoot(1);
    }
    view.playerInformation(model.getPlayerDescription());
  }

  @Override
  public boolean judgeWhetherHaveArrow() {
    if (!model.judgeWhetherHaveArrow1()) {
      view.shoot(0);
      return false;
    } else {
      return true;
    }
  }


}
