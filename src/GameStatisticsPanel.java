import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class GameStatisticsPanel extends JPanel {

	private GameWindow gameWindow;
	private Connect4 mainGame;
	private GameEngine gameEngine;

	private JLabel player1;
	private JLabel player2;
	private JLabel msg;
	private JLabel modeLabel;

	private ImageIcon icn1s = ResizeImage.changeImage(new ImageIcon(
			"src/pics/redDot.png"), 50, 50);
	private ImageIcon icn2s = ResizeImage.changeImage(new ImageIcon(
			"src/pics/yellowDot.png"), 50, 50);
	private ImageIcon icn0s = ResizeImage.changeImage(new ImageIcon(
			"src/pics/whiteDot.png"), 50, 50);
	private ImageIcon icon5 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/Baby2.png"), 100, 100);
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
	ImageIcon icon4 = new ImageIcon("src/pics/Restart-50.png");
	ImageIcon modeIcon;

	public GameStatisticsPanel(GameWindow gameWindow, Connect4 mainGame) {

		this.mainGame = mainGame;
		this.gameWindow = gameWindow;
		gameEngine = mainGame.getGameEngine();

		Font defaultFont = new Font("Arial", Font.BOLD, 20);

		setBorder(new TitledBorder("Game Statistic"));
		Dimension d = getPreferredSize();
		d.width = 150;
		setPreferredSize(d);
		setBackground(Color.LIGHT_GRAY);

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

		String modeName = null;

		if (SinglePlayerMenu.AIMode == 0
				&& DoublePlayersMenu.AIModeDouble == false) {
			modeLabel.setIcon(icon5);
			modeName = "Challenge Baby Boy!";
		} else if (SinglePlayerMenu.AIMode == 1
				&& DoublePlayersMenu.AIModeDouble == false) {
			modeLabel.setIcon(icon6);
			modeName = "Challenge Tony Stark!";
		} else if (SinglePlayerMenu.AIMode == 2
				&& DoublePlayersMenu.AIModeDouble == false) {
			modeLabel.setIcon(icon7);
			modeName = "Challenge Thunder God!";
		} else {
			modeLabel.setIcon(icon8);
			modeName = "Fight to death !";
			DoublePlayersMenu.AIModeDouble = false;
		}

		gc.gridy = 4;
		add(modeLabel, gc);
		JLabel modeDescription = new JLabel(modeName);
		gc.gridy = 5;
		add(modeDescription, gc);

		JButton restartGameButton = new JButton("Restart Game");
		restartGameButton.setFont(defaultFont);
		restartGameButton.setIcon(icon4);
		// restartGameButton.setBackground(Color.PINK);
		restartGameButton.setPreferredSize(new Dimension(220, 100));
		restartGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				getGameWindow().getGameBoardPanel().restartNewGame();
			}
		});
		gc.gridy = 6;
		add(restartGameButton, gc);

	}

	public void setPlayerNames(Player p1, Player p2) {
		player1.setText(p1.getName());
		player2.setText(p2.getName());
		msg.setText("Game Started.");
		msg.setForeground(Color.BLUE);
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
		if (gameEngine.getCurrPlayerIndex() == 0) {
			player1.setIcon(yellowWinIcn);
			player2.setIcon(icn0s);
		} else {
			player1.setIcon(icn0s);
			player2.setIcon(redWinIcn);
		}
		if (winner == null) {
			msg.setText("Board Full. Drew.");
		} else {
			msg.setText(winner.getName() + " won.");
		}
		msg.setForeground(Color.YELLOW);
		repaint();
	}

	public GameWindow getGameWindow() {
		return gameWindow;
	}

	public void setGameWindow(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
	}

	public Connect4 getMainGame() {
		return mainGame;
	}

	public void setMainGame(Connect4 mainGame) {
		this.mainGame = mainGame;
	}
}
