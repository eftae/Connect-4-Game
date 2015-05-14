import java.awt.BorderLayout;
import sun.audio.*;

import java.io.*;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Connect4 {

	private JFrame mainFrame;
	private MenuPanel menuPanel;
	private GameBoardPanel gameBoardPanel;
	//public BackgroundMusic backMusic = new BackgroundMusic();

	public Connect4() {
		mainFrame = new JFrame("Connect 4");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = new Dimension(1000, 700);
		mainFrame.setPreferredSize(d);
		mainFrame.setSize(900,700);
		mainFrame.add(new JLabel(new ImageIcon("src/pics/pic.png")));
		mainFrame.setLocationRelativeTo(null);

		// create default panels
		menuPanel = new MenuPanel(this);
		menuPanel.setPreferredSize(new Dimension(250, 700));
		//gameBoardPanel = new GameBoardPanel(2);
		//gameBoardPanel.setPreferredSize(new Dimension(750, 700));
	}

	public static void main(String[] args) {
		final Connect4 mainWindow = new Connect4();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				mainWindow.display();
				BackgroundMusic.music("src/sound/2-06_Awash_in_Ale_but_Nary_a_Mug.wav");
			}
		});
	}

	/**
	 * Method to display the main window
	 */
	private void display() {
		mainFrame.getContentPane().add(menuPanel, BorderLayout.WEST);
		//mainFrame.getContentPane().add(gameBoardPanel, BorderLayout.EAST);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}

	public void runSinglePlayerGame() {
		// Todo
		Player p1 = new User();
		Player p2 = new AI(1);
	
	}

	public void runTwoPlayersGame() {
		// Todo
		Player p1 = new User();
		Player p2 = new User();

		// GameEngine game = new GameEngine(p1, p2);
		// game.runGame();
	}

	public void displayStatistic() {
		// Todo
	}

	public void displayCredits() {
		// Todo
	}
	
	public void setVisity (boolean b) {
		mainFrame.setVisible(b);
	}

	
	
}
