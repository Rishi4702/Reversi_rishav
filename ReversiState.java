package Reversi_rishav;

import java.util.LinkedList;
import java.util.List;

public class ReversiState {
    public static final int NOTHING = -1;
    public static final int PLAYER1 = 0;
    public static final int PLAYER2 = 1;
    public static final String PLAYER_NAMES[] = {" \033[0;31mW\033[0m ", " \033[0;34mB\033[0m "};
    int boardSize;
    int board[][] ;
    int nextPlayerToMove = PLAYER1;


    public ReversiState(int boardSize) {
        this.boardSize = boardSize;
        board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                 board[i][j] = NOTHING;
            }
        }
        board[boardSize / 2 - 1][boardSize / 2 - 1] = PLAYER1;
        board[boardSize / 2][boardSize / 2] = PLAYER1;
        board[boardSize / 2 - 1][boardSize / 2] = PLAYER2;
        board[boardSize / 2][boardSize / 2 - 1] = PLAYER2;
    }

    public String toString() {
        int cloneBoard[][] = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                cloneBoard[i][j] = board[i][j];
            }
        }
        List<ReversiMove> ls1 = this.generateMoves();
        for (int i = 0; i < ls1.size(); i++) {
            cloneBoard[ls1.get(i).getx()][ls1.get(i).gety()] = 3;
        }
        StringBuffer output = new StringBuffer();
        //  System.out.println("For player : "+this.PLAYER_NAMES[this.nextPlayerToMove].toString()+"\n");
        output.append("  ");
        for (int i = 0; i < boardSize; i++) {
            output.append(" " + (i + 1) + " ");

        }
        output.append("\n");
        for (int j = 0; j < boardSize; j++) {
            output.append(j + 1 + " ");
            for (int i = 0; i < boardSize; i++) {
                if (board[i][j] == PLAYER1)
                    output.append(PLAYER_NAMES[PLAYER1]);
                else if (board[i][j] == PLAYER2)
                    output.append(PLAYER_NAMES[PLAYER2]);
                else if (cloneBoard[i][j] == 3)
                    output.append(" * ");
                else
                    output.append(" . ");
            }
            output.append(" " + (j + 1));
            output.append("\n");
        }
        output.append("  ");
        for (int i = 0; i < boardSize; i++) {
            output.append(" " + (i + 1) + " ");
        }
        output.append("\n");

        return output.toString();
    }

    public ReversiState clone() {
        ReversiState ReversiState = new ReversiState(boardSize);
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                ReversiState.board[i][j] = board[i][j];
        ReversiState.nextPlayerToMove = nextPlayerToMove;
        return ReversiState;
    }

    public boolean gameOver() {
        if (generateMoves(PLAYER1).isEmpty() && generateMoves(PLAYER2).isEmpty()) {
            return true;
        }
        return false;
    }

    public int score() {
        int score = 0;
        int pA = 0;
        int pB = 0;
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == PLAYER1) {
                    score++;
                    pA++;
                }
                if (board[i][j] == PLAYER2) {
                    pB++;
                    score--;
                }
            }
        System.out.println("PLAYER1 :" + pA + " PLAYER2 : " + pB);

        return score;
    }

    public int eval2() {
        int score = 0;
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == PLAYER1) {
                    score++;

                }
                if (board[i][j] == PLAYER2) {
                    score--;
                }
            }
        return score;
    }

    public int[] eval3() {
        int score[] = {0, 0};
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == PLAYER1) {
                    score[0]++;
                    score[1]--;
                }
                ;
                if (board[i][j] == PLAYER2) {
                    score[1]++;
                    score[0]--;
                }
                ;
            }
        if (!this.gameOver()) {
            //corners
            if (board[0][0] == PLAYER1) {
                score[0] += 4;
                score[1] -= 4;
            }
            ;
            if (board[boardSize - 1][0] == PLAYER1) {
                score[0] += 4;
                score[1] -= 4;
            }
            ;
            if (board[0][boardSize - 1] == PLAYER1) {
                score[0] += 4;
                score[1] -= 4;
            }
            ;
            if (board[boardSize - 1][boardSize - 1] == PLAYER1) {
                score[0] += 4;
                score[1] -= 4;
            }
            ;

            if (board[0][0] == PLAYER2) {
                score[0] -= 4;
                score[1] += 4;
            }
            ;
            if (board[boardSize - 1][0] == PLAYER2) {
                score[0] -= 4;
                score[1] += 4;
            }
            ;
            if (board[0][boardSize - 1] == PLAYER2) {
                score[0] -= 4;
                score[1] += 4;
            }
            ;
            if (board[boardSize - 1][boardSize - 1] == PLAYER2) {
                score[0] -= 4;
                score[1] += 4;
            }
            ;
        }
        return score;
    }

    public int eval1() {

        int[][] weights = {

                {100, -6, 12, 3, 3, 12, -6, 100},
                {-6, -20, -3, -3, -3, -3, -20, -6},
                {12, -3, 6, 1, 1, 6, -3, 12},
                {3, -3, 1, 1, 1, 1, -3, 3},
                {3, -3, 1, 1, 1, 1, -3, 3},
                {12, -3, 6, 1, 1, 6, -3, 12},
                {-6, -20, -3, -3, -3, -3, -20, -6},
                {100, -6, 12, 3, 3, 12, -6, 100}

        };
        int sum = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == PLAYER1) {
                    sum += weights[i][j];
                    sum++;
                }
                if (board[i][j] == PLAYER2) {
                    sum--;
                }
            }
        }
        return sum;
    }

    public List<ReversiMove> generateMoves() {
        return generateMoves(nextPlayerToMove);
    }

    public List<ReversiMove> generateMoves(int player) {
        List<ReversiMove> moves = new LinkedList<ReversiMove>();

        // these two arrays encode the 8 posible directions in which a player can
        // capture pieces:
        int offs_x[] = {0, 1, 1, 1, 0, -1, -1, -1};
        int offs_y[] = {-1, -1, 0, 1, 1, 1, 0, -1};

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == NOTHING) {
                    boolean moveFound = false;
                    for (int k = 0; k < offs_x.length && !moveFound; k++) {
                        int current_x = i + offs_x[k];
                        int current_y = j + offs_y[k];
                        while (current_x + offs_x[k] >= 0 && current_x + offs_x[k] < boardSize &&
                                current_y + offs_y[k] >= 0 && current_y + offs_y[k] < boardSize &&
                                board[current_x][current_y] == otherPlayer(player)) {
                            current_x += offs_x[k];
                            current_y += offs_y[k];
                            if (board[current_x][current_y] == player) {
                                // Legal move:
                                moveFound = true;
                                moves.add(new ReversiMove(player, i, j));
                                break;
                            }
                        }
                    }
                }
            }
        }

        return moves;
    }

    public int otherPlayer(int player) {
        if (player == PLAYER1)
            return PLAYER2;
        return PLAYER1;
    }

    public void applyMove(ReversiMove move) {
        nextPlayerToMove = otherPlayer(nextPlayerToMove);

        if (move == null) {
            // this.gameOver =true;
            return;
        } // player game ends


        // set the piece:
        board[move.x][move.y] = move.player;

        // these two arrays encode the 8 posible directions in which a player can capture pieces:
        int offs_x[] = {0, 1, 1, 1, 0, -1, -1, -1};
        int offs_y[] = {-1, -1, 0, 1, 1, 1, 0, -1};

        // see if any pieces are captured:
        for (int i = 0; i < offs_x.length; i++) {
            int current_x = move.x + offs_x[i];
            int current_y = move.y + offs_y[i];
            while (current_x + offs_x[i] >= 0 && current_x + offs_x[i] < boardSize &&
                    current_y + offs_y[i] >= 0 && current_y + offs_y[i] < boardSize &&
                    board[current_x][current_y] == otherPlayer(move.player)) {
                current_x += offs_x[i];
                current_y += offs_y[i];
                if (board[current_x][current_y] == move.player) {
                    // pieces captured!:
                    int reversed_x = move.x + offs_x[i];
                    int reversed_y = move.y + offs_y[i];
                    while (reversed_x != current_x || reversed_y != current_y) {
                        board[reversed_x][reversed_y] = move.player;
                        reversed_x += offs_x[i];
                        reversed_y += offs_y[i];
                    }
                    break;
                }
            }
        }
    }

    public ReversiState applyMoveCloning(ReversiMove move) {
        ReversiState newState = clone();
        newState.applyMove(move);
        return newState;
    }
}
