/**
 * Main game engine. v0.1 basic game with command line in/out. v0.11 added
 * displayBoard(), bugs fixed
 * 
 * @version v0.11
 */

public class Game {
	GameState currState;
	Player player1;
	Player player2;
	Player currPlayer;
	Disc disc1;
	Disc disc2;
	Disc currDisc;

	public Game(Player player1, Player player2) {
		currState = new GameState();
		this.player1 = player1;
		this.player2 = player2;
		currPlayer = player1;
		disc1 = new Disc(player1);
		disc2 = new Disc(player2);
		currDisc = disc1;
	}

	/**
	 * Run a game. Should be run once only for one instance.
	 */
	public void runGame() {
		displayBoard();
		// run game if winner if not defined
		while (currState.getTurn() < 42) {
			// get player next move
			int nextMove = currPlayer.decideMove(currState);

			// check nextMove valid, otherwise ask the move again
			while (!currState.runNextMove(currDisc, nextMove)) {
				nextMove = currPlayer.decideMove(currState);
			}

			// Display board
			displayBoard();

			// swap player turn
			if (currPlayer == player1) {
				currPlayer = player2;
				currDisc = disc2;
			} else {
				currPlayer = player1;
				currDisc = disc1;
			}

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
		System.out.println("Board Full.");
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
				Player p = currState.checkBoard(c, r);
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
