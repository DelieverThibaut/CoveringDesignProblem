package CoveringDesignProblem;

import CoveringDesignProblem.Exceptions.InvalidConfigurationException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public final Map<Block, Integer> BlocksMap;
    private final Map<BitSet, Set<Block>> TSubsetsMap;
    private final Map<BitSet, Integer> UncoveredTSubsets;
    private final Set<BitSet> AllTSubsets;
    private final Set<Integer> VSet;
    private final Map<Integer, Set<Block>> VElementsMap;


    public CoveringDesign(int v, int k, int t) throws InvalidConfigurationException {
        Debugger.log("FunctionCall_START \t\t~ CoveringDesign("+v+", "+k+", "+t+")");
        V = v;
        K = k;
        T = t;
        Blocks = new HashSet<>();
        BlocksMap = new HashMap<>();
        TSubsetsMap = new HashMap<>();
        UncoveredTSubsets = new HashMap<>();
        AllTSubsets = new HashSet<>();
        VSet = new HashSet<>();
        VElementsMap = new HashMap<>();

        if(!CheckConfiguration()) throw new InvalidConfigurationException(String.format("The configuration of v (= %1$d), k (= %2$d) & t (= %3$d) is not possible!", v, k, t));

        for(int i=0; i<v; i++) VSet.add(i);
        for(var set : Util.getAllSubsetsOfLengthK(VSet, t)){
            BitSet bitset = new BitSet(v);
            set.forEach(bitset::set);
            this.TSubsetsMap.put(bitset, new HashSet<>());
            this.AllTSubsets.add(bitset);
            this.UncoveredTSubsets.put(bitset, 0);
        }

        new CoveringDesignConstructor(this);

        for(Block b: Blocks){
            BitSet elements = b.getElements();
            Set<Integer> ints = IntStream.iterate(elements.nextSetBit(0), x -> x != -1, x -> elements.nextSetBit(x + 1)).boxed().collect(Collectors.toSet());
            for(int e : ints) {
                Set<Block> x = VElementsMap.getOrDefault(e, new HashSet<>());
                x.add(b);
                VElementsMap.put(e, x);
            }
        }
        Debugger.log("FunctionCall_END \t\t~ CoveringDesign("+v+", "+k+", "+t+")");
    }


    public int getV(){ return V; }
    public int getK(){ return K; }
    public int getT(){ return T; }
    public Set<Block> getBlocks(){ return Blocks; }
    public Map<BitSet, Set<Block>> getTSubsetMap(){ return TSubsetsMap; }
    public Set<BitSet> getUncoveredTSubsets(){ return UncoveredTSubsets.keySet(); }
    public int getUncoveredTSubsetsValue(BitSet subset){ return UncoveredTSubsets.getOrDefault(subset, 0); }
    public Set<BitSet> getAllTSubsets(){ return AllTSubsets; }
    public Set<Integer> getVSet(){ return VSet; }
    public Set<Block> getTSubsetValue(BitSet x){ return TSubsetsMap.getOrDefault(x, new HashSet<>()); }
    /*public int getConflicts(){ return UncoveredTSubsets.values().stream().mapToInt(Integer::intValue).sum(); }*/
    public int getConflicts(){ return UncoveredTSubsets.keySet().size(); }
    public Set<Block> getVElementValue(int x){ return VElementsMap.getOrDefault(x, new HashSet<>()); }
    public int getBlockValue(Block block) { return BlocksMap.getOrDefault(block, 1); }

    private boolean CheckConfiguration(){ return this.V > this.K && this.K > this.T && this.T >= 2; }


    public void addBlock(Block block){
        Blocks.add(block);
        UncoveredTSubsets.keySet().removeAll(block.getCoveredTSubsets());
        for(BitSet set : block.getCoveredTSubsets()) {
            Set<Block> blcks = this.getTSubsetValue(set);
            blcks.add(block);
            TSubsetsMap.put(set, blcks);
        }
    }
    public void removeBlock(Block block){
        Blocks.remove(block);
        BlocksMap.remove(block);
        for (BitSet set : block.getCoveredTSubsets()) {
            Set<Block> blcks = this.getTSubsetValue(set);
            blcks.remove(block);
            TSubsetsMap.put(set, blcks);
            if(blcks.size() == 0) {
                UncoveredTSubsets.merge(set, 1, Integer::sum);
            }
        }

        BitSet elements = block.getElements();
        Set<Integer> ints = IntStream.iterate(elements.nextSetBit(0), x -> x != -1, x -> elements.nextSetBit(x + 1)).boxed().collect(Collectors.toSet());
        for(int e : ints) {
            Set<Block> x = VElementsMap.get(e);
            x.remove(block);
            VElementsMap.put(e, x);
        }
    }


    public void replaceElement(Block block, int x, int y){
        for (BitSet set : block.getCoveredTSubsets()) {
            if (set.get(x) && !set.get(y)) {
                Set<Block> blcks = this.getTSubsetValue(set);
                blcks.remove(block);
                TSubsetsMap.put(set, blcks);
                if (blcks.size() == 0) {
                    UncoveredTSubsets.put((BitSet) set.clone(), 0);
                }

                set.set(x, false);
                set.set(y, true);

                blcks = this.getTSubsetValue(set);
                blcks.add(block);
                TSubsetsMap.put(set, blcks);
                UncoveredTSubsets.remove(set);
            }
        }

        Set<Block> i = VElementsMap.get(x);
        i.remove(block);
        VElementsMap.put(x, i);

        i = VElementsMap.get(y);
        i.add(block);
        VElementsMap.put(y, i);

        Integer value = getBlockValue(block);
        BlocksMap.put(block, value+1);

        block.replaceElement(x, y);
    }

    public void incrementUncoveredTSubsetValue(){
        UncoveredTSubsets.replaceAll((k, v) -> v+1);
    }
/*
    public void replaceTSubset(Block block, BitSet x, BitSet y){
        BitSet xx = (BitSet) x.clone();
        xx.andNot(y);
        y.andNot(x);

        List<Integer> intsx = IntStream.iterate(xx.nextSetBit(0), i -> i != -1, i -> xx.nextSetBit(i + 1)).boxed().toList();
        List<Integer> intsy = IntStream.iterate(y.nextSetBit(0), i -> i != -1, i -> y.nextSetBit(i + 1)).boxed().toList();

        var min = intsx.size();
        if(intsx.size() != intsy.size()) {
            min = Math.min(intsx.size(), intsy.size());
        }
        for(int i=0; i < min; i++) replaceElement(block, intsx.get(i), intsy.get(i));

    }*/
}
