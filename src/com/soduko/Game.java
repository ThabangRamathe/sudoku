
public class Game {
    public static void main(String[] args) {
        SodukoSolver solver=new SodukoSolver();
        Generator gen=new Generator();
        int diff = 3;
        int[][] difficulties = {
            {40 , 0},
            {81 , 5},
            {81 , 10},
            {81 , 20}
        };
        boolean x=gen.gen();
        // int[][] og = gen.getBoard();
        solver.printBoard(gen.getBoard());
        System.out.println(x);
        if(x){
            gen.reduce(difficulties[diff][0]);
            if(difficulties[diff][1] != 0) gen.reduce(difficulties[diff][1]);
        }
        
        solver.printBoard(gen.getBoard());

        System.out.println("Let's solve!");
        solver.setBoard(gen.getBoard());
        solver.solve();
        solver.printBoard(gen.getBoard());
        // int[][] res = gen.getBoard();

        // if(checkSuccess(og, res)) System.out.println("Success!");
        // else System.out.println("Failure!");
    }

    // public static boolean checkSuccess(int[][] a, int[][] b){
    //     for (int i = 0; i < 9; i++) {
    //         for (int j = 0; j < 9; j++) {
    //             if(a[i][j] != b[i][j]){
    //                 return false;
    //             }
    //         }
    //     }

    //     return true;
    // }
}