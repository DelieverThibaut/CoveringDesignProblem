package CoveringDesignProblem;


public class Candidate {
    private Block Block;
    private int SwappedOutElement;
    private int SwappedInElement;
    private int Conflicts;


    public Candidate(Block block, int el1, int el2, int conflicts){
        this.Block = block;
        this.SwappedOutElement = el1;
        this.SwappedInElement = el2;
        this.Conflicts = conflicts;
    }


    public int getConflicts(){ return this.Conflicts; }
    public void setConflicts(int conflict) { this.Conflicts = conflict; }
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
                && c.getSwappedOutElement() == this.getSwappedOutElement()
                && c.getSwappedInElement() == this.getSwappedInElement();
    }
}
