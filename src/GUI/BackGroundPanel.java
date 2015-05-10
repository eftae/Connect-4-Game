import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class BackGroundPanel extends JPanel {
	
	public BackGroundPanel() {
		add(new JLabel(new ImageIcon("c4.png")));
		setLayout (new GridBagLayout());
		setBorder(new EmptyBorder(300,300,300,300));
		setBackground(new Color(0,128,128));
		
	}
	

}
