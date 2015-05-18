import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.text.LayeredHighlighter;

public class Connect4 implements Runnable {

	private JFrame mainFrame;
	private MenuPanel menuPanel;
	private GameBoardPanel gameBoardPanel;
	private GameEngine gameEngine;
	private MyGlassPane myGlassPane;

	// public BackgroundMusic backMusic = new BackgroundMusic();

	public Connect4(GameEngine gameEngine) {
		this.gameEngine = gameEngine;

		// main frame
		mainFrame = new JFrame("Connect 4");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setPreferredSize(new Dimension(1000, 700));
		mainFrame.setLocationRelativeTo(null);

		// menu panel
		menuPanel = new MenuPanel(this);
		menuPanel.setPreferredSize(new Dimension(250, 700));

		// game board panel
		gameBoardPanel = new GameBoardPanel(0, null, this);
		gameBoardPanel.setPreferredSize(new Dimension(750, 700));

	}

	public static void main(String[] args) {
		// game engine thread
		Runnable mainGameEngine = new GameEngine();
		Thread threadGE = new Thread(mainGameEngine);
		GameEngine gameEngine = (GameEngine) mainGameEngine;
		// GUI thread
		Runnable mainWindow = new Connect4(gameEngine);
		Thread threadGUI = new Thread(mainWindow);

		// start all threads
		threadGE.start();
		threadGUI.start();
	}

	/**
	 * Method to display the main window
	 */
	private void display() {

		myGlassPane = new MyGlassPane();
		mainFrame.getContentPane().add(menuPanel, BorderLayout.EAST); // better?
		mainFrame.getContentPane().add(gameBoardPanel, BorderLayout.WEST);
		mainFrame.setGlassPane(myGlassPane);
		myGlassPane.setOpaque(false);
		myGlassPane.setVisible(true);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}

	public void displayStatistic() {
		// Todo
	}

	public void displayCredits() {
		// Todo
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
				gameBoardPanel.startNewGame(0);
			}
		}
	}

}
