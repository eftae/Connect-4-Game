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
}
