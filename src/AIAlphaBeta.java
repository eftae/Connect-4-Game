/**
 * Class for the ALPHA BETA AI.
 * 
 * @version 0.1
 */

import java.util.ArrayList;
import java.util.Random;

public class AIAlphaBeta implements Player {
	private int mode;
	private Player opponent;
	private HeuristicAlgorithm hAlgo;

	public AIAlphaBeta(int mode) {
		this.mode = mode;
	}

	public int decideMove(GameState currState) {

		opponent = currState.getOtherPlayer();
		hAlgo = new H1();
		HNode result = null;

		HNode alpha = new HNode(-1000, -1);
		HNode beta = new HNode(1000, -1);
		int depth = mode * 2 + 5;

		result = alphabeta(currState, depth, alpha, beta);

		return result.getMove();
	}

	private HNode alphabeta(GameState state, int depth, HNode alpha, HNode beta) {
		if (depth == 0) {
			int h = hAlgo.h(state);
			alpha.setValue(h);
			return alpha;
		}
		if (state.getWinner() == this) {
			alpha.setValue(999);
			return alpha;
		}
		if (state.getWinner() == opponent) {
			alpha.setValue(-999);
			return alpha;
		}
		if (state.getCurrPlayer() == this) {
			ArrayList<Integer> moves = getRandomPositions();

			for (int move : moves) {
				// skip if column is full
				if (!state.isValidMove(move)) {
					continue;
				}

				// run next move
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
			ArrayList<Integer> moves = getRandomPositions();

			for (int move : moves) {
				// skip if column is full
				if (!state.isValidMove(move)) {
					continue;
				}

				// run next move
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

	private void doNextMove(GameState state, int move) {
		state.runNextMove(move);
		if (state.getCurrPlayer() == this)
			state.setCurrPlayer(opponent);
		else
			state.setCurrPlayer(this);
		state.nTurnPlusPlus();
		state.checkGameEnd();
	}

	private ArrayList<Integer> getRandomPositions() {
		ArrayList<Integer> randList = new ArrayList<Integer>();

		while (randList.size() < 7) {
			Random rand = new Random();
			int i = rand.nextInt(7);
			while (randList.contains(i)) {
				i = rand.nextInt(7);
			}
			randList.add(i);
		}

		return randList;
	}

}
