import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class PlayerWindow extends JFrame {
	
	private GameBoardPanel gameBoardPanel;
	private GameStaticsPanel gameStatPanel;
	private Connect4 mainGame;
	
	private JTextField msg;
	
	public  PlayerWindow(Connect4 mainGame, int playMode, String player){
		super(player);
		this.mainGame = mainGame;		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 700);	
		//add(new JLabel(new ImageIcon("src/pics/pic.png")));
		setLocationRelativeTo(null);
		setResizable(false);
		gameBoardPanel = new GameBoardPanel(playMode,this,mainGame);
		gameBoardPanel.setPreferredSize(new Dimension(750, 700));
		getContentPane().add(gameBoardPanel, BorderLayout.WEST);
		
		gameStatPanel = new GameStaticsPanel ();
		getContentPane().add(gameStatPanel, BorderLayout.EAST);
		
		
		GameMenuBar menu = new GameMenuBar (mainGame,this);
		setJMenuBar(menu);
		
		msg = new JTextField();
		msg.setEditable(false);
		getContentPane().add(msg,BorderLayout.SOUTH);
	}
	
    public void setMsg(String s){
    	msg.setText(s);
    }
        

}
