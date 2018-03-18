package wit.cgd.xando.game;


public abstract class BasePlayer {
	
    public boolean  human;
    public int      mySymbol, opponentSymbol;
    public String   name;
    public Board    board;

    public BasePlayer(Board board, int symbol) {
    	this.board = board;
    }
    
    public void setSymbol(int symbol) {
    	mySymbol = board.X;
    	opponentSymbol = board.O;
    }
    
    public abstract int move ();
    
    
}
