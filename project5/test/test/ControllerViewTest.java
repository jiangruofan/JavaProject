package test;

import controller.ControllerView;
import manager.Manager;
import org.junit.Before;
import org.junit.Test;
import random.PredictableRandom;
import random.RandomInterface;

import static org.junit.Assert.assertEquals;

/**
 * test whether the controller can call the right method of view.
 */
public class ControllerViewTest {
  private ControllerView controllerView;
  private IViewMock view;

  /**
   * set up the field.
   */
  @Before
  public void setUp() {
    int[] order = {
        1, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 10, 10, 10,
        1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 5, 1, 1, 1, 1,
        0, 0, 1, 2,
        1, 3,
        0, 1, 2, 3, 4, 5, 6, 7,
        0, 1
    };
    RandomInterface random = new PredictableRandom(order);
    Manager manager = new Manager(4, 4, 0,
            false, 50, random, 3);
    controllerView = new ControllerView(manager);
    view = new IViewMock();
    controllerView.setView(view);
  }

  /**
   * test methods of controller.
   */
  @Test
  public void test() {
    controllerView.setRow(8);
    controllerView.setCol(8);
    controllerView.setInterconnectivity(1);
    controllerView.setWrap(false);
    controllerView.setPercent(50);
    controllerView.setMonsterNum(3);
    controllerView.move("north");
    controllerView.pickUp(1);
    controllerView.shoot("north", 1);
    controllerView.judgeWhetherHaveArrow();
    assertEquals("setFeaturesfocusfocusfocusfocusfocusfocusfo" +
            "cusplayerInformationshowshootplayerInformation", view.check());
  }
}