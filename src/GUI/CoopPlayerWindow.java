import java.awt.Color;

import javax.swing.JFrame;

public class CoopPlayerWindow extends JFrame {
	public CoopPlayerWindow() {
		setSize(900, 700);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBackground(new Color(0, 128, 128));
		BackGroundPanel bp = new BackGroundPanel();
		add(bp);
		
		GameMenuBar menu = new GameMenuBar ();
		setJMenuBar(menu);
	}
}
