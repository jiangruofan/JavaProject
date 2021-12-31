# Dungeon

The world for our game consists of a _dungeon_, a network of tunnels and caves that are interconnected so that player can explore the entire world by traveling from cave to cave through the tunnels that connect them.


# Overview

In this version of our adventure game, you will add an option to play the game using an interactive graphical user interface (GUI). This GUI should:

1.  expose all game settings including the size of the dungeon, the interconnectivity, whether it is wrapping or not, the percentage of caves that have treasure, and the number of Otyughs through one or more items on a  [JMenu (Links to an external site.)](https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/javax/swing/JMenu.html)
2.  provide an option for quitting the game, restarting the game as a new game with a new dungeon or reusing the same dungeon through one or more items on a  [JMenu (Links to an external site.)](https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/javax/swing/JMenu.html)
3.  display the dungeon to the screen using graphical representation. The view should begin with a mostly blank screen and display only the pieces of the maze that have been revealed by the user's exploration of the caves and tunnels. Dungeons that are bigger than the area allocated it to the screen should provide the ability to scroll the view.
4.  allow the player to move through the dungeon using a mouse click on the screen in addition to the keyboard arrow keys. A click on an invalid space in the game would not advance the player.
5.  display the details of a dungeon location to the screen. For instance, does it have treasure, does it have an arrow, does it smell.
6.  provide an option to get the player's description
7.  provide an option for the player to pick up a treasure or an arrow through pressing a key on the keyboard.
8.  provide an option for the player to shoot an arrow by pressing a key on the keyboard followed by an arrow key to indicate the direction.
9.  provide a clear indication of the results of each action a player takes.

## List of features

I added one controller class and one view class to produce the GUI interface. Now I have 2 controllers, one for text-based view and the other for GUI view.

## How to run

Find the place where your jar file is stored.
1. For GUI
Open the CMD and input java -jar project5.jar
Please make sure jar file and images fold are in the same fold, otherwise the jar file can not load images properly.
2. For text-mode
Open the CMD and input java -jar project5.jar + row + column + interconnectivity + whether wrapping + what percent of caves you want to have treasure + monster number
For example :
jave -jar project5.jar 4 4 0 false 50 3


## How to use the program

1. You can set parameters in the parameter menu.
2. After you set parameter, you can click the begin in the game menu and if you want to exit the game, you can click the quit.
You can directly begin the game without setting parameter because I have set the default parameter.
3. You can move the player either by key arrows in the keyboard or key arrows in the screen.
4. You can pick up treasure and arrow by pressing 1, 2, 3 or 4 depending on what type of objects. You can see the objects and their corresponding number on the top right on the screen.
5. You can shoot by pressing S in the keyboard and follow the instructions to choose the direction and input the distance. If you shoot a monster, it will show you successfully hit a monster, otherwise it will show you don't hit a monster. Since you need to shoot a monster twice to kill it, the first and second time you hit it, it will show you successfully hit a monster but if you hit it the third time, it will show you don't hit a monster because it is dead.
6. You can get the information about monsters nearby in the red box on the screen.
7. You can reset parameter and restart the game at any time you want. If you reach the end or were eaten by a monster, you will get corresponding information.


## Description of examples

<u>ExampleRun Image</u>

1. You can see I have collected 7 arrows, 2 diamonds and 1 red diamond. 
2. There is 1 arrow in the current room.
3. There is a monster one distance from the current room due to the message in the red box.
4. You can see the current location by directly seeing the dungeon map. There is a person shown blue there.

## Design/Model Changes

I added a manager class to coordinate the player and dungeon and I added interface for controller and manager class. 
## Assumptions

1. Each cave can have at most 3 treasure which are diamond, rubies and sapphire and each type of treasure is not repeatable.
2. If the player gets the end point, the game will be over.
3. If the interconnectivity is larger than it could be, the map will be fully connected.
4. Each room can only have one arrow at most.

## Limitations

You can only get the information of monsters by the message shown in the red box, there is no visualization of the monster.

## Citations

I completely did the project by myself and didn't cite any resources.


