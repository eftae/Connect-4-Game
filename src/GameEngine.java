/**
 * Main game engine. v0.1 basic game with command line in/out. v0.11 added
 * displayBoard(), bugs fixed
 * 
 * @version v0.11
 */

public class GameEngine implements Runnable {
	private final int MAX_TURN = 42;

	private GameState currState;
	private Player player1;
	private Player player2;
	private boolean isInGame;
	private GameBoardPanel gameBoardPanel;

	public GameEngine() {
		isInGame = false;
	}

	public void startNewGame(Player player1, Player player2,
			GameBoardPanel gameBoardPanel) {
		currState = new GameState(player1, player2);
		this.player1 = player1;
		this.player2 = player2;
		this.gameBoardPanel = gameBoardPanel;
		isInGame = true;
	}

	/**
	 * Run a game. Should be run once only for one instance.
	 */
	@Override
	public void run() {
		Player currPlayer;
		int nextMove;

		// delay thread to wait
		while (!isInGame) {
			System.out.println(isInGame);
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}

		// start game run
		while (isInGame) {
			displayBoard();

			currPlayer = currState.getCurrPlayer();

			// get next move of current player
			nextMove = currPlayer.decideMove(currState.clone());

			if (currState.isValidMove(nextMove)) {
				int row = currState.runNextMove(nextMove);
				gameBoardPanel
						.setButton(nextMove, row, currState.getTurn() % 2);
			}

			// increment turn
			currState.nTurnPlusPlus();
			if (currPlayer == player1)
				currState.setCurrPlayer(player2);
			else
				currState.setCurrPlayer(player1);

			// check game end and winner
			currState.checkGameEnd();
			Player winner = currState.getWinner();
			if (winner != null || currState.getTurn() >= MAX_TURN) {
				displayBoard();
				isInGame = false;
			}
		}
	}

	public GameState getCurrState() {
		return currState.clone();
	}

	public Player getCurrPlayer() {
		return currState.getCurrPlayer();
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
				Player p = currState.getLocation(c, r);
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
