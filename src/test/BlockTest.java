/*
package CoveringDesignProblem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.HashSet;


public class BlockTest {

    @Test
    void BlockTest_Constructor() {
        int v = 6;
        Block block = new Block(v, 2);

        Assertions.assertEquals(block.getV(), v, "Results should be equal");
        Assertions.assertEquals(block.GetElements(), new BitSet(v), "Results should be equal");
        Assertions.assertEquals(block.getCoveredTSubsets(), new HashSet<BitSet>(), "Results should be equal");
    }

    @Test
    void BlockTest_AddTSubset1() {
        int v = 6;
        Block block = new Block(v, 2);
        BitSet tSubset = new BitSet(v); //numbers -> {3, 4}
        tSubset.set(2);
        tSubset.set(3);

        block.addTSubset(tSubset);
        for(int i=1; i<=v; i++){
            if(i==3 || i==4) Assertions.assertTrue(block.hasElement(i));
            else Assertions.assertFalse(block.hasElement(i));
        }
        Assertions.assertTrue(block.hasTSubset(tSubset));
    }

    @Test
    void BlockTest_AddTSubset2() {
        int v = 6;
        Block block = new Block(v, 2);
        BitSet tSubset = new BitSet(v); //numbers -> {3, 4}
        tSubset.set(2);
        tSubset.set(3);
        block.addTSubset(tSubset);

        tSubset = new BitSet(v); //numbers -> {2, 3}
        tSubset.set(1);
        tSubset.set(2);
        block.addTSubset(tSubset);
        for(int i=1; i<=v; i++){
            if(i==2 || i==3 || i==4) Assertions.assertTrue(block.hasElement(i));
            else Assertions.assertFalse(block.hasElement(i));
        }
        Assertions.assertTrue(block.hasTSubset(tSubset));

        tSubset = new BitSet(v);
        tSubset.set(1);
        tSubset.set(3);
        Assertions.assertTrue(block.hasTSubset(tSubset));
    }

    @Test
    void BlockTest_SimulateAddTSubset1() {
        int v = 6;
        Block block = new Block(v, 2);
        BitSet tSubset = new BitSet(v); //numbers -> {3, 4}
        tSubset.set(2);
        tSubset.set(3);
        Assertions.assertEquals(block.simulateAddTSubset(tSubset), 2);
        Assertions.assertEquals(block.size(), 0);
    }

    @Test
    void BlockTest_SimulateAddTSubset2() {
        int v = 6;
        Block block = new Block(v, 2);
        BitSet tSubset = new BitSet(v); //numbers -> {3, 4}
        tSubset.set(2);
        tSubset.set(3);
        block.addTSubset(tSubset);

        tSubset = new BitSet(v); //numbers -> {2, 4}
        tSubset.set(1);
        tSubset.set(2);
        Assertions.assertEquals(block.simulateAddTSubset(tSubset), 3);
        Assertions.assertEquals(block.size(), 2);
    }

    @Test
    void BlockTest_RemoveElement1() {
        int v = 6;
        Block block = new Block(v, 2);
        BitSet tSubset = new BitSet(v); //numbers -> {3, 4}
        tSubset.set(2);
        tSubset.set(3);
        block.addTSubset(tSubset);

        block.RemoveElement(3);
        Assertions.assertFalse(block.hasElement(3));
        Assertions.assertTrue(block.hasElement(4));
        Assertions.assertEquals(block.size(), 1);
        Assertions.assertEquals(block.getCoveredTSubsets().size(), 0);
    }

    @Test
    void BlockTest_RemoveElement2() {
        int v = 6;
        Block block = new Block(v, 2);
        BitSet tSubset1 = new BitSet(v); //numbers -> {3, 4}
        tSubset1.set(2);
        tSubset1.set(3);
        block.addTSubset(tSubset1);

        BitSet tSubset2 = new BitSet(v); //numbers -> {2, 3}
        tSubset2.set(1);
        tSubset2.set(2);
        block.addTSubset(tSubset2);

        block.RemoveElement(3);
        Assertions.assertFalse(block.hasElement(3));
        Assertions.assertTrue(block.hasElement(2));
        Assertions.assertTrue(block.hasElement(4));
        Assertions.assertEquals(block.size(), 2);
        Assertions.assertEquals(block.getCoveredTSubsets().size(), 1);

        BitSet tSubset = new BitSet(v); //numbers -> 2, 4
        tSubset.set(1);
        tSubset.set(3);
        Assertions.assertTrue(block.hasTSubset(tSubset));
    }

    @Test
    void BlockTest_AddElement1() {
        int v = 6;
        Block block = new Block(v, 2);
        block.AddElement(2);

        for(int i=1; i<=v; i++){
            if(i == 2) Assertions.assertTrue(block.hasElement(i));
            else Assertions.assertFalse(block.hasElement(i));
        }
        Assertions.assertEquals(block.size(), 1);
        Assertions.assertEquals(block.getCoveredTSubsets().size(), 0);
    }

    @Test
    void BlockTest_AddElement2() {
        int v = 6;
        Block block = new Block(v, 2);
        BitSet tSubset1 = new BitSet(v); //numbers -> {3, 4}
        tSubset1.set(2);
        tSubset1.set(3);
        block.addTSubset(tSubset1);
        block.AddElement(2);

        for(int i=1; i<=v; i++){
            if(i==2 || i==3 || i==4) Assertions.assertTrue(block.hasElement(i));
            else Assertions.assertFalse(block.hasElement(i));
        }
        Assertions.assertEquals(block.size(), 3);
        Assertions.assertEquals(block.getCoveredTSubsets().size(), 3);
        Assertions.assertTrue(block.hasTSubset(tSubset1));

        BitSet tSubset = new BitSet(v); //numbers -> 2, 4
        tSubset.set(1);
        tSubset.set(3);
        Assertions.assertTrue(block.hasTSubset(tSubset));

        tSubset = new BitSet(v); //numbers -> 2, 4
        tSubset.set(1);
        tSubset.set(2);
        Assertions.assertTrue(block.hasTSubset(tSubset));
    }


}
*/
