/**
 * Interface for heuristic algorithms of alpha-beta search.
 * 
 */

public interface AlphaBetaHeuristic {

	/**
	 * Get the heuristic value of a GameState
	 * 
	 * @param state
	 *            GameState to be evaluate
	 * @return heuristic value of current state
	 */
	public int h(GameState state);
}
