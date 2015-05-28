import java.awt.Color;
import java.awt.Dimension;
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

	private GameWindow gameWindow;
	private Connect4 mainGame;
	private GameEngine gameEngine;
	private JPanel finishPanel;

	// game data
	private Player player1;
	private Player player2;
	private int gameMode;
	private ArrayList<JButton> buttons;

	// images
	private ImageIcon icn1 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/redDot.png"), 100, 100);
	private ImageIcon icn2 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/yellowDot.png"), 100, 100);
	private ImageIcon whiteDisc = ResizeImage.changeImage(new ImageIcon(
			"src/pics/whiteDot.png"), 100, 100);
	private ImageIcon arrow1 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/redarrow.png"), 100, 100);
	private ImageIcon arrow2 = ResizeImage.changeImage(new ImageIcon(
			"src/pics/yellowarrow.png"), 100, 100);

	/**
	 * 
	 * @param gameWindow
	 * @param mainGame
	 */
	public GameBoardPanel(GameWindow gameWindow, Connect4 mainGame) {

		this.gameWindow = gameWindow;
		this.mainGame = mainGame;
		gameEngine = mainGame.getGameEngine();
		buttons = new ArrayList<JButton>();

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
	}

	/**
	 * actions to do when discs clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getModifiers() != 16)
			return;
		JButton pressed = (JButton) e.getSource();
		Player currPlayer = gameEngine.getCurrPlayer();

		if (currPlayer instanceof User) {
			User currUser = (User) currPlayer;
			if (!currUser.isReady()) {
				int nextMove = buttons.indexOf(pressed) % 7;
				if (gameEngine.isValidMove(nextMove)) {
					currUser.userInputReady(nextMove);
					if (!mainGame.isMuted())
						ButtonSound.music("src/sound/button.wav");
				}
			}
		}
	}

	/**
	 * display a clicked disc.
	 * 
	 * @param col
	 * @param row
	 * @param colorId
	 *            player index
	 */
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

		// swap RolloverIcon icons every turn
		if (gameMode != 0)
			for (JButton btn : buttons) {
				if (btn.getIcon() == whiteDisc) {
					if (gameEngine.getCurrState().getOtherPlayer() instanceof AI) {
						// do not display RolloverIcon icon for AI
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

	/**
	 * Update the turn information in the statistic panel.
	 */
	public void updateStatisticsPanel() {
		if (gameWindow != null && gameWindow.getStatisticsPanel() != null) {
			gameWindow.getStatisticsPanel().setWhosTurn();
		}
	}

	/**
	 * Display after game ended.
	 * 
	 * @param winner
	 */
	public void displayEndGame(Player winner, ArrayList<Integer> winDiscs) {
		if (gameMode == 0)
			return; // stimulation

		ImageIcon redEndIcn = ResizeImage.changeImage(new ImageIcon(
				"src/pics/redGlow.png"), 100, 100);
		ImageIcon yellowEndIcn = ResizeImage.changeImage(new ImageIcon(
				"src/pics/yellowGlow.png"), 100, 100);
		ImageIcon redWinIcn = ResizeImage.changeImage(new ImageIcon(
				"src/pics/redWin.png"), 100, 100);
		ImageIcon yellowWInIcn = ResizeImage.changeImage(new ImageIcon(
				"src/pics/yellowWin.png"), 100, 100);

		for (JButton btn : buttons) {
			btn.removeActionListener(this);
			if (btn.getIcon() == whiteDisc) {
				btn.setRolloverIcon(null);
				btn.setPressedIcon(whiteDisc);
			} else if (btn.getIcon() == icn1) {
				btn.setIcon(redEndIcn); // red dots end
				btn.setPressedIcon(redEndIcn);
			} else {
				btn.setIcon(yellowEndIcn); // yellow dots end
				btn.setPressedIcon(yellowEndIcn);
			}

			// glow effects
			if (winDiscs != null) {
				for (int i = 0; i < winDiscs.size() / 2; i++) {
					int btnID = (5 - winDiscs.get(2 * i)) * 7
							+ winDiscs.get(2 * i + 1);
					if (btn.equals(buttons.get(btnID))) {
						if (gameEngine.getCurrPlayerIndex() == 0) {
							btn.setIcon(yellowWInIcn); // red dots win
							btn.setPressedIcon(yellowWInIcn);
						} else {
							btn.setIcon(redWinIcn); // yellow dots win
							btn.setPressedIcon(redWinIcn);
						}
						break;
					}
				}
			}
		}

		gameWindow.getStatisticsPanel().displayEndGame(winner);
	}

	/**
	 * Restart a new game inside the game window.
	 */
	public void restartNewGame() {
		mainGame.suspendGame();

		// randomize first player for single player mode
		if (gameMode != 0) {
			if (randPlayer() == 0) {
				Player temp = player1;
				player1 = player2;
				player2 = temp;
			}
			gameWindow.getStatisticsPanel().setPlayerNames(player1, player2);
		}

		startNewGame();
		updateStatisticsPanel();
	}

	/**
	 * Start a new game. Precondition: players are set.
	 */
	public void startNewGame() {

		gameEngine.startNewGame(player1, player2, this);

		// initialize buttons
		for (JButton b : buttons) {
			b.setIcon(whiteDisc);
			if (gameMode != 0) {
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

	}

	/**
	 * initialize simulation between 2 AI
	 */
	public void startSimulationGame() {
		player1 = new AI("BOT A", -1);
		player2 = new AI("BOT B", -1);
		gameMode = 0;
		startNewGame();
	}

	/**
	 * initialize single player mode game
	 * 
	 * @param playerName
	 *            user name
	 * @param AIMode
	 *            AI difficulty
	 */
	public void initSinglePlayerGame(String playerName, int AIMode) {
		if (playerName == null || playerName.equals("")) {
			playerName = "YOU";
		}

		String nameAI = null;
		switch (AIMode) {
		case 0:
			nameAI = "Woody";
			break;
		case 1:
			nameAI = "Tony Stark";
			break;
		case 2:
			nameAI = "Thunder God";
			break;
		default:
			nameAI = "BOT";
		}

		if (randPlayer() == 0) {
			player1 = new User(playerName);
			player2 = new AI(nameAI, AIMode);
		} else {
			player1 = new AI(nameAI, AIMode);
			player2 = new User(playerName);
		}
		gameMode = 1;
		gameWindow.getStatisticsPanel().setPlayerNames(player1, player2);
		startNewGame();
		updateStatisticsPanel();
	}

	/**
	 * initialize double players mode game.
	 * 
	 * @param name1
	 *            first player name
	 * @param name2
	 *            second player name
	 */
	public void initDoublePlayersGame(String name1, String name2) {
		// set default name if input is empty
		if (name1 == null || name1.equals("")) {
			name1 = "Player Name A";
		}
		if (name2 == null || name2.equals("")) {
			name2 = "Player Name B";
		}

		// randomize user play order
		if (randPlayer() == 0) {
			player1 = new User(name1);
			player2 = new User(name2);
		} else {
			player1 = new User(name2);
			player2 = new User(name1);
		}

		gameMode = 2;
		gameWindow.getStatisticsPanel().setPlayerNames(player1, player2);
		startNewGame();
		updateStatisticsPanel();
	}

	/**
	 * 
	 * @return random return 0 or 1 with same probability
	 */
	private int randPlayer() {
		Random rand = new Random();
		return rand.nextInt(2);
	}
}
