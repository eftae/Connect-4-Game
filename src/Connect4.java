import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Connect4 {

	public static void main(String[] args) {
		// Game for command line
		
		// increase the argument for harder AI
//		Player p1 = new AIAlphaBeta(2);
//		Player p2 = new AIAlphaBeta(3);

		//Player p1 = new User();
		//Player p2 = new AI(0);
//		Game game = new Game(p1, p2);
//		System.out.println("Game Start.");
		
		
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
				JFrame mainFrame = new MainFrame();
		        mainFrame.setVisible(true);
			}
		});
		
		
//		game.runGame();
		System.out.println("Game finsihed.");
	}
}
