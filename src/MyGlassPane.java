import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.Timer;

public class MyGlassPane extends JPanel implements ActionListener {
	Timer timer = new Timer(10, this);
	float opacity = 0;
	ImageIcon icon1 = ResizeImage.changeImage(new ImageIcon("src/pics/welcome2.png"), 800, 330);
	ImageIcon icon2 = ResizeImage.changeImage(new ImageIcon("src/pics/logo.png"), 400, 100);
	//ImageIcon icon3 = ResizeImage.changeImage(new ImageIcon("src/pics/to1.png"), 400, 200);
	
	public MyGlassPane() {
		timer.start();
	}
	
	/*public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon1 = new ImageIcon("src/pics/welcome2.png");
		ImageIcon newIcon1 = ResizeImage.changeImage(icon1, 800, 350);

		ImageIcon icon2 = new ImageIcon("src/pics/logo.png");
		ImageIcon newIcon2 = ResizeImage.changeImage(icon2, 400, 100);

		ImageIcon icon3 = new ImageIcon("src/pics/to1.png");
		ImageIcon newIcon3 = ResizeImage.changeImage(icon3, 400, 200);

		newIcon1.paintIcon(this, g, -20, 180);
		// newIcon3.paintIcon(this, g, 200, 200);
		newIcon2.paintIcon(this, g, 175, 100);
	}*/
	
	public void paint(Graphics g) {
	    super.paint(g);
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
	    g2d.drawImage(icon1.getImage(), -20, 180, null);
	    g2d.drawImage(icon2.getImage(), 175, 100, null);
	    //g2d.drawImage(icon3.getImage(), 400, 200, null);
	}
	  
	public void actionPerformed(ActionEvent e) {
	    opacity += 0.01f;
	    if (opacity > 1f) {
	    	opacity = 1f;
	    	timer.stop();
	    }
	    repaint();
	} 
}
