import java.awt.Color;

import javax.swing.JFrame;

public class SinglePlayerWindow extends JFrame {
	
	
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
		
		GameStatePanel SPlayPanel =  new GameStatePanel ();
			
		add(SPlayPanel);
		

	}
}
