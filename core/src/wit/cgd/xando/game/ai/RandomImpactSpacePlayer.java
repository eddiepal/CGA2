package wit.cgd.xando.game.ai;

import java.util.Random;

import wit.cgd.xando.game.BasePlayer;
import wit.cgd.xando.game.Board;

public class RandomImpactSpacePlayer extends BasePlayer {
	
	private Random randomGen;
	
	public RandomImpactSpacePlayer(Board board, int symbol) {
		super(board, symbol);
		// TODO Auto-generated constructor stub
		name = "Random Impact Space Player";
		randomGen = new Random();
	}

	@Override
	public int move() {
		if(board.cells[1][1]==board.EMPTY)
			return (1*3+1);
		
		
		else if(board.cells[0][0]==board.EMPTY || board.cells[0][2]==board.EMPTY || 
				board.cells[2][0]==board.EMPTY || board.cells[2][2]==board.EMPTY){
			while(true)
			{
				int p = randomGen.nextInt(9);
				if(p%2 == 0 && p != 4){
				if(board.cells[p/3][p%3] == board.EMPTY)
					return p;
				}
			}
		}
		
		else{
			while(true){
				int p = randomGen.nextInt(9);
				if(board.cells[p/3][p%3] == board.EMPTY)
					return p;	
			}
		}
		
	}
}