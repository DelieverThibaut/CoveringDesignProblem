/*
package CoveringDesignProblem.MetaHeuristicsStrategies;

import CoveringDesignProblem.*;
import CoveringDesignProblem.Interfaces.IMetaHeuristicStrategy;

import java.util.*;
import java.util.stream.IntStream;

public class TabuSearch8 implements IMetaHeuristicStrategy {
    private final Random Rand = new Random();
    private CoveringDesign CoveringDesign;
    private final Set<BitSet> Solution = new HashSet<>();
    private ArrayList<Candidate_tsubset> CandidateList = new ArrayList<>();
    private final LinkedHashSet<TabuListItem_tsubset> TabuList = new LinkedHashSet<>();

    private double TabuLength;
    private int TabuLengthMin;

    private int Count = 0;
    private int TabuIterations = 50;
    private int LowestConflicts = 0;

    private boolean Diversification = false;

    private final Set<Block> NonRemovableBlocks = new HashSet<>();

    @Override
    public Set<BitSet> executeStrategy(CoveringDesign coveringDesign) {
        CoveringDesign = coveringDesign;
        TabuLengthMin = (CoveringDesign.getV() - CoveringDesign.getK()) / CoveringDesign.getK() + 1;
        TabuLength = Rand.nextInt(7*TabuLengthMin) + TabuLengthMin;
        TabuIterations = (int) (CoveringDesign.getBlocks().size() * (2 +  (1 / (double) CoveringDesign.getT()) ));

        saveSolutionAndRemoveBlock();
        while (true){
            incrementCount();
            checkDiversification();
            tabuSearch();

            var currentConflicts = CoveringDesign.getConflicts();
            if(currentConflicts < LowestConflicts && currentConflicts != 0){
                System.out.println("Better situation found: #Conflicts=" + currentConflicts + ", \ti=" + Count);
                betterSituationFound();
            }

            if(currentConflicts == 0){
                System.out.println("Better solution found:  #Blocks=" + CoveringDesign.getBlocks().size() +  ", \ti=" + Count);
                saveSolutionAndRemoveBlock();
            }
        }

    }

    private void tabuSearch(){
        int conflicts = CoveringDesign.getConflicts();
        if(conflicts == 0) return;

        if(Count == 1 && NonRemovableBlocks.size() != 0) NonRemovableBlocks.clear();

        generateCandidates();
        Candidate_tsubset bestCandidate = getBestCandidate();
        executeCandidate(bestCandidate);
    }

    private void generateCandidates() {
        CandidateList.clear();
        int currentConflicts = CoveringDesign.getConflicts();

        Set<BitSet> uncoveredSubsets = CoveringDesign.getUncoveredTSubsets();
        outerLoop:
        for(Block b : CoveringDesign.getBlocks()){
            Set<BitSet> blockSubsets = b.getCoveredTSubsets();
            for(BitSet i : blockSubsets)
                for(BitSet j : uncoveredSubsets){
                    int conflicts = currentConflicts + calculateDeltaConflictValue(b, i, j);
                    int delta = calculateObjectiveValue(b, i, j);
                    CandidateList.add(new Candidate_tsubset(b, i, j, conflicts, delta));
                    if (conflicts == 0) break outerLoop;
                }
        }
    }

    private int calculateDeltaConflictValue(Block block, BitSet x, BitSet y){
        int delta = 0;

        BitSet xx = (BitSet) x.clone();
        xx.andNot(y);

        BitSet yy =(BitSet) y.clone();
        yy.andNot(x);

        List<Integer> intsx = IntStream.iterate(xx.nextSetBit(0), i -> i != -1, i -> xx.nextSetBit(i + 1)).boxed().toList();
        List<Integer> intsy = IntStream.iterate(yy.nextSetBit(0), i -> i != -1, i -> yy.nextSetBit(i + 1)).boxed().toList();

        var min = intsx.size();
        if(intsx.size() != intsy.size()) {
            min = Math.min(intsx.size(), intsy.size());
        }
        for(int z=0; z < min; z++) {
            int i = intsx.get(z);
            int j = intsy.get(z);
            Iterator<BitSet> sets = block.getCoveredTSubsets().stream().filter(subset -> subset.get(i) && !subset.get(j)).iterator();
            while (sets.hasNext()) {
                BitSet set = sets.next();
                if (CoveringDesign.getTSubsetValue(set).size() == 1) delta++;
                set.set(i, false);
                set.set(j);
                if (CoveringDesign.getTSubsetValue(set).size() == 0) delta--;
                set.set(i);
                set.set(j, false);
            }
        }
        return delta;
    }

    private int calculateDeltaConflictValue(Block block){
        int delta = 0;
        for (BitSet set : block.getCoveredTSubsets()) {
            if (CoveringDesign.getTSubsetValue(set).size() == 1) delta++;
        }
        return delta;
    }

    private int calculateObjectiveValue(Block block, BitSet i, BitSet j){
        int delta = 0;
        */
/*if(Diversification) delta -= 3*block.getElementFrequency(i);*//*

        return delta;
    }

    private Candidate_tsubset getBestCandidate(){
        int conflicts = CoveringDesign.getConflicts();
        Candidate_tsubset bestCandidate = null;

        for(Candidate_tsubset c : CandidateList){
            if(bestCandidate == null && !isTabu(c)) bestCandidate = c;
            if(c.getConflicts() == 0){
                bestCandidate = c;
                break;
            } else if(bestCandidate != null && c.getDelta() == bestCandidate.getDelta() && !isTabu(c) && Rand.nextBoolean())
                bestCandidate = c;
            else if(bestCandidate != null && c.getDelta() < bestCandidate.getDelta() && !isTabu(c)) bestCandidate = c;
        }
        return bestCandidate;
    }


    */
