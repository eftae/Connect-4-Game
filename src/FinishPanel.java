import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FinishPanel extends JPanel {
	private Player p;
	private int mode;
	private Connect4 mg;
	private GameWindow gw;
	ImageIcon icon = ResizeImage.changeImage(new ImageIcon(
			"src/pics/welcome2.png"), 800, 300);
	JButton btn = new JButton("Finish");
	Font defaultFont = new Font("Arial", Font.PLAIN, 20);

	public FinishPanel(Connect4 mainGame, GameWindow gameWindow, Player winner, int gameMode) {
		mg = mainGame;
		gw = gameWindow;
		p = winner;
		mode = gameMode;
		icon = new ImageIcon();
		btn.setFont(defaultFont);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				getMainGame().suspendGame();
				try {
					Thread.sleep(400);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				getMainGame().setVisity(true);
				getMainGame().changeGlassPane(0);
				getGameWindow().setVisible(false);
				getGameWindow().dispose();
			}
		});
		add(btn);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(icon.getImage(), 175, 100, null);
		//g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				//opacity));
		//g2d.drawImage(icon1.getImage(), -20, 180, null);
		//g2d.drawImage(icon2.getImage(), 175, 100, null);
		// g2d.drawImage(icon3.getImage(), 400, 200, null);
	}
	
	public ImageIcon getImage() {
		ImageIcon img = null;
		if ((this.mode == 1) && (p instanceof AI)) {
			img = ResizeImage.changeImage(new ImageIcon("src/pics/lose.gif"), 800, 300);
		} else {
			img = ResizeImage.changeImage(new ImageIcon("src/pics/win.gif"), 800, 300);
		}
		return img;
	}
	
	public Connect4 getMainGame() {
		return this.mg;
	}
	
	public GameWindow getGameWindow() {
		return this.gw;
	}
}
