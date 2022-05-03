package Reversi_rishav;
//skeleton code from here
//https://www.cs.drexel.edu/~santi/teaching/2017/CS380/assignment4/assignment4.html
public class main {

    public static void main(String[] args) {

        ReversiState state = new ReversiState(8);

        ReversiPlayer[] players = {new MiniMax(7,0),new AlphaBeta(6,1)};//wrong with static but correct with player count
//        ReversiPlayer[] players = {new MiniMax(5,0),new AlphaBeta(5,1)};
//          ReversiPlayer[] players = {new MiniMax(3,0),new AlphaBeta(4,1)}; //draw with static wins with player count
//        ReversiPlayer[] players = {new MiniMax(4,0),new AlphaBeta(8,1)};//correct with static
//        ReversiPlayer[] players = {new AlphaBeta(6,0),new AlphaBeta(3,1)};// correct with  static
//        ReversiPlayer[] players = {new AlphaBeta(6,0),new MiniMax(7,1)}; //correct result for static eval
//        ReversiPlayer[] players = {new RandomPlayer(),new MiniMax(4,1)};
//        ReversiPlayer[] players = {new RandomPlayer(),new AlphaBeta(8   ,1)};
//        ReversiPlayer[] players = {new RandomPlayer(),new MiniMax(5,1)};
        do {
            // Display the current state in the console:
            System.out.println("\nCurrent state, " + ReversiState.PLAYER_NAMES[state.nextPlayerToMove] + " to move:");
            System.out.print(state);
            // Get the move from the player:
            ReversiMove move = players[state.nextPlayerToMove].getMove(state);
            state = state.applyMoveCloning(move);

        } while (!state.gameOver());

        // Show the result of the game:
        System.out.println("\nFinal state with score: " + state.score());
        System.out.println("Final state at which game ended : " + "\n" + state);
        if (state.score() > 0) {
            System.out.println("\033[0;31mWhite won\033[0m");
        } else if (state.score() == 0) {
            System.out.println("\033[0;33mDraw\033[0m");
        } else {
            System.out.println("\033[0;34mBlue won\033[0m");
        }
    }

}

