/**
 * Class for the AI. Mode: 0. Fully randomized
 * 
 * @version 0.11
 */

import java.util.Random;

public class AI implements Player {
	private int mode;

	/**
	 *
	 * @param mode
	 *            AI mode
	 */
	public AI(int mode) {
		this.mode = mode;
	}

	public int decideMove(GameState currState) {

		switch (mode) {
		default:
			return modeRandom(currState);
		}

	}

	/**
	 * AI mode in fully randomization
	 * 
	 * @param currState
	 * @return next column move
	 */
	public int modeRandom(GameState currState) {
		Random rand = new Random();
		int nextMove = rand.nextInt(7);
		while (!currState.isValidMove(nextMove)) {
			nextMove = rand.nextInt(7);
		}
		return nextMove;
	}
    
    public int blockAction(GameState s){
        Player[][] board = s.getBoard();
        
        // check horizontal
		for (int c = 0; c < COL_MAX - 3; c++) {
			for (int r = 0; r < ROW_MAX; r++) {
                for(int i = 0; i < 4; i++){
                    int count = 0;
    	            Player curr = board[c+i][r];
                    if (curr == null) continue;
                    if(i != 0 && curr.equals(board[c][r])) count++;
                    if(i != 1 && curr.equals(board[c+1][r])) count++;
                    if(i != 2 && curr.equals(board[c+2][r])) count++;
                    if(i != 3 && curr.equals(board[c+3][r])) count++;
                    if(count == 3)
                }
	            
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
        
        
    }

}
