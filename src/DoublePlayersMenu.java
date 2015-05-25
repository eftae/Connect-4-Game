/**
 * A glass panel for displaying double players mode menu.
 */

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DoublePlayersMenu extends JPanel {

	private Connect4 mainGame;
	private final float opacity = 0;
	private int AIMode;

	/**
	 * 
	 * @param mainGame
	 */
	public DoublePlayersMenu(final Connect4 mainGame) {

		// set button layout
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.BOTH;

		// user name input
		gc.gridx = 0;
		gc.gridy = 0;
		final JTextField player1NameField = new JTextField("Player Name A", 10);
		add(player1NameField, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		final JTextField player2NameField = new JTextField("Player Name B", 10);
		add(player2NameField, gc);

		// start game button
		gc.gridx = 0;
		gc.gridy = 2;
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
				MenuPanel.closeJOptionPanel();

				if (!mainGame.isMuted()) {
					BackgroundMusic.stopMusic();
					ButtonSound.music("src/sound/button.wav");
					BackgroundMusic
							.music("src/sound/2-03_Two_Rogues_One_Mark.wav");
				}
			}
		});
		add(startGame, gc);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				opacity));

	}

}
