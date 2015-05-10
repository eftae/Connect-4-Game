import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class GUIConnect4 {

	public static void main(String[] args) {
	 
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame mainFrame = new MainFrame();
		         mainFrame.setVisible(true);
				
			}
		});

	}

}
