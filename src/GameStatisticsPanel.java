import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Game Statistics Panel is a panel that will display the current player, 
 * current AI in single player mode and contain a restart button for 
 * user to restart the game.
 */
public class GameStatisticsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private GameWindow gameWindow;
	private Connect4 mainGame;
	private GameEngine gameEngine;

	private JLabel player1;
	private JLabel player2;
	private JLabel msg;
	private JLabel modeLabel;
	private JLabel modeDescription;

	private ImageIcon icn1s = ResizeImage.changeImage(new ImageIcon(
			"src/pics/redDot.png"), 50, 50);
	private ImageIcon icn2s = ResizeImage.changeImage(new ImageIcon(
			"src/pics/yellowDot.png"), 50, 50);
	private ImageIcon icn0s = ResizeImage.changeImage(new ImageIcon(
			"src/pics/whiteDot.png"), 50, 50);
	private ImageIcon icon5 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/1432707696_Woody.png"), 100, 100);
	private ImageIcon icon6 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/Ironman.png"), 100, 100);
	private ImageIcon icon7 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/Thor3.png"), 100, 100);
	private ImageIcon icon8 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/vs.png"), 100, 100);
	private ImageIcon redWinIcn = ResizeImage.changeImage(new ImageIcon(
			"src/pics/redWin.png"), 50, 50);
	private ImageIcon yellowWinIcn = ResizeImage.changeImage(new ImageIcon(
			"src/pics/yellowWin.png"), 50, 50);

	/**
	 * Construct a new game statistics panel with the given game window and 
	 * the main frame Connect4
	 * @param gw the game windows frome 
	 * @param mg the Connect 4 main frmae
	 */
	public GameStatisticsPanel(GameWindow gw, Connect4 mg) {

		mainGame = mg;
		gameWindow = gw;
		gameEngine = mainGame.getGameEngine();

		Font defaultFont = new Font("Arial", Font.BOLD, 20);

		setBackground(Color.WHITE);

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.WEST;

		// who's turn
		player1 = new JLabel();
		add(player1, gc);
		gc.gridy = 2;
		player2 = new JLabel();
		add(player2, gc);

		gc.anchor = GridBagConstraints.SOUTH;
		msg = new JLabel();
		msg.setFont(defaultFont);
		gc.gridy = 3;
		add(msg, gc);

		modeLabel = new JLabel();
		gc.gridy = 4;
		add(modeLabel, gc);

		modeDescription = new JLabel();
		gc.gridy = 5;
		add(modeDescription, gc);

		ImageIcon restartIcn = ResizeImage.changeImage(new ImageIcon(
				"src/pics/restartIcn.png"), 245, 114);
		ImageIcon restartRO = ResizeImage.changeImage(new ImageIcon(
				"src/pics/restartRO.png"), 245, 114);
		ImageIcon restartClick = ResizeImage.changeImage(new ImageIcon(
				"src/pics/restartIcnC.png"), 245, 114);
		JButton restartGameButton = new JButton();
		restartGameButton.setContentAreaFilled(false);
		restartGameButton.setFocusPainted(false);
		restartGameButton.setBorderPainted(false);
		restartGameButton.setIcon(restartIcn);
		restartGameButton.setPressedIcon(restartClick);
		restartGameButton.setRolloverIcon(restartRO);
		restartGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				gameWindow.getGameBoardPanel().restartNewGame();
			}
		});
		gc.gridy = 6;
		add(restartGameButton, gc);

	}

	/**
	 * Setter function to set the player name for 
	 * player 1 and player 2.
	 * @param p1 player 1's name
	 * @param p2 player 2's name
	 */
	public void setPlayerNames(Player p1, Player p2) {
		player1.setText(p1.getName());
		player2.setText(p2.getName());
		msg.setText("Game Started.");
		msg.setForeground(Color.BLUE);
	}

	/**
	 * Set the Avatar icon and the message for the player 
	 * in single player mode.
	 */
	public void setAvatarIcon() {
		if (gameEngine.getGameMode() == 1) {
			if (gameEngine.getAIMode() == 0) {
				modeLabel.setIcon(icon5);
				modeDescription.setText("Challenge to Woddy");
			} else if (gameEngine.getAIMode() == 1) {
				modeLabel.setIcon(icon6);
				modeDescription.setText("Challenge to Tony Stark!");
			} else if (gameEngine.getAIMode() == 2) {
				modeLabel.setIcon(icon7);
				modeDescription.setText("Challenge to Thunder God!");
			}
		} else if (gameEngine.getGameMode() == 2) {
			modeLabel.setIcon(icon8);
			modeDescription.setText("Fight to death!");
		}
	}

	/**
	 * Set the icon for the player to a win icon
	 */
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

	/**
	 * Display the end game message
	 * @param winner the winner player
	 */
	public void displayEndGame(Player winner) {
		if (winner == null) {
			msg.setText("Board Full. Drew.");
			player1.setIcon(icn0s);
			player2.setIcon(icn0s);
		} else {
			msg.setText(winner.getName() + " won.");
			if (gameEngine.getCurrPlayerIndex() == 0) {
				player1.setIcon(yellowWinIcn);
				player2.setIcon(icn0s);
				msg.setForeground(new Color(255, 211, 2));
			} else {
				player1.setIcon(icn0s);
				player2.setIcon(redWinIcn);
				msg.setForeground(Color.RED);
			}
		}
		repaint();
	}

}
