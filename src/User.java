/**
 * Handle user input
 * v0.1: allows for command line input only.
 * v0.11: minor bug fixed, problem with Scanner.close
 * 
 * @version v0.11
 */

import java.util.Scanner;

public class User implements Player {

	public int decideMove(GameState currState) {
		return modeCommandLineInput(currState);
	}

	/**
	 * get next move from user in command line
	 * 
	 * @param currState
	 * @return 0 - 6
	 */
	public int modeCommandLineInput(GameState currState) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Next move (1-7): ");
		int nextMove = sc.nextInt() - 1;
		while (!currState.isMoveValid(nextMove)) {
			System.out.print("Invalid move, try again (1-7): ");
			nextMove = sc.nextInt() - 1;
		}
		return nextMove;
	}
}
