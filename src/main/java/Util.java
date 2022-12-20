package CoveringDesignProblem;

import com.google.common.collect.Sets;
import java.util.Set;

/**
 *
 * @author  Thibaut Deliever
 * @version 1.0
 * @since   2022-11-07
 */

public class Util {
    public static Set<Set<Integer>> getAllSubsetsOfLengthK(Set<Integer> set, int k){
        return Sets.combinations(set, k);
    }
}
