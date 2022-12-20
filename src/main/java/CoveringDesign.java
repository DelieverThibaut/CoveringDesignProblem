package CoveringDesignProblem;

import CoveringDesignProblem.Exceptions.InvalidConfigurationException;

import java.util.*;

/**
 * The CoveringDesign class represents a full design with blocks and subsets inside the CoveringDesignProblem-package.
 * This class contains also all methods that can be used on a certain CoveringDesign-object.
 *
 * @author  Thibaut Deliever
 * @version 1.0
 * @since   2022-10-31
 */

public class CoveringDesign {
    private final int V;
    private final int K;
    private final int T;
    private final Set<Block> Blocks;
    private final Map<BitSet, Integer> TSubsetsMap;
    private final Set<BitSet> UncoveredTSubsets;
    private final Set<BitSet> AllTSubsets;
    private final Set<Integer> VSet;


    public CoveringDesign(int v, int k, int t) throws InvalidConfigurationException {
        Debugger.log("FunctionCall_START \t\t~ CoveringDesign("+v+", "+k+", "+t+")");
        V = v;
        K = k;
        T = t;
        Blocks = new HashSet<>();
        TSubsetsMap = new HashMap<>();
        UncoveredTSubsets = new HashSet<>();
        AllTSubsets = new HashSet<>();
        VSet = new HashSet<>();

        if(!CheckConfiguration()) throw new InvalidConfigurationException(String.format("The configuration of v (= %1$d), k (= %2$d) & t (= %3$d) is not possible!", v, k, t));

        for(int i=0; i<v; i++) VSet.add(i);
        for(var set : Util.getAllSubsetsOfLengthK(VSet, t)){
            BitSet bitset = new BitSet(v);
            set.forEach(bitset::set);
            this.TSubsetsMap.put(bitset, 0);
            this.AllTSubsets.add(bitset);
            this.UncoveredTSubsets.add(bitset);
        }

        new CoveringDesignConstructor(this);
        Debugger.log("FunctionCall_END \t\t~ CoveringDesign("+v+", "+k+", "+t+")");
    }


    public int getV(){ return V; }
    public int getK(){ return K; }
    public int getT(){ return T; }
    public Set<Block> getBlocks(){ return Blocks; }
    public Map<BitSet, Integer> getTSubsetMap(){ return TSubsetsMap; }
    public Set<BitSet> getUncoveredTSubsets(){ return UncoveredTSubsets; }
    public Set<BitSet> getAllTSubsets(){ return AllTSubsets; }
    public Set<Integer> getVSet(){ return VSet; }
    public int getTSubsetValue(BitSet x){ return TSubsetsMap.getOrDefault(x, 0); }
    public int getConflicts(){ return UncoveredTSubsets.size(); }

    private boolean CheckConfiguration(){ return this.V > this.K && this.K > this.T && this.T >= 2; }


    public void addBlock(Block block){
        Blocks.add(block);
        UncoveredTSubsets.removeAll(block.getCoveredTSubsets());
        for(BitSet set : block.getCoveredTSubsets()) {
            int count = TSubsetsMap.getOrDefault(set, 0);
            TSubsetsMap.put(set, count + 1);
        }
    }
    public void removeBlock(Block block){
        Blocks.remove(block);
        for (BitSet set : block.getCoveredTSubsets()) {
            int count = TSubsetsMap.get(set);
            TSubsetsMap.put(set, count - 1);
            if (count == 1) UncoveredTSubsets.add(set);
        }
    }


    public void replaceElement(Block block, int x, int y){
        for (BitSet set : block.getCoveredTSubsets()) {
            if (set.get(x) && !set.get(y)) {
                int count = TSubsetsMap.get(set);
                TSubsetsMap.put(set, count - 1);
                if (count == 1) UncoveredTSubsets.add((BitSet) set.clone());

                set.set(x, false);
                set.set(y, true);

                count = TSubsetsMap.get(set);
                TSubsetsMap.put(set, count + 1);
                UncoveredTSubsets.remove(set);
            }
        }
        block.ReplaceElement(x, y);
    }
}
