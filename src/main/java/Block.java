package CoveringDesignProblem;

import CoveringDesignProblem.Exceptions.ElementAlreadyInBlockException;
import CoveringDesignProblem.Exceptions.ElementNotFoundException;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Block {
    private int V;
    private int T;
    private BitSet Elements;
    private Set<BitSet> CoveredTSubsets;
    private HashMap<Integer, Integer> ElementFrequencyMap = new HashMap<>();


    public Block(int v, int t) {
        Debugger.log("FunctionCall_START \t\t~ Block("+v+")");
        V = v;
        T = t;
        Elements = new BitSet(v);
        CoveredTSubsets = new HashSet<>();

        Debugger.log("FunctionCall_END \t\t~ Block("+v+")");
    }
    public Block(int v, int t, Set<Integer> elements) {
        Debugger.log("FunctionCall_START \t\t~ Block("+v+", "+elements+")");
        V = v;
        T = t;
        Elements = new BitSet(v);
        CoveredTSubsets = new HashSet<>();

        elements.forEach(this::addElement);
        CoveredTSubsets.addAll(generateAllTSubsets(elements, t));
        Debugger.log("FunctionCall_END \t\t~ Block("+v+")");
    }


    public int getT(){ return T; }
    public int getV() { return this.V; }
    public BitSet getElements(){ return Elements; }
    public Set<BitSet> getCoveredTSubsets(){ return CoveredTSubsets; }
    public int getElementFrequency(int element){ return ElementFrequencyMap.getOrDefault(element, 0); }
    public void resetElementFrequency(){ ElementFrequencyMap.clear(); }

    public void incrementFrequency(){
        IntStream.iterate(Elements.nextSetBit(0), i -> i != -1, i -> Elements.nextSetBit(i + 1)).forEach(i -> ElementFrequencyMap.merge(i, 1, Integer::sum));
    }

    public void resetFrequency(){
        ElementFrequencyMap.clear();
    }

    private Set<BitSet> generateAllTSubsets(Set<Integer> elements, int t){
        return Util.getAllSubsetsOfLengthK(elements, t).stream().map(this::convertIntegerSetToBitSet).collect(Collectors.toCollection(HashSet::new));
    }

    private BitSet convertIntegerSetToBitSet(Set<Integer> set){
        var result = new BitSet(V);
        set.forEach(result::set);
        return result;
    }


    public int size(){ return Elements.cardinality(); }


    public boolean hasElement(int x){ return Elements.get(x); }
    public boolean hasTSubset(BitSet tSubset){ return CoveredTSubsets.contains(tSubset); }


    public void addTSubset(BitSet tSubset){
        Elements.or(tSubset);

        CoveredTSubsets.clear();
        if(Elements.cardinality() >= T) {
            Set<Integer> ElementIntegers = IntStream.iterate(this.Elements.nextSetBit(0), i -> i >= 0, i -> this.Elements.nextSetBit(i + 1)).boxed().collect(Collectors.toSet());
            Util.getAllSubsetsOfLengthK(ElementIntegers, T).forEach(set -> {
                BitSet coveredSet = new BitSet(V);
                set.forEach(coveredSet::set);
                CoveredTSubsets.add(coveredSet);
            });
        }
    }
    public int simulateAddTSubset(BitSet tSubset){
        BitSet elementsClone = (BitSet) this.Elements.clone();
        elementsClone.or(tSubset);
        return elementsClone.cardinality();
    }


    public void addElement(int x){
        Elements.set(x);
        CoveredTSubsets.clear();
        if(Elements.cardinality() >= T) {
            Set<Integer> ElementIntegers = IntStream.iterate(this.Elements.nextSetBit(0), i -> i >= 0, i -> this.Elements.nextSetBit(i + 1)).boxed().collect(Collectors.toSet());
            Util.getAllSubsetsOfLengthK(ElementIntegers, T).forEach(set -> {
                BitSet coveredSet = new BitSet(V);
                set.forEach(coveredSet::set);
                CoveredTSubsets.add(coveredSet);
            });
        }
    }


    public void replaceElement(int x, int y){
        Debugger.log("FunctionCall_START \t~ replaceElement("+x+", "+y+")");
        if(!this.hasElement(x)) throw new ElementNotFoundException(String.format("The element (= %1$d) is not found inside this block!", x));
        if(this.hasElement(y)) throw new ElementAlreadyInBlockException(String.format("The element (= %1$d) is already inside this block!", y));

        this.Elements.set(x, false);
        this.Elements.set(y);

        this.CoveredTSubsets.stream().filter(subset -> subset.get(x)).forEach(subset -> {
            subset.set(x, false);
            subset.set(y, true);
        });
        Debugger.log("FunctionCall_END \t~ replaceElement("+x+", "+y+")");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block b = (Block) o;
        return b.getElements() == getElements();
    }
}
