import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Menu Panel is the side menu on the main page. 
 * It allow the user to enter a single/double game, 
 * open the "how to play" or "credit" windows, 
 * mute or unmute the sound effect, 
 * and quit the game.
 */
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Connect4 mainGame;

	// mute function fields
	private JButton muteButton;
	private ActionListener muteActionListener;

	/**
	 * Construct menu panel on home window.
	 * @param mg main game system
	 */
	public MenuPanel(Connect4 mg) {
		mainGame = mg;

		Font defaultFont = new Font("Arial", Font.PLAIN, 20);

		setLayout(new GridBagLayout());

		setOpaque(true);
		setBackground(new Color(27, 120, 236));
		// setBackground(Color.BLACK);

		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 10;
		gc.fill = GridBagConstraints.BOTH;

		// Button for Single Player
		gc.gridx = 0;
		gc.gridy = 0;

		ImageIcon singlePlayerButtonIcon = ResizeImage.changeImage(
				new ImageIcon("src/pics/singleIcn.png"), 245, 114);
		ImageIcon singlePlayerButtonIconClick = ResizeImage.changeImage(
				new ImageIcon("src/pics/singleIcnC.png"), 245, 114);
		ImageIcon singlePlayerButtonIconRO = ResizeImage.changeImage(
				new ImageIcon("src/pics/singleRO.png"), 245, 114);
		// ImageIcon singlePlayerButtonIcon = new
		// ImageIcon("src/pics/singleIcn.png");
		JButton singlePlayerButton = new JButton();
		singlePlayerButton.setToolTipText("Start a game against with BOT");
		singlePlayerButton.setFont(defaultFont);

		singlePlayerButton.setOpaque(false);
		singlePlayerButton.setContentAreaFilled(false);
		singlePlayerButton.setFocusPainted(false);
		singlePlayerButton.setBorderPainted(false);
		singlePlayerButton.setIcon(singlePlayerButtonIcon);
		singlePlayerButton.setPressedIcon(singlePlayerButtonIconClick);
		singlePlayerButton.setRolloverIcon(singlePlayerButtonIconRO);

		add(singlePlayerButton, gc);
		singlePlayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainGame.isMuted())
					ButtonSound.music("src/sound/button.wav");
				// mainGame.changeGlassPane(1);
				SinglePlayerMenu singlePlayer = new SinglePlayerMenu(mainGame);
				JOptionPane.showOptionDialog(null, singlePlayer,
						"Mode Selection", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null,
						new String[] { "Cancel" }, "default");

			}
		});

		// Button for Two Player
		gc.gridy = 1;
		ImageIcon doublePlayerButtonIcon = ResizeImage.changeImage(
				new ImageIcon("src/pics/doubleIcn.png"), 245, 114);
		ImageIcon doublePlayerButtonIconClick = ResizeImage.changeImage(
				new ImageIcon("src/pics/doubleIcnC.png"), 245, 114);
		ImageIcon doublePlayerButtonIconRO = ResizeImage.changeImage(
				new ImageIcon("src/pics/doubleRO.png"), 245, 114);
		// ImageIcon twoPlayersButtonIcon = new
		// ImageIcon("src/pics/Group-50.png");
		JButton twoPlayersButton = new JButton();
		twoPlayersButton.setToolTipText("Enter a mutiplayer VS Your Friend");
		twoPlayersButton.setFont(defaultFont);
		twoPlayersButton.setOpaque(false);
		twoPlayersButton.setContentAreaFilled(false);
		twoPlayersButton.setFocusPainted(false);
		twoPlayersButton.setBorderPainted(false);
		twoPlayersButton.setIcon(doublePlayerButtonIcon);
		twoPlayersButton.setPressedIcon(doublePlayerButtonIconClick);
		twoPlayersButton.setRolloverIcon(doublePlayerButtonIconRO);
		add(twoPlayersButton, gc);
		twoPlayersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainGame.isMuted())
					ButtonSound.music("src/sound/button.wav");
				// mainGame.changeGlassPane(2);
				DoublePlayersMenu doublePlayer = new DoublePlayersMenu(mainGame);
				JOptionPane.showOptionDialog(null, doublePlayer,
						"Enter Players Name", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null,
						new String[] { "Cancel" }, "default");
			}

		});

		// how to play button
		gc.gridy = 2;
		ImageIcon htpIcn = ResizeImage.changeImage(new ImageIcon(
				"src/pics/htpIcn.png"), 245, 114);
		ImageIcon htpIcnClick = ResizeImage.changeImage(new ImageIcon(
				"src/pics/htpIcnC.png"), 245, 114);
		ImageIcon htpIcnRO = ResizeImage.changeImage(new ImageIcon(
				"src/pics/htpRO.png"), 245, 114);
		// ImageIcon htpIcn = new ImageIcon("src/pics/Rules-50.png");
		JButton htpButton = new JButton();
		htpButton.setToolTipText("User Manual");
		htpButton.setFont(defaultFont);
		htpButton.setOpaque(false);
		htpButton.setContentAreaFilled(false);
		htpButton.setFocusPainted(false);
		htpButton.setBorderPainted(false);
		htpButton.setIcon(htpIcn);
		htpButton.setPressedIcon(htpIcnClick);
		htpButton.setRolloverIcon(htpIcnRO);
		add(htpButton, gc);
		htpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainGame.isMuted())
					ButtonSound.music("src/sound/button.wav");
				mainGame.changeGlassPane(4);
			}
		});

		// Button for Credits
		gc.gridy = 3;
		ImageIcon creditsIcn = ResizeImage.changeImage(new ImageIcon(
				"src/pics/creditIcn.png"), 245, 114);
		ImageIcon creditsIcnClick = ResizeImage.changeImage(new ImageIcon(
				"src/pics/creditIcnC.png"), 245, 114);
		ImageIcon creditsIcnRO = ResizeImage.changeImage(new ImageIcon(
				"src/pics/creditRO.png"), 245, 114);
		// ImageIcon creditsButtonIcon = new
		// ImageIcon("src/pics/Gorilla-50.png");
		JButton creditsButton = new JButton();
		creditsButton.setToolTipText("View the Development Team");
		creditsButton.setFont(defaultFont);
		creditsButton.setOpaque(false);
		creditsButton.setContentAreaFilled(false);
		creditsButton.setFocusPainted(false);
		creditsButton.setBorderPainted(false);
		creditsButton.setIcon(creditsIcn);
		creditsButton.setPressedIcon(creditsIcnClick);
		creditsButton.setRolloverIcon(creditsIcnRO);
		add(creditsButton, gc);
		creditsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainGame.isMuted())
					ButtonSound.music("src/sound/button.wav");
				mainGame.changeGlassPane(3);
			}
		});

		// button for mute/play music
		gc.gridy = 4;
		muteButton = new JButton();
		muteButton.setToolTipText("Mute/Unmute Music");
		muteButton.setFont(defaultFont);
		ImageIcon muteIcn = ResizeImage.changeImage(new ImageIcon(
				"src/pics/muteIcn.png"), 245, 114);
		ImageIcon muteIcnClick = ResizeImage.changeImage(new ImageIcon(
				"src/pics/muteIcnC.png"), 245, 114);
		ImageIcon muteIcnRO = ResizeImage.changeImage(new ImageIcon(
				"src/pics/muteRO.png"), 245, 114);
		muteButton.setIcon(muteIcn);
		muteButton.setPressedIcon(muteIcnClick);
		muteButton.setOpaque(false);
		muteButton.setContentAreaFilled(false);
		muteButton.setFocusPainted(false);
		muteButton.setBorderPainted(false);
		muteButton.setRolloverIcon(muteIcnRO);
		add(muteButton, gc);
		muteActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainGame.isMuted()) {
					mainGame.setisMuted(true);
				} else {
					mainGame.setisMuted(false);
					BackgroundMusic
							.music("src/sound/2-06_Awash_in_Ale_but_Nary_a_Mug.wav");
				}
			}
		};
		muteButton.addActionListener(muteActionListener);

		// Button for Quit
		gc.gridy = 5;
		ImageIcon quitIcn = ResizeImage.changeImage(new ImageIcon(
				"src/pics/quitIcn.png"), 245, 114);
		ImageIcon quitIcnClick = ResizeImage.changeImage(new ImageIcon(
				"src/pics/quitIcnC.png"), 245, 114);
		ImageIcon quitIcnRO = ResizeImage.changeImage(new ImageIcon(
				"src/pics/quitRO.png"), 245, 114);
		// ImageIcon quitButtonIcon = new ImageIcon("src/pics/Exit-50.png");
		JButton quitButton = new JButton();
		quitButton.setToolTipText("Exit the Game");
		quitButton.setFont(defaultFont);
		quitButton.setOpaque(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.setBorderPainted(false);
		quitButton.setIcon(quitIcn);
		quitButton.setPressedIcon(quitIcnClick);
		quitButton.setRolloverIcon(quitIcnRO);
		add(quitButton, gc);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainGame.isMuted())
					ButtonSound.music("src/sound/button.wav");
				System.exit(0);
			}

		});
	}

	public ActionListener getMuteActionListener() {
		return muteActionListener;
	}

	public void mute() {
		ImageIcon unmuteIcn = ResizeImage.changeImage(new ImageIcon(
				"src/pics/unmuteIcn.png"), 245, 114);
		ImageIcon unmuteIcnClick = ResizeImage.changeImage(new ImageIcon(
				"src/pics/unmuteIcnC.png"), 245, 114);
		ImageIcon unmuteIcnRO = ResizeImage.changeImage(new ImageIcon(
				"src/pics/unmuteRO.png"), 245, 114);
		muteButton.setIcon(unmuteIcn);
		muteButton.setPressedIcon(unmuteIcnClick);
		muteButton.setRolloverIcon(unmuteIcnRO);
		BackgroundMusic.stopMusic();
	}

	public void unmute() {
		ImageIcon muteIcn = ResizeImage.changeImage(new ImageIcon(
				"src/pics/muteIcn.png"), 245, 114);
		ImageIcon muteIcnClick = ResizeImage.changeImage(new ImageIcon(
				"src/pics/muteIcnC.png"), 245, 114);
		ImageIcon muteIcnRO = ResizeImage.changeImage(new ImageIcon(
				"src/pics/muteRO.png"), 245, 114);
		muteButton.setIcon(muteIcn);
		muteButton.setPressedIcon(muteIcnClick);
		muteButton.setRolloverIcon(muteIcnRO);
		ButtonSound.music("src/sound/button.wav");
	}

	public static void closeJOptionPanel() {
		JOptionPane.getRootFrame().dispose();
	}
}
