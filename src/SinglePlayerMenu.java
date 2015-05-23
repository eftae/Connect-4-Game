import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SinglePlayerMenu extends JPanel {
	private Connect4 mainGame;
	private final float opacity = 0.3f;
	private int AIMode;

	public SinglePlayerMenu(Connect4 mainGame) {
		// user name input
		JTextField playerNameField = new JTextField("Player Name", 25);
		add(playerNameField);

		// Easy button
		JButton AIButton0 = new JButton("Easy");
		AIButton0.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AIMode = 0;
			}
		});
		add(AIButton0);

		// Medium button
		JButton AIButton1 = new JButton("Medium");
		AIButton1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AIMode = 1;
			}
		});
		add(AIButton1);

		// Hard button
		JButton AIButton2 = new JButton("Hard");
		AIButton2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AIMode = 2;
			}
		});
		add(AIButton2);

		// start game button
		JButton startGame = new JButton("Start Game");
		startGame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!mainGame.isMuted()) {
					BackgroundMusic.stopMusic();
					ButtonSound.music("src/sound/button.wav");
					BackgroundMusic
							.music("src/sound/2-05_Playing_with_a_Full_Deck.wav");
				}
				
				GameWindow singlePlayerWindow = new GameWindow(mainGame,
						"Single Player Game");
				singlePlayerWindow.startSinglePlayerGame(
						playerNameField.getText(), AIMode);

				singlePlayerWindow.setVisible(true);
				mainGame.setVisity(false);
			}
		});
		add(startGame);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				opacity));
	}

}
