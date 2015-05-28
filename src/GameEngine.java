
/**
 * 
 * The game engine will control the current game by 
 * communicating with the game state and the game board panel 
 * to load the current game status and interact with the user through 
 * the game board panel with the GUI.
 */
public class GameEngine implements Runnable {

	private GameState currState;  // the current game state
	private GameBoardPanel gameBoardPanel;  // the game board panel
	private int gameMode;     // 0 for simulation, 1 for single player, 2 for double player
	private boolean isInGame; // game is in run
	private int totalGame;    // number of game started
	
	public void startNewGame(int gameMode, Player player1, Player player2,
			GameBoardPanel gameBoardPanel) {
		this.gameMode = gameMode;
		this.gameBoardPanel = gameBoardPanel;

		// reset users
		if (player1 instanceof User) {
			((User) player1).resetPlayer();
		}
		if (player2 instanceof User) {
			((User) player2).resetPlayer();
		}

		currState = new GameState(player1, player2);
		totalGame++;
		isInGame = true;
	}

	@Override
	/**
	 * This method contains the management process of the game, it
	 * collect input from AI or human user, check the validity of the move, 
	 * and eventually make the move and update the statistics panel.
	 * <p>
	 * The run method is called when the thread is started by the main, 
	 * and will keep running until the program terminated.
	 */
	public void run() {
		// delay thread to wait game started
		while (true) {
			sleep(500);

			// start game run
			int thisGame = totalGame;
			while (isInGame && thisGame == totalGame) {
				Player currPlayer = currState.getCurrPlayer();

				// get next move of current player
				int nextMove = currPlayer.decideMove(currState.clone());

				// check isInGame to avoid AI delay while screen jumping
				if (!Thread.interrupted() && isInGame && thisGame == totalGame
						&& currState.isValidMove(nextMove)) {

					int row = currState.runNextMove(nextMove);

					// display disc dropped
					gameBoardPanel.displayDisc(nextMove,row,getCurrPlayerIndex());

					// increment turn
					currState.incTurn();
					currState.setCurrPlayer(currState.getOtherPlayer());

					gameBoardPanel.updateStatisticsPanel();

					// check game end and winner
					if (currState.checkGameEnd()) {
						isInGame = false;
						gameBoardPanel.displayEndGame(currState.getWinner(),
								currState.getWinDiscs());
					}
				} else {
					break;
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

	/**
	 * Getter function for the current game state.
	 * @return a copy of the current game state
	 */
	public GameState getCurrState() {
		return currState.clone();
	}

	/**
	 * Getter function of the current player.
	 * @return the current player
	 */
	public Player getCurrPlayer() {
		return currState.getCurrPlayer();
	}

	/**
	 * Getter function of the opposite player
	 * @return the opposite player in the current game
	 */
	public Player getOtherPlayer() {
		return currState.getOtherPlayer();
	}

	/**
	 * Check if the move is valid in the current game state
	 * @param move the column number for the move
	 * @return true if the move is valid false if the move is not valid
	 */
	public boolean isValidMove(int move) {
		return currState.isValidMove(move);
	}

	/**
	 * Check if the game is running currently
	 * @return true if the game is running, false is the game has been paused
	 */
	public boolean isInGame() {
		return isInGame;
	}

	/**
	 * Suspend the game instantly
	 */
	public void suspendGame() {
		isInGame = false;
	}

	/**
	 * Getter function for the player index.
	 * @return 0 for player 1 and 1 for player 2
	 */
	public int getCurrPlayerIndex() {
		return currState.getTurn() % 2;
	}

	/**
	 * Getter function for the game mode
	 * @return 0 for simulation, 1 for single player, 2 for double player
	 */
	public int getGameMode() {
		return gameMode;
	}

	/**
	 * Getter function for the AI mode in a single player game
	 * @return the difficulty of the AI (AI mode)
	 */
	public int getAIMode() {
		if (gameMode == 1) {
			if (getCurrPlayer() instanceof AI) {
				return ((AI) getCurrPlayer()).getMode();
			}
			if (getOtherPlayer() instanceof AI) {
				return ((AI) getOtherPlayer()).getMode();
			}
		}
		return -1;
	}
}
