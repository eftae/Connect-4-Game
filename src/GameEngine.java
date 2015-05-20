/**
 * Game engine.
 * 
 */

public class GameEngine implements Runnable {

	private GameState currState;
	private GameBoardPanel gameBoardPanel;
	private GameStaticsPanel gameStaticsPanel;
	private boolean isInGame; // game is in run
	private int totalGame; // number of game started

	public void startNewGame(Player player1, Player player2,
			GameBoardPanel gameBoardPanel) {
		currState = new GameState(player1, player2);
		this.gameBoardPanel = gameBoardPanel;
		gameStaticsPanel = null;
		totalGame++;
		isInGame = true;
	}

	// public void startNewGame(Player player1, Player player2,
	// GameBoardPanel gameBoardPanel, GameStaticsPanel gameStaticsPanel) {
	// currState = new GameState(player1, player2);
	// this.gameBoardPanel = gameBoardPanel;
	// this.gameStaticsPanel = gameStaticsPanel;
	// totalGame++;
	// isInGame = true;
	// }

	@Override
	public void run() {
		// delay thread to wait game started
		while (true) {
			sleep(100);

			// delay before game started
			if (isInGame && currState.getCurrPlayer() instanceof AI) {
				sleep(500);
			}

			// start game run
			int thisGame = totalGame;
			while (isInGame && thisGame == totalGame) {
				Player currPlayer = currState.getCurrPlayer();

				// get next move of current player
				int nextMove = currPlayer.decideMove(currState.clone());

				// check isInGame to avoid AI delay while screen jumping
				if (isInGame && thisGame == totalGame
						&& currState.isValidMove(nextMove)) {

					int row = currState.runNextMove(nextMove);

					// display disc dropped
					gameBoardPanel.displayDisc(nextMove, row,
							getCurrPlayerIndex());

					// increment turn
					currState.incTurn();
					currState.setCurrPlayer(currState.getOtherPlayer());

					gameBoardPanel.updateStaticsPanel();

					// check game end and winner
					if (currState.checkGameEnd()) {
						isInGame = false;
						gameBoardPanel.displayEndGame(currState.getWinner());
					}
				}
			}
		}
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public GameState getCurrState() {
		return currState.clone();
	}

	public Player getCurrPlayer() {
		return currState.getCurrPlayer();
	}

	public Player getOtherPlayer() {
		return currState.getOtherPlayer();
	}

	public boolean isValidMove(int move) {
		return currState.isValidMove(move);
	}

	public boolean isGameOn() {
		return isInGame;
	}

	public void suspendGame() {
		isInGame = false;
	}

	public int getCurrPlayerIndex() {
		return currState.getTurn() % 2;
	}

}
