# MineSweeper-Game

<h3>TechStack: Java • Swing GUI • File Handling • Depth-First-Search Algorithm</h3>

<h1>Overview</h1>
• A fun to play logic based puzzle game.<br>
• Depth-first search algorithm is used to explore new blocks.<br>
• High Scores of players are maintained using the File Handling System of Java.<br>

<h1>Description</h1>
  The game has three different modes of difficulty based on the grid size and number of mines present in that grid. Mines are generated only after the first move of the player to ensure that no player encounters the mine at the very first move. The generation of mines is completely random for each new game.<br><br>
  The exploration of grid area uses a recursive algorithm which further explores the adjacent areas when no mines are present next to that area. Each block displays the number of mines adjacent to that block.<br><br>
  The game also provides a leaderboard which displays the names of 10 fastest winners for each difficulty. This has been achieved with the help of file handling and sorting technique which sorts the data according to completion time each time a new victory is recorded.<br><br>
  As the new records get listed in the leaderboard, old data is deleted to free up the space.
