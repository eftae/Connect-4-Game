import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Connect4 implements Runnable {

	private JFrame mainFrame;
	private MenuPanel menuPanel;
	private GameBoardPanel gameBoardPanel;
	private GameEngine gameEngine;
	private JPanel homeGlassPane;
	private static Thread threadGE;
	private static Thread threadGUI;

	// public BackgroundMusic backMusic = new BackgroundMusic();

	public Connect4(GameEngine gameEngine) {
		this.gameEngine = gameEngine;

		// main frame
		mainFrame = new JFrame("Connect 4");
		mainFrame.setSize(1000, 700);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// menu panel
		menuPanel = new MenuPanel(this);
		menuPanel.setPreferredSize(new Dimension(250, 700));

		// game board panel
		gameBoardPanel = new GameBoardPanel(null, this);
		gameBoardPanel.setPreferredSize(new Dimension(750, 700));

		// logo panel
		homeGlassPane = new LogoPanel();
		homeGlassPane.setPreferredSize(new Dimension(750, 700));
	}

	public static void main(String[] args) {
		// game engine thread
		Runnable mainGameEngine = new GameEngine();
		threadGE = new Thread(mainGameEngine);
		GameEngine gameEngine = (GameEngine) mainGameEngine;
		// GUI thread
		Runnable mainWindow = new Connect4(gameEngine);
		threadGUI = new Thread(mainWindow);

		// start all threads
		threadGE.start();
		threadGUI.start();
	}

	/**
	 * Method to display the main window
	 */
	private void display() {
		if (homeGlassPane != null) {
			mainFrame.setGlassPane(homeGlassPane);
			homeGlassPane.setOpaque(false);
			homeGlassPane.setVisible(true);
		}
		mainFrame.getContentPane().add(menuPanel, BorderLayout.EAST);
		mainFrame.getContentPane().add(gameBoardPanel, BorderLayout.WEST);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}

	public void setVisity(boolean b) {
		mainFrame.setVisible(b);
	}

	public GameEngine getGameEngine() {
		return gameEngine;
	}

	public GameBoardPanel getGameBoardPanel() {
		return gameBoardPanel;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void suspendGame() {
		gameEngine.suspendGame();
		threadGE.interrupt();
	}

	public void changeGlassPane(int mode) {
		switch (mode) {
		case 0:
			homeGlassPane = new LogoPanel();
			break;
		case 1:
			homeGlassPane = new SinglePlayerMenu(this);
			break;
		case 2:
			homeGlassPane = new DoublePlayersMenu(this);
			break;
		case 3:
			// homeGlassPane = new Statistic();
			break;
		case 4:
			homeGlassPane = new Credits();
			break;
		default:
			homeGlassPane = null;
		}
		display();
	}

	@Override
	public void run() {
		display();
		BackgroundMusic.music("src/sound/2-06_Awash_in_Ale_but_Nary_a_Mug.wav");

		while (true) {
			// delay waiting
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			// start new simulation if game is not on
			if (!gameEngine.isGameOn()) {
				gameBoardPanel.startSimulationGame();
			}
		}
	}

}
