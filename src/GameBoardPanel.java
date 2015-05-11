import java.awt.Color;
import java.awt.GridLayout;
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

	ArrayList<JButton> buttons = new ArrayList<JButton>();
	ImageIcon icn1 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/blueDot.jpeg"), 50, 50);
	ImageIcon icn2 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/yellowDot.jpeg"), 50, 50);

	GameState g;
	Player p1;
	Player p2;
	int gameMode;

	// for GUI player
	int nextMove;

	public GameBoardPanel(int nHumans) {
		gameMode = nHumans;

		// setup the game panel
		setLayout(new GridLayout(6, 7));
		setBorder(new TitledBorder("Single Player Game"));
		// setBackground(new Color(0, 128, 128));

		// setup every buttons
		for (int i = 0; i < 42; i++) {
			buttons.add(new JButton());
			buttons.get(i).setBackground(Color.WHITE);
			add(buttons.get(i));
			buttons.get(i).addActionListener(this);
		}

		// setup the game
		switch (nHumans) {
		case 1:
			// setup for one player
			p1 = new User();
			p2 = new AIAlphaBeta(0);
			gameMode = 2;
			break;
		case 2:
			// setup for two player
			p1 = new User();
			p2 = new User();
			gameMode = 1;
			break;
		default:
			// setup for two AI
			p1 = new AIAlphaBeta(0);
			p2 = new AIAlphaBeta(0);
			gameMode = MAX_TURN;
			runGame();
		}

		g = new GameState(p1, p2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println(buttons.indexOf(e.getSource()));
		if (!buttons.contains(e.getSource()))
			return;
		JButton pressed = (JButton) e.getSource();
		nextMove = buttons.indexOf(pressed) % 7;

		runGame();
	}

	public void setButton(int btnId, int colorId) {
		JButton b = buttons.get(btnId);
		if (colorId == 0)
			b.setIcon(icn1);
		else
			b.setIcon(icn2);
	}

	public void runGame() {
		// run game if winner if not defined
		for (int i = 0; i < gameMode; i++) {
			if (g.getTurn() > MAX_TURN)
				return;
			Player currPlayer = g.getCurrPlayer();

			// get player next move
			if (currPlayer instanceof AI || currPlayer instanceof AIAlphaBeta)
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
				JOptionPane.showMessageDialog(null, "Player 1 win");
				System.exit(0);
			} else if (me == p2) {
				JOptionPane.showMessageDialog(null, "Player 2 (AI) win");
				System.exit(0);
			}
		}
		// no winner
		// System.out.println("Board Full, Game Over");
	}

	// @Override
	// public int decideMove(GameState currState) {
	// return nextMove;
	// }

	private int colRowToBtnId(int col, int row) {
		return (5 - row) * 7 + col;
	}
}
