/*
package CoveringDesignProblem;
import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoveringDesignWrapperTest {

    @Test
    void CoveringDesignWrapperTest_Constructor1() {
        int v = 6;
        int k = 4;
        int t = 2;
        CoveringDesignWrapper wrapper = new CoveringDesignWrapper(v, k, t);

        BitSet vSet = new BitSet(v);
        Set<BitSet> allTSubsets = new HashSet<>();
        for(var blck : wrapper.getCoveringDesign().getBlocks()){
            vSet.or(blck.GetElements());
            allTSubsets.addAll(blck.getCoveredTSubsets());
        }

        BitSet expectedVSet = new BitSet(v);
        for(int i=0; i<v; i++) expectedVSet.set(i);
        assertEquals(expectedVSet, vSet);

        Set<BitSet> expectedAllTSubsets = wrapper.getCoveringDesign().getAllTSubsets();
        assertEquals(expectedAllTSubsets, allTSubsets);
    }

    @Test
    void CoveringDesignWrapperTest_Constructor2() {
        int v = 8;
        int k = 5;
        int t = 2;
        CoveringDesignWrapper wrapper = new CoveringDesignWrapper(v, k, t);

        BitSet vSet = new BitSet(v);
        Set<BitSet> allTSubsets = new HashSet<>();
        for(var blck : wrapper.getCoveringDesign().getBlocks()){
            vSet.or(blck.GetElements());
            allTSubsets.addAll(blck.getCoveredTSubsets());
        }

        BitSet expectedVSet = new BitSet(v);
        for(int i=0; i<v; i++) expectedVSet.set(i);
        assertEquals(expectedVSet, vSet);

        Set<BitSet> expectedAllTSubsets = wrapper.getCoveringDesign().getAllTSubsets();
        assertEquals(expectedAllTSubsets, allTSubsets);
    }
}
*/
