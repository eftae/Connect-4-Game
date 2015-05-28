/**
 * A glass panel for displaying game logo and welcome. With fading effect.
 */

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LogoPanel extends JPanel {

	Timer timer;
	float opacity = 0;

	// images
	ImageIcon icon1 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/welcome2.png"), 800, 267);
//	ImageIcon icon2 = ResizeImage.changeImage(
//			new ImageIcon("src/pics/logo.png"), 500, 150);
	ImageIcon icon2 = ResizeImage.changeImage(
			new ImageIcon("src/pics/logo1.png"), 450, 98);


	public LogoPanel() {
		timer = new Timer(15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opacity += 0.01f;
				if (opacity > 1f) {
					opacity = 1f;
					timer.stop();
				} else {
					repaint();
				}
			}
		});
		timer.start();
	}

	/**
	 * Paint the graphics.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				opacity));
		g2d.drawImage(icon1.getImage(), -20, 210, null);
//		g2d.drawImage(icon2.getImage(), 130, 100, null);
		g2d.drawImage(icon2.getImage(), 160, 100, null);
	}

}
