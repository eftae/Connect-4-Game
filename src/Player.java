/**
 * Interface for Player. Player includes User, AI
 */

public interface Player {
	/**
	 * Get user name.
	 * 
	 * @return user name
	 */
	public String getName();

	/**
	 * Get next move.
	 * 
	 * @param currState
	 * @return column number of next move
	 */
	public int decideMove(GameState currState);
}
