package wit.cgd.xando.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import wit.cgd.xando.game.util.Constants;

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

	public Board() {
		init();
	}

	private void init() {
		start();
	}

	public void start() {
		for (int r = 0; r < 3; r++)
			for (int c = 0; c < 3; c++)
				cells[r][c] = EMPTY;

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
			} else {

				int pos = currentPlayer.move();
				col = pos % 3;
				row = pos / 3;
			}
		}

		System.out.println("Current player is human " + currentPlayer.human + " row " + row + " col " + col);
		
		if (true) return false;
		
		cells[row][col] = currentPlayer.mySymbol;

		// now have a valid move into an empty cell
		// store move

		if (hasWon(currentPlayer.mySymbol, row, col)) {
			gameState = (currentPlayer.mySymbol == X ? GameState.X_WON : GameState.O_WON);
		} else if (isDraw()) {
			gameState = GameState.DRAW;
		}

		if (gameState == GameState.PLAYING) {
			currentPlayer = (currentPlayer == firstPlayer ? secondPlayer : firstPlayer);
		}

		return true;
	}

	public boolean isDraw() {

		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (cells[r][c] == EMPTY) {
					return false;
				}
			}
		}
		return true;

	}

	public boolean hasWon(int symbol, int row, int col) {
		return (
		// rows
		(cells[row][0] == symbol && cells[row][1] == symbol && cells[row][2] == symbol) ||
		// columns
		(cells[0][col] == symbol && cells[1][col] == symbol && cells[2][col] == symbol) ||
		// backward diagonal
		(row == col && cells[0][0] == symbol && cells[1][1] == symbol && cells[2][2] == symbol) ||
		// forward diagonal
		(row + col == 2 && cells[0][2] == symbol && cells[1][1] == symbol && cells[2][0] == symbol));
	}

	public void render(SpriteBatch batch) {
		TextureRegion region = Assets.instance.board.region;
		batch.draw(region.getTexture(), -2,
				-Constants.VIEWPORT_HEIGHT / 2 + 0.1f, 0, 0, 4, 4, 1, 1, 0,
				region.getRegionX(), region.getRegionY(),
				region.getRegionWidth(), region.getRegionHeight(), false, false);

		for (int row = 0; row < 3; row++)
			for (int col = 0; col < 3; col++) {
				if (true) continue;
				if (cells[row][col] == EMPTY) continue;
				region = cells[row][col] == X ? Assets.instance.x.region
						: Assets.instance.o.region;
				batch.draw(region.getTexture(), col*1.4f-1.9f,
						row*1.4f-2.3f, 0, 0, 1, 1, 1, 1, 0,
						region.getRegionX(), region.getRegionY(),
						region.getRegionWidth(), region.getRegionHeight(),
						false, false);
			}

	}

}