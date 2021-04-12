import java.util.Random;
import java.util.*;

public class Generator{
    // private int missing;//number of unknown cells
    // private Random rand;
    private int[][] board;

    public Generator(){
        // missing=(9*9)-g;
        // rand=new Random();
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
        // ArrayList<Integer> nums=new ArrayList<>();
        int[] cells = java.util.stream.IntStream.rangeClosed(1, 9).toArray();
        cells = shuffle(cells);
        board[0] = cells;
        // int j = 0;

        // for(int i = 0; i<9; i++){
        //     int k=rand.nextInt(9)+1;
        //     while(nums.contains(k)){
        //         k=rand.nextInt(9)+1;
        //     }

        //     board[j][i] = k;
        //     nums.add(k);
        // }

        int m;

        for (int i = 1; i < 9; i++) {
            // int[] tempRow = board[i-1];
            if(i%3 == 0) m = 1;
            else m = 3;

            for (int k = 0; k < 9; k++) {
                int n = (9+(k-m))%9;
                board[i][k] = board[i-1][n];
            }
        }

        return true;
    }

    public void reduce(int cutoff){
        int[] cells = getUsedCells();
        cells = shuffle(cells);
        for (int i : cells) {
            if(getPossibleNums(i) == 1){
                removeCell(i);
                cutoff--;
            }

            if(cutoff == 0) break;
        }
    }

    public int[] getUsedCells(){
        ArrayList<Integer> used = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(board[i][j] != 0){
                    int cell = (i*9)+j;
                    used.add(cell);
                }
            }
        }
        
        return toPrimitive(used);
    }

    public int[] toPrimitive(ArrayList<Integer> a){
        int[] cells = new int[a.size()];

        for (int i = 0; i < cells.length; i++) {
            cells[i] = a.get(i);
        }

        return cells;
    }

    public void removeCell(int cell){
        int i = cell/9;
        int j = cell%9;

        board[i][j] = 0;
    }

    public int getPossibleNums(int cell){
        int i = cell/9;
        int j = cell%9;
        int possibles = 0;

        for (int k = 1; k < 10; k++) {
            if(isValid(board,i,j,k)) possibles++;;
        }

        return possibles;
    }

    public int[] shuffle(int[] arr){
        int[] res = arr;
        int n = arr.length;
        Random rand = new Random();
        rand.nextInt();

        for(int i=0; i<n; i++){
            int pivot = rand.nextInt(n-1);
            swap(res, i, pivot);
        }
        // List<Integer> list = Arrays.asList(res);
        // Collections.shuffle(list);
        // list.toArray(res);
        return res;
    }

    public void swap(int[] arr,int l,int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    // public String getEmpty(int[][] board){
    //     for (int i = 0; i < board.length; i++) {
    //         for (int j = 0; j < board[0].length; j++) {
    //             if(board[i][j]==0){
    //                 String pos=i+""+j;
    //                 return pos;
    //             }
    //         }
    //     }

    //     return "";
    // }

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

    // public void makePuzzle(){

    //     while(missing!=0){
    //         int row=rand.nextInt(9);
    //         int col=rand.nextInt(9);

    //         while(board[row][col]==0){
    //             row=rand.nextInt(9);
    //             col=rand.nextInt(9);
    //         }

    //         missing--;
    //         board[row][col]=0;
    //     }
    // }
}
