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
    private Set<Integer> UncoveredIntegers;
    private Set<Integer> TooMuchCoveredIntegers;
    private Set<Candidate> CandidateList;
    private final LinkedHashSet<Candidate> TabuList = new LinkedHashSet<>();

    private int TabuLength;
    private int TabuLengthMax;
    private final int MaxIterations = 5000;


    @Override
    public Set<BitSet> executeStrategy(CoveringDesign coveringDesign) {
        /*System.out.println("FunctionCall_START \t\t~ executeStrategy()");*/
        CoveringDesign = coveringDesign;
        CandidateList = new HashSet<>();
        UncoveredIntegers = new HashSet<>();
        TooMuchCoveredIntegers = new HashSet<>();
        TabuLengthMax = Util.getAllSubsetsOfLengthK(CoveringDesign.getVSet(), 2).size() * CoveringDesign.getBlocks().size() / 2;
        TabuLength = TabuLengthMax / 3;

        setSolution();
        removeBlock();
        int count = 0;
        while (count++ < MaxIterations){
            if(CoveringDesign.getConflicts() == 0){
                setSolution();
                removeBlock();
                TabuList.clear();
                count = 0;
            }
            else if(!tabuSearch()){
                break;
            }
        }
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

        int conflicts = CoveringDesign.getConflicts();
        outerLoop:
        for (Integer i : TooMuchCoveredIntegers)
            for (Integer j : UncoveredIntegers)
                if (!i.equals(j))
                    for (Block block : CoveringDesign.getBlocks())
                        if (isValidSwap(block, i, j)) {
                            int delta = 0;
                            Set<BitSet> sets = block.getCoveredTSubsets().stream().filter(subset -> subset.get(i)).collect(Collectors.toSet());
                            for(BitSet set : sets){
                                if(CoveringDesign.getTSubsetValue(set) == 1) delta++;
                                set.set(i, false);
                                set.set(j);
                                if(CoveringDesign.getTSubsetValue(set) == 0) delta--;
                                set.set(i);
                                set.set(j, false);
                            }
                            CandidateList.add(new Candidate(block, i, j, conflicts+delta));
                            if (conflicts + delta == 0) break outerLoop;
                        }

        if(CandidateList.size() == 0){
            outerLoop2:
            for (Integer i : TooMuchCoveredIntegers)
                for (Integer j : CoveringDesign.getVSet())
                    if (!i.equals(j))
                        for (Block block : CoveringDesign.getBlocks())
                            if (isValidSwap(block, i, j)) {
                                int delta = 0;
                                Set<BitSet> sets = block.getCoveredTSubsets().stream().filter(subset -> subset.get(i)).collect(Collectors.toSet());
                                for(BitSet set : sets){
                                    if(CoveringDesign.getTSubsetValue(set) == 1) delta++;
                                    set.set(i, false);
                                    set.set(j);
                                    if(CoveringDesign.getTSubsetValue(set) == 0) delta--;
                                    set.set(i);
                                    set.set(j, false);
                                }
                                CandidateList.add(new Candidate(block, i, j, conflicts+delta));
                                if (conflicts + delta == 0) break outerLoop2;
                            }
        }
    }


    private boolean isValidSwap(Block block, int i, int j){
        return block.hasElement(i) && !block.hasElement(j);
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
        /*System.out.println("\tFunctionCall_INFO \t~ removeBlock() - Block: " + blockToRemove.getElements());*/
        CoveringDesign.removeBlock(blockToRemove);
        CandidateList.removeIf(c -> c.getBlock() == blockToRemove);
        Debugger.log("FunctionCall_END \t\t~ removeBlock()");
    }


    private void executeCandidate(Candidate c){
        executeSwap(c);
        TabuList.add(c);
        int tabuLength = (int) (TabuLength*1.05);
        int randomLength = Rand.nextInt(TabuLengthMax-TabuLength) + 1;
        while(TabuList.size() > tabuLength + randomLength && !TabuList.isEmpty()) TabuList.remove(TabuList.iterator().next());
    }


    private void executeSwap(Candidate c){ executeSwap(c.getBlock(), c.getSwappedOutElement(), c.getSwappedInElement()); }


    private void executeSwap(Block b, int i, int j){ CoveringDesign.replaceElement(b, i, j); }


    private void setSolution(){
        Solution.clear();
        for (Block block : CoveringDesign.getBlocks()) Solution.add((BitSet) block.getElements().clone());
    }
}