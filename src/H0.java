/**
 * Heuristic for AI resulting as a random order.
 * 
 */

public class H0 implements AlphaBetaHeuristic {
	public int h(GameState state) {
		return 0;
	}
}