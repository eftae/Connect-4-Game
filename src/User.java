/**
 * Handle user input v0.1: allows for command line input only. v0.11: minor bug
 * fixed, problem with Scanner.close
 * 
 */

public class User implements Player {
	String name;
	int nextMove;
	boolean isReady; // is user input set

	/**
	 * 
	 * @param name
	 *            username
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
	 * 
	 * @return if input is ready
	 */
	public boolean isReady() {
		return isReady;
	}

	/**
	 * reset user status.
	 */
	public void resetPlayer() {
		isReady = false;
	}

	/**
	 * assign next move value.
	 * 
	 * @param nextMove
	 */
	public void userInputReady(int nextMove) {
		isReady = true;
		this.nextMove = nextMove;
	}

	@Override
	public int decideMove(GameState currState) {
		isReady = false;// get ready to recieve input
		return modeGUI();
	}

	/**
	 * Loop to listen the user input.
	 * 
	 * @return next move or -1 if interruptedf
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
