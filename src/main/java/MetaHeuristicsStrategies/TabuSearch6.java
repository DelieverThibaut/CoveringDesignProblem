/*
package CoveringDesignProblem.MetaHeuristicsStrategies;

import CoveringDesignProblem.*;
import CoveringDesignProblem.Interfaces.IMetaHeuristicStrategy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TabuSearch6 implements IMetaHeuristicStrategy {
    private final Random Rand = new Random();
    private CoveringDesign CoveringDesign;
    private final Set<BitSet> Solution = new HashSet<>();
    private ArrayList<Candidate> CandidateList;
    private final LinkedHashSet<TabuListItem> TabuListIn = new LinkedHashSet<>();
    private final LinkedHashSet<TabuListItem> TabuListOut = new LinkedHashSet<>();

    private double TabuLength;
    private int TabuLengthMin;
    private final int MaxIterations = 50000;
    private int Count = 0;


    @Override
    public Set<BitSet> executeStrategy(CoveringDesign coveringDesign) {
        CoveringDesign = coveringDesign;
        CandidateList = new ArrayList<>();
        TabuLengthMin = (CoveringDesign.getV() - CoveringDesign.getK()) / CoveringDesign.getK() + 1;
        TabuLength = Rand.nextInt(7*TabuLengthMin) + TabuLengthMin;

        setSolution();
        removeBlock();
        while (Count++ < MaxIterations){
             if(CoveringDesign.getConflicts() == 0){
                 System.out.println("Better solution found, "+Count+": #Blocks = " + CoveringDesign.getBlocks().size());
                 setSolution();
                 removeBlock();
                 clearTabuLists();
                 CoveringDesign.BlocksMap.clear();
                 Count = 0;

            }
            else if(!tabuSearch()){
                break;
            }
        }
        return Solution;
    }


    private boolean tabuSearch(){
        generateCandidates();
        Candidate bestCandidate = getBestCandidate();
        if(bestCandidate != null){
            executeCandidate(bestCandidate);
            return true;
        }
        */
/*clearTabuLists();*//*

        bestCandidate = getBestCandidate();
        if(bestCandidate != null){
            executeCandidate(bestCandidate);
            return true;
        }

        return false;
    }

    private Candidate getBestCandidate(){
        int conflicts = CoveringDesign.getConflicts();
        Candidate bestCandidate = null;

        for(Candidate c : CandidateList){
            if(bestCandidate == null && !isTabu(c)){
                bestCandidate = c;
            }
            if(c.getConflicts() == 0){
                bestCandidate = c;
                break;
            } else if(bestCandidate != null && c.getDelta() == bestCandidate.getDelta() && !isTabu(c) && Rand.nextBoolean()){
                bestCandidate = c;
            } else if(bestCandidate != null && c.getDelta() < bestCandidate.getDelta() && !isTabu(c)){
                bestCandidate = c;
            }
        }
        return bestCandidate;
    }

    private void generateCandidates() {
        CandidateList.clear();

        Set<Integer> uncoveredInts = new HashSet<>();
        for(BitSet subsetJ : CoveringDesign.getUncoveredTSubsets()) {
            Set<Integer> ints = IntStream.iterate(subsetJ.nextSetBit(0), x -> x != -1, x -> subsetJ.nextSetBit(x + 1)).boxed().collect(Collectors.toSet());
            uncoveredInts.addAll(ints);
        }

        int conflicts = CoveringDesign.getConflicts();
        outerLoop:
        for(int i : CoveringDesign.getVSet()) {
            Set<Block> blocks = CoveringDesign.getVElementValue(i);
            for (int j : CoveringDesign.getVSet())
                if (i != j)
                    for (Block block : blocks)
                        if (!block.hasElement(j)) {
                            int _conflicts = conflicts + calculateDeltaConflictValue(block, i, j);
                            int delta = calculateObjectiveValue(block);
                            CandidateList.add(new Candidate(block, i, j, _conflicts, delta));
                            if (_conflicts == 0) break outerLoop;
                        }
        }
    }

    private int calculateDeltaConflictValue(Block block, int i, int j){
        int delta = 0;
        Iterator<BitSet> sets = block.getCoveredTSubsets().stream().filter(subset -> subset.get(i)).iterator();
        while (sets.hasNext()) {
            BitSet set = sets.next();
            if (CoveringDesign.getTSubsetValue(set).size() == 1) delta++;
            set.set(i, false);
            set.set(j);
            if (CoveringDesign.getTSubsetValue(set).size() == 0) delta--;
            set.set(i);
            set.set(j, false);
        }
        return delta;
    }

    private int calculateObjectiveValue(Block block){
        int delta = 0;
        delta += Math.pow(CoveringDesign.getBlockValue(block), 1.05);
        return delta;
    }

    private void clearTabuLists(){
        TabuListIn.clear();
        TabuListOut.clear();
    }

    private boolean isTabu(Candidate c){
        return TabuListIn.contains(c.getTabuOut()) || TabuListOut.contains(c.getTabuIn());
    }

    private void removeBlock(){
        Debugger.log("FunctionCall_START \t\t~ removeBlock()");

        int max = 0;
        List<Block> blocksToRemove = new ArrayList<>();
        for(Block blck : CoveringDesign.getBlocks()){
            int count = blck.getCoveredTSubsets().parallelStream().mapToInt(set -> CoveringDesign.getTSubsetValue(set).size()).sum();
            if(count > max){
                blocksToRemove.clear();
                blocksToRemove.add(blck);
                max = count;
            }
            else if (count == max) blocksToRemove.add(blck);
        }
        Block blockToRemove = blocksToRemove.get(Rand.nextInt(blocksToRemove.size()));
        */
/*System.out.println("\tFunctionCall_INFO \t~ removeBlock() - Block: " + blockToRemove.getElements());*//*

        CoveringDesign.removeBlock(blockToRemove);
        Debugger.log("FunctionCall_END \t\t~ removeBlock()");
    }

    private void executeCandidate(Candidate c){
        executeSwap(c);
        makeTabu(c);
        adjustTabuList();
    }

    private void makeTabu(Candidate c){
        this.TabuListIn.add(c.getTabuIn());
        this.TabuListOut.add(c.getTabuOut());
    }

    private void adjustTabuList(){
        int tabulength = Math.max(2, (int) (this.TabuLength + Math.pow(0.5*Count, 0.5)) );
        while(TabuListIn.size() > tabulength && !TabuListIn.isEmpty()) TabuListIn.remove(TabuListIn.iterator().next());
        while(TabuListOut.size() > tabulength && !TabuListOut.isEmpty()) TabuListOut.remove(TabuListOut.iterator().next());
    }

    private void executeSwap(Candidate c){ executeSwap(c.getBlock(), c.getSwappedOutElement(), c.getSwappedInElement()); }


    private void executeSwap(Block b, int i, int j){ CoveringDesign.replaceElement(b, i, j); }


    private void setSolution(){
        Solution.clear();
        for (Block block : CoveringDesign.getBlocks()) Solution.add((BitSet) block.getElements().clone());
    }
}*/
