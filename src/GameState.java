
public class GameState {
    private final int COL_MAX = 7;
    private final int ROW_MAX = 6;
    
    private Disc[COL_MAX][ROW_MAX] board;
    private ArrayList<Player> players;
    
    
    public GameState() {
        // initialise the board to null
        for(int i = 0; i < COL_MAX; i++)
            for(int j = 0; j < ROW_MAX; j++)
                board[i][j] = null;

        players = new ArrayList<Player>();
    }
}
