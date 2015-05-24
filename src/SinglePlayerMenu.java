import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SinglePlayerMenu extends JPanel {
	private Connect4 mainGame;
	private final float opacity = 0.3f;
	private int AIMode;
	private JRadioButton AIButton0;
	private JRadioButton AIButton1;
	private JRadioButton AIButton2;
	ButtonGroup group;
	

	public SinglePlayerMenu(final Connect4 mainGame) {
		// user name input
		final JTextField playerNameField = new JTextField("Player Name", 25);
		add(playerNameField);
		
	

		// Easy button
		AIButton0 = new JRadioButton("Easy");
		AIButton0.setToolTipText("For Beginner of this game");
		AIButton0.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AIMode = 0;
			}
		});
		add(AIButton0);

		// Medium button
		AIButton1 = new JRadioButton("Medium");
		AIButton1.setToolTipText("For Adavance Thinker");
		AIButton1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AIMode = 1;
			}
		});
		add(AIButton1);

		// Hard button
		AIButton2 = new JRadioButton("Hard");
		AIButton2.setToolTipText("For Extreme Player!");
		AIButton2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AIMode = 2;
			}
		});
		add(AIButton2);
		
		
		group = new ButtonGroup ();
		group.add(AIButton0);
		group.add(AIButton1);
		group.add(AIButton2);
		
		
		

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
