/*
package CoveringDesignProblem;

import Exceptions.ElementAlreadyInBlockException;
import CoveringDesignProblem.Exceptions.ElementNotFoundException;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class Block {
    private int V;
    private int T;
    private BitSet Elements;
    private Set<BitSet> CoveredTSubsets;

    public Block(int v, int t) {
        Debugger.log("FunctionCall_START \t\t~ Block("+v+")");

        this.V = v;
        this.T = t;
        this.Elements = new BitSet(v);
        this.CoveredTSubsets = new HashSet<>();

        Debugger.log("FunctionCall_END \t\t~ Block("+v+")");
    }

    public Block(int v, int t, Set<Integer> elements) {
        Debugger.log("FunctionCall_START \t\t~ Block("+v+", "+elements+")");

        this.V = v;
        this.T = t;
        this.Elements = new BitSet(v);
        this.CoveredTSubsets = new HashSet<>();

        elements.forEach(this::AddElement);

        Debugger.log("FunctionCall_END \t\t~ Block("+v+")");
    }

    public int Size(){ return this.Elements.cardinality(); }

    public Set<BitSet> GetCoveredTSubsets(){ return this.CoveredTSubsets; }

    public BitSet GetElements(){ return this.Elements; }

    public int GetV() { return this.V; }

    public boolean HasElement(int x){ return this.Elements.get(x-1); }

    public boolean HasElement(BitSet x){ return x.intersects(this.Elements); }

    public boolean HasTSubset(BitSet tSet){ return this.CoveredTSubsets.contains(tSet); }

    */
/*public void AddElement(int x){
        for (int i = this.Elements.nextSetBit(0); i >= 0; i = this.Elements.nextSetBit(i+1)) {
            if(i != x-1) {
                BitSet newTSubset = new BitSet(this.V);
                newTSubset.set(i);
                newTSubset.set(x-1);
                this.CoveredTSubsets.add(newTSubset);
            }
        }
        this.Elements.set(x-1);
    }
    *//*


    public void AddElement(int x){
        this.CoveredTSubsets.clear();
        this.Elements.set(x-1);

        if(this.Elements.cardinality() >= T) {
            Set<Integer> ElementIntegers = new HashSet<>();
            for (int i = this.Elements.nextSetBit(0); i >= 0; i = this.Elements.nextSetBit(i+1))
                ElementIntegers.add(i + 1);

            var result = Util.GetAllSubsetsOfLengthK(ElementIntegers, T);
            result.forEach(set ->{
                var coveredSet = new BitSet(V);
                set.forEach(el -> coveredSet.set(el-1));
                CoveredTSubsets.add(coveredSet);
            });
            var xx = 1;
        }
    }

    public void RemoveElement(int x){
        this.Elements.set(x-1, false);
        this.CoveredTSubsets.removeIf(element -> element.get(x-1));
    }

    public void AddTSubset(BitSet tSubset){
        CoveredTSubsets.add(tSubset);
        for (int i = tSubset.nextSetBit(0); i >= 0; i = tSubset.nextSetBit(i+1)) {
            AddElement(i+1);
        }
    }

    public int SimulateAddTSubset(BitSet tSubset){
        BitSet elementsClone = (BitSet) this.Elements.clone();
        elementsClone.or(tSubset);
        return elementsClone.cardinality();
    }

    public void ReplaceElement(int x, int y){
        Debugger.log("FunctionCall_START \t~ replaceElement(x, y)");

        if(!this.HasElement(x)) throw new ElementNotFoundException(String.format("The element (= %1$d) is not found inside this block!", x));
        if(this.HasElement(y)) throw new ElementAlreadyInBlockException(String.format("The element (= %1$d) is already inside this block!", y));

        this.Elements.set(x-1, false);
        this.Elements.set(y-1);

        this.CoveredTSubsets.stream().filter(subset -> subset.get(x - 1)).forEach(subset -> {
            subset.set(x - 1, false);
            subset.set(y - 1, true);
        });


        Debugger.log("FunctionCall_END \t~ replaceElement(x, y)");
    }

    */
/*
    public void ReplaceElement(int x, int y){
        Debugger.log("FunctionCall_START \t~ replaceElement(x, y)");

        if(!this.HasElement(x)) throw new ElementNotFoundException(String.format("The element (= %1$d) is not found inside this block!", x));
        if(this.HasElement(y)) throw new ElementAlreadyInBlockException(String.format("The element (= %1$d) is already inside this block!", y));

        this.RemoveElement(x);
        this.AddElement(y);

        Debugger.log("FunctionCall_END \t~ replaceElement(x, y)");
    }*//*

}
*/
