package wit.cgd.xando.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board {
	public static final String TAG = Board.class.getName();

	public static enum GameState {
		PLAYING, DRAW, X_WON, O_WON
	}

	public GameState gameState;

	public final int EMPTY = 0;
	public final int X = 1;
	public final int O = 2;
	public int[][] cells = new int[3][3];

	public BasePlayer firstPlayer, secondPlayer;
	public BasePlayer currentPlayer;
	public HumanPlayer humanPlayer;

	public Board(String filename) {
		init(filename);
	}

	private void init(String filename) {
		// TODO Auto-generated method stub
		start();
	}

	public void start() {
		// TODO Auto-generated method stub
		cells = new int[0][0];
		gameState = GameState.PLAYING;
		currentPlayer = firstPlayer;
	}

	public boolean move() {
		return move(-1, -1);
	}

	public boolean move(int row, int col) {
		
		if (currentPlayer.human) {
			if (row < 0 || col < 0 || row > 2 || col > 2 || cells[row][col] != EMPTY) {
				return false;
			}
			else {

			int pos = currentPlayer.move();
			col = pos % 3;
			row = pos / 3;
		}
		}
		return false;

	    // now have a valid move into an empty cell 
/*	    store move

	    check for win using method __hasWon()__ and update game state

	    if still playing then check for draw using method __isDraw()__ and update game state

	    if still playing then switch player*/
	}

	public boolean isDraw() {
		return false;

	}

	public boolean hasWon(int symbol, int row, int col) {
		return false;

	}

	public void render(SpriteBatch batch) {

	}

}