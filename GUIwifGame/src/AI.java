/**
 * Class for the AI. Mode: 0. Fully randomized
 * 
 * @version 0.11
 */

import java.util.Random;

public class AI implements Player {
    
	private final int COL_MAX = 7;
	private final int ROW_MAX = 6;    
    
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

        return blockAction(currState);
		// switch (mode) {
//         default:
//             return modeRandom(currState);
//         }

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
        int targetCol = 0;
        
        // check horizontal
		for (int c = 0; c < COL_MAX - 3; c++) {
			for (int r = 0; r < ROW_MAX; r++) {
                int count = 0;
                int targetRow = 0;
                int i;
                for(i = 0; i < 4; i++){
                    Player enemy = s.getOtherPlayer();
                    if (enemy == null) continue;
                    if(enemy.equals(board[c+i][r])){
                        count++;
                    } else if(board[c+i][r] == null){ 
                        targetCol = c+i;
                    }
                }
	            if(count == 3 && s.getAvailableRow(targetCol) == r)
                    return targetCol;
			}
		}
		// check vertical
		for (int c = 0; c < COL_MAX; c++) {
			for (int r = 0; r < ROW_MAX - 3; r++) {
				int count = 0;
                for(int i = 0; i < 3; i++){
                    Player enemy = s.getOtherPlayer();
                    if (enemy == null) continue;
                    if(enemy.equals(board[c][r+i]))
                        count++;
                    if(board[c][r+3] == null && count == 3)
                        targetCol = c;
                }
                if(count == 3 && s.getAvailableRow(c) == (r+3))
                    return targetCol;
			}
		}
		return modeRandom(s);
    }

}
