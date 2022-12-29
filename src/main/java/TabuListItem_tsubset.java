/*
package CoveringDesignProblem;


import java.util.BitSet;

public class TabuListItem_tsubset {
    private Block Block;
    private BitSet SwappedTSubset;


    public TabuListItem_tsubset(Block block, BitSet subset){
        this.Block = block;
        this.SwappedTSubset = subset;
    }

    public BitSet getSwappedElement() { return this.SwappedTSubset; }
    public Block getBlock() { return this.Block; }


    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + getBlock().hashCode();
        result = prime * result + getSwappedElement().hashCode();
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabuListItem_tsubset c = (TabuListItem_tsubset) o;
        return c.getBlock() == this.getBlock()
                && c.getSwappedElement() == this.getSwappedElement();
    }
}
*/
