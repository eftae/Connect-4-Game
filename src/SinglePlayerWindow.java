import java.awt.Color;

import javax.swing.JFrame;

public class SinglePlayerWindow extends JFrame implements Player{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6678966135048256026L;
	private final int MAX_TURN = 42;
	GameState g;
	GameStatePanel SPlayPanel;
	Player AI;
	Player b;
	
	public SinglePlayerWindow() {
		setSize(900, 700);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setBackground(new Color(0, 128, 128));
		BackGroundPanel bp = new BackGroundPanel();
		add(bp);
		
		
		GameMenuBar menu = new GameMenuBar ();
		setJMenuBar(menu);
		
		SPlayPanel =  new GameStatePanel ();
		add(SPlayPanel);
		
		AI = new AI(0);
		b = new AI(0);
		g = new GameState(b, AI);
		
		runGame();
	}
	
	public void runGame() {
		// run game if winner if not defined
		while (g.getTurn() <= MAX_TURN) {
			Player currPlayer = g.getCurrPlayer();
			
			// get player next move			
			int nextMove = currPlayer.decideMove(g);

            if(g.isValidMove(nextMove)){
                int row = g.runNextMove(nextMove);
                if(currPlayer.equals(AI)) SPlayPanel.setButton(colRowToBtnId(nextMove,row),0);
                else SPlayPanel.setButton(colRowToBtnId(nextMove,row),1);
            }
            
            // swap player turn
			g.swapPlayer();
            
            // update the game state
            g.nTurnPlusPlus();
            g.checkGameEnd();
			
			// check winner
			Player me = g.getWinner();
			if (me == b) {
				System.out.println("Player1 win.");
				return;
			} else if(me == AI){
				System.out.println("Player2 win.");
				return;
			}
		}
		// no winner
		System.out.println("Board Full, Game Over");
	}

	@Override
	public int decideMove(GameState currState) {
		int move;
		do{
			move = SPlayPanel.registerMove();
		} while(move == -1);
		return move;
//		return SPlayPanel.registerMove();
	}
	
	private int colRowToBtnId (int col, int row){
		return (5-row)*7 + col;
	}
}
