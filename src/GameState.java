import java.util.ArrayList;

// our board representation
//
//           7 columns
//           0 1 2 3 4 5 6
//        5 | | | | | | | |
//        4 | | | | | | | |
// 6 rows 3 | | | | | | | |
//        2 | | | | | | | |
//        1 | | | | | | | |
//        0 | | | | | | | |


/**
 * Game State will store all the information of the game, 
 * including the board, current player, next player, 
 * winner if there is one, number of turn, 
 * and the wining disc for the game if the game end.
 */
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

	/**
	 * Construct a game state with the given players by 
	 * initializing the board, the turn number, and the winDise array.
	 * @param firstPlayer the first player
	 * @param secondPlayer the second player
	 */
	public GameState(Player firstPlayer, Player secondPlayer) {
		board = new Player[COL_MAX][ROW_MAX];
		currPlayer = firstPlayer;
		nextPlayer = secondPlayer;
		turn = 1;
		winDiscs = new ArrayList<Integer>();
	}

	/**
	 * Constructor for cloning the game state with 
	 * the given board other specific of a game information 
	 * including different players and the number of turn.
	 * Use by the AI.
	 */
	public GameState(Player[][] board, Player currPlayer, Player nextPlayer,
			Player winner, int turn) {
		this.board = board;
		this.currPlayer = currPlayer;
		this.nextPlayer = nextPlayer;
		this.winner = winner;
		this.turn = turn;
		winDiscs = new ArrayList<Integer>();
	}

	/**
     * Given a column number, and will place a disc 
     * at the next available row of the given column 
     * in the board. Use by game engine.
	 * @param col the column number (0-6)
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
     * Check if the game is finish by any 4 consecutive discs.
     * Set the winDisc array with the 4 discs that cause the 
     * game to finish.
     * Set the winner to the player if the player wins the game
     * @return true if the game is finish or false if the game is not finish.
     */
	public boolean checkGameEnd() {
		boolean gameEnd = false;
		winDiscs.clear();

		// check vertical
		for (int c = 0; c < COL_MAX; c++) {
			for (int r = 0; r < ROW_MAX - 3; r++) {
				Player curr = board[c][r];
				if (curr != null && curr.equals(board[c][r + 1])
						&& curr.equals(board[c][r + 2])
						&& curr.equals(board[c][r + 3])) {
					winner = curr;
					winDiscs.add(r);
					winDiscs.add(c);
					winDiscs.add(r + 1);
					winDiscs.add(c);
					winDiscs.add(r + 2);
					winDiscs.add(c);
					winDiscs.add(r + 3);
					winDiscs.add(c);
					gameEnd = true;
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
					winDiscs.add(r);
					winDiscs.add(c);
					winDiscs.add(r);
					winDiscs.add(c + 1);
					winDiscs.add(r);
					winDiscs.add(c + 2);
					winDiscs.add(r);
					winDiscs.add(c + 3);
					if (c + 4 < COL_MAX && curr.equals(board[c + 4][r])) {
						winDiscs.add(r);
						winDiscs.add(c + 4);
						if (c + 5 < COL_MAX && curr.equals(board[c + 5][r])) {
							winDiscs.add(r);
							winDiscs.add(c + 5);
							if (c + 6 < COL_MAX && curr.equals(board[c + 6][r])) {
								winDiscs.add(r);
								winDiscs.add(c + 6);
							}
						}
					}
					gameEnd = true;
				}
			}

			for (int r = 0; r < ROW_MAX - 3; r++) {
				// check diagonals: /
				Player curr = board[c][r];
				if (curr != null && curr.equals(board[c + 1][r + 1])
						&& curr.equals(board[c + 2][r + 2])
						&& curr.equals(board[c + 3][r + 3])) {
					winner = curr;
					winDiscs.add(r);
					winDiscs.add(c);
					winDiscs.add(r + 1);
					winDiscs.add(c + 1);
					winDiscs.add(r + 2);
					winDiscs.add(c + 2);
					winDiscs.add(r + 3);
					winDiscs.add(c + 3);
					if (c + 4 < COL_MAX && r + 4 < ROW_MAX
							&& curr.equals(board[c + 4][r + 4])) {
						winDiscs.add(r + 4);
						winDiscs.add(c + 4);
						if (c + 5 < COL_MAX && r + 5 < ROW_MAX
								&& curr.equals(board[c + 5][r + 5])) {
							winDiscs.add(r + 5);
							winDiscs.add(c + 5);
						}
					}
					gameEnd = true;
				}

				// check diagonals: \
				curr = board[c][r + 3];
				if (curr != null && curr.equals(board[c + 1][r + 2])
						&& curr.equals(board[c + 2][r + 1])
						&& curr.equals(board[c + 3][r])) {
					winner = curr;
					winDiscs.add(r + 3);
					winDiscs.add(c);
					winDiscs.add(r + 2);
					winDiscs.add(c + 1);
					winDiscs.add(r + 1);
					winDiscs.add(c + 2);
					winDiscs.add(r);
					winDiscs.add(c + 3);
					if (c + 4 < COL_MAX && r - 1 >= 0
							&& curr.equals(board[c + 4][r - 1])) {
						winDiscs.add(r - 1);
						winDiscs.add(c + 4);
						if (c + 5 < COL_MAX && r - 2 >= 0
								&& curr.equals(board[c + 5][r - 2])) {
							winDiscs.add(r - 2);
							winDiscs.add(c + 5);
						}
					}
					gameEnd = true;
				}
			}
		}
		// check turn number
		// check only if no winner on 42
		if (turn == MAX_TURN + 1) gameEnd = true;
		if (gameEnd) return true;
		return false;
	}

	/**
	 * Getter function of the winDise arrayList
	 */
	public ArrayList<Integer> getWinDiscs() {
		if (winDiscs == null) {
			return null;
		}
		return new ArrayList<Integer>(winDiscs);
	}

	/**
	 * Return the affiliation of a slot for the given location on the board.
	 * @param col the column number
	 * @param row the row number
	 * @return player of affiliation of the given slot
	 */
	public Player getLocation(int col, int row) {
		if (col >= 0 && col < COL_MAX && row >= 0 && row < ROW_MAX)
			return board[col][row];
		return null;
	}

	/**
	 * Getter function or the game board
	 * @return the game board represented by a 2D array
	 */
	public Player[][] getBoard() {
		Player[][] cloneBoard = new Player[COL_MAX][];
		for (int i = 0; i < COL_MAX; i++)
			cloneBoard[i] = board[i].clone();
		return cloneBoard;
	}

	/**
	 * Getter function of the opposite player (not the current player)
	 * @return the opposite player of the current game turn
	 */
	public Player getOtherPlayer() {
		return nextPlayer;
	}

	/**
	 * Given a column number of the board, return the next available 
	 * row number of that column.
	 * @param col the column number
	 * @return a row number or -1 if the column is full
	 */
	public int getAvailableRow(int col) {
		if (col >= 0 && col < COL_MAX)
			for (int r = 0; r < ROW_MAX; r++)
				if (board[col][r] == null)
					return r;
		return -1;
	}

	/**
	 * Getter function of the winner player
	 * @return the player who win the game
	 */
	public Player getWinner() {
		return winner;
	}

	/**
	 * Setter function for the current player
	 * @param p
	 */
	public void setCurrPlayer(Player p) {
		Player temp = currPlayer;
		currPlayer = p;
		nextPlayer = temp;
	}

	/**
	 * Getter function of the current player
	 * @return the current player
	 */
	public Player getCurrPlayer() {
		return currPlayer;
	}

	/**
	 * Getter function of the current turn number
	 * @return the current turn number
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * Increment the turn number by one
	 */
	public void incTurn() {
		turn++;
	}

	/**
	 * Check if the input column is a valid move for the current board state
	 * @param col the column number 
	 * @return true if valid to use with runNextMove(), false otherwise
	 */
	public boolean isValidMove(int col) {
		if (col >= 0 && col < COL_MAX && board[col][5] == null)
			return true;
		return false;
	}

	@Override
	/**
	 * clone the current game state
	 * @return the copy of the current game state.
	 */
	public GameState clone() {
		GameState cloneState = new GameState(getBoard(), currPlayer,
				nextPlayer, winner, turn);
		return cloneState;
	}
}