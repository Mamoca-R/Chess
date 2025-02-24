# Chess Engine (In-Progress)

A fully functional chess engine built from scratch, designed to be integrated into a personal website. This project is focused on providing an engaging, interactive chess-playing experience while exploring core software engineering principles, game logic, and AI development.

# Features

Full chess rules implementation (legal moves, check, checkmate, stalemate)

Interactive UI for web play

Move validation and game state management

Basic AI opponent with adjustable difficulty (planned)

Support for two-player mode (local play)

Game history tracking and undo functionality

# Installation

Prerequisites

Node.js (for running the development server)

Git (to clone the repository)

Steps

Clone the repository:

git clone https://github.com/Mamoca-R/chess.git
cd chess-engine

Install dependencies:

npm install

Run the development server:

npm start

Open your browser and go to http://localhost:3000 to play.

# Usage

Once the server is running:

Click "New Game" to start a fresh match.

Play against a friend locally or challenge the AI (when available).

Use the undo button to revert the last move.

# Project Structure

chess-engine/
│
├── public/          # Static files
├── src/
│   ├── components/  # React components (Board, Piece, UI elements)
│   ├── engine/      # Core chess logic and AI
│   ├── styles/      # CSS and styling files
│   ├── App.js       # Main application entry
│   └── index.js     # Entry point for React
├── package.json     # Project configuration
└── README.md        # This file

# Development Roadmap



# Contributing

Contributions are welcome! Here's how you can help:

Fork the repo and create your branch (git checkout -b feature/your-feature)

Commit your changes (git commit -am 'Add some feature')

Push to the branch (git push origin feature/your-feature)

Open a pull request

# License

This project is licensed under the MIT License. See the LICENSE file for details.

# Acknowledgments

Chess Programming Wiki for algorithm references

react-chessboard for inspiration

Open-source community for tools and resources

Happy playing and coding!
