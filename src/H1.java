/**
 * Heuristic for alphabeta search.
 * Considering number of empty slot connects to 2/3 consecutive discs.
 *
 */
public class H1 implements HeuristicAlgorithm {

	@Override
	public int h(GameState state) {

		int h = 0;
		Player curr = state.getCurrPlayer();
		Player oppo = state.getOtherPlayer();

		// calculate how many empty slot connecting to sequence of 3
		int count = 0;
		for (int c = 0; c < 7; c++) {
			for (int r = 0; r < 6; r++) {
				if (state.getLocation(c, r) == null) {
					// left
					if (c - 3 >= 0
							&& state.getLocation(c - 1, r) == curr
							&& state.getLocation(c - 1, r) == state
									.getLocation(c - 2, r)
							&& state.getLocation(c - 1, r) == state
									.getLocation(c - 3, r)) {
						count++;
					}
					// right
					if (c + 3 < 7
							&& state.getLocation(c + 1, r) == curr
							&& state.getLocation(c + 1, r) == state
									.getLocation(c + 2, r)
							&& state.getLocation(c + 1, r) == state
									.getLocation(c + 3, r)) {
						count++;
					}
					// right
					if (r - 3 >= 0
							&& state.getLocation(c, r - 1) == curr
							&& state.getLocation(c, r - 1) == state
									.getLocation(c, r - 2)
							&& state.getLocation(c, r - 1) == state
									.getLocation(c, r - 3)) {
						count++;
					}
					// bottom-right
					if (r - 3 >= 0
							&& c - 3 >= 0
							&& state.getLocation(c - 1, r - 1) == curr
							&& state.getLocation(c - 1, r - 1) == state
									.getLocation(c - 2, r - 2)
							&& state.getLocation(c - 1, r - 1) == state
									.getLocation(c - 3, r - 3)) {
						count++;
					}
					// bottom-left
					if (r - 3 >= 0
							&& c + 3 < 7
							&& state.getLocation(c + 1, r - 1) == curr
							&& state.getLocation(c + 1, r - 1) == state
									.getLocation(c + 2, r - 2)
							&& state.getLocation(c + 1, r - 1) == state
									.getLocation(c + 3, r - 3)) {
						count++;
					}
					// top-left
					if (r + 3 < 6
							&& c - 3 >= 0
							&& state.getLocation(c - 1, r + 1) == curr
							&& state.getLocation(c - 1, r + 1) == state
									.getLocation(c - 2, r + 2)
							&& state.getLocation(c - 1, r + 1) == state
									.getLocation(c - 3, r + 3)) {
						count++;
					}
					// top-right
					if (r + 3 < 6
							&& c + 3 < 7
							&& state.getLocation(c + 1, r + 1) == curr
							&& state.getLocation(c + 1, r + 1) == state
									.getLocation(c + 2, r + 2)
							&& state.getLocation(c + 1, r + 1) == state
									.getLocation(c + 3, r + 3)) {
						count++;
					}
				}
			}
		}

		h = (count << 3);

		return h;
	}
}
