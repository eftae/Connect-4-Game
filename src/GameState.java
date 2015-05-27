import java.util.ArrayList;

/**
 * Handle the state of a game v0.11 added clone() v0.12 modified
 * 
 * @version v0.12
 */

// our board representation
//
// 7 columns
// 0 1 2 3 4 5 6
// 5 | | | | | | | |
// 4 | | | | | | | |
// 6 rows 3 | | | | | | | |
// 2 | | | | | | | |
// 1 | | | | | | | |
// 0 | | | | | | | |

public class GameState {
	private final int MAX_TURN = 42;
	private final int COL_MAX = 7;
	private final int ROW_MAX = 6;

	private Player[][] board;
	private Player currPlayer;
	private Player nextPlayer;
	private Player winner;
	private int turn;
	private ArrayList<Integer> winDiscs;

	public GameState(Player firstPlayer, Player secondPlayer) {
		board = new Player[COL_MAX][ROW_MAX];
		currPlayer = firstPlayer;
		nextPlayer = secondPlayer;
		turn = 1;
	}

	/**
	 * Constructor for cloning.
	 */
	public GameState(Player[][] board, Player currPlayer, Player nextPlayer,
			Player winner, int turn) {
		this.board = board;
		this.currPlayer = currPlayer;
		this.nextPlayer = nextPlayer;
		this.winner = winner;
		this.turn = turn;
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
	public boolean checkGameEnd() {

		// check vertical
		for (int c = 0; c < COL_MAX; c++) {
			for (int r = 0; r < ROW_MAX - 3; r++) {
				Player curr = board[c][r];
				if (curr != null && curr.equals(board[c][r + 1])
						&& curr.equals(board[c][r + 2])
						&& curr.equals(board[c][r + 3])) {
					winner = curr;
					winDiscs = new ArrayList<Integer>();
					winDiscs.add(r);
					winDiscs.add(c);
					winDiscs.add(r + 1);
					winDiscs.add(c);
					winDiscs.add(r + 2);
					winDiscs.add(c);
					winDiscs.add(r + 3);
					winDiscs.add(c);
					return true;
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
					winDiscs = new ArrayList<Integer>();
					winDiscs.add(r);
					winDiscs.add(c);
					winDiscs.add(r);
					winDiscs.add(c + 1);
					winDiscs.add(r);
					winDiscs.add(c + 2);
					winDiscs.add(r);
					winDiscs.add(c + 3);
					return true;
				}
			}

			for (int r = 0; r < ROW_MAX - 3; r++) {
				// check diagonals: /
				Player curr = board[c][r];
				if (curr != null && curr.equals(board[c + 1][r + 1])
						&& curr.equals(board[c + 2][r + 2])
						&& curr.equals(board[c + 3][r + 3])) {
					winner = curr;
					winDiscs = new ArrayList<Integer>();
					winDiscs.add(r);
					winDiscs.add(c);
					winDiscs.add(r + 1);
					winDiscs.add(c + 1);
					winDiscs.add(r + 2);
					winDiscs.add(c + 2);
					winDiscs.add(r + 3);
					winDiscs.add(c + 3);
					return true;
				}

				// check diagonals: \
				curr = board[c][r + 3];
				if (curr != null && curr.equals(board[c + 1][r + 2])
						&& curr.equals(board[c + 2][r + 1])
						&& curr.equals(board[c + 3][r])) {
					winner = curr;
					winDiscs = new ArrayList<Integer>();
					winDiscs.add(r);
					winDiscs.add(c);
					winDiscs.add(r + 2);
					winDiscs.add(c + 1);
					winDiscs.add(r + 1);
					winDiscs.add(c + 2);
					winDiscs.add(r);
					winDiscs.add(c + 3);
					return true;
				}
			}
		}

		// check turn number
		// check only if no winner on 42
		if (turn == MAX_TURN + 1) {
			return true;
		}

		return false;
	}

	public ArrayList<Integer> getWinDiscs() {
		return new ArrayList<Integer>(winDiscs);
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
		return nextPlayer;
	}

	public int getAvailableRow(int col) {
		if (col >= 0 && col < COL_MAX)
			for (int r = 0; r < ROW_MAX; r++)
				if (board[col][r] == null)
					return r;
		return -1;
	}

	public Player getWinner() {
		return winner;
	}

	public void setCurrPlayer(Player p) {
		Player temp = currPlayer;
		currPlayer = p;
		nextPlayer = temp;
	}

	public Player getCurrPlayer() {
		return currPlayer;
	}

	public int getTurn() {
		return turn;
	}

	public void incTurn() {
		turn++;
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
		GameState cloneState = new GameState(getBoard(), currPlayer,
				nextPlayer, winner, turn);
		return cloneState;
	}
}