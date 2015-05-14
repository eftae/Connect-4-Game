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
		setBorder(new TitledBorder("Single Player Game"));

		// setup discs space
		for (int i = 0; i < 42; i++) {
			JButton b = new JButton();
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.setIcon(whiteDisc);
			buttons.add(b);
			add(b);
			b.addActionListener(this);
		}

		// setup new game
		gameEngine = mainFrame.getGameEngine();

		String player1Name = null;
		String player2Name = null;
		switch (playMode) {
		case 1:
			// setup for one player
			player1Name = JOptionPane
					.showInputDialog("Please enter your name: ");
			player1 = new User(player1Name);
			player2 = new AI("BOT", 1);
			break;
		case 2:
			// setup for two player
			player1Name = JOptionPane
					.showInputDialog("Please enter your name for player 1");
			player2Name = JOptionPane
					.showInputDialog("Please enter your name for player 2");
			player1 = new User(player1Name);
			player2 = new User(player2Name);
			JOptionPane.showMessageDialog(null, player1Name + " goes first");
			break;
		default:
			// setup for two AI
			player1 = new AI("BOT A", 1);
			player2 = new AI("BOT B", 1);
		}

		// start new game
		gameEngine.startNewGame(player1, player2, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pressed = (JButton) e.getSource();
		Player currPlayer = gameEngine.getCurrPlayer();

		if (currPlayer instanceof User && buttons.contains(pressed)) {
			User currUser = (User) currPlayer;
			if (!currUser.isReady()) {
				GameState gameState = gameEngine.getCurrState();
				int nextMove = buttons.indexOf(pressed) % 7;
				if (gameState.isValidMove(nextMove)) {
					int row = gameState.runNextMove(nextMove);
					currUser.userInputReady(nextMove);
					ButtonSound.music("src/sound/button.wav");
					checkEndGame(gameState);
					return;
				}
			}
		}
		// Todo: play invalid effect
	}

	private void checkEndGame(GameState gameState) {
		gameState.checkGameEnd();
		Player winner = gameState.getWinner();
		if (winner != null) {
			JOptionPane.showMessageDialog(null, winner.getName() + " win");
			playerWindow.setVisible(false);
			playerWindow.dispose();
			mainFrame.setVisity(true);
			return;
		} else if (gameState.getTurn() >= 42) {
			JOptionPane.showMessageDialog(null, "Board Full, Game Over");
			playerWindow.setVisible(false);
			playerWindow.dispose();
			mainFrame.setVisity(true);
		}
	}

	public void setButton(int col, int row, int colorId) {
		JButton b = buttons.get(colRowToBtnId(col, row));
		if (colorId == 0)
			b.setIcon(icn1);
		else
			b.setIcon(icn2);
	}

	private void setButton(int btnId, int colorId) {
		JButton b = buttons.get(btnId);
		if (colorId == 0)
			b.setIcon(icn1);
		else
			b.setIcon(icn2);
	}

	private int colRowToBtnId(int col, int row) {
		return (5 - row) * 7 + col;
	}
}
