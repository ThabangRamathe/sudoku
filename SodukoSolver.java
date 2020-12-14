
public class SodukoSolver{
    public int[][] board={
        {7,8,0,4,0,0,1,2,0},
        {6,0,0,0,7,5,0,0,9},
        {0,0,0,6,0,1,0,7,8},
        {0,0,7,0,4,0,2,6,0},
        {0,0,1,0,5,0,9,3,0},
        {9,0,4,0,6,0,0,0,5},
        {0,7,0,3,0,0,0,1,2},
        {1,2,0,0,0,7,4,0,0},
        {0,4,9,2,0,6,0,0,7}
    };

    public static void main(String[] args) {
        SodukoSolver solver=new SodukoSolver();
        solver.solve(solver.board);
        solver.printBoard(solver.board);
    }

    public void printBoard(int[][] board){
        for(int i=0; i<board.length; i++){
            if(i%3==0){ System.out.println(" - - - - - - - - - - - -");}

            for (int j = 0; j < board[0].length; j++) {
                if(j%3==0){System.out.print("| ");}

                if(j==8){System.out.println(board[i][j]+" |");}
                else{ System.out.print(board[i][j]+" ");}
            }
        }

        System.out.println(" - - - - - - - - - - - -");
    }

    public String getEmpty(int[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j]==0){
                    String pos=i+""+j;
                    return pos;
                }
            }
        }

        return "";
    }

    public boolean isValid(int[][] board, int x, int y, int num){
        for (int i = 0; i < board[0].length; i++) {
            if(board[y][i]==num && x!=i) return false;
        }

        for (int i = 0; i < board.length; i++) {
            if(board[i][x]==num && y!=i) return false;
        }

        int boxX=x/3, boxY=y/3;

        for (int i = boxY*3; i < boxY*3 +3; i++) {
            for (int j = boxX*3; j < boxX*3+3; j++) {
                if(board[i][j]==num && i!=y && j!=x) return false;
            }
        }

        return true;
    }

    public void solve(){ solve(board);}

    public boolean solve(int[][] board){
        String pos= getEmpty(board);
        int row, col;

        if(pos.equalsIgnoreCase("")){return true;}
        else{
            row=Integer.parseInt(pos.substring(0,1));
            col=Integer.parseInt(pos.substring(1));
        }

        for (int i = 1; i < 10; i++) {
            if(isValid(board, col, row, i)){

                board[row][col]=i;

                if(solve(board))return true;

                board[row][col]=0;
            }
        }

        return false;
    }

    public void setBoard(int[][] b){ board=b;}

    public int[][] getBoard(){ return board;}
}
