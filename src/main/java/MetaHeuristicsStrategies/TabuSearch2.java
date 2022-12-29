/*
package CoveringDesignProblem.MetaHeuristicsStrategies;

import CoveringDesignProblem.Block;
import CoveringDesignProblem.Candidate;
import CoveringDesignProblem.CoveringDesign;
import CoveringDesignProblem.Debugger;
import CoveringDesignProblem.Interfaces.IMetaHeuristicStrategy;

import java.util.*;

public class TabuSearch2 implements IMetaHeuristicStrategy {
    private final Random Rand = new Random();

    private CoveringDesign CoveringDesign;
    private HashSet<Candidate> CandidateList;

    private Set<Integer> UncoveredIntegers;
    private Set<Integer> TooMuchCoveredIntegers;

    private Set<BitSet> Solution = new HashSet<>();

    private LinkedHashSet<Candidate> TabuList = new LinkedHashSet<>();

    private int TabuLength = 50;
    private int MaxIterations = 5000;


    @Override
    public Set<BitSet> executeStrategy(CoveringDesign coveringDesign) {
        System.out.println("FunctionCall_START \t\t~ executeStrategy()");

        this.CoveringDesign = coveringDesign;

        this.CandidateList = new HashSet<>();
        this.UncoveredIntegers = new HashSet<>();
        this.TooMuchCoveredIntegers = new HashSet<>();
        System.out.println("\tFunctionCall_INFO \t~ executeStrategy() - Solution found: #Blocks = " + this.CoveringDesign.getBlocks().size() );

        setSolution();
        removeRandomBlock();
        int count = 0;

        while (count++ < MaxIterations){
            */
/*System.out.println("\tFunctionCall_INFO \t~ executeStrategy() - Count = " + count);*//*

            if(!tabuSearch()){
                System.out.println("\tFunctionCall_INFO \t~ executeStrategy() - TabuSearch ended since they cannot be no candidates generated.");
                if(CoveringDesign.getConflicts() == 0) {
                    System.out.println("\tFunctionCall_INFO \t~ executeStrategy() - Better solution found: #Blocks = " + this.CoveringDesign.getBlocks().size());
                    setSolution();
                }
                break;
            }
            if(CoveringDesign.getConflicts() == 0){
                System.out.println("\tFunctionCall_INFO \t~ executeStrategy() - Better solution found: #Blocks = " + this.CoveringDesign.getBlocks().size() );
                setSolution();
                count = 0;
                removeRandomBlock();
            }
        }

        System.out.println("FunctionCall_END \t\t~ executeStrategy()");
        */
/*checkSolution();*//*

        return Solution;
    }

    private void generateCandidates() {
        this.CandidateList.clear();
        this.TooMuchCoveredIntegers.clear();
        this.UncoveredIntegers.clear();

        this.CoveringDesign.getTSubsetMap().forEach((k, v) -> {
            if (v.equals(0)) {
                var x = k.nextSetBit(0) + 1;
                var y = k.nextSetBit(x-1) + 1;
                UncoveredIntegers.add(x);
                UncoveredIntegers.add(y);
            }
            else if(!v.equals(1)) {
                var x = k.nextSetBit(0) + 1;
                var y = k.nextSetBit(x-1) + 1;
                TooMuchCoveredIntegers.add(x);
                TooMuchCoveredIntegers.add(y);
            }
        });

        this.CoveringDesign.getBlocks().forEach(block -> {
            this.TooMuchCoveredIntegers.forEach(i -> {
                this.UncoveredIntegers.forEach(j -> {
                    if(block.hasElement(i) && !block.hasElement(j)){
                        executeSwap(block, i, j);
                        Candidate candidate = new Candidate(block, i, j, this.CoveringDesign.getConflicts());
                        executeSwap(block, j, i);
                        CandidateList.add(candidate);
                    }
                });
            });
        });
    }

    private void removeRandomBlock(){
        Debugger.log("FunctionCall_START \t\t~ removeRandomBlock()");

        var x = this.CoveringDesign.getBlocks().size();
        var k = Rand.nextInt(x);
        int i = 0;
        for(Block blck : this.CoveringDesign.getBlocks())
            if (i++ == k) {
                this.CoveringDesign.removeBlock(blck);
                break;
            }

        Debugger.log("FunctionCall_END \t\t~ removeRandomBlock()");
    }

    private boolean tabuSearch(){
        generateCandidates();
        Candidate bestCandidate = null;

        for(Candidate c : CandidateList){
            if(bestCandidate == null) bestCandidate = c;
            if(c.getDeltaConflicts() == 0){
                bestCandidate = c;
                break;
            }
            else if(c.getDeltaConflicts() == bestCandidate.getDeltaConflicts() && !TabuList.contains(c) && Rand.nextBoolean()){
                bestCandidate = c;
            } else if(c.getDeltaConflicts() < bestCandidate.getDeltaConflicts() && !TabuList.contains(c)){
                bestCandidate = c;
            }
        }
        if(bestCandidate != null){
            executeCandidate(bestCandidate);
            return true;
        }
        return false;
    }

    private void executeCandidate(Candidate c){
        executeSwap(c);
        TabuList.add(c);
        int randomLength = Rand.nextInt(TabuLength) + 1;
        while(TabuList.size() > randomLength && !TabuList.isEmpty()) TabuList.remove(TabuList.iterator().next());
    }

    private void executeSwap(Candidate c){
        executeSwap(c.getBlock(), c.getSwappedOutElement(), c.getSwappedInElement());
    }

    private void executeSwap(Block b, int i, int j){
        this.CoveringDesign.replaceElement(b, i, j);
    }

    private void setSolution(){
        this.Solution.clear();
        this.CoveringDesign.getBlocks().forEach(block -> {
            this.Solution.add((BitSet) block.getElements().clone());
        });

        this.TabuList.clear();

        */
/*checkSolution();*//*

    }
}
*/
