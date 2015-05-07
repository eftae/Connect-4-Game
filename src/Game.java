/**
 * Main game engine. v0.1 basic game with command line in/out. v0.11 added
 * displayBoard(), bugs fixed
 * 
 * @version v0.11
 */

public class Game {
	private final int MAX_TURN = 42;
	
	GameState currState;
	Player player1;
	Player player2;

	public Game(Player player1, Player player2) {
		currState = new GameState(player1,player2);
		this.player1 = player1;
		this.player2 = player2;
	}

	/**
	 * Run a game. Should be run once only for one instance.
	 */
	public void runGame() {
		displayBoard();
		// run game if winner if not defined
		while (currState.getTurn() <= MAX_TURN) {
			// get player next move
			int nextMove = currState.getCurrPlayer().decideMove(currState);

            if(currState.isValidMove(nextMove)){
                currState.runNextMove(nextMove);
            }

			// check nextMove valid, otherwise ask the move again
            // while (!currState.runNextMove(currDisc, nextMove)) {
            //     nextMove = currPlayer.decideMove(currState);
            // }
            // ^ if the player enter an invalid move, we will just skip the turn

			// Display board
			displayBoard();

			// swap player turn
			if (currState.getCurrPlayer() == player1)
				currState.setCurrPlayer(player2);
			else
                currState.setCurrPlayer(player1);
            
            // update the game state
            currState.nTurnPlusPlus();
            currState.checkGameEnd();
			
			// check winner
			Player winner = currState.getWinner();
			if (winner == player1) {
				System.out.println("Player1 won.");
				return;
			} else if (winner == player2) {
				System.out.println("Player2 won.");
				return;
			}
		}
		// no winner
		System.out.println("Board Full, Game Over");
	}

	/**
	 * Display board in command line view.
	 */
	private void displayBoard() {
		System.out.println("    Turn " + currState.getTurn());
		System.out.println("-----------------");
		for (int r = 5; r >= 0; r--) {
			System.out.print("| ");
			for (int c = 0; c < 7; c++) {
				Player p = currState.getLocation(c,r);
				if (p == player1) {
					System.out.print("O ");
				} else if (p == player2) {
					System.out.print("X ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println("|");
		}
		System.out.println("-----------------");
		System.out.println();
	}
}
