

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;


public class Credits extends JPanel implements ActionListener {
	Timer timer = new Timer(15,this);
	float opacity = 0;
	ImageIcon icn = ResizeImage.changeImage(new ImageIcon("src/pics/credit.png"),800,753);

	public Credits() {
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(icn.getImage(), -20, -40, null);
	}

	public void actionPerformed(ActionEvent e) {
		opacity += 0.01f;
		if (opacity > 1f) {
			opacity = 1f;
			timer.stop();
		} else
			repaint();
	}
}
