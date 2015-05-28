/**
 * A glass panel for displaying double players mode menu.
 */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DoublePlayersMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	private Connect4 mainGame;

	/**
	 * 
	 * @param mainGame
	 */
	public DoublePlayersMenu(Connect4 mg) {
		mainGame = mg;

		// set button layout
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.BOTH;

		// user name input
		gc.gridx = 0;
		gc.gridy = 0;
		JLabel username = new JLabel("Player Name A:  ");
		add(username, gc);
		gc.gridx = 1;
		gc.gridy = 0;
		final JTextField player1NameField = new JTextField("", 10);
		add(player1NameField, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		JLabel username2 = new JLabel("Player Name B:  ");
		add(username2, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		final JTextField player2NameField = new JTextField("", 10);
		add(player2NameField, gc);

		// start game button
		gc.gridx = 1;
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

}
