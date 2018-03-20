package wit.cgd.xando.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.InputAdapter;
import wit.cgd.xando.game.Board.GameState;
import wit.cgd.xando.game.ai.FirstSpacePlayer;

public class WorldController extends InputAdapter {
	private static final String TAG = WorldController.class.getName();
	public float viewportWidth;
	public int width, height;
	public Board board;

	public WorldController() {
		init();
	}

	private void init() {
		Gdx.input.setInputProcessor(this);
		board = new Board();
		board.firstPlayer = new HumanPlayer(board, board.X);
		board.secondPlayer = new FirstSpacePlayer(board, board.O);
		board.start();
	}

	// @Override
	public void update(float deltaTime) {
		if (board.gameState == GameState.PLAYING) {
			board.move();
		}
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (board.gameState == Board.GameState.PLAYING && board.currentPlayer.human) {

			// convert to cell position
			int row = 4 * (height - screenY) / height;
			int col = (int) (viewportWidth * (screenX - 0.5 * width) / width) + 1;

			// board move - just place piece and return
			if (row >= 0 && row < 3 && col >= 0 && col < 3) {
				board.move(row, col);
			}
		}
		return false;
	}

}
