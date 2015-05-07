import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;

public class DetailsPanel extends JPanel {

	protected Window mainFrame;

	public DetailsPanel() {
		
		setLayout(new GridBagLayout());
		setBorder(new TitledBorder("New Game"));

		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0.5;
		gc.weighty = 1;
		gc.fill =GridBagConstraints.BOTH;
		

		gc.gridx = 0;
		gc.gridy = 0;
		JButton button1 = new JButton ("Singer Player");
		button1.setFont(new Font("Arial", Font.PLAIN, 20));
		
		add(button1,gc);
		button1.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				SinglePlayerWindow spw = new SinglePlayerWindow ();
				
				JOptionPane.showMessageDialog(null, "Game Start!");
			}
			
		});
		
		
		gc.gridx = 0;
		gc.gridy = 1;
		JButton button2 = new JButton ("Co-op Player");
		button2.setFont(new Font("Arial", Font.PLAIN, 20));
		add(button2, gc);
		button2.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				CoopPlayerWindow spw = new CoopPlayerWindow ();	
				JOptionPane.showMessageDialog(null, "Game Start!");
			}
			
		});
		
		gc.gridx = 0;
		gc.gridy = 3;
		JButton button3 = new JButton("Check High Score");
		button3.setFont(new Font("Arial", Font.PLAIN, 20));
		add(button3, gc);
		button3.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				HighScores spw = new HighScores ();	
				JOptionPane.showMessageDialog(null, "Highest Score: 1 Superman Score:999999");
			}
			
		});
		
		gc.gridx = 0;
		gc.gridy = 4;
		JButton button4 = new JButton("Quit Game");
		button4.setFont(new Font("Arial", Font.PLAIN, 20));
		add(button4,gc);
		button4.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "You exited Game");
				 mainFrame.dispose ();
			}
			
		});
		
		gc.gridx = 0;
		gc.gridy = 5;
		JButton button5 = new JButton("Credits");
		button5.setFont(new Font("Arial", Font.PLAIN, 20));
		add(button5,gc);
		button5.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Wayne\n Kelvin\n,Chris\n,Mick");
				 
			}
			
		});
		
		
		
		
		

	}
	
	
	
	

}
