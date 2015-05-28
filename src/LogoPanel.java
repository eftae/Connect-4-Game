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

	private static final long serialVersionUID = 1L;

	private Timer timer;
	private float opacity = 0;

	private ImageIcon logo1 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/welcome2.png"), 800, 267);
	private ImageIcon logo2 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/logo.png"), 500, 150);

	public LogoPanel() {
		timer = new Timer(15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opacity += 0.1f;

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
		g2d.drawImage(logo1.getImage(), -20, 210, null);
		g2d.drawImage(logo2.getImage(), 130, 100, null);
	}

}
