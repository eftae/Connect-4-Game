/**
 * Handle the state of a game
 * 
 * @version v0.1
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
        this.currPlayer = firstPlayer;
        winner = null;
		nTurn = 1;
	}

	public void runNextMove(int col) {
		if (col < 0 || col >= COL_MAX) return;
		// drop the disc to the column
	    board[col][getAvailableRow(col)] = currPlayer;
	}

	/**
	 * Check any 4 discs connected.
	 * 
	 * @return the player connects 4 discs, winner
	 */
	public void checkGameEnd() {
		// check horizontal
		for (int c = 0; c < COL_MAX - 3; c++) {
			for (int r = 0; r < ROW_MAX; r++) {
	            Player curr = board[c][r];
				if (curr != null && curr.equals(board[c+1][r]) &&
	                curr.equals(board[c+2][r]) && curr.equals(board[c+3][r]))
					winner = curr;
			}
		}
		// check vertical
		for (int c = 0; c < COL_MAX; c++) {
			for (int r = 0; r < ROW_MAX - 3; r++) {
				Player curr = board[c][r];
				if (curr != null && curr.equals(board[c][r+1]) &&
	                curr.equals(board[c][r+2]) && curr.equals(board[c][r+3]))
					winner = curr;
			}
		}
	    // check diagonals: /
	    for(int c = 0; c < COL_MAX-3; c++){
	        for(int r = 0; r < ROW_MAX-3; r++){
	        	Player curr = board[c][r];
	            if(curr != null && curr.equals(board[c+1][r+1]) &&
	               curr.equals(board[c+2][r+2]) && curr.equals(board[c+3][r+3]))
	                winner = curr;
	        }
	    }
	    // check diagonals: \
	    for(int c = 0; c < COL_MAX-3; c++){
	        for(int r = 3; r < ROW_MAX; r++){
	        	Player curr = board[c][r];
	            if(curr != null && curr.equals(board[c+1][r-1]) &&
	               curr.equals(board[c+2][r-2]) && curr.equals(board[c+3][r-3]))
	                winner = curr;
	        }
	    }
        winner = null;
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
			if (board[col][row] != null)
				return board[col][row];
		return null;
	}
    
    public Player[][] getBoard(){
        return board;
    }
    
    public Player getOtherPlayer(){
        if(currPlayer.equals(players[0]))
            return players[1];
        else
            return players[0];
    }
    
    public int getAvailableRow(int col){
		for (int r = 0; r < ROW_MAX; r++)
			if (board[col][r] == null)
			    return r;
		return -1;
    }

	public Player getWinner() {
		return winner;
	}
    
    public void setCurrPlayer(Player p){
        currPlayer = p;
    }
    
    public Player getCurrPlayer(){
        return currPlayer;
    }

	public int getTurn() {
		return nTurn;
	}
    
    public void nTurnPlusPlus(){
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
}
