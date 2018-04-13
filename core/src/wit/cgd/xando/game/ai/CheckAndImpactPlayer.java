package wit.cgd.xando.game.ai;

import java.util.Random;

import wit.cgd.xando.game.BasePlayer;
import wit.cgd.xando.game.Board;

public class CheckAndImpactPlayer extends BasePlayer {

	private Random randomGen;

	public CheckAndImpactPlayer(Board board, int symbol) {
		super(board, symbol);
		// TODO Auto-generated constructor stub

		randomGen = new Random();
		name = "Check And Impact Player";
	}

	@Override
	public int move() {

		// Step 1 - check for a win in next move for player
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (board.cells[r][c] == board.EMPTY) {
					if (board.hasWon(super.mySymbol, r, c))
						return (r * 3 + c);
				}
			}
		}

		// Step 2 - check for a win in next move for other player
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (board.cells[r][c] == board.EMPTY) {
					if (board.hasWon(super.opponentSymbol, r, c))
						return (r * 3 + c);
				}
			}
		}

		// Step 3 - imply standard RandomImpactSpacePlayer strategy
		if (board.cells[1][1] == board.EMPTY)
			return (1 * 3 + 1);

		// checking the corners of the board
		else if (board.cells[0][0] == board.EMPTY
				|| board.cells[0][2] == board.EMPTY
				|| board.cells[2][0] == board.EMPTY
				|| board.cells[2][2] == board.EMPTY) {
			while (true) {
				int p = randomGen.nextInt(9);
				if (p % 2 == 0 && p != 4) {
					if (board.cells[p / 3][p % 3] == board.EMPTY)
						return p;
				}
			}
		}

		// else generate a random number and complete the turn
		else {
			while (true) {
				int p = randomGen.nextInt(9);
				if (board.cells[p / 3][p % 3] == board.EMPTY)
					return p;
			}
		}
	}
}