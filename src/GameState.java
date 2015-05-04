
public class GameState {
    private final int COL_MAX = 7;
    private final int ROW_MAX = 6;
    
    private Disc[COL_MAX][ROW_MAX] board;
    private Player currPlayer;
    private Player winner;
    
    
    public GameState() {
        // initialise the board to null
        for(int i = 0; i < COL_MAX; i++)
            for(int j = 0; j < ROW_MAX; j++)
                board[i][j] = null;

        players = new ArrayList<Player>();
    }
    
    public Player checkBoard(int col, int row){
        if(col >= 0 && col < COL_MAX && row >= 0 && row < ROW_MAX)
            return board[col][row];
        else
            return null;
    }
    
    public Player getCurrPlayer() {
        return currPlayer;
    }
    
    public boolean isColFull (int col) {
        for(int i = 0; i < ROW_MAX; i++)
            if(board[col][i] == null)
                return false;
        return true;
    }
}
