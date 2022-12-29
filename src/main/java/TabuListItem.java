package CoveringDesignProblem;


public class TabuListItem {
    private Block Block;
    private int SwappedElement;


    public TabuListItem(Block block, int el){
        this.Block = block;
        this.SwappedElement = el;
    }

    public int getSwappedElement() { return this.SwappedElement; }
    public Block getBlock() { return this.Block; }


    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + getBlock().hashCode();
        result = prime * result + getSwappedElement();
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabuListItem c = (TabuListItem) o;
        return c.getBlock() == this.getBlock()
                && c.getSwappedElement() == this.getSwappedElement();
    }
}
