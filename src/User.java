/**
 * Handle user input
 * v0.1: allows for command line input only.
 * v0.11: minor bug fixed, problem with Scanner.close
 * 
 * @version v0.11
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class User implements Player {
	Scanner sc;
	String name;
	int nextMove;
	boolean isReady;

	public User(String name) {
		this.name = name;
		sc = new Scanner(System.in);
		isReady = false;
	}

	public String getName() {
		return name;
	}

	public boolean isReady() {
		return isReady;
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
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		return nextMove;
	}

	/**
	 * get next move from user in command line
	 * 
	 * @param currState
	 * @return 0 - 6
	 */
	private int modeCommandLineInput(GameState currState) {
		int nextMove = -1;
		try {
			System.out.print("Next move (1-7): ");
			nextMove = sc.nextInt() - 1;
			while (!currState.isValidMove(nextMove)) {
				System.out.print("Invalid move, try again (1-7): ");
				nextMove = sc.nextInt() - 1;
			}
		} catch (InputMismatchException e) {
		} finally {
			if (sc != null && currState.getTurn() > 40)
				sc.close();
		}
		return nextMove;
	}
}
