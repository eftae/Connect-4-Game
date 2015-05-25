/**
 * Handle user input v0.1: allows for command line input only. v0.11: minor bug
 * fixed, problem with Scanner.close
 * 
 * @version v0.11
 */

public class User implements Player {
	String name;
	int nextMove;
	boolean isReady;

	public User(String name) {
		this.name = name;
		isReady = false;
	}

	public String getName() {
		return name;
	}

	public boolean isReady() {
		return isReady;
	}

	public void resetPlayer() {
		isReady = false;
	}

	public void userInputReady(int nextMove) {
		isReady = true;
		this.nextMove = nextMove;
	}

	public int decideMove(GameState currState) {
		isReady = false;// get ready to recieve input
		return modeGUI();
	}

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
