import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GameBoardPanel extends JPanel implements ActionListener {

	private int playMode;
	private PlayerWindow playerWindow;
	private Connect4 mainGame;
	private GameEngine gameEngine;

	Player player1;
	Player player2;
	ArrayList<JButton> buttons = new ArrayList<JButton>();

	ImageIcon icn1 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/redDot.png"), 100, 100);
	ImageIcon icn2 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/yellowDot.png"), 100, 100);
	ImageIcon icn1s = ResizeImage.changeImage(new ImageIcon(
			"src/pics/redDot.png"), 50, 50);
	ImageIcon icn2s = ResizeImage.changeImage(new ImageIcon(
			"src/pics/yellowDot.png"), 50, 50);
	ImageIcon whiteDisc = ResizeImage.changeImage(new ImageIcon(
			"src/pics/whiteDot.png"), 100, 100);
	ImageIcon arrow1 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/redarrow.png"), 100, 100);
	ImageIcon arrow2 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/yellowarrow.png"), 100, 100);

	public GameBoardPanel(int playMode, PlayerWindow playerWindow,
			Connect4 mainGame) {

		this.playMode = playMode;
		this.playerWindow = playerWindow;
		this.mainGame = mainGame;

		// setup the game panel
		Color bkgdColor = new Color(27, 120, 236);
		setOpaque(true);
		setBackground(bkgdColor);
		setLayout(new GridLayout(6, 7));

		// setup discs space
		for (int i = 0; i < 42; i++) {
			JButton b = new JButton();
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setFocusPainted(false);
			b.setBorderPainted(false);
			b.setIcon(whiteDisc);
			b.setPressedIcon(whiteDisc);
			buttons.add(b);
			add(b);
		}

		// setup new game
		gameEngine = mainGame.getGameEngine();
		startNewGame(playMode);
	}

	private void initSinglePlayerGame() {
		String name1 = null;
		setBorder(new TitledBorder("Single Player Game"));
		name1 = JOptionPane.showInputDialog("Please enter your name: ");
		if (name1 != null && name1.equals("")) {
			name1 = "Player 1";
		}
		if (randPlayer() == 0) {
			player1 = new User(name1);
			player2 = new AI("BOT", 2);
		} else {
			player1 = new AI("BOT", 2);
			player2 = new User(name1);
		}
		// JOptionPane.showMessageDialog(null, player1.getName() +
		// " goes first.");
	}

	private void initDoublePlayersGame() {
		String name1 = null;
		String name2 = null;

		setBorder(new TitledBorder("Double Players Game"));
		name1 = JOptionPane
				.showInputDialog("Please enter your name for player 1: ");
		name2 = JOptionPane
				.showInputDialog("Please enter your name for player 2: ");
		if (name1 != null && name1.equals("")) {
			name1 = "Player 1";
		}
		if (name2 != null && name2.equals("")) {
			name2 = "Player 2";
		}
		if (randPlayer() == 0) {
			player1 = new User(name1);
			player2 = new User(name2);
		} else {
			player1 = new User(name2);
			player2 = new User(name1);
		}
		// JOptionPane.showMessageDialog(null, player1.getName() +
		// " goes first.");
	}

	private int randPlayer() {
		Random rand = new Random();
		return rand.nextInt(2);
	}

	private void initSimulationGame() {
		setBorder(new TitledBorder("Simulation"));
		player1 = new AI("BOT A", -1);
		player2 = new AI("BOT B", -1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pressed = (JButton) e.getSource();
		Player currPlayer = gameEngine.getCurrPlayer();

		if (currPlayer instanceof User) {
			User currUser = (User) currPlayer;
			if (!currUser.isReady()) {
				int nextMove = buttons.indexOf(pressed) % 7;
				if (gameEngine.isValidMove(nextMove)) {
					currUser.userInputReady(nextMove);
					if (!MenuPanel.isMute)
						ButtonSound.music("src/sound/button.wav");
					return;
				}
			}
		}
	}

	public void displayDisc(int col, int row, int colorId) {
		int btnID = (5 - row) * 7 + col;
		JButton b = buttons.get(btnID);
		ImageIcon icn = null;
		ImageIcon arrow = null;
		if (colorId == 0) {
			icn = icn1;
			arrow = arrow2;
		} else {
			icn = icn2;
			arrow = arrow1;
		}

		// change rollover icon
		if (b.getRolloverIcon() != null)
			for (JButton btn : buttons) {
				if (btn.getIcon() == whiteDisc) {
					if (gameEngine.getCurrState().getOtherPlayer() instanceof AI) {
						// do not display rollover icon for AI
						btn.setRolloverIcon(whiteDisc);
					} else {
						btn.setRolloverIcon(arrow);
						btn.setPressedIcon(arrow);
					}
				}
			}

		// pressed button settings
		b.setIcon(icn);
		b.setPressedIcon(icn);
		b.removeActionListener(this);
		b.setRolloverIcon(null);
	}

	public void updateStaticsPanel() {
		if (playerWindow != null && playerWindow.getStaticsPanel() != null) {
			ImageIcon icn = null;
			if (gameEngine.getCurrPlayerIndex() == 1)
				icn = icn2s;
			else
				icn = icn1s;

			playerWindow.getStaticsPanel().setWhosTurn(
					gameEngine.getCurrPlayer(), icn);
		}
	}

	public void displayEndGame(Player winner) {
		if (playMode == 0)
			return;

		if (winner != null) {
			JOptionPane.showMessageDialog(null, winner.getName() + " win");
		} else {
			JOptionPane.showMessageDialog(null, "Board Full, Game Over");
		}

		playerWindow.setVisible(false);
		playerWindow.dispose();
		mainGame.setVisity(true);
	}

	public void startNewGame(int playMode) {

		switch (playMode) {
		case 0:
			initSimulationGame();
			break;
		case 1:
			initSinglePlayerGame();
			break;
		case 2:
			initDoublePlayersGame();
			break;
		default:
			initSimulationGame();
		}
		gameEngine.startNewGame(player1, player2, this);

		// clear/initialize buttons icons
		for (JButton b : buttons) {
			b.setIcon(whiteDisc);
			if (playMode != 0) {
				if (gameEngine.getCurrPlayer() instanceof AI) {
					// do not display rollover icon for AI
					b.setRolloverIcon(whiteDisc);
					b.setPressedIcon(whiteDisc);
				} else {
					b.setRolloverIcon(arrow2);
					b.setPressedIcon(arrow2);
				}
				b.addActionListener(this);
			} else {
				b.setPressedIcon(whiteDisc);
			}
		}

		updateStaticsPanel();
	}

}
