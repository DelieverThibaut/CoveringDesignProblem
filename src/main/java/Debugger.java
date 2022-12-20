package CoveringDesignProblem;

/**
 *
 * @author  Thibaut Deliever
 * @version 1.0
 * @since   2022-11-29
 */

public class Debugger {
    public static boolean isEnabled(){
        return false;
    }


    public static void log(Object o){
        if(Debugger.isEnabled()) System.out.println(o.toString());
    }
}
