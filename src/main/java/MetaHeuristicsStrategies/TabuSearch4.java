package CoveringDesignProblem.MetaHeuristicsStrategies;

import CoveringDesignProblem.Block;
import CoveringDesignProblem.Candidate;
import CoveringDesignProblem.CoveringDesign;
import CoveringDesignProblem.Debugger;
import CoveringDesignProblem.Interfaces.IMetaHeuristicStrategy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TabuSearch4 implements IMetaHeuristicStrategy {
    private final Random Rand = new Random();
    private CoveringDesign CoveringDesign;
    private final Set<BitSet> Solution = new HashSet<>();
    private Set<Integer> UncoveredIntegers;
    private Set<Integer> TooMuchCoveredIntegers;
    private Set<Candidate> CandidateList;
    private final LinkedHashSet<Candidate> TabuList = new LinkedHashSet<>();

    private final int TabuLength = 150;
    private final int MaxIterations = 5000;


    @Override
    public Set<BitSet> executeStrategy(CoveringDesign coveringDesign) {
        System.out.println("FunctionCall_START \t\t~ executeStrategy()");
        CoveringDesign = coveringDesign;
        CandidateList = new HashSet<>();
        UncoveredIntegers = new HashSet<>();
        TooMuchCoveredIntegers = new HashSet<>();
        System.out.println("\tFunctionCall_INFO \t~ executeStrategy() - Solution found: #Blocks = " + CoveringDesign.getBlocks().size());
        setSolution();
        removeBlock();
        int count = 0;
        while (count++ < MaxIterations){
            System.out.println("\tFunctionCall_INFO \t~ executeStrategy() - Count = "+count+" - Conflicts = "+CoveringDesign.getConflicts());
            if(CoveringDesign.getConflicts() == 0){
                System.out.println("\tFunctionCall_INFO \t~ executeStrategy() - Better solution found, "+count+": #Blocks = " + CoveringDesign.getBlocks().size());
                setSolution();
                removeBlock();
                TabuList.clear();
                count = 0;
            }
            else if(!tabuSearch()){
                System.out.println("\tFunctionCall_INFO \t~ executeStrategy() - TabuSearch ended since they cannot be no candidates generated.");
                break;
            }
        }
        System.out.println("FunctionCall_END \t\t~ executeStrategy()");
        return Solution;
    }


    private boolean tabuSearch(){
        generateCandidates();
        Candidate bestCandidate = null;

        for(Candidate c : CandidateList){
            if(bestCandidate == null) bestCandidate = c;
            if(c.getConflicts() == 0){
                bestCandidate = c;
                break;
            }
            else if(c.getConflicts() == bestCandidate.getConflicts() && !TabuList.contains(c) && Rand.nextBoolean()){
                bestCandidate = c;
            } else if(c.getConflicts() < bestCandidate.getConflicts() && !TabuList.contains(c)){
                bestCandidate = c;
            }
        }
        if(bestCandidate != null){
            executeCandidate(bestCandidate);
            return true;
        }
        return false;
    }


    private void generateCandidates() {
        CandidateList.clear();
        TooMuchCoveredIntegers.clear();
        UncoveredIntegers.clear();

        CoveringDesign.getTSubsetMap().forEach((k, v) -> {
            Set<Integer> ints = IntStream.iterate(k.nextSetBit(0), i -> i != -1, i -> k.nextSetBit(i + 1)).boxed().collect(Collectors.toSet());
            if (v.equals(0)) UncoveredIntegers.addAll(ints);
            else TooMuchCoveredIntegers.addAll(ints);
        });

        outerLoop:
        for (Integer i : TooMuchCoveredIntegers)
            for (Integer j : UncoveredIntegers)
                if (!i.equals(j))
                    for (Block block : CoveringDesign.getBlocks())
                        if (isValidSwap(block, i, j)) {
                            executeSwap(block, i, j);
                            int conflicts = CoveringDesign.getConflicts();
                            Candidate candidate = new Candidate(block, i, j, conflicts);
                            executeSwap(block, j, i);
                            CandidateList.add(candidate);
                            if (conflicts == 0) break outerLoop;
                        }
    }


    private boolean isValidSwap(Block block, int i, int j){
        if(!block.hasElement(i)) return false;
        if(block.hasElement(j)) return false;

        BitSet x = (BitSet) block.getElements().clone();
        x.set(i, false);
        x.set(j, true);
        for(Block blck : CoveringDesign.getBlocks())
            if (block != blck && blck.getElements().equals(x)) return false;
        return true;
    }


    private void removeBlock(){
        Debugger.log("FunctionCall_START \t\t~ removeBlock()");

        int max = 0;
        List<Block> blocksToRemove = new ArrayList<>();
        for(Block blck : CoveringDesign.getBlocks()){
            int count = blck.getCoveredTSubsets().stream().mapToInt(set -> CoveringDesign.getTSubsetValue(set)).sum();
            if(count > max){
                blocksToRemove.clear();
                blocksToRemove.add(blck);
                max = count;
            }
            else if (count == max) blocksToRemove.add(blck);
        }
        Block blockToRemove = blocksToRemove.get(Rand.nextInt(blocksToRemove.size()));
        System.out.println("\tFunctionCall_INFO \t~ removeBlock() - Block: " + blockToRemove.getElements());
        CoveringDesign.removeBlock(blockToRemove);
        Debugger.log("FunctionCall_END \t\t~ removeBlock()");
    }


    private void executeCandidate(Candidate c){
        executeSwap(c);
        TabuList.add(c);
        int randomLength = Rand.nextInt(TabuLength) + 1;
        while(TabuList.size() > randomLength && !TabuList.isEmpty()) TabuList.remove(TabuList.iterator().next());
    }


    private void executeSwap(Candidate c){ executeSwap(c.getBlock(), c.getSwappedOutElement(), c.getSwappedInElement()); }


    private void executeSwap(Block b, int i, int j){ CoveringDesign.replaceElement(b, i, j); }


    private void setSolution(){
        Solution.clear();
        for (Block block : CoveringDesign.getBlocks()) Solution.add((BitSet) block.getElements().clone());
    }
}
