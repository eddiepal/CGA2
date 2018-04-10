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
    final float                 TIME_LEFT_GAME_OVER_DELAY = 2;

    final int                   GAME_COUNT = 100;
    public int                  gameCount = 0;
    public int                  win=0, draw=0, loss=0;
	
	public WorldController() {
		
		init();
	}
	
	    private void init() {
	        Gdx.input.setInputProcessor(this);
	        board = new Board();

	        // Players:
	        //      HumanPlayer 
	        //      FirstSpacePlayer 
	        //      RandomSpacePlayer 
	        //      ImpactSpacePlayer 
	        //      RandomImpactSpacePlayer
	        //      CheckAndImpactPlayer
	        //      MinimaxPlayer
	        board.firstPlayer = new HumanPlayer(board, board.X);
	        board.secondPlayer = new CheckAndImpactPlayer(board, board.O);

	        timeLeftGameOverDelay = TIME_LEFT_GAME_OVER_DELAY;
	        board.start();
	    }
	
	
public void update(float deltaTime) {
    if (board.gameState == Board.GameState.PLAYING) {
        board.move();
    } else {
        timeLeftGameOverDelay -= deltaTime;
        if (timeLeftGameOverDelay < 0) {
            gameCount++;
            if (board.gameState == Board.GameState.X_WON) {
                win++;
            } else if (board.gameState == Board.GameState.O_WON) {
                loss++;
            } else {
                assert (board.gameState == Board.GameState.DRAW);
                draw++;
            }

            if (gameCount==GAME_COUNT) {
                Gdx.app.log(TAG,
                    "\nPlayeed "+ gameCount + " games \t" + board.firstPlayer.name + " vs "+ board.secondPlayer.name +
                    "\n\t X  win \t"+ win + "\t X draw \t"+ draw + "\t X loss \t"+ loss); 
                Gdx.app.exit();
            }
            board.start();
            timeLeftGameOverDelay = TIME_LEFT_GAME_OVER_DELAY;
            board.gameState = Board.GameState.PLAYING;
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
