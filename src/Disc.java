/**
 * Class for a disc.
 * 
 * @version v0.2
 */

public class Disc {
	private Player currPlayer;
	private int col;
	private int row;
	
	public Disc(Player p, int col) {
		currPlayer = p;
        this.col = col;
	}
	
	public void setPlayer(Player p) {
		this.currPlayer = p;
	}
	
	public Player getAffiliation() {
		return this.currPlayer;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getRpw() {
		return row;
	}
	
    public void setRow(int r){
        row = r;
    }
    
	public void setLocation(int c, int r) {
		col = c;
		row = r;
	}
}
