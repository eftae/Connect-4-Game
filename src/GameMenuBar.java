import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Class create the top game menu for game windows
 * 
 * @author Jiangkan Pan
 *
 */

public class GameMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private Connect4 mainGame;
	private GameWindow gameWindow;
	private ImageIcon muteIcon;
	private String title;

	/**
	 * 
	 * @param mainGame
	 * @param psw
	 */

	public GameMenuBar(Connect4 mg, GameWindow psw) {

		mainGame = mg;
		gameWindow = psw;

		JMenu first = new JMenu("Menu");
		add(first);

		ImageIcon icon1 = new ImageIcon("src/pics/Save-50.png");
		ImageIcon icon2 = new ImageIcon("src/pics/Home-50.png");
		ImageIcon icon3 = new ImageIcon("src/pics/Exit-50.png");
		final ImageIcon icon4 = new ImageIcon("src/pics/Mute.png");
		final ImageIcon icon5 = new ImageIcon("src/pics/Speaker.png");

		ImageIcon newIcon1 = ResizeImage.changeImage(icon1, 18, 18);
		ImageIcon newIcon2 = ResizeImage.changeImage(icon2, 20, 20);
		ImageIcon newIcon3 = ResizeImage.changeImage(icon3, 20, 20);
		final ImageIcon newIcon4 = ResizeImage.changeImage(icon4, 20, 20);
		final ImageIcon newIcon5 = ResizeImage.changeImage(icon5, 20, 20);

		if (!mainGame.isMuted()) {
			title = "Mute";
			muteIcon = newIcon4;
		} else {
			title = "Unmute";
			muteIcon = newIcon5;
		}

		JMenuItem menuItem1 = new JMenuItem("Restart Game", newIcon1);
		JMenuItem menuItem2 = new JMenuItem("Home", newIcon2);
		final JMenuItem menuItem3 = new JMenuItem(title, muteIcon);
		JMenuItem menuItem4 = new JMenuItem("Exit", newIcon3);

		menuItem1.setToolTipText("Restart Game");
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				gameWindow.getGameBoardPanel().restartNewGame();
			}
		});

		menuItem2.setToolTipText("Return to Menu");
		menuItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				mainGame.suspendGame();
				try {
					Thread.sleep(400);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				mainGame.setVisity(true);
				mainGame.changeGlassPane(0);
				gameWindow.setVisible(false);
				gameWindow.dispose();
			}
		});

		menuItem3.setToolTipText("Mute/unmute sound");
		menuItem3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (!mainGame.isMuted()) {
					menuItem3.setIcon(newIcon5);
					menuItem3.setText("Unmute");
					BackgroundMusic.stopMusic();
					mainGame.setisMuted(true);
				} else {
					menuItem3.setIcon(newIcon4);
					menuItem3.setText("Mute");
					BackgroundMusic
							.music("src/sound/2-05_Playing_with_a_Full_Deck.wav");
					mainGame.setisMuted(false);
				}
			}
		});

		menuItem4.setToolTipText("Exit application");
		menuItem4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		first.add(menuItem1);
		first.addSeparator();
		first.add(menuItem2);
		first.addSeparator();
		first.add(menuItem3);
		first.addSeparator();
		first.add(menuItem4);

	}
}
