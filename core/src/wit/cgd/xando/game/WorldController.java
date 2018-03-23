package wit.cgd.xando.game;

import wit.cgd.xando.game.ai.FirstSpacePlayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class WorldController extends InputAdapter {

	@SuppressWarnings("unused")
	private static final String	TAG	= WorldRenderer.class.getName(); 

	public float viewportWidth;
	public int width, height;
	public Board board;
	float						timeLeftGameOverDelay;
	
	public WorldController() {
		
		init();
	}
	
	private void init() {
		Gdx.input.setInputProcessor(this);
		board = new Board();
		board.firstPlayer = new HumanPlayer(board, board.X);
		board.secondPlayer = new FirstSpacePlayer(board, board.O);
		board.start();

		timeLeftGameOverDelay = 2;
	}
	
	public void update(float deltaTime) {
		if (board.gameState == Board.GameState.PLAYING) {
			board.move();
		} else {
			if (timeLeftGameOverDelay<0) {
				timeLeftGameOverDelay = 2;
			} else {
				timeLeftGameOverDelay -= deltaTime;
			}
			if (timeLeftGameOverDelay<0) {
				board.start();
			}
		}
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		System.out.println("Touch down");
		if (board.gameState == Board.GameState.PLAYING && board.currentPlayer.human) {
			// convert to cell position

			int row = 4 * (height - screenY) / height;
			int col = (int) (viewportWidth * (screenX - 0.5 * width) / width) + 1;

			if (row >= 0 && row < 3 && col >= 0 && col < 3) {
				board.move(row, col);
			}
		}

		return true;

	}

}
