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
		while (!currState.isMoveValid(nextMove)) {
			nextMove = rand.nextInt(7);
		}
		return nextMove;
	}

}
