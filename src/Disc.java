/**
 * Class for a disc.
 * 
 * @version v0.1
 */

public class Disc {
	Player affiliation; // belong to a player

	public Disc(Player affiliation) {
		this.affiliation = affiliation;
	}

	public Player getAffiliation() {
		return affiliation;
	}
}
