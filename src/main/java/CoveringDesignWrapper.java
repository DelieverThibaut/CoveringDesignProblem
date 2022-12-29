package CoveringDesignProblem;

import CoveringDesignProblem.Interfaces.IMetaHeuristicStrategy;
import CoveringDesignProblem.MetaHeuristicsStrategies.*;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author  Thibaut Deliever
 * @version 1.0
 * @since   2022-11-09
 */

public class CoveringDesignWrapper {
    private final CoveringDesign CoveringDesign;
    private IMetaHeuristicStrategy MetaHeuristicStrategy;
    private Set<BitSet> Solution = new HashSet<>();


    public CoveringDesignWrapper(int v, int k, int t, int stratNum, boolean manualStart){
        this.CoveringDesign = new CoveringDesign(v, k, t);
        IMetaHeuristicStrategy strat;
        strat = switch (stratNum) {
            /*case 1 -> new TabuSearch1();
            case 2 -> new TabuSearch2();
            case 3 -> new TabuSearch3();
            case 4 -> new TabuSearch4();
            case 5 -> new TabuSearch5();
            case 6 -> new TabuSearch6();*/
            case 7 -> new TabuSearch7();
            default -> new TabuSearch7();
        };
        setMetaHeuristicStrategy(strat);
        if(!manualStart) executeStrategy();
    }


    public CoveringDesign getCoveringDesign(){ return CoveringDesign; }
    public Set<BitSet> getSolution(){ return Solution; }
    public void setMetaHeuristicStrategy(IMetaHeuristicStrategy strat){ MetaHeuristicStrategy = strat; }


    public void executeStrategy(){ Solution = MetaHeuristicStrategy.executeStrategy(getCoveringDesign()); }


    public void printSolution(){
        System.out.print("Solution: \n");
        this.Solution.forEach(set ->{
            System.out.print("\t\t");
            for (int i = set.nextSetBit(0); i >= 0; i = set.nextSetBit(i+1)) System.out.printf((i+1) + " ");
            System.out.print("\n");
        });
    }
    public boolean checkSolution(){
        for (BitSet set : CoveringDesign.getAllTSubsets()) {
            boolean found = false;
            for (BitSet solution : Solution) {
                BitSet x = (BitSet) solution.clone();
                x.and(set);
                if (x.cardinality() >= 2) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }
}