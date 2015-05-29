/**
 * Defensive Heuristic Algorithm for alpha-beta search. Considering number of
 * empty slots connects to 3 consecutive discs, which means have more chance to
 * place the last disc to connect 4.
 */
public class H1 implements AlphaBetaHeuristic {

	public int h(GameState state) {

		int h = 0;

		// for each empty slot, check all directions
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

	/**
	 * Gives score only if there is a chance to connect 4 if placing one more
	 * disc in the given empty slot. Giving larger scale for opponent to make
	 * the AI becomes more defensive.
	 * 
	 * @param state
	 * @param c
	 *            column
	 * @param r
	 *            row
	 * @param dc
	 *            direction displacement of column
	 * @param dr
	 *            direction displacement of row
	 * @return
	 */
	private int evalEmptySlot(GameState state, int c, int r, int dc, int dr) {

		Player oppo = state.getOtherPlayer();
		// opponent, lower is better
		if (state.getLocation(c + dc, r + dr) == oppo
				&& state.getLocation(c + dc * 2, r + dr * 2) == oppo
				&& state.getLocation(c + dc * 3, r + dr * 3) == oppo) {
			return -10;
		}

		Player curr = state.getCurrPlayer();
		// current player, higher is better
		if (state.getLocation(c + dc, r + dr) == curr
				&& state.getLocation(c + dc * 2, r + dr * 2) == curr
				&& state.getLocation(c + dc * 3, r + dr * 3) == curr) {
			return 1;
		}

		return 0;
	}

}
