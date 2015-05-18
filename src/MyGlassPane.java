import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyGlassPane extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon1 = new ImageIcon("src/pics/welcome.png");
		ImageIcon newIcon1 = ResizeImage.changeImage(icon1, 600, 160);

		ImageIcon icon2 = new ImageIcon("src/pics/logo.png");
		ImageIcon newIcon2 = ResizeImage.changeImage(icon2, 400, 100);

		ImageIcon icon3 = new ImageIcon("src/pics/to1.png");
		ImageIcon newIcon3 = ResizeImage.changeImage(icon3, 400, 200);

		newIcon1.paintIcon(this, g, 80, 260);
		// newIcon3.paintIcon(this, g, 200, 200);
		newIcon2.paintIcon(this, g, 175, 100);

	}
}
