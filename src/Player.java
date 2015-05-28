/**
 * Interface for Player. Player includes User, AI.
 */

public interface Player {
	/**
	 * Get teh player's name.
	 * 
	 * @return user name
	 */
	public String getName();

	/**
	 * Get next move from the player.
	 * 
	 * @param currState
	 * @return column number of next move
	 */
	public int decideMove(GameState currState);
}
