/**
 * Handle the state of a game
 * 
 * @version v0.1
 */

public class GameState {
	private final int COL_MAX = 7;
	private final int ROW_MAX = 6;

	private Disc[][] board;
	private Player lastMovePlayer;
	private Player winner;
	private int turn;

	public GameState() {
		board = new Disc[COL_MAX][ROW_MAX];
		turn = 0;
	}

	/**
	 * Drop a disc for next player.
	 * 
	 * @param discDrop
	 *            the disc to be drop
	 * @param col
	 *            position of the disc to be drop
	 * @return true if dropped, false otherwise
	 */
	public boolean runNextMove(Disc discDrop, int col) {
		if (discDrop == null || !isValidMove(col)) {
			return false;
		}

		// drop the disc to the column
		for (int r = 0; r < ROW_MAX; r++) {
			if (board[col][r] == null) {
				board[col][r] = discDrop;
				turn++;
				winner = checkWinner();
				return true;
			}
		}

		return false;
	}

	/**
	 * Check any 4 discs connected.
	 * 
	 * @return the player connects 4 discs, winner
	 */
	public Player checkWinner() {
		// check row of 4
		for (int c = 0; c < COL_MAX - 3; c++) {
			for (int r = 0; r < ROW_MAX; r++) {
				if (board[c][r] != null && board[c][r] == board[c + 1][r]
						&& board[c][r] == board[c + 2][r]
						&& board[c][r] == board[c + 3][r]) {
					return board[c][r].getAffiliation();
				}
			}
		}
		// check column of 4
		for (int c = 0; c < COL_MAX; c++) {
			for (int r = 0; r < ROW_MAX - 3; r++) {
				if (board[c][r] != null && board[c][r] == board[c][r + 1]
						&& board[c][r] == board[c][r + 2]
						&& board[c][r] == board[c][r + 3]) {
					return board[c][r].getAffiliation();
				}
			}
		}
		// check diagonals of 4
		for (int c = 0; c < COL_MAX; c++) {
			for (int r = 0; r < ROW_MAX; r++) {
				// direction: /
				if (c + 3 < COL_MAX && r + 3 < ROW_MAX) {
					if (board[c][r] != null
							&& board[c][r] == board[c + 1][r + 1]
							&& board[c][r] == board[c + 2][r + 2]
							&& board[c][r] == board[c + 3][r + 3]) {
						return board[c][r].getAffiliation();
					}
				}
				// direction: \
				if (c - 3 >= 0 && r + 3 < ROW_MAX) {
					if (board[c][r] != null
							&& board[c][r] == board[c - 1][r + 1]
							&& board[c][r] == board[c - 2][r + 2]
							&& board[c][r] == board[c - 3][r + 3]) {
						return board[c][r].getAffiliation();
					}
				}
			}
		}
		// no winner
		return null;
	}

	/**
	 * Return the affiliation of a slot.
	 * 
	 * @param col
	 * @param row
	 * @return player of affiliation
	 */
	public Player checkBoard(int col, int row) {
		if (col >= 0 && col < COL_MAX && row >= 0 && row < ROW_MAX)
			if (board[col][row] != null)
				return board[col][row].getAffiliation();
		return null;
	}

	public Player getLastMovePlayer() {
		return lastMovePlayer;
	}

	public Player getWinner() {
		return winner;
	}

	public int getTurn() {
		return turn;
	}

	/**
	 * Check if the input column is a valid move.
	 * 
	 * @param col
	 * @return true if valid to use with runNextMove(), false otherwise
	 */
	public boolean isValidMove(int col) {
		if (col >= 0 && col < COL_MAX && board[col][5] == null)
			return true;
		return false;
	}
}
