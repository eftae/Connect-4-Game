/**
 * Handle user input from GUI, also contains user information.
 * 
 */

public class User implements Player {
	String name;
	int nextMove;
	boolean isReady; // is user input set

	/**
	 * 
	 * @param name
	 *            user name
	 */
	public User(String name) {
		this.name = name;
		isReady = false;
	}

	/**
	 * Getter for name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for isReady.
	 * 
	 * @return if input is ready
	 */
	public boolean isReady() {
		return isReady;
	}

	/**
	 * reset user status. Prevent reading next move.
	 */
	public void resetPlayer() {
		isReady = false;
	}

	/**
	 * Assign next move value.
	 * 
	 * @param nextMove
	 */
	public void userInputReady(int nextMove) {
		isReady = true;
		this.nextMove = nextMove;
	}

	@Override
	public int decideMove(GameState currState) {
		isReady = false;// get ready to receive input
		return modeGUI();
	}

	/**
	 * A loop listening to the user input.
	 * 
	 * @return next move or -1 if interrupted
	 */
	private int modeGUI() {
		// wait isReady to be set
		while (!isReady) {
			if (Thread.interrupted()) {
				return -1;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}

		return nextMove;
	}
}
