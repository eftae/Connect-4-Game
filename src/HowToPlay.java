import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class HowToPlay extends JPanel {
	Timer timer;
	float opacity = 0;

	// images
	ImageIcon icn = ResizeImage.changeImage(
			new ImageIcon("src/pics/htp.png"), 770, 815);

	public HowToPlay() {
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

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(icn.getImage(), -20, -80, null);
	}

}
