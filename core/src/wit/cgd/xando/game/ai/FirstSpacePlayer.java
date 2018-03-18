package wit.cgd.xando.game.ai;

import wit.cgd.xando.game.BasePlayer;
import wit.cgd.xando.game.Board;

public class FirstSpacePlayer extends BasePlayer{

	public FirstSpacePlayer(Board board, int symbol) {
		super(board, symbol);
		human = false;
	}

	@Override
	public int move() {
		return 0;
		
		
		//pos = row*3+col;
	}

}
