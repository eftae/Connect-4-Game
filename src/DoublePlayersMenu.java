import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DoublePlayersMenu extends JPanel {
	private Connect4 mainGame;
	private final float opacity = 0.3f;
	private int AIMode;

	public DoublePlayersMenu(Connect4 mainGame) {
		// user name input
		JTextField player1NameField = new JTextField("Player Name A", 25);
		add(player1NameField);
		JTextField player2NameField = new JTextField("Player Name B", 25);
		add(player2NameField);

		// start game button
		JButton startGame = new JButton("Start Game");
		startGame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GameWindow doublePlayersWindow = new GameWindow(mainGame,
						"Double Players Game");
				doublePlayersWindow.startDoublePlayersGame(
						player1NameField.getText(), player2NameField.getText());
				mainGame.changeGlassPane(-1);
				doublePlayersWindow.setVisible(true);
				mainGame.setVisity(false);
			}
		});
		add(startGame);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				opacity));

	}

}