/*private void perturbate(){
        ArrayList<Block> blocks = new ArrayList<>(CoveringDesign.getBlocks());
        Block blockToPerturbate = blocks.get(Rand.nextInt(blocks.size()));

        Set<Integer> elements = new HashSet<>(CoveringDesign.getVSet());
        Set<Integer> blockElements = IntStream.iterate(blockToPerturbate.getElements().nextSetBit(0), x -> x != -1, x -> blockToPerturbate.getElements().nextSetBit(x + 1)).boxed().collect(Collectors.toSet());
        elements.removeAll(blockElements);

        HashSet<Candidate> candidateHashSet = new HashSet<>();
        for(int i : blockElements) {
            Candidate candidateToExecute = null;
            for(int j : elements){
                int _conflicts = calculateDeltaConflictValue(blockToPerturbate, i, j);
                if(candidateToExecute == null || candidateToExecute.getConflicts() < _conflicts || ( candidateToExecute.getConflicts() == _conflicts && Rand.nextBoolean()) )
                    candidateToExecute = new Candidate(blockToPerturbate, i, j, _conflicts, 0);
            }
            if(candidateToExecute != null){
                candidateHashSet.add(candidateToExecute);
                elements.remove(candidateToExecute.getSwappedInElement());
            }

        }

        for(Candidate c : candidateHashSet) executeCandidate(c);
    }*//*


    private void betterSituationFound(){
        Count = 0;
        LowestConflicts = CoveringDesign.getConflicts();
    }

    private void saveSolutionAndRemoveBlock(){
        setSolution();
        removeBlock();
        clearTabuLists();
        if(!Diversification) resetElementFrequency();
        betterSituationFound();
    }

    private void clearTabuLists(){
        TabuList.clear();
    }

    private boolean isTabu(Candidate_tsubset c){
        return TabuList.contains(c.getTabuOut()) || TabuList.contains(c.getTabuIn());
    }

    private void removeBlock(){
        Debugger.log("FunctionCall_START \t\t~ removeBlock()");

        int max = -1;
        List<Block> blocksToRemove = new ArrayList<>();

        var blcks = new HashSet<>(CoveringDesign.getBlocks());
        blcks.removeAll(NonRemovableBlocks);
        if(blcks.size() == 0) blcks = (HashSet<Block>) CoveringDesign.getBlocks();

        for(Block blck : blcks){
            int delta = calculateDeltaConflictValue(blck);
            if (delta == 0){
                blocksToRemove.clear();
                blocksToRemove.add(blck);
                break;
            } else if(delta < max || max == -1){
                max = delta;
                blocksToRemove.clear();
                blocksToRemove.add(blck);
                NonRemovableBlocks.add(blck);
            } else if (delta == max) {
                blocksToRemove.add(blck);
                NonRemovableBlocks.add(blck);
            }
        }
        Block blockToRemove = blocksToRemove.get(Rand.nextInt(blocksToRemove.size()));
        CoveringDesign.removeBlock(blockToRemove);
        Debugger.log("FunctionCall_END \t\t~ removeBlock()");
    }

    private void executeCandidate(Candidate_tsubset c){
        executeSwap(c);
        makeTabu(c);
        adjustTabuList();
        if(!Diversification) incrementElementFrequency();
    }

    private void incrementElementFrequency(){
        CoveringDesign.getBlocks().forEach(Block::incrementFrequency);
    }

    private void resetElementFrequency(){
        CoveringDesign.getBlocks().forEach(Block::resetFrequency);
    }

    private void makeTabu(Candidate_tsubset c){
        this.TabuList.add(c.getTabuIn());
        this.TabuList.add(c.getTabuOut());
    }

    private void adjustTabuList(){
        int tabulength = Math.max(2, (int) (this.TabuLength + Math.pow(0.5*Count, 0.75)) * 2 );
        int randomLength = (Rand.nextInt(tabulength/2)+1) * 2;
        while(TabuList.size() > randomLength && !TabuList.isEmpty()) TabuList.remove(TabuList.iterator().next());
    }

    private void executeSwap(Candidate_tsubset c){ executeSwap(c.getBlock(), c.getSwappedOutTSubset(), c.getSwappedInTSubset()); }

    private void executeSwap(Block b, BitSet i, BitSet j){ CoveringDesign.replaceTSubset(b, i, j); }

    private void incrementCount(){
        Count++;
    }

    private void checkDiversification(){
        if(Count > 0 && Count%TabuIterations == 0 && Diversification) {
            System.out.println("Diversification FALSE");
            Diversification = false;
            resetElementFrequency();
        }
        else if(Count > 0 && Count%(3*TabuIterations) == 0 */
/*&& (Count/(3*TabuIterations) % 2) != 0*//*
 && !Diversification) {
            System.out.println("Diversification TRUE");
            Diversification = true;
        }
        */
/*else if(Count > 0 && Count%(3*TabuIterations) == 0 && (Count/(3*TabuIterations) % 2) == 0 && !Diversification) {
            System.out.println("Perturbation");
            perturbate();
        }*//*

    }

    private void setSolution(){
        Solution.clear();
        for (Block block : CoveringDesign.getBlocks()) Solution.add((BitSet) block.getElements().clone());
    }
}*/
