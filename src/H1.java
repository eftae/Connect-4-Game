/**
 * Heuristic for alphabeta search. Considering number of empty slot connects to
 * 3 consecutive discs.
 * 
 */

public class H1 implements HeuristicAlgorithm {

	public int h(GameState state) {

		int h = 0;
		Player curr = state.getCurrPlayer();
		// Player oppo = state.getOtherPlayer();

		// calculate how many empty slot connecting to sequence of 3
		int count = 0;
		for (int c = 0; c < 7; c++) {
			for (int r = 0; r < 6; r++) {
				if (state.getLocation(c, r) == null) {
					// bottom
					if (r - 3 >= 0 && isThreeSame(state, curr, c, r, 0, -1)) {
						count++;
					}

					if (c - 3 >= 0) {
						// left
						if (isThreeSame(state, curr, c, r, -1, 0)) {
							count++;
						}
						// bottom-left
						if (r - 3 >= 0
								&& isThreeSame(state, curr, c, r, -1, -1)) {
							count++;
						}
						// top-left
						if (r + 3 < 6 && isThreeSame(state, curr, c, r, -1, 1)) {
							count++;
						}
					}

					if (c + 3 < 7) {
						// right
						if (isThreeSame(state, curr, c, r, 1, 0)) {
							count++;
						}
						// bottom-right
						if (r - 3 >= 0 && isThreeSame(state, curr, c, r, 1, -1)) {
							count++;
						}
						// top-right
						if (r + 3 < 6 && isThreeSame(state, curr, c, r, 1, 1)) {
							count++;
						}
					}

				}
			}
		}

		h = count;

		return h;
	}

	private boolean isThreeSame(GameState state, Player p, int c, int r,
			int dc, int dr) {

		if (state.getLocation(c + dc, r + dr) == p
				&& state.getLocation(c + dc * 2, r + dr * 2) == p
				&& state.getLocation(c + dc * 3, r + dr * 3) == p) {
			return true;
		}

		return false;
	}

}
