import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import com.sun.prism.paint.Color;

public class Connect4 implements Runnable {

	private JFrame mainFrame;
	private MenuPanel menuPanel;
	private GameBoardPanel simulationPanel;
	private GameEngine gameEngine;
	private JPanel homeGlassPane;
	private static Thread threadGE;
	private static Thread threadGUI;
	private boolean isMuted;

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
		simulationPanel = new GameBoardPanel(null, this);
		simulationPanel.setPreferredSize(new Dimension(750, 700));

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
		mainFrame.getContentPane().add(simulationPanel, BorderLayout.WEST);
		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	public void setVisity(boolean b) {
		mainFrame.setVisible(b);
	}

	public GameEngine getGameEngine() {
		return gameEngine;
	}

	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void suspendGame() {
		gameEngine.suspendGame();
		threadGE.interrupt();
	}

	public boolean isMuted() {
		return isMuted;
	}

	public void setisMuted(boolean isMuted) {
		this.isMuted = isMuted;
		if (isMuted) {
			menuPanel.mute();
		} else {
			menuPanel.unmute();
		}
	}

	public void changeGlassPane(int mode) {
		switch (mode) {
		case 0:
			homeGlassPane = new LogoPanel();
			break;
		case 1:
			if(homeGlassPane instanceof SinglePlayerMenu) homeGlassPane = new LogoPanel();
			else homeGlassPane = new SinglePlayerMenu(this);
			break;
		case 2:
			if(homeGlassPane instanceof DoublePlayersMenu) homeGlassPane = new LogoPanel();
			else homeGlassPane = new DoublePlayersMenu(this);
			break;
		case 3:
			// homeGlassPane = new Statistic();
			break;
		case 4:
			if(homeGlassPane instanceof Credits) homeGlassPane = new LogoPanel();
			else homeGlassPane = new Credits();
			break;
		default:
			homeGlassPane = new JPanel();
		}
		display();
	}

	/**
	 * GUI thread. Detect game state and restart simulation.
	 */
	@Override
	public void run() {
		display();
		BackgroundMusic.music("src/sound/2-06_Awash_in_Ale_but_Nary_a_Mug.wav");

		while (true) {
			// start new simulation if game is not on
			if (!gameEngine.isInGame() && mainFrame.isVisible()) {
				simulationPanel.startSimulationGame();
			}
			// delay waiting
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

}
