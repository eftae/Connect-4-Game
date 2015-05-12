import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Connect4 {

	private JFrame mainFrame;
	private JPanel menuPanel;
	private JPanel gameBoardPanel;

	public Connect4() {
		mainFrame = new JFrame("Connect 4");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = new Dimension(1000, 700);
		mainFrame.setPreferredSize(d);
		// mainFrame.setSize(900,700);
		mainFrame.setLocationRelativeTo(null);

		// create default panels
		menuPanel = new MenuPanel(this);
		gameBoardPanel = new GameBoardPanel(2);

	}

	public static void main(String[] args) {
		final Connect4 mainWindow = new Connect4();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				mainWindow.display();
			}
		});
	}

	/**
	 * Method to display the main window
	 */
	private void display() {
		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().add(menuPanel, BorderLayout.WEST);
		mainFrame.getContentPane().add(gameBoardPanel, BorderLayout.EAST);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}

	public void runSinglePlayerGame() {
		// Todo
		Player p1 = new User();
		Player p2 = new AIAlphaBeta(1);

		menuPanel = new GameConsolePanel(this);
		gameBoardPanel = new GameBoardPanel(1);
		display();

	}

	public void runTwoPlayersGame() {
		// Todo
		Player p1 = new User();
		Player p2 = new User();

		// GameEngine game = new GameEngine(p1, p2);
		// game.runGame();
		menuPanel = new GameConsolePanel(this);
		gameBoardPanel = new GameBoardPanel(2);
		display();
	}

	public void displayStatistic() {
		// Todo
	}

	public void displayCredits() {
		// Todo
	}
}
