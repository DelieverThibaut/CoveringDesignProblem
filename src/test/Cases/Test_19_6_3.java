package CoveringDesignProblem.Cases;

import CoveringDesignProblem.Main;
import org.junit.jupiter.api.Test;

/**
 * V = 19
 * K = 6
 * T = 3
 *
 * CurrentSolution = 60;
 * MinimalSolution = 57;
 */
public class Test_19_6_3 {
    private final int V = 19;
    private final int K = 6;
    private final int T = 3;
    private final int ExpectedCoveringNum = 57;

    @Test
    void coveringDesign_Main() {
        Main.main(new Integer[]{V, K, T, 8, 3, ExpectedCoveringNum});
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
