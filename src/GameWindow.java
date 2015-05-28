/**
 * Class of Game Window. It contains the main game board and a console panel to show the current game information.
 *
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private GameBoardPanel gameBoardPanel;
	private GameStatisticsPanel gameStatisticsPanel;
	private Connect4 mainGame;

	public GameWindow(Connect4 mg, String title) {
		super(title);
		mainGame = mg;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setBounds(mainGame.getMainFrame().getBounds());

		gameStatisticsPanel = new GameStatisticsPanel(this, mainGame);
		gameStatisticsPanel.setPreferredSize(new Dimension(250, 700));
		getContentPane().add(gameStatisticsPanel, BorderLayout.EAST);

		gameBoardPanel = new GameBoardPanel(this, mainGame);
		gameBoardPanel.setPreferredSize(new Dimension(750, 700));
		getContentPane().add(gameBoardPanel, BorderLayout.WEST);

		GameMenuBar menu = new GameMenuBar(mainGame, this);
		setJMenuBar(menu);

		pack();
	}

	/**
	 * Getter for statistic panel.
	 * 
	 * @return gameStatisticsPanel
	 */
	public GameStatisticsPanel getStatisticsPanel() {
		return gameStatisticsPanel;
	}

	/**
	 * Getter for gameBoardPanel.
	 * 
	 * @return gameBoardPanel
	 */
	public GameBoardPanel getGameBoardPanel() {
		return gameBoardPanel;
	}

	/**
	 * Call the function of initializing single player game.
	 * 
	 * @param playerName
	 *            user name
	 * @param modeAI
	 *            difficulty of the AI
	 */
	public void startSinglePlayerGame(String playerName, int modeAI) {
		gameBoardPanel.initSinglePlayerGame(playerName, modeAI);
	}

	/**
	 * Call the function of initializing double players game.
	 * 
	 * @param playerName
	 *            first user name
	 * @param playerName2
	 *            second user name
	 */
	public void startDoublePlayersGame(String playerName, String playerName2) {
		gameBoardPanel.initDoublePlayersGame(playerName, playerName2);
	}

}
