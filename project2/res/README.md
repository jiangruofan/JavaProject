# **Battle**
Jumptastic Games has contracted me to _design and implement a model_ for their new [role-playing game (Links to an external site.)](https://en.wikipedia.org/wiki/Role-playing_game) that will allow gamers to build players that can then be entered into an arena for one-on-one combat following this turn-based battle strategy.
# **Overview**


Players have four different abilities:

-   **Strength**  affects how effective the player is at striking their opponent.
    
-   **Constitution**  affects how much damage a player can take when they are hit in battle.
    
-   **Dexterity**  affects how effective the player is at avoiding a strike from their opponent.
    
-   **Charisma**  affects how their opponent views them.
    

Each player ability is determined randomly by rolling four 6-sided dice, re-rolling any 1s, and then adding together the highest 3 values resulting in a value between 6 and 18. Therefore each ability will have a different value based on the result of running this process four separate times, once for each of the four abilities.

Furthermore, players' abilities can be temporarily affected, either positively or negatively, by the gear that they use (details of how is left as a design decision for the student):

-   **Headgear**  is worn on the player's head and affects the player's constitution. Since a player has one head, they can only wear one piece of headgear.
-   **Potions**  are consumed by the player before entering the field of battle. They can temporarily affect any of the player's abilities. There is no limit to the number of these that the player can drink.
    
-   **Belts**  come in three sizes -- small, medium, and large -- and are worn around the player's torso affecting up to two of the player's abilities. Players have the ability to wear 10 "units" of belts where small belts count as 1 unit, medium as 2 units, and large as 4 units.
    
-   **Footwear**  is worn on the player's feet and affects the player's dexterity. Footwear always comes in pairs and a player can only wear one piece of footwear at a time.
    

Finally, the player can wield a sword, an axe, or a flail as their weapon of choice:

-   Swords come in three varieties:
    -   **Katanas**  are lightweight curved swords that come in pairs. They can do a base of 4-6 points of damage when they hit. They are so light that a player can carry two of them (which attack separately).
    -   **Broadswords**  are a good medium weapon that can do 6-10 points of damage when they hit.
    -   **Two-handed swords**  are a heavy sword that can only be effectively wielded by players with strength greater than 14, but they can do 8-12 points of damage when they hit. If the player does not have the strength to wield a two-handed sword, the sword only does half damage.
-   **Axes**  are great general weapons doing 6-10 points of damage when they hit.
-   **Flails**  are also great general weapons but they can only be effectively wielded by players with a dexterity greater than 14. They do 8-12 points of damage when they hit. If the player does not have the dexterity to wield a flail, the flail only does half damage.
 
The model should use the player information to create a battle between two players who enter the arena. To do this the model should support operations that allow

-   players to enter the arena with only their basic abilities and their bare hands.
-   players to equip themselves from a bag of equipment that contains a minimum of 5 items of headgear, 5 items of footwear, 15 belts, and 15 potions. When players equip themselves from the bag, they are randomly assigned 20 items from the bag. Any item that is randomly assigned must be used unless it cannot be combined with what the player is already using. 25% of the items that are in the bag will diminish the player's ability rather than enhance it.
-   players to request a weapon from the armory. Requests for a weapon are satisfied by randomly selecting one of the many weapons that are available (at least 1 of each type of weapon).
-   provide a complete description of players that will enter the arena including the player's temporary ability values (based on the affects of the potions that they may have consumed) along with any and all the gear they are wearing, and what weapon they are using. Gear should be printed in order of top to bottom, then alphabetically: thus any headgear should come before potions which come before any belts which should come before any footwear.
-   two players to battle in the arena. As players enter the arena, the player with the higher charisma dazzles their opponent and gets in the first strike.

A player's  **health**  is calculated to be the sum of their 4 abilities. Players start a battle with full health.
-   **Striking power**  is the sum of the strength of the player, any of the gear that adds (or substracts) from strength, and a random number between 1 and 10 (inclusive).
-   **Avoidance ability**  is the sum of the dexterity of the player, any of the gear that adds (or subtracts) from dexterity, and a random number between 1 and 6 (inclusive).
-   If the striking power of the attacking player is greater than the avoidance ability of their opponent, the attacking player successfully strikes their opponent and the damage must be calculated.
-   The  **potential striking damage**  is calculated by adding the strength of the attacking player to a random value in the range of the damage that their weapon can inflict (if they have a weapon).
-   The  **actual damage**  is the potential striking damage minus the constitution of their opponent.
-   If the actual damage is greater than 0, it is subtracted from the player's health.
## **List of features**

I created an interface and one gear class for all 4 different gear to inherit and I set the weapon as enumeration. I also created a player class to represent a player and a battle class which is used as a central manager to equip the player and make them fight. There is also a random interface which has two implementation representing true and false randomness.

## **How to run**
1. Find the place where your jar file is stored.
Open the cmd and input java -jar (name of jar file).jar
2. You could input yes or no to decide whether to rematch two players and make them fight again.

## **How to use the program**

First you need to create a random object which is used to set random values for player and battle class. Then you should create two players object and one battle object. You can give players equipment or weapon at your will. After those, we successfully set the condition and then we need to judge whether a hit can actually occur and who should attack first. If a hit never occurs, it will throw an exception and you should rerun the program. Finally, you could just let them fight turn by turn and there will be one winner in the end. In addition, you could make them rematch and start another fight by inputting yes or no after seeing the hint shown in the screen.
## **Description of Examples**

<u>Run -- ExampleRun.txt </u>
1. Print an introduction message.
2. Create a random object, two player object and a battle object.
3. Print two players' basic ability information.
4. Equip both players with gear and weapon.
5. Print a description of two players.
6. Judge whether a hit can occur. If not, please rerun the program.
7. Judge who should first attack and print this information.
8. Make players fight turn by turn and print out information for each round of the battle including who attacks, whether they successfully hit their opponent, and how much damage their opponent took.
9. Print out the result of the battle.
10. Provide the option for the characters to play a rematch, if you want a rematch, input yes otherwise no.


## **Design/Model Changes**

At first, I only create one random class to get both the real and false random number. Later, I created an interface and make two class to implement it so that producing true and false random class is done in two different class. This can make the function of my model more clear and readable.


## **Assumptions**

1. Belts needs to affect two ability and potions need to affect all 4 ability.
2. Two players are equipped from independent armory.
3. When equipping the player with weapon, if the player get the katanas, then it has a chance to get another katanas. If the player gets a second katanas, then he will have a pair of katanas otherwise it can only have one katanas.
4. If the charisma of two players is same, who should first attacks is decided by randomness.

## **Limitations**
There is a small probability that a hit can never occur. In this case, you need to run the program again.

## **Citations**
I finished the project completely on my own and didn't cite any resources.
