package wit.cgd.xando.game.ai;

import java.util.Random;

import wit.cgd.xando.game.BasePlayer;
import wit.cgd.xando.game.Board;

public class ImpactSpacePlayer extends BasePlayer {
	
	private Random randomGen;
	
	public ImpactSpacePlayer(Board board, int symbol) {
		super(board, symbol);
		// TODO Auto-generated constructor stub
		
		randomGen = new Random();
	}

	@Override
	public int move() {
		if(board.cells[1][1]==board.EMPTY)
			return (1*3+1);
		
		
		else if(board.cells[0][0]==board.EMPTY)
			return (0*3+0);
		else if(board.cells[0][2]==board.EMPTY)
			return (0*3+2);
		else if(board.cells[2][0]==board.EMPTY)
			return (2*3+0);
		else if(board.cells[2][2]==board.EMPTY)
			return (2*3+0);
		
		else{
			while(true){
				int p = randomGen.nextInt(9);
				if(board.cells[p/3][p%3] == board.EMPTY)
					return p;	
			}
		}
		
	}
}