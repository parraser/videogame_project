project-team-subpar Product Backlog
====================================

  * This project will utilize the Fibonacci sequence to eliminate user stories.
  
---------------------------------------------

#### User Story 0: Learn Java Graphics API

**CHORE**

As a developer, I want to read up on the Java Graphic API so that I know how to build the game.

Priority: 1

Points: N/A

Dependencies: N/A

Tasks: Read and understand Game example

----------------------------------------------------

#### User Story 1: Create player model

As a casual/competitive player, I want to see my character so that I can know where he is and know who to shoot.

Criteria of Satisfaction:
  * There must be some variety between different user's player avatar per match.

Priority: 1

Points: 5

Dependencies: N/A

Tasks: Implement Player class, Implement Graphics2D and have player drawn on window

-------------------

#### User Story 2: Player movement

As a casual/competitive player, I want to move my character so that I can dodge incoming projectiles.

Criteria of Satisfaction: 
  * A user must be able to at minimum, move 8 different directions.
  * A user must be able to move their player avatar smoothly with no input lag or choppiness.

Priority: 1

Points: 3

Dependencies: 1

Tasks: Implement Keyboard listener, Implement new player move methods

------------------------

#### User Story 3: Mex Wall

As a casual/competitive player, I want walls so that I have a more varied experience so that bullets can bounce off.

Criteria of Satisfaction: 
  * The walls must be solid objects so that the player's shots do not phase through the wall.

Priority: 3

Points: 3

Dependencies: 2

-----------------------

#### User Story 4: Object Physics

As a competitive player, I want to be able to collide with obstacles so that I can hide from projectiles.

Criteria of Satisfaction: 
  * The obstacles must be solid objects, so that the player avatar does not phase through the obstacle.


Priority: 3

Points: 8

Dependencies: 3

----------------------

#### User Story 5: PVP and scoring

As a casual/competitive player, I want to be able to shoot, so that I can kill my enemy and score points.

Criteria of Satisfaction: 
  * A user must be able to at minimum, shoot 8 different directions.
  * A user must have their bullets move smoothly with no input lag or choppiness.

Priority: 1

Points: 5

Dependencies: 1

------------------------

#### User Story 6: Bullet types

As a competitive player, I want bullets to bounce off walls realistically, so that I can have interesting varied gameplay allowing room for epic strategies.

Criteria of Satisfaction:
  * The bullets must follow some form of physics so that the bouncing shots are more realistic.

Priority: 4

Points: 8

Dependencies: 5

-------------------------

#### User Story 7: Menu Options

As a casual player, I want to be able to choose between different game modes, so that I can have infinite bullet hell or a more passive open-field.

Criteria of Satisfaction:
  * There could be more game modes, but the two game modes above must work with no bugs or issues.

Priority: 5

Points: 5

Dependencies: 12

---------------------------

#### User Story 8: Create and spawn PowerUps

As a casual player, I want random collectible power-ups so that I can have a different power to make it easier to kill my enemy.

Criteria of Satisfaction:
  * The collectible power-ups must work properly for each power introduced.
  * (Optional) The collectable power-up is different each stage.

Priority: 3

Points: 8

Dependencies: 5

-------------------------

#### User Story 9: Create traps

As a casual player, I want miscellaneous hazards so that I can have more varied gameplay giving the players more options to win.

Criteria of Satisfaction:
  * If the hazard is triggered, the result must show both to the player's avatar and on the player's score.
  
Priority: 3

Points: 8

Dependencies: 5

--------------------------

#### User Story 10: Global Ranking

As a competitive player, I want a ranking system, so that I can assert my dominance over the rest of the community

Criteria of Satisfaction:
  * The ranking system must properly follow how many points each individual player has scored.
  * The ranking system could show up after each round and/or after each match. 
  
Priority: 5

Points: 8

Dependencies: 17

-------------------------

#### User Story 11: Select Maps

As a casual player, I want multiple maps, so that I can have a variety in multiple battlefields

Criteria of Satisfaction:
  * Each map must be different and should introduce something new that the other maps do not have.

Priority: 4

Points: 8

Dependencies: 2

------------------------

#### User Story 12: Lobby Browser

As a casual/competitive player,	I want it so that when I start up the game I want to be able to browse the game's simple main menu, with basic options.

Criteria of Satisfaction:
  * Each customization must properly affect either the players or that of the map.
  * The set up on the main menu must properly work.
  * The default settings given must not be necessarily difficult or too simple.

Priority: 2

Points: 5

Dependencies: 17

----------------------------

#### User Story 13: Sudden death

As a	casual player, I want it so that after time limit is up the stage transforms into Sudden Death Mode where new randomly chosen map hazards are introduced, so that there are no ties and the round ends with someone getting a point.

Criteria of Satisfaction:
  * The time limit must be properly set up in all modes and stages.
  * The sudden death mode must be properly set up in all modes and stages.
  * The hazard introduced must be properly randomized.

Priority: 4

Points: 8

Dependencies: 11

-----------------------------

#### User Story 14: Random Sudden Death

As a casual player,	I want a variety of different Sudden Death hazards based on chosen map so that each map is interesting and provides a challenge should the time limit ends up finishing.

Criteria of Satisfaction:
  * The time limit must be properly set up in all modes and stages.
  * The sudden death mode must be properly set up in all modes and stages.
  * The hazards must be properly working for each stage.

Priority: 4

Points: 8

Dependencies: 13

-----------------------------

#### User Story 15: Create Quick Match Option

As a more casual player, I want a quick start up to a match with default settings so that I can easily pick up the game or drop it when I am taking a break from my work.

Criteria of Satisfaction:
  * The default setting in this quick set up must not be too difficult or too easy.
  * The player must be able to have a quick drop out option, without interrupting the other possible player or points.
  
Priority: 2

Points: 3

Dependencies: 12

---------------------------------
#### User Story 16: Custom Game

As a casual/competitive player,	I want the main menu and settings to be more elaborate so that I can have custom gameplay.

Criteria of Satisfaction:
  * Each customization must properly affect either the players or that of the map.
  * The set up on the main menu must properly work.
  * The default settings given must not be necessarily difficult or too simple.

Priority: 5

Points: 5

Dependencies: 12

----------------------------

#### User Story 17: Match Complete Alert

As a casual/competitive player, I want to see a win screen when I win so that I can see that I won.

Criteria of Satisfactions:
  * The win screen must appear depending on the win conditions.
  
Priority: 3

Points: 1

Dependencies: 5

-------------------------------

#### User Story 18: Game Foundation

As a casual/competitive player, I want to have a foundation for the game so the game window can draw game objects after a set period of time.

Criteria of Satisfaction: Fully implements a timer that updates the game after every game tick.

Priority: 1

Points: 8

Dependencies: N/A

Tasks: GameObj interface, Observer model for main Game class for each game tick, Main Game Class

-------------------------------

#### User Story 19: GUI

As a casual/competitive player, I want to see the screen so that I can know what is happening.

Criteria of Satisfactions:
The GUI must display the character model and project the players movements.

Priority: 2

Points: 5

Dependencies: User Story 18

-----------------------------------

#### User Story 20: release 1

**RELEASE 1**

As a casual/competitive player, I want to see a working iteration of the game for testing release.

Criteria of Satisfactions:
There must be some working features for release

Priority: 3

Points: N/A

Dependencies: N/A/ User Stories 0,1,2,18

-------------------------------------
