# Arkanoid

This is an Arkanoid game project that was developed as part of an Object-Oriented Programming course. The game contains several levels with increasing difficulty and is implemented in a GUI-friendly platform.

## Design Patterns

### Builder Design Pattern
The Builder design pattern was used in this project to create various game objects in a step-by-step manner. This allows for greater flexibility and ease of use, as each builder class is responsible for constructing a specific object. Builders were used to create balls, blocks, limits, and paddles in a clear and organized manner. This approach allows for more readable and maintainable code and makes it easy to modify the properties of each object during the construction process.

### Strategy Design Pattern
The strategy design pattern was used in the project to allow different behaviors for block placement and coloring in the first level of the Arkanoid game. The implementation of these strategies allows for more flexibility in creating different levels with different characteristics without having to change the core game code.

### Decorator Design Pattern
The decorator design pattern was used to add new functionalities to existing Animation objects. The WaitingForKeyPressDecorator class is the decorator, while the PauseScreen and KeyPressStoppableAnimation classes are the concrete components that are decorated. The decorator pattern allows for dynamically adding new functionalities to existing objects without changing their structure, while keeping the code clean and maintainable.

### Observer Design Pattern
The Observer design pattern was used to handle events triggered by different game objects. The game objects are implemented as Observables, and the listener classes are implemented as Observers. The listener classes subscribe to events triggered by the game objects and act accordingly, such as removing blocks or balls from the game or updating the player's score. The use of the Observer pattern allows for a flexible and modular code structure, where new listeners can easily be added without modifying the game objects themselves.

## Instructions
Use the 'Left' and 'Right' arrows to control the paddle. Press 'p' to pause and 'Space' to re-enter the game. Pop all the blocks and progress through the game until victory or lose and try again :)

## Dependencies
- Windows / Linux / macOS
- Git
- Keyboard that contains: Spacebar (for stoppage), "P" button, and all arrows.
- Apache Ant

## Installation and Execution
If you wish to run the game, you can download the "oriArkanoid.jar" from the release section.
