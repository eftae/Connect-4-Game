import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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

		// setup discs space
		for (int i = 0; i < 42; i++) {
			JButton b = new JButton();
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.setIcon(whiteDisc);
			buttons.add(b);
			add(b);
			if (playMode != 0)
				b.addActionListener(this);
		}

		// setup new game
		gameEngine = mainFrame.getGameEngine();

		String name1 = null;
		String name2 = null;
		Random rand = new Random();
		int randPlayer = rand.nextInt(2);
		switch (playMode) {
		case 1:
			setBorder(new TitledBorder("Single Player Game"));
			name1 = JOptionPane.showInputDialog("Please enter your name: ");
			if (randPlayer == 0) {
				player1 = new User(name1);
				player2 = new AI("BOT", 1);
			} else {
				player1 = new AI("BOT", 1);
				player2 = new User(name1);
			}
			JOptionPane.showMessageDialog(null, player1.getName()
					+ " goes first.");
			break;
		case 2:
			setBorder(new TitledBorder("Double Players Game"));
			name1 = JOptionPane
					.showInputDialog("Please enter your name for player 1: ");
			name2 = JOptionPane
					.showInputDialog("Please enter your name for player 2: ");
			if (randPlayer == 0) {
				player1 = new User(name1);
				player2 = new User(name2);
			} else {
				player1 = new User(name2);
				player2 = new User(name1);
			}
			JOptionPane.showMessageDialog(null, player1.getName()
					+ " goes first.");
			break;
		default:
			setBorder(new TitledBorder("Simulation"));
			player1 = new AI("BOT A", 2);
			player2 = new AI("BOT B", 2);
		}

		gameEngine.startNewGame(player1, player2, this); // Initialize engine
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pressed = (JButton) e.getSource();
		Player currPlayer = gameEngine.getCurrPlayer();

		if (currPlayer instanceof User && buttons.contains(pressed)) {
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
		if (colorId == 0)
			b.setIcon(icn1);
		else
			b.setIcon(icn2);
	}

	public void displayEndGame(Player winner) {
		if (winner != null) {
			JOptionPane.showMessageDialog(null, winner.getName() + " win");
		} else {
			JOptionPane.showMessageDialog(null, "Board Full, Game Over");
		}

		playerWindow.setVisible(false);
		playerWindow.dispose();
		mainFrame.setVisity(true);

		player1 = new AI("BOT A", 2);
		player2 = new AI("BOT B", 2);

		gameEngine
				.startNewGame(player1, player2, mainFrame.getGameBoardPanel());
	}

}
