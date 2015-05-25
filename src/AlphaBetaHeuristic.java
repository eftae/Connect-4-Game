/**
 * Interface for heuristic algorithms of alpha-beta search.
 * 
 * @version 0.1
 */

public interface AlphaBetaHeuristic {

	/**
	 * Get the heuristic value of a GameState
	 * 
	 * @param state
	 *            GameState to be evaluate
	 * @return heuristic value
	 */
	public int h(GameState state);
}
