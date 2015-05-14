/**
 * Main game engine. v0.1 basic game with command line in/out. v0.11 added
 * displayBoard(), bugs fixed. v0.2 multithreads.
 * 
 * @version v0.2
 */

public class GameEngine implements Runnable {

	private GameState currState;
	private Player player1;
	private Player player2;
	private boolean isInGame; // game is in run
	private GameBoardPanel gameBoardPanel;

	public void startNewGame(Player player1, Player player2,
			GameBoardPanel gameBoardPanel) {
		currState = new GameState(player1, player2);
		this.player1 = player1;
		this.player2 = player2;
		this.gameBoardPanel = gameBoardPanel;
		isInGame = true;
	}

	@Override
	public void run() {
		// delay thread to wait game started
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			// start game run
			while (isInGame) {
				Player currPlayer = currState.getCurrPlayer();

				// get next move of current player
				int nextMove = currPlayer.decideMove(currState.clone());

				//check isInGame to avoid AI delay while screen jumping
				if (isInGame && currState.isValidMove(nextMove)) {
					int row = currState.runNextMove(nextMove);
					// increment turn
					currState.incTurn();
					if (currPlayer == player1) {
						currState.setCurrPlayer(player2);
						// display disc dropped
						gameBoardPanel.displayDisc(nextMove, row, 0);
					} else {
						currState.setCurrPlayer(player1);
						gameBoardPanel.displayDisc(nextMove, row, 1);
					}

					// check game end and winner
					if (currState.checkGameEnd()) {
						isInGame = false;
						gameBoardPanel.displayEndGame(currState.getWinner());
					}
				}
			}
		}
	}

	public void suspendGame() {
		isInGame = false;
	}

	public GameState getCurrState() {
		return currState.clone();
	}

	public Player getCurrPlayer() {
		return currState.getCurrPlayer();
	}

	public boolean isValidMove(int move) {
		return currState.isValidMove(move);
	}
}
