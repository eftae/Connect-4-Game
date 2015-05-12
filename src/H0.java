/**
 * Zero Heuristic to give a randomize priority.
 * 
 */

public class H0 implements HeuristicAlgorithm {
	public int h(GameState state) {
		return 0;
	}
}