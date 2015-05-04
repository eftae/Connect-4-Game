/**
 * Main game engine.
 * v0.1 basic game with command line in/out.
 * 
 * @version v0.1
 */

public class Game {
	GameState currState;
	Player player1;
	Player player2;
	Player currPlayer;

	public Game(Player player1, Player player2) {
		currState = new GameState();
		this.player1 = player1;
		this.player2 = player2;
		currPlayer = player1;
	}

	/**
	 * Run a game. Should be run once only for one instance.
	 */
	public void runGame() {
		// run game if winner if not defined
		while (currState.getWinner() == null) {
			// get player next move
			int nextMove = currPlayer.decideMove(currState);

			// check nextMove valid, otherwise ask the move again
			while (currState.isColFull(nextMove)) {
				System.out.println("Invalid move. Try again.");
				nextMove = currPlayer.decideMove(currState);
			}

			// execute the move
			currState.runNextMove(currPlayer, nextMove);

			// if no more empty slot, end game
			if (currState.getTurn() == 41) {
				System.out.println("Withdrawed");
				return;
			}

			// swap player turn
			if (currPlayer == player1) {
				currPlayer = player2;
			} else {
				currPlayer = player1;
			}
		}

		// display winner
		if (currState.getWinner() == player1) {
			System.out.println("Player1 won.");
		} else {
			System.out.println("Player2 won.");
		}
	}
}
