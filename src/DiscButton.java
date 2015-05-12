import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class DiscButton extends JButton implements ActionListener {
	
	private int buttonID;
	
	ImageIcon oldp1 = new ImageIcon("blueDot.jpeg");
	ImageIcon oldp2 = new ImageIcon("yellowDot.jpeg");
	ImageIcon p1 = ResizeImage.changeImage ( oldp1, 125, 110);
	ImageIcon p2 = ResizeImage.changeImage ( oldp2, 125, 110);
	static int value = 1;
	
	public DiscButton(int buttonID) {
		 
		this.buttonID = buttonID;
		 this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		value++;
		int setValue = value%2;
		switch (setValue) {
		case 0:
			setIcon(p1);
			break;
		case 1:
			setIcon (p2);
			break;
		}	
	}
	
	public int generateCol () {
		return buttonID % 7;
	}
}
