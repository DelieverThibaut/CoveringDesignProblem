package CoveringDesignProblem.Cases;

import CoveringDesignProblem.CoveringDesignWrapper;
import CoveringDesignProblem.Main;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * V = 30
 * K = 14
 * T = 7
 *
 * CurrentSolution = 1805;
 * MinimalSolution = 804;
 */
public class Test_30_14_7 {
    private final int V = 30;
    private final int K = 14;
    private final int T = 7;
    private final int ExpectedCoveringNum = 804;

    @RepeatedTest(1000)
    void coveringDesign_Strat5() {
        int strat = 5;
        CoveringDesignWrapper cd = new CoveringDesignWrapper(V, K, T, strat, false);
        cd.printSolution();
        assertTrue(cd.checkSolution());
        assertEquals(ExpectedCoveringNum, cd.getSolution().size());
    }

    @RepeatedTest(1000)
    void coveringDesign_Strat6() {
        int strat = 6;
        CoveringDesignWrapper cd = new CoveringDesignWrapper(V, K, T, strat, false);
        cd.printSolution();
        assertTrue(cd.checkSolution());
        assertEquals(ExpectedCoveringNum, cd.getSolution().size());
    }


    @Test
    void coveringDesign_Main() {
        Main.main(new Integer[]{V, K, T, 7, 1, ExpectedCoveringNum});
    }

    /*@Test
    void coveringDesign_6_4_2_Strat3_Troubleshoot() {
        CoveringDesignWrapper cd = new CoveringDesignWrapper(6, 4, 2, 3, true);

        cd.GetCoveringDesign().ResetBlocks();
        cd.GetCoveringDesign().AddBlock(new Block(cd.GetCoveringDesign().GetV(), new HashSet<>(Arrays.asList(1,2,5,6))));
        cd.GetCoveringDesign().AddBlock(new Block(cd.GetCoveringDesign().GetV(), new HashSet<>(Arrays.asList(1,2,4,5))));
        cd.GetCoveringDesign().AddBlock(new Block(cd.GetCoveringDesign().GetV(), new HashSet<>(Arrays.asList(2,3,4,5))));
        cd.GetCoveringDesign().AddBlock(new Block(cd.GetCoveringDesign().GetV(), new HashSet<>(Arrays.asList(1,3,4,6))));

        cd.ExecuteStrategy();
        *//*
        cd.printSolution();
        assertTrue(cd.checkSolution());
        assertEquals(3, cd.getSolution().size());*//*
    }

    @Test
    void coveringDesign_6_4_2_Strat3_Troubleshoot2() {
        CoveringDesignWrapper cd = new CoveringDesignWrapper(6, 4, 2, 3, true);

        cd.GetCoveringDesign().ResetBlocks();
        cd.GetCoveringDesign().AddBlock(new Block(cd.GetCoveringDesign().GetV(), new HashSet<>(Arrays.asList(1,2,4,5))));
        cd.GetCoveringDesign().AddBlock(new Block(cd.GetCoveringDesign().GetV(), new HashSet<>(Arrays.asList(2,3,4,5))));
        cd.GetCoveringDesign().AddBlock(new Block(cd.GetCoveringDesign().GetV(), new HashSet<>(Arrays.asList(1,3,4,6))));

        cd.ExecuteStrategy();
        *//*
        cd.printSolution();
        assertTrue(cd.checkSolution());
        assertEquals(3, cd.getSolution().size());*//*
    }*/


}
