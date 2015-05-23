import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GameStatisticsPanel extends JPanel {

	private JLabel turns;
	private JLabel winMsg;

	public GameStatisticsPanel() {
		setBorder(new TitledBorder("Game Statistic"));
		Dimension d = getPreferredSize();
		d.width = 150;
		setPreferredSize(d);
		setBackground(Color.WHITE);
		setLayout(new GridLayout(5, 1));

		// who's turn
		turns = new JLabel("Who's Turn");
		add(turns);
		winMsg = new JLabel("Game started.");
		add(winMsg);
	}

	public void setWhosTurn(Player p, ImageIcon icn) {
		turns.setText(p.getName() + "'s Turn");
		turns.setIcon(icn);
		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void displayEndGame(Player winner) {
		if (winner == null) {
			winMsg.setText("Board Full. Drew.");
		} else {
			winMsg.setText(winner.getName() + " won.");
		}
		repaint();
	}
}
