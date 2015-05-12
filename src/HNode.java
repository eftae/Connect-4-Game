/**
 * Heuristic node for AI.
 * 
 * @version v0.11
 *
 */

public class HNode {
	private int value;
	private int move;

	public HNode(int value, int move) {
		this.value = value;
		this.move = move;
	}

	public int getValue() {
		return value;
	}

	public int getMove() {
		return move;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setMove(int move) {
		this.move = move;
	}

}
