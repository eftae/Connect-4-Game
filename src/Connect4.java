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

	/**
	 * 
	 * @param gameEngine
	 *            main game engine for threadGUI
	 */
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
	 * Method to display the main game window
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

	/**
	 * Set visibility of the mainframe.
	 * 
	 * @param visible
	 */
	public void setVisibility(boolean visible) {
		mainFrame.setVisible(visible);
	}

	/**
	 * Getter for game engine.
	 * 
	 * @return gameEngine
	 */
	public GameEngine getGameEngine() {
		return gameEngine;
	}

	/**
	 * Getter for menu panel.
	 * 
	 * @return menuPanel
	 */
	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	/**
	 * Getter for main frame.
	 * 
	 * @return mainFrame
	 */
	public JFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * suspend current game.
	 */
	public void suspendGame() {
		gameEngine.suspendGame();
		// interrupt user input
		threadGE.interrupt();
	}

	/**
	 * 
	 * @return if sound is muted
	 */
	public boolean isMuted() {
		return isMuted;
	}

	/**
	 * set mute or unmute
	 * 
	 * @param isMuted
	 */
	public void setisMuted(boolean isMuted) {
		this.isMuted = isMuted;
		if (isMuted) {
			menuPanel.mute();
		} else {
			menuPanel.unmute();
		}
	}

	/**
	 * Change the glass panel on home screen.
	 * 
	 * @param mode
	 */
	public void changeGlassPane(int mode) {
		switch (mode) {
		case 0:
			// logo
			homeGlassPane = new LogoPanel();
			break;
		case 1:
			// single player menu
			if (homeGlassPane instanceof SinglePlayerMenu)
				homeGlassPane = new LogoPanel();
			else
				homeGlassPane = new SinglePlayerMenu(this);
			break;
		case 2:
			// double players menu
			if (homeGlassPane instanceof DoublePlayersMenu)
				homeGlassPane = new LogoPanel();
			else
				homeGlassPane = new DoublePlayersMenu(this);
			break;
		case 3:
			// credits page
			if (homeGlassPane instanceof Credits)
				homeGlassPane = new LogoPanel();
			else
				homeGlassPane = new Credits();
			break;
		case 4:
			// credits page
			if (homeGlassPane instanceof HowToPlay)
				homeGlassPane = new LogoPanel();
			else
				homeGlassPane = new HowToPlay();
			break;
		default:
			// blank
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
