package CoveringDesignProblem.Cases;

import CoveringDesignProblem.CoveringDesignWrapper;
import CoveringDesignProblem.Main;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * V = 6
 * K = 4
 * T = 3
 *
 * CurrentSolution = 6;
 * MinimalSolution = 6;
 */
public class Test_6_4_3 {
    @RepeatedTest(1000)
    void coveringDesign_6_4_3_Strat2() {
        CoveringDesignWrapper cd = new CoveringDesignWrapper(6, 4, 3, 2, false);
        cd.printSolution();
        assertTrue(cd.checkSolution());
        assertEquals(6, cd.getSolution().size());
    }

    @RepeatedTest(1000)
    void coveringDesign_6_4_3_Strat3() {
        CoveringDesignWrapper cd = new CoveringDesignWrapper(6, 4, 3, 3, false);
        cd.printSolution();
        assertTrue(cd.checkSolution());
        assertEquals(6, cd.getSolution().size());
    }

    @RepeatedTest(1000)
    void coveringDesign_6_4_3_Strat4() {
        CoveringDesignWrapper cd = new CoveringDesignWrapper(6, 4, 3, 4, false);
        cd.printSolution();
        assertTrue(cd.checkSolution());
        assertEquals(6, cd.getSolution().size());
    }

    @RepeatedTest(1000)
    void coveringDesign_6_4_3_Strat5() {
        CoveringDesignWrapper cd = new CoveringDesignWrapper(6, 4, 3, 5, false);
        cd.printSolution();
        assertTrue(cd.checkSolution());
        assertEquals(6, cd.getSolution().size());
    }

    @RepeatedTest(1)
    void coveringDesign_6_4_3_Strat6() {
        CoveringDesignWrapper cd = new CoveringDesignWrapper(6, 4, 3, 6, false);
        cd.printSolution();
        assertTrue(cd.checkSolution());
        assertEquals(6, cd.getSolution().size());
    }


    @Test
    void coveringDesign_6_4_3_Main() {
        Main.main(new Integer[]{6, 4, 3, 5, 1000, 6});
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
