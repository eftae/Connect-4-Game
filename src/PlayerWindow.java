import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class PlayerWindow extends JFrame {

	private GameBoardPanel gameBoardPanel;
	private GameStaticsPanel gameStaticsPanel;
	private Connect4 mainGame;

	private JTextField msg;

	public PlayerWindow(Connect4 mainGame, int playMode, String player) {
		super(player);
		this.mainGame = mainGame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		// add(new JLabel(new ImageIcon("src/pics/pic.png")));
		setLocationRelativeTo(null);
		setResizable(false);
		setBounds(mainGame.getMainFrame().getBounds());

		gameStaticsPanel = new GameStaticsPanel();
		gameStaticsPanel.setPreferredSize(new Dimension(250, 700));
		getContentPane().add(gameStaticsPanel, BorderLayout.EAST);

		gameBoardPanel = new GameBoardPanel(playMode, this, mainGame);
		gameBoardPanel.setPreferredSize(new Dimension(750, 700));
		getContentPane().add(gameBoardPanel, BorderLayout.WEST);

		GameMenuBar menu = new GameMenuBar(mainGame, this);
		setJMenuBar(menu);

		msg = new JTextField();
		msg.setEditable(false);
		// getContentPane().add(msg, BorderLayout.SOUTH);
	}

	public void setMsg(String s) {
		msg.setText(s);
	}

	public GameStaticsPanel getStaticsPanel() {
		return gameStaticsPanel;
	}

	public GameBoardPanel getGameBoardPanel() {
		return gameBoardPanel;
	}
}
