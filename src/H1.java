/**
 * Defensive Heuristic Algorithm for alpha-beta search. Considering number of
 * empty slots connects to 3 consecutive discs.
 * 
 * @version v0.1
 */

public class H1 implements HeuristicAlgorithm {

	public int h(GameState state) {

		int h = 0;

		// sequence of 3
		for (int c = 0; c < 7; c++) {
			for (int r = 0; r < 6; r++) {
				if (state.getLocation(c, r) == null) {
					// bottom
					if (r - 3 >= 0) {
						h += evalEmptySlot(state, c, r, 0, -1);
					}

					if (c - 3 >= 0) {
						// left
						h += evalEmptySlot(state, c, r, -1, 0);
						// bottom-left
						if (r - 3 >= 0) {
							h += evalEmptySlot(state, c, r, -1, -1);
						}
						// top-left
						if (r + 3 < 6) {
							h += evalEmptySlot(state, c, r, -1, -1);
						}
					}

					if (c + 3 < 7) {
						// right
						h += evalEmptySlot(state, c, r, 1, 0);
						// bottom-right
						if (r - 3 >= 0) {
							h += evalEmptySlot(state, c, r, 1, -1);
						}
						// top-right
						if (r + 3 < 6) {
							h += evalEmptySlot(state, c, r, 1, 1);
						}
					}

				}
			}
		}

		return h;
	}

	private int evalEmptySlot(GameState state, int c, int r, int dc, int dr) {

		Player oppo = state.getOtherPlayer();
		// opponent
		if (state.getLocation(c + dc, r + dr) == oppo
				&& state.getLocation(c + dc * 2, r + dr * 2) == oppo
				&& state.getLocation(c + dc * 3, r + dr * 3) == oppo) {
			return -10;
		}

		Player curr = state.getCurrPlayer();
		// current player
		if (state.getLocation(c + dc, r + dr) == curr
				&& state.getLocation(c + dc * 2, r + dr * 2) == curr
				&& state.getLocation(c + dc * 3, r + dr * 3) == curr) {
			return 1;
		}

		return 0;
	}

}
