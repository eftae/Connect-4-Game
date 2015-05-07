import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DetailsPanel detailsPanel;

	public MainFrame() {
		super("Welcome to Connect4!");
		setSize(900,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new JLabel(new ImageIcon("/Users/jiangkanpan/Desktop/pic.png")));
	
		DetailsPanel panel =new DetailsPanel();
		
		add(panel,BorderLayout.WEST);
	
	}
	
	// setMusic 

}
