package CoveringDesignProblem;

public class Main {
    private static int V;
    private static int K;
    private static int T;
    private static int StratNumber;
    private static int RepeatedExecutions;

    private static int LowerBound = 1;
    private static int BestFoundSolution = -1;

    public static void main(Integer[] args){
        int argsCount = args.length;
        if(argsCount < 5 || argsCount > 6) return;

        V = args[0];
        K = args[1];
        T = args[2];
        StratNumber = args[3];
        RepeatedExecutions = args[4];
        if(argsCount == 6) LowerBound = args[5];

        for(int i=0; i<RepeatedExecutions; i++){
            if(BestFoundSolution == LowerBound) break;
            CoveringDesignWrapper cd = new CoveringDesignWrapper(V, K, T, StratNumber, false);
            if(cd.checkSolution()) {
                System.out.println("Best solution found: i=" + i + " - size=" + cd.getSolution().size());
                if(BestFoundSolution == -1 || cd.getSolution().size() < BestFoundSolution)
                    BestFoundSolution = cd.getSolution().size();

            }
        }

        System.out.println("Best solution found: size=" + BestFoundSolution);
    }
}
