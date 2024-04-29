project4-caravan: "ASCII-RAVAN"
---
Final Project for CS121, Java and OOP Principles. 


![image](https://github.com/alecthedev/project4-caravan/assets/168141387/4e6b014c-22ed-4f8d-875b-651694a15d24)



This is one of the most ambitious projects I have ever done so far, and spent just over a week on it. Overall, I am pleased with it. 
I believe the I underestimated the scope of recreating Caravan, but I regret nothing and had a blast solving all the challenges.

I was able to replicate most of the rules and squash as many bugs as possible in the time I had. I learned a lot and would approach this project differently if I were to do it again.

_Note: A few rules differ from the [official Caravan rules.](https://fallout.fandom.com/wiki/How_to_play_Caravan) See ["Note on rules/Known bugs"](https://github.com/alecthedev/project4-caravan#note-on-rulesknown-bugs) section below._


How to run
---
1. Open project with your IDE, run main method in Main class
2. Run in the console. If you don't know how, check out [this freecodecamp.org article](https://www.freecodecamp.org/news/how-to-execute-and-run-java-code/).

_Note: You need to have the [JRE](https://www.java.com/en/download/help/whatis_java.html) installed for these methods._

Gameplay
---
This game was designed with _two players_ in mind. 

Once the game has begun, players will find themselves looking at this table:

![image](https://github.com/alecthedev/project4-caravan/assets/168141387/4f966975-e129-4861-942b-0a7e445bde04)

Each player is competing to win at least 2 of the 3 caravan "routes" (A, B, or C).

Entering "O" on this screen shows us these options:

![image](https://github.com/alecthedev/project4-caravan/assets/168141387/497e8730-0f77-4fdb-8fbb-4fd102fdbbdf)

To begin, Player 1 must type "V" then choose a caravan route. This will bring up Player 1's "caravan" for that route. 

![image](https://github.com/alecthedev/project4-caravan/assets/168141387/ebbce313-ba47-4214-b29d-ad3ad7aba742)

All six caravans (two per route, i.e. one for each player) must be initialized with a number card in the first round.

![image](https://github.com/alecthedev/project4-caravan/assets/168141387/7d263337-5525-4d86-90fb-94828f33b16f)

Upon playing our first card, as Player 1, we can see that the card's value has been added to the table view. It is now Player 2's turn.

![image](https://github.com/alecthedev/project4-caravan/assets/168141387/434f8480-147a-48b7-9c9f-417938041d0b)

Entering "O" while viewing a caravan shows the above available options. "S" allows the player to play a card on their opponents caravan.
This can only be done with face cards, and only after the first round is complete (all caravans have had a number card played).

![image](https://github.com/alecthedev/project4-caravan/assets/168141387/9a62c878-2ce3-4889-af2e-6d81a54b565f)

Not so fast Player 1...

![image](https://github.com/alecthedev/project4-caravan/assets/168141387/e8c78f84-f431-46d2-a4db-163c8c26b5c8)

Those are the basics of command entering and general gameplay.

Rules
---

The rules can be pulled up in-game by typing "R" whenever the screen displays "ENTER 'O' FOR OPTIONS OR TYPE COMMAND".

![image](https://github.com/alecthedev/project4-caravan/assets/168141387/3e66551f-4bf5-4a9e-8d38-5ef3a1e9f1a1)

Note on rules/Known bugs
---
As mentioned at the start of the README, there are a few things that differ from the official rules.
- Players start with a 52 card deck, therefore there are no jokers. There is no deck customization.
- There is no gambling. It isn't really necessary to play the game and would only add complexity.
- While multiple kings can be stacked, currently they do not continue to double the card they are attached to.

