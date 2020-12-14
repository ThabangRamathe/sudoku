
import java.util.Random;
import java.util.*;

public class Generator{
    private int missing;//number of unknown cells
    private Random rand;
    private int[][] board;

    public Generator(int g){
        missing=(9*9)-g;
        rand=new Random();
        board=new int[9][9];
        resetBoard();
    }

    public int[][] getBoard(){ return board;}

    public void resetBoard(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j]=0;
            }
        }
    }

    public boolean gen(){
        ArrayList<Integer> nums=new ArrayList<>();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                int k=rand.nextInt(9)+1;
                while(nums.contains(k)){
                    k=rand.nextInt(9)+1;
                }

                board[i][j]=k;
                nums.add(k);
            }
        }
        nums.clear();
        for(int i=3; i<6; i++){
            for(int j=3; j<6; j++){
                int k=rand.nextInt(9)+1;
                while(nums.contains(k)){
                    k=rand.nextInt(9)+1;
                }

                board[i][j]=k;
                nums.add(k);
            }
        }
        nums.clear();
        for(int i=6; i<9; i++){
            for(int j=6; j<9; j++){
                int k=rand.nextInt(9)+1;
                while(nums.contains(k)){
                    k=rand.nextInt(9)+1;
                }

                board[i][j]=k;
                nums.add(k);
            }
        }
        return generate(board);
    }

    public boolean generate(int[][] board){
        String pos=getEmpty(board);
        int row, col;

        if(pos.equals("")){return true;}
        else{
            row=Integer.parseInt(pos.substring(0, 1));
            col=Integer.parseInt(pos.substring(1));
        }

        for (int i = 1; i < 10; i++) {
            if(isValid(board, col, row, i)){

                board[row][col]=i;

                if(generate(board))return true;

                board[row][col]=0;
            }
        }

        return false;
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

    public void makePuzzle(){

        while(missing!=0){
            int row=rand.nextInt(9);
            int col=rand.nextInt(9);

            while(board[row][col]==0){
                row=rand.nextInt(9);
                col=rand.nextInt(9);
            }

            missing--;
            board[row][col]=0;
        }
    }
}
