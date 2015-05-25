import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GameStatisticsPanel extends JPanel {

	private GameEngine gameEngine;

	private JLabel player1;
	private JLabel player2;
	private JLabel msg;

	private ImageIcon icn1s = ResizeImage.changeImage(new ImageIcon(
			"src/pics/redDot.png"), 50, 50);
	private ImageIcon icn2s = ResizeImage.changeImage(new ImageIcon(
			"src/pics/yellowDot.png"), 50, 50);
	private ImageIcon icn0s = ResizeImage.changeImage(new ImageIcon(
			"src/pics/whiteDot.png"), 50, 50);

	public GameStatisticsPanel(GameEngine gameEngine) {
		this.gameEngine = gameEngine;

		setBorder(new TitledBorder("Game Statistic"));
		Dimension d = getPreferredSize();
		d.width = 150;
		setPreferredSize(d);
		setBackground(Color.WHITE);
		setLayout(new GridLayout(5, 1));

		// who's turn
		player1 = new JLabel();
		add(player1);
		player2 = new JLabel();
		add(player2);
		msg = new JLabel();
		add(msg);
	}

	public void setPlayerNames(Player p1, Player p2) {
		player1.setText(p1.getName());
		player2.setText(p2.getName());
		msg.setText("Game Started.");
	}

	public void setWhosTurn() {
		if (gameEngine.getCurrPlayerIndex() == 0) {
			player1.setIcon(icn0s);
			player2.setIcon(icn1s);
		} else {
			player1.setIcon(icn2s);
			player2.setIcon(icn0s);
		}

		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void displayEndGame(Player winner) {
		player1.setIcon(icn0s);
		player2.setIcon(icn0s);
		if (winner == null) {
			msg.setText("Board Full. Drew.");
		} else {
			msg.setText(winner.getName() + " won.");		
			
		}
		repaint();
	}
}
