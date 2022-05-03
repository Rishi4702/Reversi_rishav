package Reversi_rishav;
public class  ReversiMove{
    int x, y;
    int player;

    public ReversiMove(int player,int x, int y){
        this.player = player;
        this.x = x;
        this.y = y;
    }
    public int getx(){return x;}
    public int gety(){return y;}

    public String toString(){
        return "Player "+ ReversiState.PLAYER_NAMES[player] + " to " + (x+1) + " , "+(y+1);
    }
    }
