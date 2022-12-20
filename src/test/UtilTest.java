package CoveringDesignProblem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UtilTest {
    @Test
    void UtilTest_GetAllSubsetsOfLengthK_1() {
        int v = 4;
        int k = 3;
        Set<Integer> vSet = new HashSet<>();
        for(int i=1; i<=v;i++) vSet.add(i);

        Set<Set<Integer>> expectedResult = new HashSet<>();
        expectedResult.add(new HashSet<>(Arrays.asList(1, 2, 3)) );
        expectedResult.add(new HashSet<>(Arrays.asList(1, 2, 4)) );
        expectedResult.add(new HashSet<>(Arrays.asList(1, 3, 4)) );
        expectedResult.add(new HashSet<>(Arrays.asList(2, 3, 4)) );

        Set<Set<Integer>> result = Util.getAllSubsetsOfLengthK(vSet, k);
        Assertions.assertEquals(expectedResult, result, "Results should be equal");
    }


    @Test
    void UtilTest_GetAllSubsetsOfLengthK_2() {
        int v = 6;
        int k = 3;
        Set<Integer> vSet = new HashSet<>();
        for(int i=1; i<=v;i++) vSet.add(i);

        Set<Set<Integer>> expectedResult = new HashSet<>();
        expectedResult.add(new HashSet<>(Arrays.asList(1, 2, 3)) );
        expectedResult.add(new HashSet<>(Arrays.asList(1, 2, 4)) );
        expectedResult.add(new HashSet<>(Arrays.asList(1, 2, 5)) );
        expectedResult.add(new HashSet<>(Arrays.asList(1, 2, 6)) );
        expectedResult.add(new HashSet<>(Arrays.asList(1, 3, 4)) );
        expectedResult.add(new HashSet<>(Arrays.asList(1, 3, 5)) );
        expectedResult.add(new HashSet<>(Arrays.asList(1, 3, 6)) );
        expectedResult.add(new HashSet<>(Arrays.asList(1, 4, 5)) );
        expectedResult.add(new HashSet<>(Arrays.asList(1, 4, 6)) );
        expectedResult.add(new HashSet<>(Arrays.asList(1, 5, 6)) );
        expectedResult.add(new HashSet<>(Arrays.asList(2, 3, 4)) );
        expectedResult.add(new HashSet<>(Arrays.asList(2, 3, 5)) );
        expectedResult.add(new HashSet<>(Arrays.asList(2, 3, 6)) );
        expectedResult.add(new HashSet<>(Arrays.asList(2, 4, 5)) );
        expectedResult.add(new HashSet<>(Arrays.asList(2, 4, 6)) );
        expectedResult.add(new HashSet<>(Arrays.asList(2, 5, 6)) );
        expectedResult.add(new HashSet<>(Arrays.asList(3, 4, 5)) );
        expectedResult.add(new HashSet<>(Arrays.asList(3, 4, 6)) );
        expectedResult.add(new HashSet<>(Arrays.asList(3, 5, 6)) );
        expectedResult.add(new HashSet<>(Arrays.asList(4, 5, 6)) );

        Set<Set<Integer>> result = Util.getAllSubsetsOfLengthK(vSet, k);
        Assertions.assertEquals(expectedResult, result, "Results should be equal");
    }
}
