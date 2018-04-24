package wit.cgd.xando.game.ai;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import wit.cgd.xando.game.BasePlayer;
import wit.cgd.xando.game.Board;
import wit.cgd.xando.game.util.Constants;

public class MinimaxPlayer extends BasePlayer {

    private Random randomGenerator;
    Preferences prefs = Gdx.app.getPreferences(Constants.PREFERENCES);

    public MinimaxPlayer(Board board, int symbol) {
        super(board, symbol);
        name = "MinimaxPlayer";

       
        
        String skillSelector = symbol ==1 ? "firstPlayerSkill" : "secondPlayerSkill";
        
        skill = (int) prefs.getFloat(skillSelector);
        System.out.println("skill:" + skill);

        randomGenerator = new Random();
    }

    @Override
    public int move () {
        return (int) minimax(mySymbol, opponentSymbol, 0);
    }

    private float minimax(int p_mySymbol, int p_opponentSymbol, int depth) {

        final float WIN_SCORE = 100;        
        final float DRAW_SCORE = 0;

        float score;
        float maxScore = -10000;
        int maxPos = -1;

        // for each board position
        for (int r=0; r<3; ++r) {
            for (int c=0; c<3; ++c) {

                // skip over used positions
                if (board.cells[r][c]!=board.EMPTY) continue;

                String indent = new String(new char[depth]).replace("\0", "  ");
                //Gdx.app.log(indent, "search ("+r+","+c+")");

                // place move 
                board.cells[r][c] = p_mySymbol;

                // evaluate board (recursively)
                if (board.hasWon(p_mySymbol, r, c)) {
                    score = WIN_SCORE;
                } else if (board.isDraw()) {
                    score = DRAW_SCORE;
                } else {
                    if (depth<skill) {
                        score = -minimax(p_opponentSymbol, p_mySymbol, depth+1);
                    } else {
                        score = 0;
                    }
                }

                // update ranking

                if (Math.abs(score-maxScore)<1.0E-5 && randomGenerator.nextDouble()<0.1) {
                    maxScore = score;
                    maxPos = 3*r+c;

                } else if (score>maxScore) {    // clear 
                    maxScore = score;
                    maxPos = 3*r+c;
                } 

                //Gdx.app.log(indent, "Score "+score);

                // undo move 
                board.cells[r][c] = board.EMPTY;

            }
        }

        // on uppermost call return move not score
        return (depth==0? maxPos : maxScore);

    };

}