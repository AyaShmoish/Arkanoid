# Arkanoid Game  

## Overview  
This project is a classic **Arkanoid Game** implemented in **Java**.  
The player controls a paddle to keep the ball in play and break all the blocks on the screen.  

## Features  
- **Paddle Control**: Move the paddle horizontally using the keyboard.  
- **Blocks**: Rows of breakable blocks that must be destroyed.  
- **Ball Physics**: Realistic bouncing of the ball against walls, paddle, and blocks.  
- **Collision Detection**: Accurate detection between the ball, paddle, and blocks.  
- **Game Over Conditions**:  
  - Win: Destroy all the blocks.  
  - Lose: Miss the ball and let it fall below the paddle.  
- **Score Tracking**: Points are awarded for each block destroyed.  
- **Dynamic Ball Color**: The ball changes color to match the last block you hit. You can only break blocks that have a different color than the ball.  

## How to Play  
1. Run the program.  
2. Use the **Left (←)** and **Right (→)** arrow keys to move the paddle.  
3. Keep the ball in play and try to destroy all the blocks.  
4. The ball can only break blocks if their color is **different** from the ball’s current color.  
5. The game ends when all blocks are destroyed (win) or when the ball falls below the paddle (lose).  

## Technologies Used  
- **Language**: Java  
- **Concepts**:  
  - Object-Oriented Programming (Classes, Inheritance, Polymorphism)  
  - Collision Detection  
  - Event & Input Handling  
  - Sprite Management  
  - Observer Pattern  
  - Bouncing Mechanics  

## Setup and Usage  
Clone the repository:  
```bash
git clone https://github.com/AyaShmoish/Arkanoid.git

```
Navigate to the project directory:
```bash
cd Arkanoid
```

🚀 How to Compile and Run (Windows PowerShell)
```bash
mkdir build -Force
```
# Compile
```bash
javac -cp "biuoop-1.4.jar" -d build `
  src\collections\*.java `
  src\collision\*.java `
  src\counter\*.java `
  src\game\*.java `
  src\geometry\*.java `
  src\listeners\*.java `
  src\sprites\*.java `
  src\Ass5Game.java
```

 Run
```bash
java -cp "build;biuoop-1.4.jar" Ass5Game
```
## Project Structure
- `src/`: Contains all the Java source code of the game. Inside it you will find the main packages and classes.  
- `collections/`: Data structures and utilities used by the game.  
- `collision/`: Classes that handle collision detection between the ball, paddle, and blocks.  
- `counter/`: Classes that manage score, lives, and other counters.  
- `game/`: Main game logic and flow (initialization, levels, game loop).  
- `geometry/`: Geometric primitives such as points, lines, and rectangles used in the game engine.  
- `listeners/`: Event listeners for score tracking, block removal, and game events.  
- `sprites/`: Visual and interactive game objects such as the paddle, ball, and blocks.  
- `Ass5Game.java`: The entry point of the project — contains the `main` method to run the Arkanoid game.  
- `biuoop-1.4.jar`: External library required for graphics, keyboard input, and animation (contains classes like `GUI`, `DrawSurface`, and `KeyboardSensor`). 
