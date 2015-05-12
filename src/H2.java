/**
 * Heuristic for alpha-beta search. Considering number of empty slot connects to
 * 3 consecutive discs and any of those two empty slots connect together.
 * 
 */

public class H2 implements HeuristicAlgorithm {
	int[] connect3;

	public int h(GameState state) {

		int h = 0;

		// sequence of 3
		for (int c = 0; c < 7; c++) {
			connect3 = new int[6];

			for (int r = 0; r < 6; r++) {
				if (state.getLocation(c, r) == null) {
					// bottom
					if (r - 3 >= 0) {
						h += evalThreeSlots(state, c, r, 0, -1);
					}

					if (c - 3 >= 0) {
						// left
						h += evalThreeSlots(state, c, r, -1, 0);
						// bottom-left
						if (r - 3 >= 0) {
							h += evalThreeSlots(state, c, r, -1, -1);
						}
						// top-left
						if (r + 3 < 6) {
							h += evalThreeSlots(state, c, r, -1, -1);
						}
					}

					if (c + 3 < 7) {
						// right
						h += evalThreeSlots(state, c, r, 1, 0);
						// bottom-right
						if (r - 3 >= 0) {
							h += evalThreeSlots(state, c, r, 1, -1);
						}
						// top-right
						if (r + 3 < 6) {
							h += evalThreeSlots(state, c, r, 1, 1);
						}
					}
				}

				if (r > 0) {
					if (connect3[r] == 1 && connect3[r] == connect3[r - 1]) {
						h += 1000;
					} else if (connect3[r] == 2
							&& connect3[r] == connect3[r - 1]) {
						h -= 1000;
					}
				}
			}

		}

		return h;
	}

	private int evalThreeSlots(GameState state, int c, int r, int dc, int dr) {

		Player oppo = state.getOtherPlayer();
		// opponent
		if (state.getLocation(c + dc, r + dr) == oppo
				&& state.getLocation(c + dc * 2, r + dr * 2) == oppo
				&& state.getLocation(c + dc * 3, r + dr * 3) == oppo) {
			connect3[r] = 2;
			return -10;
		}

		Player curr = state.getCurrPlayer();
		// current player
		if (state.getLocation(c + dc, r + dr) == curr
				&& state.getLocation(c + dc * 2, r + dr * 2) == curr
				&& state.getLocation(c + dc * 3, r + dr * 3) == curr) {
			connect3[r] = 1;
			return 1;
		}

		return 0;
	}

}
