package view;

import controller.Features;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.Image;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * represent GUI and is the view of MVC design pattern.
 */
public class JFrameView extends JFrame implements IView {
  private JScrollPane jsp;
  private JPanel jp;
  private final JPanel jp1;
  private final JPanel jp2;
  private final JLabel display;
  private final JButton buttonUp;
  private final JButton buttonDown;
  private final JButton buttonLeft;
  private final JButton buttonRight;
  private ImageIcon img;
  private final JMenuItem item1;
  private final JMenuItem item2;
  private final JMenuItem item3;
  private final JMenuItem item4;
  private final JMenuItem item5;
  private final JMenuItem item6;
  private final JMenuItem item7;
  private final JMenuItem item8;
  private Map<Integer, String> map;
  private int row;
  private int col;
  private boolean control;

  /**
   * Constructor.
   *
   * @param title the title of the view.
   */
  public JFrameView(String title) {
    super(title);
    map = new HashMap<>();
    control = false;

    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("game");
    JMenu menu1 = new JMenu("parameter");
    item1 = new JMenuItem("begin");
    item2 = new JMenuItem("quit");
    item3 = new JMenuItem("row");
    item4 = new JMenuItem("column");
    item5 = new JMenuItem("interconnectivity");
    item6 = new JMenuItem("wrap");
    item7 = new JMenuItem("percent");
    item8 = new JMenuItem("monsterNum");

    menu.add(item1);
    menu.addSeparator();
    menu.add(item2);

    menu1.add(item3);
    menu1.addSeparator();
    menu1.add(item4);
    menu1.addSeparator();
    menu1.add(item5);
    menu1.addSeparator();
    menu1.add(item6);
    menu1.addSeparator();
    menu1.add(item7);
    menu1.addSeparator();
    menu1.add(item8);

    menuBar.add(menu);
    menuBar.add(menu1);

    setJMenuBar(menuBar);

    jsp = null;
    jp = null;

    JPanel jp = new JPanel();
    jp.setBounds(700, 0, 300, 1000);
    jp.setLayout(null);
    add(jp);

    img = new ImageIcon("res/images/up.jpg");
    img.setImage(img.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    buttonUp = new JButton(img);
    jp.add(buttonUp);
    buttonUp.setBounds(110, 470, 80, 80);

    img = new ImageIcon("res/images/down.jpg");
    img.setImage(img.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    buttonDown = new JButton(img);
    jp.add(buttonDown);
    buttonDown.setBounds(110, 630, 80, 80);

    img = new ImageIcon("res/images/left.jpg");
    img.setImage(img.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    buttonLeft = new JButton(img);
    jp.add(buttonLeft);
    buttonLeft.setBounds(30, 550, 80, 80);

    img = new ImageIcon("res/images/right.jpg");
    img.setImage(img.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    buttonRight = new JButton(img);
    jp.add(buttonRight);
    buttonRight.setBounds(190, 550, 80, 80);

    JLabel jLabel;
    img = new ImageIcon("res/images/diamond.png");
    img.setImage(img.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
    jLabel = new JLabel("1", img, JLabel.CENTER);
    jp.add(jLabel);
    jLabel.setBounds(0, 10, 75, 75);

    img = new ImageIcon("res/images/emerald.png");
    img.setImage(img.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
    jLabel = new JLabel("2", img, JLabel.CENTER);
    jp.add(jLabel);
    jLabel.setBounds(75, 10, 75, 75);

    img = new ImageIcon("res/images/ruby.png");
    img.setImage(img.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
    jLabel = new JLabel("3", img, JLabel.CENTER);
    jp.add(jLabel);
    jLabel.setBounds(150, 10, 75, 75);

    img = new ImageIcon("res/images/arrow.png");
    img.setImage(img.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
    jLabel = new JLabel("4", img, JLabel.CENTER);
    jp.add(jLabel);
    jLabel.setBounds(225, 10, 75, 75);

    display = new JLabel();
    jp.add(display);
    display.setBounds(20, 90, 260, 100);
    display.setOpaque(true);
    display.setBackground(Color.RED);

    jLabel = new JLabel("Things in room:");
    jp.add(jLabel);
    jLabel.setBounds(10, 200, 100, 20);

    jp1 = new JPanel();
    jp1.setBounds(0, 230, 300, 60);
    jp.add(jp1);

    jLabel = new JLabel("Player information:");
    jp.add(jLabel);
    jLabel.setBounds(10, 300, 300, 20);

    jp2 = new JPanel();
    jp2.setBounds(0, 330, 300, 75);
    jp.add(jp2);

    setSize(1000, 1000);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
    setVisible(true);
  }

  @Override
  public void createDungeon(int row, int col) {
    this.row = row;
    this.col = col;
    map = new HashMap<>();
    control = true;
  }

  @Override
  public void createDungeonFail() {
    JOptionPane.showMessageDialog(null, "invalid dungeon parameter"
            , "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void updateDungeon(int loc, String direction) {
    Set<Integer> keySet = new HashSet<>(map.keySet());
    if (!keySet.contains(loc)) {
      map.put(loc, direction);
      keySet = new HashSet<>(map.keySet());
    }
    if (jsp != null) {
      getContentPane().remove(jsp);
      getContentPane().repaint();
      jsp = null;
    }
    if (jp != null) {
      getContentPane().remove(jp);
      getContentPane().repaint();
      jp = null;
    }

    if (row > 8 || col > 6) {
      ImageIcon img1 = new ImageIcon("res/images/blank.png");
      img1.setImage(img1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

      JPanel jp1 = new JPanel();
      jp1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
      jp1.setPreferredSize(new Dimension(col * 100, row * 100));

      JLabel cl;
      for (int i = 1; i < col * row + 1; i++) {
        cl = new JLabel();
        if (keySet.contains(i)) {
          if (i == loc) {
            img = new ImageIcon(String.format("res/images/%s1.png", map.get(i)));
          } else {
            img = new ImageIcon(String.format("res/images/%s.png", map.get(i)));
          }
          img.setImage(img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
          cl.setIcon(img);
        } else {
          cl.setIcon(img1);
        }
        jp1.add(cl);
        cl.setSize(100, 100);
      }

      int width = 700;
      int height = 850;

      if (row <= 8) {
        height = row * 100 + 50;
      }
      if (col <= 6) {
        width = col * 100 + 50;
      }

      jsp = new JScrollPane(jp1);
      add(jsp);
      jsp.setBounds(0, 0, width, height);
      revalidate();
    } else {
      ImageIcon img1 = new ImageIcon("res/images/blank.png");
      img1.setImage(img1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

      jp = new JPanel();
      jp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

      JLabel cl;
      for (int i = 1; i < col * row + 1; i++) {
        cl = new JLabel();
        if (keySet.contains(i)) {
          if (i == loc) {
            img = new ImageIcon(String.format("res/images/%s1.png", map.get(i)));
          } else {
            img = new ImageIcon(String.format("res/images/%s.png", map.get(i)));
          }
          img.setImage(img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
          cl.setIcon(img);
        } else {
          cl.setIcon(img1);
        }
        jp.add(cl);
        cl.setSize(100, 100);
      }

      add(jp);
      jp.setBounds(0, 0, col * 100, row * 100);
      revalidate();
    }
  }

  @Override
  public void setFeatures(Features f) {
    item1.addActionListener(new MenuActionListener1() {

      @Override
      public void actionPerformed(ActionEvent e) {
        f.begin();
      }
    });

    item2.addActionListener(new MenuActionListener2() {
      @Override
      public void actionPerformed(ActionEvent e) {
        f.exitProgram();
      }
    });

    item3.addActionListener(new MenuActionListener3() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String str = JOptionPane.showInputDialog("input row of dungeon:");
        if (str != null) {
          try {
            int num = Integer.parseInt(str);
            f.setRow(num);
            JOptionPane.showMessageDialog(null, "you successfully set row to be "
                    + String.format("%s", num));
          } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "row should be integer", "Error"
                    , JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });

    item4.addActionListener(new MenuActionListener4() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String str = JOptionPane.showInputDialog("input column of dungeon:");
        if (str != null) {
          try {
            int num = Integer.parseInt(str);
            f.setCol(num);
            JOptionPane.showMessageDialog(null, "you successfully set column to be "
                    + String.format("%s", num));
          } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "column should be integer",
                    "Error", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });

    item5.addActionListener(new MenuActionListener5() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String str = JOptionPane.showInputDialog("input interconnectivity of dungeon:");
        if (str != null) {
          try {
            int num = Integer.parseInt(str);
            f.setInterconnectivity(num);
            JOptionPane.showMessageDialog(null, "you successfully set interconnectivity to be "
                    + String.format("%s", num));
          } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "interconnectivity should be integer",
                    "Error", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });

    item6.addActionListener(new MenuActionListener6() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String[] options = {"true", "false"};
        int i = JOptionPane.showOptionDialog(null, "wrap of dungeon",
                "Click a button", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[1]);
        if (i == 1) {
          f.setWrap(false);
          JOptionPane.showMessageDialog(null, "you successfully set the dungeon to be unwrapped");
        } else if (i == 0) {
          f.setWrap(true);
          JOptionPane.showMessageDialog(null, "you successfully set the dungeon to be wrapped");
        }
      }
    });

    item7.addActionListener(new MenuActionListener7() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String str = JOptionPane.showInputDialog("input percentage of dungeon:");
        if (str != null) {
          try {
            int num = Integer.parseInt(str);
            f.setPercent(num);
            JOptionPane.showMessageDialog(null, "you successfully set percentage to be "
                    + String.format("%s", num));
          } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "percentage should be integer",
                    "Error", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });

    item8.addActionListener(new MenuActionListener8() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String str = JOptionPane.showInputDialog("input monster number of dungeon:");
        if (str != null) {
          try {
            int num = Integer.parseInt(str);
            f.setMonsterNum(num);
            JOptionPane.showMessageDialog(null, "you successfully set monster number to be "
                    + String.format("%s", num));
          } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "monster number should be integer",
                    "Error", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });

    buttonUp.addActionListener(e -> {
      if (control) {
        f.move("north");
      }
    });

    buttonDown.addActionListener(e -> {
      if (control) {
        f.move("south");
      }
    });

    buttonLeft.addActionListener(e -> {
      if (control) {
        f.move("west");
      }
    });

    buttonRight.addActionListener(e -> {
      if (control) {
        f.move("east");
      }
    });

    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (control) {
          switch (e.getKeyChar()) {
            case '1':
              f.pickUp(1);
              break;
            case '2':
              f.pickUp(2);
              break;
            case '3':
              f.pickUp(3);
              break;
            case '4':
              f.pickUp(4);
              break;
            case 's':
              if (!f.judgeWhetherHaveArrow()) {
                break;
              }
              String[] options = {"north", "west", "south", "east"};
              int i = JOptionPane.showOptionDialog(null, "choose direction",
                      "Click a button", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                      null, options, options[3]);
              if (i != -1) {
                String str = JOptionPane.showInputDialog("input the distance:");
                if (str != null) {
                  try {
                    int num = Integer.parseInt(str);
                    String dir = "east";
                    if (i == 2) {
                      dir = "south";
                    } else if (i == 1) {
                      dir = "west";
                    } else if (i == 0) {
                      dir = "north";
                    }
                    f.shoot(dir, num);
                  } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "distance should be integer",
                            "Error", JOptionPane.ERROR_MESSAGE);
                  }
                }
              }
              break;
            default:
              break;
          }
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (control) {
          switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
              f.move("north");
              break;
            case KeyEvent.VK_DOWN:
              f.move("south");
              break;
            case KeyEvent.VK_LEFT:
              f.move("west");
              break;
            case KeyEvent.VK_RIGHT:
              f.move("east");
              break;
            default:
              break;
          }
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // we don't use this method.
      }
    });
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void gameEnd(int i) {
    if (i == 2) {
      JOptionPane.showMessageDialog(null, "you reach the end!");
      control = false;
    } else if (i == 3) {
      JOptionPane.showMessageDialog(null,
              "Unfortunately you are eaten by the monster, Good Luck next time!");
      control = false;
    }
  }

  @Override
  public void showObject(List<Integer> list) {
    jp1.removeAll();
    jp1.updateUI();
    JLabel jLabel;
    for (Integer i : list) {
      if (i == 1) {
        img = new ImageIcon("res/images/diamond.png");
        img.setImage(img.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        jLabel = new JLabel(img);
        jp1.add(jLabel);
      } else if (i == 2) {
        img = new ImageIcon("res/images/emerald.png");
        img.setImage(img.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        jLabel = new JLabel(img);
        jp1.add(jLabel);
      } else if (i == 3) {
        img = new ImageIcon("res/images/ruby.png");
        img.setImage(img.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        jLabel = new JLabel(img);
        jp1.add(jLabel);
      } else if (i == 4) {
        img = new ImageIcon("res/images/arrow.png");
        img.setImage(img.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        jLabel = new JLabel(img);
        jp1.add(jLabel);
      }
      jp1.updateUI();
    }
    revalidate();
  }

  @Override
  public void setJLabel(int[] i, int j) {
    if (j == 0) {
      display.setText(String.format("current location is [%d, %d]", i[0], i[1]));
    } else if (j == 1) {
      display.setText(String.format("<html>current location is [%d, %d]<br/>" +
              "there is a less pungent smell</html>", i[0], i[1]));
    } else {
      display.setText(String.format("<html>current location is [%d, %d]<br/>" +
              "there is a great pungent smell</html>", i[0], i[1]));
    }
  }

  @Override
  public void playerInformation(Map<String, Integer> input) {
    jp2.removeAll();
    jp2.updateUI();
    JLabel jLabel;
    Set<String> keySet = new HashSet<>(input.keySet());
    if (keySet.contains("Arrow") && input.get("Arrow") != 0) {
      img = new ImageIcon("res/images/arrow.png");
      img.setImage(img.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
      jLabel = new JLabel(Integer.toString(input.get("Arrow")), img, JLabel.CENTER);
      jp2.add(jLabel);
      jp2.updateUI();
    }
    if (keySet.contains("DIAMONDS")) {
      img = new ImageIcon("res/images/diamond.png");
      img.setImage(img.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
      jLabel = new JLabel(Integer.toString(input.get("DIAMONDS")), img, JLabel.CENTER);
      jp2.add(jLabel);
      jp2.updateUI();
    }
    if (keySet.contains("RUBIES")) {
      img = new ImageIcon("res/images/ruby.png");
      img.setImage(img.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
      jLabel = new JLabel(Integer.toString(input.get("RUBIES")), img, JLabel.CENTER);
      jp2.add(jLabel);
      jp2.updateUI();
    }
    if (keySet.contains("SAPPHIRES")) {
      img = new ImageIcon("res/images/emerald.png");
      img.setImage(img.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
      jLabel = new JLabel(Integer.toString(input.get("SAPPHIRES")), img, JLabel.CENTER);
      jp2.add(jLabel);
      jp2.updateUI();
    }
    revalidate();
  }

  @Override
  public void shoot(int i) {
    if (i == 0) {
      JOptionPane.showMessageDialog(null, "you have no arrow",
              "Warning", JOptionPane.WARNING_MESSAGE);
    } else if (i == 1) {
      JOptionPane.showMessageDialog(null, "you don't hit the monster");
    } else if (i == 2) {
      JOptionPane.showMessageDialog(null, "you successfully hit the monster");
    }
  }

  /**
   * represent menu action listener for the beginning menu item.
   */
  static abstract class MenuActionListener1 implements ActionListener {
  }

  /**
   * represent menu action listener for the quit menu item.
   */
  static abstract class MenuActionListener2 implements ActionListener {
  }

  /**
   * represent menu action listener for the row menu item.
   */
  static abstract class MenuActionListener3 implements ActionListener {
  }

  /**
   * represent menu action listener for the column menu item.
   */
  static abstract class MenuActionListener4 implements ActionListener {
  }

  /**
   * represent menu action listener for the interconnectivity menu item.
   */
  static abstract class MenuActionListener5 implements ActionListener {
  }

  /**
   * represent menu action listener for the wrap menu item.
   */
  static abstract class MenuActionListener6 implements ActionListener {
  }

  /**
   * represent menu action listener for the percent menu item.
   */
  static abstract class MenuActionListener7 implements ActionListener {
  }

  /**
   * represent menu action listener for the monsterNum menu item.
   */
  static abstract class MenuActionListener8 implements ActionListener {
  }

}
