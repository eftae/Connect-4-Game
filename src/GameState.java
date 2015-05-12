/**
 * Handle the state of a game
 * v0.11 added clone()
 * v0.12 modified 
 * 
 * @version v0.12
 */


// our board representation
//     
//            7 columns
//           0 1 2 3 4 5 6
//        5 | | | | | | | |
//        4 | | | | | | | |
// 6 rows 3 | | | | | | | |
//        2 | | | | | | | |
//        1 | | | | | | | |
//        0 | | | | | | | |

public class GameState {
	private final int COL_MAX = 7;
	private final int ROW_MAX = 6;

	private Player[][] board;
	private Player[] players;
	private Player currPlayer;
	private Player winner;
	private int nTurn;

	public GameState(Player firstPlayer, Player secondPlayer) {
		board = new Player[COL_MAX][ROW_MAX];
		players = new Player[2];
		players[0] = firstPlayer;
		players[1] = secondPlayer;
		currPlayer = firstPlayer;
		nTurn = 1;
	}

	/**
	 * Constructor for cloning.
	 */
	public GameState(Player[][] board, Player[] players, Player currPlayer,
			Player winner, int nTurn) {
		this.board = board;
		this.players = players;
		this.currPlayer = currPlayer;
		this.winner = winner;
		this.nTurn = nTurn;
	}

	/**
	 * Drop the disc to the column
	 * 
	 * @param col
	 */
	public int runNextMove(int col) {
		if (col >= 0 & col < COL_MAX) {
			int row = getAvailableRow(col);
			board[col][row] = currPlayer;
			return row;
		}
		return -1;
	}

	/**
	 * Check any 4 discs connected.
	 * 
	 * @return the player connects 4 discs, winner
	 */
	public void checkGameEnd() {

		// check vertical
		for (int c = 0; c < COL_MAX; c++) {
			for (int r = 0; r < ROW_MAX - 3; r++) {
				Player curr = board[c][r];
				if (curr != null && curr.equals(board[c][r + 1])
						&& curr.equals(board[c][r + 2])
						&& curr.equals(board[c][r + 3])) {
					winner = curr;
					return;
				}
			}
		}

		for (int c = 0; c < COL_MAX - 3; c++) {
			// check horizontal
			for (int r = 0; r < ROW_MAX; r++) {
				Player curr = board[c][r];
				if (curr != null && curr.equals(board[c + 1][r])
						&& curr.equals(board[c + 2][r])
						&& curr.equals(board[c + 3][r])) {
					winner = curr;
					return;
				}
			}

			for (int r = 0; r < ROW_MAX - 3; r++) {
				// check diagonals: /
				Player curr = board[c][r];
				if (curr != null && curr.equals(board[c + 1][r + 1])
						&& curr.equals(board[c + 2][r + 2])
						&& curr.equals(board[c + 3][r + 3])) {
					winner = curr;
					return;
				}

				// check diagonals: \
				curr = board[c][r + 3];
				if (curr != null && curr.equals(board[c + 1][r + 2])
						&& curr.equals(board[c + 2][r + 1])
						&& curr.equals(board[c + 3][r])) {
					winner = curr;
					return;
				}
			}
		}
	}

	/**
	 * Return the affiliation of a slot.
	 * 
	 * @param col
	 * @param row
	 * @return player of affiliation
	 */
	public Player getLocation(int col, int row) {
		if (col >= 0 && col < COL_MAX && row >= 0 && row < ROW_MAX)
			return board[col][row];
		return null;
	}

	public Player[][] getBoard() {
		Player[][] cloneBoard = new Player[COL_MAX][];
		for (int i = 0; i < COL_MAX; i++)
			cloneBoard[i] = board[i].clone();
		return cloneBoard;
	}

	public Player getOtherPlayer() {
		if (currPlayer.equals(players[0]))
			return players[1];
		else
			return players[0];
	}
	
	public void swapPlayer(){
		if (currPlayer.equals(players[0])) currPlayer = players[1];
		else currPlayer = players[0];
	}

	public int getAvailableRow(int col) {
		for (int r = 0; r < ROW_MAX; r++)
			if (board[col][r] == null)
				return r;
		return -1;
	}

	public Player getWinner() {
		return winner;
	}

	public void setCurrPlayer(Player p) {
		currPlayer = p;
	}

	public Player getCurrPlayer() {
		return currPlayer;
	}

	public int getTurn() {
		return nTurn;
	}

	public void nTurnPlusPlus() {
		nTurn++;
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

	@Override
	public GameState clone() {
		GameState cloneState = new GameState(getBoard(), players, currPlayer,
				winner, nTurn);
		return cloneState;
	}
}