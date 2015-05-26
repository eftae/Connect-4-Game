import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Painter;

public class FinishPanel extends JPanel {
	private Player p;
	private int mode;
	private Connect4 mg;
	private GameWindow gw;
	private ImageIcon icon;
	private JButton btn = new JButton("Finish");
	private Font defaultFont = new Font("Arial", Font.PLAIN, 20);

	public FinishPanel(Connect4 mainGame, GameWindow gameWindow, Player winner, int gameMode) {
		mg = mainGame;
		gw = gameWindow;
		p = winner;
		mode = gameMode;
		icon = this.getImage();
		JLabel iLabel = new JLabel();
		iLabel.setIcon(icon);
		add(iLabel);
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

	/*public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(icon.getImage(), 175, 100, null);
		//g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				//opacity));
		//g2d.drawImage(icon1.getImage(), -20, 180, null);
		//g2d.drawImage(icon2.getImage(), 175, 100, null);
		// g2d.drawImage(icon3.getImage(), 400, 200, null);
	}*/
	
	public ImageIcon getImage() {
		ImageIcon img = null;
		if ((this.mode == 1) && (p instanceof AI)) {
			img = ResizeImage.changeImage(new ImageIcon("src/pics/lose.gif"), 800, 300);
			//System.out.println("src/pics/lose.gif");
		} else {
			img = ResizeImage.changeImage(new ImageIcon("src/pics/win.gif"), 800, 300);
			//System.out.println("src/pics/win.gif");
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
