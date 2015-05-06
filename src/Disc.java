/**
 * Class for a disc.
 * 
 * @version v0.2
 */

public class Disc {
	private Player currPlayer;
	private int x;
	private int y;
	
	public Disc(Player p) {
		currPlayer = p;
	}
	
	public void setPlayer(Player p) {
		this.currPlayer = p;
	}
	
	public Player getAffiliation() {
		return this.currPlayer;
	}
	
	public int getHoriz() {
		return this.x;
	}
	
	public int getVert() {
		return this.y;
	}
	
	public void setLocation(int c, int r) {
		this.x = c;
		this.y = r;
	}
}
