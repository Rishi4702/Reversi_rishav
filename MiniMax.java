package Reversi_rishav;

import java.util.List;

public class MiniMax extends ReversiPlayer{
    public int Depth;
    public int player_number;
    
    public MiniMax(int depth,int player) {
        Depth = depth;
        player_number =player;
    }
  
   public ReversiMove getMove(ReversiState state) {
       long t = System.currentTimeMillis();
       ReversiMove m = MiniMaxDecision(state);
       System.out.println("Mini Max Desicion time : "+(System.currentTimeMillis()-t));
       return m;
   }
   
   public int maxValue(ReversiState state, int level) {
          level++;
          int max = Integer.MIN_VALUE;
          if(level < Depth && !state.gameOver()){
              List<ReversiMove> rm = state.generateMoves();
              for (int i = 0; i < rm.size(); i++) {
                  int tmp = minValue(state.applyMoveCloning(rm.get(i)),level);
                  if(max<tmp){
                      max = tmp;
                  }
              }
          }else {
              max = state.eval1();
              if(player_number==1){
                  max = -max;
              }
          }
          return max;
   }
   
   public int minValue(ReversiState state, int level) {
        level++;
        int minValue = Integer.MAX_VALUE;
        if(level < Depth && !state.gameOver()){
            List<ReversiMove> rm = state.generateMoves();
            for (int i = 0; i < rm.size(); i++) {
                int tmp = maxValue(state.applyMoveCloning(rm.get(i)),level);
                    if(minValue > tmp){
                        minValue = tmp;
                    }
            }
        }else {
            minValue = state.eval1();
            if(player_number == 1  ){
                minValue = -minValue;
            }
        }
        return minValue;
   }

   public ReversiMove MiniMaxDecision(ReversiState state ) {
          int indx = 0;
          int v = Integer.MIN_VALUE;
          List<ReversiMove> rm = state.generateMoves();
          if(!rm.isEmpty()){
              for (int i = 0; i < rm.size(); i++) {
                  int tmp = minValue(state.applyMoveCloning(rm.get(i)),0);
                  if(v < tmp){
                      v = tmp;
                      indx =i;
                  }
              }
          }else return null;

       return rm.get(indx);
   }
   
}




