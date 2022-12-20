package CoveringDesignProblem.Interfaces;

import CoveringDesignProblem.CoveringDesign;

import java.util.BitSet;
import java.util.Set;

public interface IMetaHeuristicStrategy {
    public Set<BitSet> executeStrategy(CoveringDesign coveringDesign);
}
