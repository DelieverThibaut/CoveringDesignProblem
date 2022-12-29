package CoveringDesignProblem;


public class Candidate {
    private final Block Block;
    private final int SwappedOutElement;
    private final int SwappedInElement;
    private int Conflicts;
    private int Delta;

    private TabuListItem TabuIn;
    private TabuListItem TabuOut;



    public Candidate(Block block, int el1, int el2, int conflicts, int delta){
        this.Block = block;
        this.SwappedOutElement = el1;
        this.SwappedInElement = el2;
        this.Conflicts = conflicts;
        this.Delta = conflicts + delta;

        setTabuOut(block, el1);
        setTabuIn(block, el2);
    }

    private void setTabuIn(Block block, int element){
        this.TabuIn = new TabuListItem(block, element);
    }
    public TabuListItem getTabuIn(){
        return this.TabuIn;
    }
    private void setTabuOut(Block block, int element){
        this.TabuOut = new TabuListItem(block, element);
    }
    public TabuListItem getTabuOut() {
        return this.TabuOut;
    }
    public int getConflicts(){ return this.Conflicts; }
    public int getDelta(){ return this.Delta; }
    public int getSwappedOutElement() { return this.SwappedOutElement; }
    public int getSwappedInElement() { return this.SwappedInElement; }
    public Block getBlock() { return this.Block; }


    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + getBlock().hashCode();
        result = prime * result + getSwappedOutElement();
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate c = (Candidate) o;
        return c.getBlock() == this.getBlock()
                && ((c.getSwappedOutElement() == this.getSwappedOutElement() && c.getSwappedInElement() == this.getSwappedInElement())
                    || (c.getSwappedOutElement() == this.getSwappedInElement() && c.getSwappedInElement() == this.getSwappedOutElement()));
    }
}
