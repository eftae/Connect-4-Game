import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A glass panel for displaying credits, with fading effect.
 * The panel contain a image that shows all of the creaters of this game
 */
public class Credits extends JPanel {

	private static final long serialVersionUID = 1L;

	private Timer timer;
	private float opacity;

	private ImageIcon image = ResizeImage.changeImage(new ImageIcon(
			"src/pics/credit.png"), 800, 753);

	/**
	 * Construct the credits class and implement the action listener 
	 * for the timer to run the fade in effect
	 */
	public Credits() {
		timer = new Timer(15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opacity += 0.08f;
				if (opacity > 1f) {
					opacity = 1f;
					timer.stop();
				}
				repaint();
			}
		});
		timer.start();
	}

	@Override
	/**
	 * repaint the panel for the fade in effect.
	 * @param g Grahpics of the panel
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,opacity));
		g2d.drawImage(image.getImage(), -20, -40, null);
	}

}
