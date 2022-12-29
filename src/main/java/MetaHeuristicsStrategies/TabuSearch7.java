package CoveringDesignProblem.MetaHeuristicsStrategies;

import CoveringDesignProblem.*;
import CoveringDesignProblem.Interfaces.IMetaHeuristicStrategy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TabuSearch7 implements IMetaHeuristicStrategy {
    private final Random Rand = new Random();
    private CoveringDesign CoveringDesign;
    private final Set<BitSet> Solution = new HashSet<>();
    private final Set<BitSet> BestSituation = new HashSet<>();
    private ArrayList<Candidate> CandidateList = new ArrayList<>();

    private final LinkedHashSet<TabuListItem> TabuListIn = new LinkedHashSet<>();
    private final LinkedHashSet<TabuListItem> TabuListOut = new LinkedHashSet<>();

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
        TabuIterations = (int) (CoveringDesign.getBlocks().size() * (2 +  (1 / (double) CoveringDesign.getT()) ) * Math.pow(CoveringDesign.getBlocks().size(), 0.6));

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
        Candidate bestCandidate = getBestCandidate();
        if(bestCandidate == null){
            clearTabuLists();
            perturbate();
        }
        else executeCandidate(bestCandidate);
    }

    private void generateCandidates() {
        CandidateList.clear();
        int currentConflicts = CoveringDesign.getConflicts();

        Set<Integer> iInts;
        iInts = new HashSet<>();
        for(BitSet subsetJ : CoveringDesign.getUncoveredTSubsets()) {
            Set<Integer> ints = IntStream.iterate(subsetJ.nextSetBit(0), x -> x != -1, x -> subsetJ.nextSetBit(x + 1)).boxed().collect(Collectors.toSet());
            iInts.addAll(ints);
        }

        outerLoop:
        for(Block b : CoveringDesign.getBlocks()){
            var elements = new HashSet<>(iInts);
            Set<Integer> blockElements = IntStream.iterate(b.getElements().nextSetBit(0), x -> x != -1, x -> b.getElements().nextSetBit(x + 1)).boxed().collect(Collectors.toSet());
            elements.removeAll(blockElements);
            for(int i : blockElements)
                for(int j : elements){
                    int conflicts = currentConflicts + calculateDeltaConflictValue(b, i, j);
                    int delta = calculateObjectiveValue(b, i, j);
                    CandidateList.add(new Candidate(b, i, j, conflicts, delta));
                    if (conflicts == 0) break outerLoop;
                }
        }
    }

    private int calculateDeltaConflictValue(Block block, int i, int j){
        int delta = 0;
        Iterator<BitSet> sets = block.getCoveredTSubsets().stream().filter(subset -> subset.get(i)).iterator();
        while (sets.hasNext()) {
            BitSet set = sets.next();
            if (CoveringDesign.getTSubsetValue(set).size() == 1) delta ++;
            set.set(i, false);
            set.set(j);
            /*if (CoveringDesign.getTSubsetValue(set).size() == 0) delta -= CoveringDesign.getUncoveredTSubsetsValue(set);*/
            if (CoveringDesign.getTSubsetValue(set).size() == 0) delta --;
            set.set(i);
            set.set(j, false);
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

    private int calculateObjectiveValue(Block block, int i, int j){
        int delta = 0;
        /*if(Diversification) {
            delta -= Math.pow(block.getElementFrequency(i), 2);
        }*/
        return delta;
    }

    private Candidate getBestCandidate(){
        int conflicts = CoveringDesign.getConflicts();
        ArrayList<Candidate> bestCandidates = new ArrayList<>();
        int bestDelta = -1;
        int bestConflicts = -1;

        for(Candidate c : CandidateList){
            if(bestDelta == -1 && !isTabu(c)) {
                bestCandidates.add(c);
                bestDelta = c.getDelta();
                bestConflicts = c.getConflicts();
            }
            if(c.getConflicts() == 0){
                bestCandidates.clear();
                bestCandidates.add(c);
                break;
            } else if(c.getConflicts() == bestConflicts && c.getDelta() == bestDelta && !isTabu(c))
                bestCandidates.add(c);
            else if ((c.getConflicts() == bestConflicts) && (c.getDelta() < bestDelta) && !isTabu(c)) {
                bestCandidates.clear();
                bestCandidates.add(c);
                bestDelta = c.getDelta();
            } else if (c.getConflicts() < bestConflicts && !isTabu(c)) {
                bestCandidates.clear();
                bestCandidates.add(c);
                bestDelta = c.getDelta();
                bestConflicts = c.getConflicts();
            }

            /*else if(Diversification && c.getDelta() == bestDelta && c.getConflicts() == bestConflicts && !isTabu(c))
                bestCandidates.add(c);
            else if (Diversification && (c.getDelta() == bestDelta) && (c.getConflicts() < bestConflicts) && !isTabu(c)) {
                bestCandidates.clear();
                bestCandidates.add(c);
                bestConflicts = c.getConflicts();
            } else if (Diversification && (c.getDelta() < bestDelta) && !isTabu(c)) {
                bestCandidates.clear();
                bestCandidates.add(c);
                bestDelta = c.getDelta();
                bestConflicts = c.getConflicts();
            }*/
        }
        if(bestCandidates.size() == 0) return null;
        else if(bestCandidates.size() == 1) return bestCandidates.get(0);
        else return bestCandidates.get(Rand.nextInt(bestCandidates.size()));
    }


    private void perturbate(){
        System.out.println("PERTURBATION");
        ArrayList<Block> blocks = new ArrayList<>();
        int val = -1;
        for(Block b : CoveringDesign.getBlocks()){
            int calc = b.getCoveredTSubsets().stream().mapToInt(set -> CoveringDesign.getTSubsetValue(set).size()).sum();
            if(val == -1 || calc > val){
                val = calc;
                blocks.clear();
                blocks.add(b);
            } else if(b.getCoveredTSubsets().size() == val) blocks.add(b);
        }

        Block blockToPerturbate = blocks.get(Rand.nextInt(blocks.size()));
        Set<Integer> blockElements = IntStream.iterate(blockToPerturbate.getElements().nextSetBit(0), x -> x != -1, x -> blockToPerturbate.getElements().nextSetBit(x + 1)).boxed().collect(Collectors.toSet());

        for(int i : blockElements) {
            if(CoveringDesign.getConflicts() == 0) break;
            Set<Integer> elements = new HashSet<>(CoveringDesign.getVSet());
            Set<Integer> _blockElements = IntStream.iterate(blockToPerturbate.getElements().nextSetBit(0), x -> x != -1, x -> blockToPerturbate.getElements().nextSetBit(x + 1)).boxed().collect(Collectors.toSet());
            elements.removeAll(_blockElements);
            Candidate candidateToExecute = null;
            for(int j : elements){
                int _conflicts = calculateDeltaConflictValue(blockToPerturbate, i, j);
                if(candidateToExecute == null || candidateToExecute.getConflicts() > _conflicts || ( candidateToExecute.getConflicts() == _conflicts && Rand.nextBoolean()) )
                    candidateToExecute = new Candidate(blockToPerturbate, i, j, _conflicts, 0);
            }
            if(candidateToExecute != null){
                executeCandidate(candidateToExecute);
                elements.remove(candidateToExecute.getSwappedInElement());
            }
        }
    }

    private void betterSituationFound(){
        Count = 0;
        LowestConflicts = CoveringDesign.getConflicts();
        setBetterSituation();
    }

    private void saveSolutionAndRemoveBlock(){
        setSolution();
        removeBlock();
        clearTabuLists();
        if(!Diversification) resetElementFrequency();
        betterSituationFound();
    }

    private void clearTabuLists(){
        TabuListIn.clear();
        TabuListOut.clear();
    }

    private boolean isTabu(Candidate c){
        return TabuListOut.contains(c.getTabuIn()) || TabuListIn.contains(c.getTabuOut());
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

    private void executeCandidate(Candidate c){
        executeSwap(c);
        makeTabu(c);
        adjustTabuList();
        CoveringDesign.incrementUncoveredTSubsetValue();
        if(!Diversification) incrementElementFrequency();
    }

    private void incrementElementFrequency(){
        CoveringDesign.getBlocks().forEach(Block::incrementFrequency);
    }

    private void resetElementFrequency(){
        CoveringDesign.getBlocks().forEach(Block::resetFrequency);
    }

    private void makeTabu(Candidate c){
        this.TabuListIn.add(c.getTabuIn());
        this.TabuListOut.add(c.getTabuOut());
    }

    private void adjustTabuList(){
        int tabulength = Math.max(2, (int) (this.TabuLength + Math.pow(0.25*Count, 0.65)));
        int randomLength = Rand.nextInt(tabulength/2);
        while(TabuListIn.size() > randomLength && !TabuListIn.isEmpty()) TabuListIn.remove(TabuListIn.iterator().next());

        randomLength = Rand.nextInt(tabulength);
        while(TabuListOut.size() > randomLength && !TabuListOut.isEmpty()) TabuListOut.remove(TabuListOut.iterator().next());
    }

    private void executeSwap(Candidate c){ executeSwap(c.getBlock(), c.getSwappedOutElement(), c.getSwappedInElement()); }

    private void executeSwap(Block b, int i, int j){ CoveringDesign.replaceElement(b, i, j); }

    private void incrementCount(){
        Count++;
    }

    private void checkDiversification(){
        /*if(Count > 0 && Count%(TabuIterations/4) == 0 && Diversification) {
            System.out.println("Diversification FALSE");
            Diversification = false;
            resetElementFrequency();
        }
        else */if(Count > 0 && Count%(2*TabuIterations) == 0 && !Diversification) {
            /*System.out.println("Diversification TRUE");*/
            perturbate();
            resetElementFrequency();
            /*Diversification = true;*/
        }
    }

    private void setSolution(){
        Solution.clear();
        for (Block block : CoveringDesign.getBlocks()) Solution.add((BitSet) block.getElements().clone());
    }

    private void setBetterSituation(){
        BestSituation.clear();
        for (Block block : CoveringDesign.getBlocks()) BestSituation.add((BitSet) block.getElements().clone());
    }
}
