import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LogoPanel extends JPanel implements ActionListener {
	Timer timer = new Timer(15, this);
	float opacity = 0;
	ImageIcon icon1 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/welcome2.png"), 800, 300);
	ImageIcon icon2 = ResizeImage.changeImage(
			new ImageIcon("src/pics/logo.png"), 400, 90);

	// ImageIcon icon3 = ResizeImage.changeImage(new
	// ImageIcon("src/pics/to1.png"), 400, 200);

	public LogoPanel() {
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				opacity));
		g2d.drawImage(icon1.getImage(), -20, 180, null);
		g2d.drawImage(icon2.getImage(), 175, 100, null);
		// g2d.drawImage(icon3.getImage(), 400, 200, null);
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
