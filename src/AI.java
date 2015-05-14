/**
 * Class for the ALPHA BETA AI.
 * v0.2 many bugs fixed
 * v0.21 fixed the bug of turn number
 * v0.22 set game modes
 * 
 * @version 0.22
 */

import java.util.ArrayList;
import java.util.Random;

public class AI implements Player {
	private String name;
	private int mode;
	private Player opponent;
	private HeuristicAlgorithm hAlgo;

	/**
	 * Three modes suggested, 0:Basic 1: Medium, 2: Difficult.
	 * 
	 * @param mode
	 *            three difficulty
	 */
	public AI(String name, int mode) {
		this.name = name;
		this.mode = mode;
	}

	public String getName() {
		return name;
	}

	public int decideMove(GameState currState) {
		// setting difficulty
		int depth = mode * 4 + 3;
		switch (mode) {
		case 0:
			hAlgo = new H0();
			break;
		case 1:
			hAlgo = new H1();
			break;
		case 2:
			hAlgo = new H2();
			break;
		default:
			hAlgo = new H0();
		}

		opponent = currState.getOtherPlayer();
		int nextMove = -1;
		HNode alpha = new HNode(Integer.MIN_VALUE, nextMove);
		HNode beta = new HNode(Integer.MAX_VALUE, nextMove);

		nextMove = alphabeta(currState, depth, alpha, beta).getMove();
		return nextMove;
	}

	/**
	 * Alpha-Beta algorithm.
	 * 
	 * @param state
	 *            current state
	 * @param depth
	 *            difficulty
	 * @param alpha
	 *            alpha HNode
	 * @param beta
	 *            beta HNode
	 * @return
	 */
	private HNode alphabeta(GameState state, int depth, HNode alpha, HNode beta) {
		// check win/lose
		if (state.getTurn() > 42) {
			alpha.setValue(0);
			return alpha;
		}
		if (state.getWinner() == this) {
			alpha.setValue(1000 << depth);
			return alpha;
		}
		if (state.getWinner() == opponent) {
			alpha.setValue(-1000 << depth);
			return alpha;
		}

		// reaches depth limit
		if (depth == 0) {
			int h = hAlgo.h(state);
			alpha.setValue(h);
			return alpha;
		}

		ArrayList<Integer> moves = getRandomPositions();
		if (state.getCurrPlayer() == this) {
			for (int move : moves) {
				if (!state.isValidMove(move)) {
					continue;
				}
				GameState cloneState = state.clone();
				doNextMove(cloneState, move);

				HNode newAlpha = new HNode(alpha.getValue(), move);
				HNode newBeta = new HNode(beta.getValue(), move);

				int v = alphabeta(cloneState, depth - 1, newAlpha, newBeta)
						.getValue();

				if (alpha.getValue() < v) {
					alpha.setValue(v);
					alpha.setMove(move);

				}
				if (alpha.getValue() >= beta.getValue()) {
					break;
				}
			}
			return alpha;
		} else {
			for (int move : moves) {
				if (!state.isValidMove(move)) {
					continue;
				}

				GameState cloneState = state.clone();
				doNextMove(cloneState, move);

				HNode newAlpha = new HNode(alpha.getValue(), move);
				HNode newBeta = new HNode(beta.getValue(), move);

				int v = alphabeta(cloneState, depth - 1, newAlpha, newBeta)
						.getValue();

				if (beta.getValue() > v) {
					beta.setValue(v);
					beta.setMove(move);
				}
				if (alpha.getValue() >= beta.getValue()) {
					break;
				}
			}
			return beta;
		}
	}

	/**
	 * Execute a move to a state.
	 * 
	 * @param state
	 *            game state to be execute
	 * @param move
	 *            requested move
	 */
	private void doNextMove(GameState state, int move) {
		state.runNextMove(move);
		if (state.getCurrPlayer() == this) {
			state.setCurrPlayer(opponent);
		} else {
			state.setCurrPlayer(this);
		}
		state.incTurn();
		state.checkGameEnd();
	}

	/**
	 * Generate a random list from 0 to 6.
	 * 
	 * @return a list of 7 distinct value, 0 - 6
	 */
	private ArrayList<Integer> getRandomPositions() {
		ArrayList<Integer> randList = new ArrayList<Integer>();
		Random rand = new Random();
		int i = rand.nextInt(7);
		while (randList.size() < 7) {
			while (randList.contains(i)) {
				i = rand.nextInt(7);
			}
			randList.add(i);
		}
		return randList;
	}

	/**
	 * AI mode in fully randomization
	 * 
	 * @param currState
	 * @return next column move
	 */
	private int modeRandom(GameState currState) {
		Random rand = new Random();
		int nextMove = rand.nextInt(7);
		while (!currState.isValidMove(nextMove)) {
			nextMove = rand.nextInt(7);
		}
		return nextMove;
	}

}
