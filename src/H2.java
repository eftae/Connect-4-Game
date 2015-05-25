/**
 * Aggressive heuristic algorithm for alpha-beta search. Considering minimum
 * number of steps to win.
 * 
 */

public class H2 implements AlphaBetaHeuristic {

	public int h(GameState state) {

		Player currPlayer = state.getCurrPlayer();

		int h = 0;
		int steps = 0;

		// checks every slot belong to current AI player
		for (int c = 0; c < 7; c++) {
			for (int r = 0; r < 6; r++) {

				if (state.getLocation(c, r) != currPlayer) {
					continue;
				}

				// bottom
				if (r >= 3) {
					steps = evalStepsToWin(state, c, r, 0, -1);
					h += 20 - steps;
				}

				if (c >= 3) {
					// left
					steps = evalStepsToWin(state, c, r, -1, 0);
					h += 20 - steps;

					// bottom-left
					if (r >= 3) {
						steps = evalStepsToWin(state, c, r, -1, -1);
						h += 20 - steps;
					}

					// top-left
					if (r < 3) {
						steps = evalStepsToWin(state, c, r, -1, -1);
						h += 20 - steps;
					}
				}

				if (c < 4) {
					// right
					steps = evalStepsToWin(state, c, r, 1, 0);
					h += 20 - steps;

					// bottom-right
					if (r >= 3) {
						steps = evalStepsToWin(state, c, r, 1, -1);
						h += 20 - steps;

					}

					// top-right
					if (r < 3) {
						steps = evalStepsToWin(state, c, r, 1, 1);
						h += 20 - steps;
					}
				}

			}
		}
		// System.out.println(h);
		return h;
	}

	/**
	 * check how many discs have to put in a column in order to connect 4.
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
	private int evalStepsToWin(GameState state, int c, int r, int dc, int dr) {

		Player curr = state.getCurrPlayer();

		if (state.getLocation(c + dc, r + dr) == curr) {
			if (state.getLocation(c + dc * 2, r + dr * 2) == curr) {
				if (state.getLocation(c + dc * 3, r + dr * 3) == curr) {
					return 4; // xxxx
				}
				if (state.getLocation(c + dc * 3, r + dr * 3) == null) {
					int steps = (r + dr * 3)
							- state.getAvailableRow(c + dc * 3) + 1;
					return steps; // xxx-
				}
			}
			if (state.getLocation(c + dc * 2, r + dr * 2) == null) {
				if (state.getLocation(c + dc * 3, r + dr * 3) == curr) {
					return (r + dr * 2) - state.getAvailableRow(c + dc * 2) + 1; // xx-x
				}
				if (state.getLocation(c + dc * 3, r + dr * 3) == null) {
					int steps = (r + dr * 2)
							- state.getAvailableRow(c + dc * 2) + 1;
					steps += (r + dr * 3) - state.getAvailableRow(c + dc * 3)
							+ 1;
					return steps; // xx--
				}
			}
		} else if (state.getLocation(c + dc, r + dr) == null) {
			if (state.getLocation(c + dc * 2, r + dr * 2) == curr) {
				if (state.getLocation(c + dc * 3, r + dr * 3) == curr) {
					int steps = (r + dr) - state.getAvailableRow(c + dc) + 1;
					return steps; // x-xx
				}
				if (state.getLocation(c + dc * 3, r + dr * 3) == null) {
					int steps = (r + dr) - state.getAvailableRow(c + dc) + 1;
					steps += (r + dr * 3) - state.getAvailableRow(c + dc * 3)
							+ 1;
					return steps; // x-x-
				}
			}
			if (state.getLocation(c + dc * 2, r + dr * 2) == null) {
				if (state.getLocation(c + dc * 3, r + dr * 3) == curr) {
					int steps = (r + dr) - state.getAvailableRow(c + dc) + 1;
					steps += (r + dr * 2) - state.getAvailableRow(c + dc * 2)
							+ 1;
					return steps; // x--x
				}
				if (state.getLocation(c + dc * 3, r + dr * 3) == null) {
					int steps = (r + dr) - state.getAvailableRow(c + dc) + 1;
					steps += (r + dr * 2) - state.getAvailableRow(c + dc * 2)
							+ 1;
					steps += (r + dr * 3) - state.getAvailableRow(c + dc * 3)
							+ 1;
					return steps; // x---
				}
			}
		}
		return 0;
	}
}
