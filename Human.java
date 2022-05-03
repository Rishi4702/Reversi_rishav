package Reversi_rishav;

import java.util.List;
import java.util.Scanner;

public class Human extends ReversiPlayer {
    public ReversiMove getMove(ReversiState state) {
        List<ReversiMove> moves = state.generateMoves();
        boolean validMove =false;
        if(moves.size()>0){
            for (int i = 0; i < moves.size(); i++){
                System.out.println("For this move please select number "+i+ "  " +(moves.get(i).getx()+1)+" "+(moves.get(i).gety()+1));
            }
            System.out.print("Please choose a number less than : "+ moves.size()+" ");
            Scanner scanner = new Scanner(System.in);    
            while(!validMove){
                int mo = scanner.nextInt();
                if(mo<moves.size()){
                    validMove= true;
                    return moves.get(mo);
                }
                System.out.print("Invalid move Please choose a number less than : "+ moves.size()+" ");
            }
         
        }
      // scanner.close();
        return null;
    }
}
