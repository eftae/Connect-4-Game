import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GameBoardPanel extends JPanel implements ActionListener {

	private final int MAX_TURN = 42;
	private PlayerWindow playerWindow;
	private Connect4 mainGame;

	ArrayList<JButton> buttons = new ArrayList<JButton>();
	ImageIcon icn1 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/blueDot.png"), 100, 100);
	ImageIcon icn2 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/yellowDot.png"), 100, 100);
	
	Image bkgd = new ImageIcon("/src/pics/bkgd.png").getImage();
	

	GameState g;
	Player p1;
	Player p2;
	int gameMode;
	String name1;
	String name2;

	// for GUI player
	int nextMove;

	public GameBoardPanel(int nHumans, PlayerWindow playerWindow,
			Connect4 mainGame) {
		gameMode = nHumans;
		this.playerWindow = playerWindow;
		this.mainGame = mainGame;
		// setup the game panel
		setLayout(new GridLayout(6, 7));
		setBorder(new TitledBorder("Single Player Game"));
		// setBackground(new Color(0, 128, 128));

		// setup every buttons
		for (int i = 0; i < 42; i++) {
			buttons.add(new JButton());
			buttons.get(i).setOpaque(false);
			buttons.get(i).setContentAreaFilled(false);
			buttons.get(i).setBorderPainted(false);
			buttons.get(i).setBackground(Color.WHITE);
			add(buttons.get(i));
			buttons.get(i).addActionListener(this);
		}

		// setup the game
		switch (nHumans) {
		case 1:
			// setup for one player
			p1 = new User();
			p2 = new AI(1);
			gameMode = 2;
			do {
				name1 = JOptionPane.showInputDialog("Please enter your name: ");
			} while (name1.length() == 0);
			JOptionPane.showMessageDialog(null, "Welcome to the Connect 4, "
					+ name1);
			name2 = "Alpha Beta AI";
			break;
		case 2:
			// setup for two player
			p1 = new User();
			p2 = new User();
			gameMode = 1;
			do {
				name1 = JOptionPane
						.showInputDialog("Please enter your name for player 1");
			} while (name1.length() == 0);
			do {
				name2 = JOptionPane
						.showInputDialog("Please enter your name for player 2");
			} while (name2.length() == 0);
			JOptionPane.showMessageDialog(null, "Welcome to the Connect 4, "
					+ name1 + " and " + name2);
			break;
		default:
			// setup for two AI
			p1 = new AI(2);
			p2 = new AI(2);
			gameMode = MAX_TURN;
			name1 = "Alpha Beta AI 1";
			name2 = "Alpha Beta AI 2";
			runGame(playerWindow, mainGame);
		}
		if (nHumans == 2)
			JOptionPane.showMessageDialog(null, name1 + " goes first");
		g = new GameState(p1, p2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println(buttons.indexOf(e.getSource()));
		ButtonSound.music("src/sound/button.wav");
		if (!buttons.contains(e.getSource()))
			return;
		JButton pressed = (JButton) e.getSource();
		nextMove = buttons.indexOf(pressed) % 7;

		runGame(playerWindow, mainGame);
	}

	public void setButton(int btnId, int colorId) {
		JButton b = buttons.get(btnId);
		if (colorId == 0)
			b.setIcon(icn1);
		else
			b.setIcon(icn2);
	}

	public void runGame(PlayerWindow playWindow, Connect4 mainGame) {
		Player currPlayer = g.getCurrPlayer();

		// run game if winner if not defined
		for (int i = 0; i < gameMode; i++) {
			if (g.getTurn() > MAX_TURN) {
				JOptionPane.showMessageDialog(null, "Game Over");
				playerWindow.dispose();
				mainGame.setVisity(true);
			}
			currPlayer = g.getCurrPlayer();

			// get player next move
			if (currPlayer instanceof AI)
				nextMove = currPlayer.decideMove(g);

			if (g.isValidMove(nextMove)) {
				int row = g.runNextMove(nextMove);
				if (currPlayer.equals(p2))
					setButton(colRowToBtnId(nextMove, row), 0);
				else
					setButton(colRowToBtnId(nextMove, row), 1);
			}

			// swap player turn
			g.swapPlayer();

			// update the game state
			g.nTurnPlusPlus();
			g.checkGameEnd();

			// check winner
			Player me = g.getWinner();
			if (me == p1) {
				JOptionPane.showMessageDialog(null, name1 + " win");
				playerWindow.setVisible(false);
				playerWindow.dispose();
				mainGame.setVisity(true);
			} else if (me == p2) {
				JOptionPane.showMessageDialog(null, name2 + " win");
				playerWindow.setVisible(false);
				playerWindow.dispose();
				mainGame.setVisity(true);
			}
		}
		if (gameMode == 1) {
			if (currPlayer.equals(p2))
				playerWindow.setMsg(name1 + "'s Turn");
			else
				playerWindow.setMsg(name2 + "'s Turn");
		}
	}

	// @Override
	// public int decideMove(GameState currState) {
	// return nextMove;
	// }

	private int colRowToBtnId(int col, int row) {
		return (5 - row) * 7 + col;
	}
	
//	@Override
//	protected void paintComponent (Graphics g){
//	      super.paintComponent(g);
//	      
//	      g.drawImage(bkgd,0,0,getWidth(),getHeight(),null);
//	      g.dispose();
//	}
}
