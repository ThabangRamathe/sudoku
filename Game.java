
public class Game {
    public static void main(String[] args) {
        SodukoSolver solver=new SodukoSolver();
        Generator gen=new Generator(27);
        boolean x=gen.gen();
        solver.printBoard(gen.getBoard());
        System.out.println(x);
        if(x){
            gen.makePuzzle();
        }
        
        solver.printBoard(gen.getBoard());

        System.out.println("Let's solve!");
        solver.setBoard(gen.getBoard());
        solver.solve();
        solver.printBoard(gen.getBoard());
    }
}