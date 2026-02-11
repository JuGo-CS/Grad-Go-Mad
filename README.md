# 🎓 Grad Go Mad! - A Chaotic College Simulator

A fun and engaging Java-based desktop game that simulates the wild journey of being a graduate student. Manage your sleep, food, academic goals, and coins while surviving exams, brain rot moments, and other hilarious challenges!

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Game Mechanics](#game-mechanics)
- [Technologies Used](#technologies-used)
- [How to Play](#how-to-play)
- [Project Structure](#project-structure)
- [Credits](#credits)

---

## 🎮 Overview

**Grad Go Mad!** is a university project created as a final project that brings humor and relatability to the graduate student experience. The game challenges players to maintain their well-being across multiple dimensions while keeping up with academic responsibilities through an engaging typing game mechanic and event-based gameplay.

The game progresses through different academic years, each with increasing difficulty and challenges. Will you survive the journey to graduation, or will you go mad along the way?

---

## ✨ Features

- **Dynamic Player Stats System**: Monitor and manage your health in multiple dimensions:
  - 😴 **Sleep Bar**: Stay rested or face the consequences
  - 🍔 **Food Bar**: Feed yourself to survive
  - 📚 **Academic Points**: Accumulate knowledge through typing
  - 💰 **Coins**: Earn currency through successful typing challenges

- **Interactive Typing Game**: Test your typing speed and accuracy to earn coins and academic points

- **Event System**: Random events that occur throughout the game:
  - 📝 **Exams**: Test your academic preparedness
  - 🧠 **Brain Rot**: Social media has its toll
  - 🤒 **Sickness**: Get sick and lose stats
  - And more!

- **Progressive Difficulty**: Game difficulty increases as you progress through academic years

- **Visual GUI**: User-friendly graphical interface with animated elements and real-time stat tracking

- **Audio**: Immersive 8-bit sound effects and background music to enhance gameplay

---

## 🎯 Game Mechanics

### Player Statistics
- Each player statistic ranges from 0 to 100
- **Game Over Condition**: If sleep or food drops to 0 or below, the game ends
- **Win Condition**: Survive 360 seconds (6 minutes) with an Academic Bar ≥ 500 points

### Typing Game
- Players are presented with random words to type
- Correct answers award coins and academic points
- Words vary in difficulty based on the current academic year
- Press **Enter** to submit your answer

### Events
Each event type has different impacts on player stats:
- **Exam Events**: Reduce sleep more, slight food reduction
- **Brain Rot Events**: Moderate sleep and food reduction
- **Sickness Events**: Significant stat reduction
- Effects scale with the player's current academic year

### Time & Progression
- The game runs on a real-time timer (6-minute duration)
- Stats gradually decrease over time
- Players must balance maintaining basic stats while earning academic points

---

## 💻 Technologies Used

- **Language**: Java (JDK)
- **GUI Framework**: Java Swing (JFrame, JPanel, JButton, JLabel, etc.)
- **Audio**: Java Sound API for music and sound effects
- **Graphics**: Animated GIFs and image assets for visual enhancement
- **Build System**: Standard Java compilation

### Key Libraries:
- `javax.swing.*` - GUI components
- `java.awt.*` - Graphics and layout management
- `java.util.Timer` - Game timing and event scheduling

---

## 🎮 How to Play

### Prerequisites
- Java Development Kit (JDK) installed on your system
- Navigate to the project directory

### Running the Game

1. **Compile the project** (if not already compiled):
   ```bash
   javac -d . codefiles/*.java
   ```

2. **Run the game**:
   ```bash
   java codefiles.GradGoMad
   ```

3. **Start Screen**: Click "START GAME" to begin your graduate journey

4. **Main Game**:
   - Look at the word displayed in the typing area
   - Type the word correctly into the text field
   - Press **Enter** to submit
   - Monitor your stats and respond to events
   - Survive for 6 minutes with academic points ≥ 500 to win!

5. **End Game**:
   - Game ends when your sleep or food reaches 0 (Loss)
   - Game ends after 6 minutes with enough academic points (Win)
   - See your final results and statistics

---

## 📁 Project Structure

```
Grad-Go-Mad/
├── codefiles/                    # Main Java source code
│   ├── GradGoMad.java           # Main game controller
│   ├── GUI.java                 # Game interface and display
│   ├── Player.java              # Player stats and logic
│   ├── TypingGame.java          # Typing game mechanics
│   ├── ActionClasses.java       # Event action handlers
│   ├── ButtonOne.java           # Button action handlers
│   ├── ButtonTwo.java           # Button action handlers
│   ├── ButtonThree.java         # Button action handlers
│   ├── RandWords.java           # Random word generator interface
│   ├── WordGenerator.java       # Word generation base class
│   ├── StartGui.java            # Start screen and music player
│   ├── Miscellaneous.java       # Utility functions
│   └── [Other supporting classes]
│
├── assets/                       # Game assets
│   ├── soundeffects/            # Background music and sound effects
│   │   └── upnamingmahal8bit.wav # Main background music
│   ├── needs/                   # Animated GIFs and visual assets
│   ├── acadphotos/              # Academic-related images
│   ├── foodsphotos/             # Food-related images
│   ├── miscphotos/              # Miscellaneous images
│   ├── statusBars/              # Status bar visuals
│   └── bin/                     # Compiled class files
│
└── README.md                     # This file
```

---

## 🏆 Credits

This project was created as a collaborative effort during a Computer Science university course. Special thanks to the following people who worked together to bring this chaotic graduate simulator to life:

### Team Members
- **Kenneth Mondejar** - Project Leader & Coordinator
- **Angel May Janiola** - Game design and mechanics
- **Meshach Borla** - GUI development and visual assets
- **Mark Leonel Misola** - Core systems and gameplay logic

### Special Mentions
This project captures the humorous (and sometimes very real) struggles of being a graduate student. It's a tribute to late nights, energy drinks, and the eternal question: "Why did I choose this path?" 😄

---

## 📝 Notes

- The game features 8-bit style music and sound effects for that retro college gaming vibe
- Different word pools exist for different academic years, increasing difficulty as you progress
- The game is designed for a quick play session (6 minutes total)
- All assets are included in the `assets/` folder

## 🤝 Contributing

This is a completed university project. However, if you'd like to extend it or fix bugs, feel free to fork and create your own version!

---

**Grad Go Mad! - Because graduation is an adventure!** 🎓✨