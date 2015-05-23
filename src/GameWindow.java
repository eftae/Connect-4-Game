import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class GameWindow extends JFrame {

	private GameBoardPanel gameBoardPanel;
	private GameStatisticsPanel gameStatisticsPanel;
	private Connect4 mainGame;

	Player player1;
	Player player2;

	public GameWindow(Connect4 mainGame, String title) {
		super(title);
		this.mainGame = mainGame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setBounds(mainGame.getMainFrame().getBounds());

		gameStatisticsPanel = new GameStatisticsPanel(mainGame.getGameEngine());
		gameStatisticsPanel.setPreferredSize(new Dimension(250, 700));
		getContentPane().add(gameStatisticsPanel, BorderLayout.EAST);

		gameBoardPanel = new GameBoardPanel(this, mainGame);
		gameBoardPanel.setPreferredSize(new Dimension(750, 700));
		getContentPane().add(gameBoardPanel, BorderLayout.WEST);

		GameMenuBar menu = new GameMenuBar(mainGame, this);
		setJMenuBar(menu);
	}

	public GameStatisticsPanel getStatisticsPanel() {
		return gameStatisticsPanel;
	}

	public GameBoardPanel getGameBoardPanel() {
		return gameBoardPanel;
	}

	public void startSinglePlayerGame(String playerName, int modeAI) {
		gameBoardPanel.initSinglePlayerGame(playerName, modeAI);
	}

	public void startDoublePlayersGame(String playerName, String playerName2) {
		gameBoardPanel.initDoublePlayersGame(playerName, playerName2);
	}
}
