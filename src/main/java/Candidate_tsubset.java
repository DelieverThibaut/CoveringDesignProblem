/*
package CoveringDesignProblem;


import java.util.BitSet;

public class Candidate_tsubset {
    private final Block Block;
    private final BitSet SwappedOutTSubset;
    private final BitSet SwappedInTSubset;
    private int Conflicts;
    private int Delta;

    private TabuListItem_tsubset TabuIn;
    private TabuListItem_tsubset TabuOut;



    public Candidate_tsubset(Block block, BitSet el1, BitSet el2, int conflicts, int delta){
        this.Block = block;
        this.SwappedOutTSubset = el1;
        this.SwappedInTSubset = el2;
        this.Conflicts = conflicts;
        this.Delta = conflicts + delta;

        setTabuOut(block, el1);
        setTabuIn(block, el2);
    }

    private void setTabuIn(Block block, BitSet subset){
        this.TabuIn = new TabuListItem_tsubset(block, subset);
    }
    public TabuListItem_tsubset getTabuIn(){
        return this.TabuIn;
    }
    private void setTabuOut(Block block, BitSet subset){
        this.TabuOut = new TabuListItem_tsubset(block, subset);
    }
    public TabuListItem_tsubset getTabuOut() {
        return this.TabuOut;
    }
    public int getConflicts(){ return this.Conflicts; }
    public int getDelta(){ return this.Delta; }
    public BitSet getSwappedOutTSubset() { return this.SwappedOutTSubset; }
    public BitSet getSwappedInTSubset() { return this.SwappedInTSubset; }
    public Block getBlock() { return this.Block; }


    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + getBlock().hashCode();
        result = prime * result + getSwappedOutTSubset().hashCode();
        result = prime * result + getSwappedInTSubset().hashCode();
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate_tsubset c = (Candidate_tsubset) o;
        return c.getBlock() == this.getBlock()
                && c.getSwappedInTSubset() == this.getSwappedInTSubset()
                && c.getSwappedOutTSubset() == this.getSwappedOutTSubset();
    }
}
*/
