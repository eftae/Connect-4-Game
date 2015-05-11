import java.awt.Color;

import javax.swing.JFrame;

public class SinglePlayerWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6678966135048256026L;

	GameStatePanel SPlayPanel;
	
	public SinglePlayerWindow() {
		setSize(900, 700);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setBackground(new Color(0, 128, 128));
		BackGroundPanel bp = new BackGroundPanel();
		add(bp);
		
		
		GameMenuBar menu = new GameMenuBar ();
		setJMenuBar(menu);
				
		SPlayPanel =  new GameStatePanel (1);
		add(SPlayPanel);
	}
	
	
}
