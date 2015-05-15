/**
 * Zero Heuristic giving a random priority.
 * 
 */

public class H0 implements HeuristicAlgorithm {
	public int h(GameState state) {
		return 0;
	}
}