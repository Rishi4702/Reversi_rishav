package Reversi_rishav;

import java.util.List;

public class AlphaBeta extends ReversiPlayer {

    public int depth;
    public int player;

    public AlphaBeta(int d, int p) {
        this.depth = d;
        this.player = p;
    }

    @Override
    public ReversiMove getMove(ReversiState state) {
        // TODO Auto-generated method stub
        long time = System.currentTimeMillis();
        ReversiMove move = MiniMaxAlphaBeta(state);
        System.out.println("Aplha Beta time in milisec :" + (System.currentTimeMillis() - time));
        return move;
    }

    public ReversiMove MiniMaxAlphaBeta(ReversiState state) {
        int indx = 0;
        int min = Integer.MIN_VALUE;
        List<ReversiMove> rm = state.generateMoves();
        if (!rm.isEmpty()) {
            for (int i = 0; i < rm.size(); i++) {
                int tmp = minValue(state.applyMoveCloning(rm.get(i)), Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
                if(min<tmp){
                    min =tmp;
                    indx=i;
                }
            }
        } else return null;
        return rm.get(indx);
    }

    private int minValue(ReversiState state, int alpha, int beta, int level) {
        level++;
        int min = Integer.MAX_VALUE;
        if (level < depth && !state.gameOver()) {
            List<ReversiMove> rm = state.generateMoves();
            for (int i = 0; i < rm.size(); i++) {
                int tmp = maxValue(state.applyMoveCloning(rm.get(i)), alpha, beta, level);
                if (tmp < min) {
                    min = tmp;
                }
                if (tmp <= alpha) {
                    return min;
                }
                if (tmp < beta) {
                    beta = tmp;
                }
            }
        }else {
            min = state.eval1();
            if(player == 1){
                min = -min;
            }
        }
        return min;
    }

    private int maxValue(ReversiState state, int alpha, int beta, int level) {
        level++;
        int max = Integer.MIN_VALUE;
        if (level < depth && !state.gameOver()) {
            List<ReversiMove> rm = state.generateMoves();
            for (int i = 0; i < rm.size(); i++) {
                int tmp = minValue(state.applyMoveCloning(rm.get(i)), alpha, beta, level);
                if (tmp > max) {
                    max = tmp;
                }
                if (tmp >= beta) {
                    return max;
                }
                if (tmp > alpha) {
                    alpha = tmp;
                }
            }
        }else {
            max = state.eval1();
            if(player == 1        ){
                max = -max;
            }
        }
        return max;
    }


}
