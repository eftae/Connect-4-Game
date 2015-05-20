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
	private Connect4 mainFrame;
	private GameEngine gameEngine;

	Player player1;
	Player player2;

	ArrayList<JButton> buttons = new ArrayList<JButton>();
	ImageIcon icn1 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/redDot.png"), 100, 100);
	ImageIcon icn2 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/yellowDot.png"), 100, 100);
	ImageIcon whiteDisc = ResizeImage.changeImage(new ImageIcon(
			"src/pics/whiteDot.png"), 100, 100);
	ImageIcon arrow1 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/redarrow.png"), 100, 100);
	ImageIcon arrow2 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/yellowarrow.png"), 100, 100);

	public GameBoardPanel(int playMode, PlayerWindow playerWindow,
			Connect4 mainFrame) {

		this.playMode = playMode;
		this.playerWindow = playerWindow;
		this.mainFrame = mainFrame;

		// setup the game panel
		Color bkgdColor = new Color(27, 120, 236);
		setOpaque(true);
		setBackground(bkgdColor);
		setLayout(new GridLayout(6, 7));

		// setup new game
		gameEngine = mainFrame.getGameEngine();
		startNewGame(playMode);
		updateStaticsPanel();

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
			if (playMode != 0) {// not for simulation
				if (gameEngine.getCurrState().getCurrPlayer() instanceof AI) {
					// do not display rollover icon for AI
					b.setRolloverIcon(whiteDisc);
				} else {
					b.setRolloverIcon(arrow2);
					b.setPressedIcon(arrow2);
				}
				b.addActionListener(this);
			}
		}
	}

	private void initSinglePlayerGame() {
		String name1 = null;
		Random rand = new Random();
		int randPlayer = rand.nextInt(2);
		setBorder(new TitledBorder("Single Player Game"));
		name1 = JOptionPane.showInputDialog("Please enter your name: ");
		if (name1.equals("")) {
			name1 = "Player 1";
		}
		if (randPlayer == 0) {
			player1 = new User(name1);
			player2 = new AI("BOT", 2);
		} else {
			player1 = new AI("BOT", 2);
			player2 = new User(name1);
		}
		JOptionPane.showMessageDialog(null, player1.getName() + " goes first.");
		gameEngine.startNewGame(player1, player2, this);
	}

	private void initDoublePlayersGame() {
		String name1 = null;
		String name2 = null;
		Random rand = new Random();
		int randPlayer = rand.nextInt(2);
		setBorder(new TitledBorder("Double Players Game"));
		name1 = JOptionPane
				.showInputDialog("Please enter your name for player 1: ");
		name2 = JOptionPane
				.showInputDialog("Please enter your name for player 2: ");
		if (name1.equals("")) {
			name1 = "Player 1";
		}
		if (name2.equals("")) {
			name2 = "Player 2";
		}
		if (randPlayer == 0) {
			player1 = new User(name1);
			player2 = new User(name2);
		} else {
			player1 = new User(name2);
			player2 = new User(name1);
		}
		JOptionPane.showMessageDialog(null, player1.getName() + " goes first.");
		gameEngine.startNewGame(player1, player2, this);
	}

	private void initSimulationGame() {
		setBorder(new TitledBorder("Simulation"));
		player1 = new AI("BOT A", -1);
		player2 = new AI("BOT B", -1);
		gameEngine.startNewGame(player1, player2, this);
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
					ButtonSound.music("src/sound/button.wav");
					return;
				}
			}
		}
		// Todo: play invalid effect
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
		ImageIcon icn = null;
		if (gameEngine.getCurrPlayerIndex() == 1)
			icn = ResizeImage.changeImage(icn2, 50, 50);
		else
			icn = ResizeImage.changeImage(icn1, 50, 50);

		if (playerWindow != null && playerWindow.getStaticsPanel() != null)
			playerWindow.getStaticsPanel().setWhosTurn(
					gameEngine.getCurrPlayer(), icn);
	}

	public void displayEndGame(Player winner) {
		if (playMode == 0)
			return;
		if (winner != null) {
			JOptionPane.showMessageDialog(null, winner.getName() + " win");
		} else {
			JOptionPane.showMessageDialog(null, "Board Full, Game Over");
		}

		// Todo: use restart button or return home button instead
		// just call the corresponding init function
		// how to clear the discs?

		playerWindow.setVisible(false);
		playerWindow.dispose();
		mainFrame.setVisity(true);

	}

	public void startNewGame(int playMode) {
		// clear buttons icons
		for (JButton b : buttons) {
			b.setIcon(whiteDisc);
			b.setPressedIcon(whiteDisc);
			if (playMode != 0) {
				b.setRolloverIcon(arrow2);
				b.setPressedIcon(arrow2);
				b.addActionListener(this);
			}
		}

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
	}

}
