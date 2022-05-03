package Reversi_rishav;

import java.util.*;

public class RandomPlayer extends ReversiPlayer{
    Random random = new Random();
    public ReversiMove getMove(ReversiState state) {
        List<ReversiMove> moves = state.generateMoves();
        if(moves.size()!=0){
            return moves.get(random.nextInt(moves.size()));
        }
        return null;
    }
}
