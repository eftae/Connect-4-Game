import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class PlayerWindow extends JFrame {

	private GameBoardPanel gameBoardPanel;
	private GameStaticsPanel gameStaticsPanel;
	private Connect4 mainFrame;

	private JTextField msg;

	public PlayerWindow(Connect4 mainFrame, int playMode, String player) {
		super(player);
		this.mainFrame = mainFrame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 700);
		// add(new JLabel(new ImageIcon("src/pics/pic.png")));
		setLocationRelativeTo(null);
		setResizable(false);
		
		gameStaticsPanel = new GameStaticsPanel();
		getContentPane().add(gameStaticsPanel, BorderLayout.EAST);
		
		gameBoardPanel = new GameBoardPanel(playMode, this, mainFrame);
		gameBoardPanel.setPreferredSize(new Dimension(750, 700));
		getContentPane().add(gameBoardPanel, BorderLayout.WEST);



		GameMenuBar menu = new GameMenuBar(mainFrame, this);
		setJMenuBar(menu);

		msg = new JTextField();
		msg.setEditable(false);
		//getContentPane().add(msg, BorderLayout.SOUTH);
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
