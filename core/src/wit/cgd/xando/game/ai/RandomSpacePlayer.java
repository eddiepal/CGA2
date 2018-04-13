package wit.cgd.xando.game.ai;

import wit.cgd.xando.game.BasePlayer;
import wit.cgd.xando.game.Board;

import java.util.Random;

public class RandomSpacePlayer extends BasePlayer {
	
	private Random randomGen;
	
	public RandomSpacePlayer(Board board, int symbol) {
		super(board, symbol);
		name = "Random Generator";
		
		randomGen = new Random();
	}

	@Override
	public int move() {
		while(true){
			int p = randomGen.nextInt(9);
			if(board.cells[p/3][p%3] == board.EMPTY)
				return p;
		}
	}
		
}