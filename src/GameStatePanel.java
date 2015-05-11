import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class GameStatePanel extends JPanel {
	
	DiscButton buttons [] = new DiscButton [42];
	
	
	public GameStatePanel() {
		
		setLayout(new GridLayout(6,7));
		setBorder(new TitledBorder("Single Play Game"));
		//setBackground(new Color(0, 128, 128));
	
		
		for (int i = 0; i< 42; i++) {
			
			buttons[i] = new DiscButton (i);
			buttons[i].setBackground(Color.WHITE);
			add(buttons[i]);
		}
			
		
		
		
		
	}

}
