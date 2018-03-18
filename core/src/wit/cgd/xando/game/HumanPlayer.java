package wit.cgd.xando.game;

public class HumanPlayer extends BasePlayer {

	public HumanPlayer(Board board, int symbol) {
		super(board, symbol);
		human = false;
	}

	@Override
	public int move() {
		return 0;
	}

}
