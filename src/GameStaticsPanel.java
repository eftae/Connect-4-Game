import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class GameStaticsPanel extends JPanel {
	
	public GameStaticsPanel() {
		setBorder(new TitledBorder("Game Statistic"));
		Dimension d = getPreferredSize ();
		d.width = 150;
		setPreferredSize (d);
		setBackground(new Color(0,128,128));
	}

}
