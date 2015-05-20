/**
 * Class for menu. Calling corresponding function in the main.
 */

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class MenuPanel extends JPanel {

	private Connect4 mainFrame;
	public static boolean counter = true;
	public static JButton muteButton = null;
	


	public MenuPanel(final Connect4 mainFrame) {
		this.mainFrame = mainFrame;

		setLayout(new GridBagLayout());
		setBorder(new TitledBorder("New Game"));

		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 0.5;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;

		// Button for Single Player
		gc.gridx = 0;
		gc.gridy = 0;
		ImageIcon singlePlayerButtonIcon = new ImageIcon("src/pics/single.png");
		JButton singlePlayerButton = new JButton(" Single Player",
				singlePlayerButtonIcon);
		singlePlayerButton.setToolTipText("Start a game against with BOT");
		singlePlayerButton.setFont(new Font("Arial", Font.PLAIN, 20));
		add(singlePlayerButton, gc);
		singlePlayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ButtonSound.music("src/sound/button.wav");
				PlayerWindow singlePlayerWindow = new PlayerWindow(mainFrame,
						1, "Single Player Game");
				singlePlayerWindow.setVisible(true);
				mainFrame.setVisity(false);

				BackgroundMusic.stopMusic();
				if (counter == true) {
					BackgroundMusic
							.music("src/sound/2-05_Playing_with_a_Full_Deck.wav");
				}
			}
		});

		// Button for Two Player
		gc.gridx = 0;
		gc.gridy = 1;
		ImageIcon twoPlayersButtonIcon = new ImageIcon("src/pics/Group-50.png");
		JButton twoPlayersButton = new JButton("Co-op Player",
				twoPlayersButtonIcon);
		twoPlayersButton.setToolTipText("Enter a mutiplayer VS Your Friend");
		twoPlayersButton.setFont(new Font("Arial", Font.PLAIN, 20));
		add(twoPlayersButton, gc);
		twoPlayersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ButtonSound.music("src/sound/button.wav");
				PlayerWindow twoPlayerWindow = new PlayerWindow(mainFrame, 2,
						"Mutiple Player Game");
				twoPlayerWindow.setVisible(true);
				mainFrame.setVisity(false);

				BackgroundMusic.stopMusic();
				if (counter == true) {
					BackgroundMusic
							.music("src/sound/2-03_Two_Rogues_One_Mark.wav");
				}
			}

		});

		// Button for Statistic
		gc.gridx = 0;
		gc.gridy = 2;
		ImageIcon statisticButtonIcon = new ImageIcon("src/pics/Trophy-50.png");
		JButton statisticButton = new JButton(" High Score",
				statisticButtonIcon);
		statisticButton.setToolTipText("Check High Scored Record");
		statisticButton.setFont(new Font("Arial", Font.PLAIN, 20));
		add(statisticButton, gc);
		statisticButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonSound.music("src/sound/button.wav");
				mainFrame.displayStatistic();
			}

		});

		// Button for Credits
		gc.gridx = 0;
		gc.gridy = 3;
		ImageIcon creditsButtonIcon = new ImageIcon("src/pics/Gorilla-50.png");
		JButton creditsButton = new JButton("  Credits", creditsButtonIcon);
		creditsButton.setToolTipText("View the Development Team");
		creditsButton.setFont(new Font("Arial", Font.PLAIN, 20));
		add(creditsButton, gc);
		creditsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonSound.music("src/sound/button.wav");
				mainFrame.displayCredits();
			}
		});

		// button for mute/play music
		gc.gridx = 0;
		gc.gridy = 4;
		
		final ImageIcon muteButtonIcon = new ImageIcon("src/pics/Mute.png");
		final ImageIcon speakerButtonIcon = new ImageIcon(
				"src/pics/Speaker.png");
		muteButton = new JButton("Mute Music", muteButtonIcon);
		muteButton.setToolTipText("Mute/Start Music");
		muteButton.setFont(new Font("Arial", Font.PLAIN, 20));
		add(muteButton, gc);
		muteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonSound.music("src/sound/button.wav");
				if (counter == true) {
					muteButton.setIcon(speakerButtonIcon);
					muteButton.setText("Play music");
					muteButton.setFont(new Font("Arial", Font.PLAIN, 20));
					BackgroundMusic.stopMusic();
					counter = false;
				} else {
					muteButton.setIcon(muteButtonIcon);
					muteButton.setText("Mute music");
					muteButton.setFont(new Font("Arial", Font.PLAIN, 20));
					BackgroundMusic
							.music("src/sound/2-06_Awash_in_Ale_but_Nary_a_Mug.wav");
					counter = true;
				}

			}

		});

		// Button for Quit
		gc.gridx = 0;
		gc.gridy = 5;
		ImageIcon quitButtonIcon = new ImageIcon("src/pics/Exit-50.png");
		JButton quitButton = new JButton("Quit Game", quitButtonIcon);
		quitButton.setToolTipText("Exit the Game");
		quitButton.setFont(new Font("Arial", Font.PLAIN, 20));
		add(quitButton, gc);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonSound.music("src/sound/button.wav");
				System.exit(0);
			}

		});

	}
}
