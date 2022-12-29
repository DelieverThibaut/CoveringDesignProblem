package CoveringDesignProblem;

import java.util.BitSet;
import java.util.Random;

/**
 *
 * @author  Thibaut Deliever
 * @version 1.0
 * @since   2022-11-09
 */

public class CoveringDesignConstructor {
    private final Random Rand = new Random();
    private final CoveringDesign CoveringDesign;
    private Block OpenBlock;


    public CoveringDesignConstructor(CoveringDesign cDesign){
        Debugger.log("FunctionCall_START \t\t~ CoveringDesignConstructor(cDesign)");
        CoveringDesign = cDesign;
        OpenBlock = new Block(CoveringDesign.getV(), CoveringDesign.getT());

        Construct();
        Debugger.log("FunctionCall_END \t\t~ CoveringDesignConstructor(cDesign)");
    }


    private void Construct(){
        Debugger.log("FunctionCall_START \t\t~ Construct()");
        for (BitSet bitSet : this.CoveringDesign.getAllTSubsets()) AddTSetToBlock(bitSet);

        AddOpenBlockToDesign();
        Debugger.log("FunctionCall_END \t\t~ Construct()");
    }


    private void AddTSetToBlock(BitSet tSet){
        Debugger.log("FunctionCall_START \t\t~ AddTSetToBlock("+tSet+")");
        if(CoveringDesign.getTSubsetValue(tSet).size() > 0) return;
        Debugger.log("\tFunctionCall_INFO \t~ AddTSetToBlock("+tSet+") - tSet not found in current blocks");

        int simVal = OpenBlock.simulateAddTSubset(tSet);
        Debugger.log("\tFunctionCall_INFO \t~ AddTSetToBlock("+tSet+") - simulated size of open block: " + simVal);
        if(simVal > CoveringDesign.getK()) {
            AddOpenBlockToDesign();
            OpenBlock.addTSubset(tSet);
        }
        else if(simVal == this.CoveringDesign.getK()){
            this.OpenBlock.addTSubset(tSet);
            AddOpenBlockToDesign();
        }
        else this.OpenBlock.addTSubset(tSet);
        Debugger.log("\tFunctionCall_INFO \t~ AddTSetToBlock("+tSet+") - add tSet to open block");
        Debugger.log("FunctionCall_END \t\t~ AddTSetToBlock("+tSet+")");
    }
    private void AddOpenBlockToDesign(){
        Debugger.log("FunctionCall_START \t\t~ AddOpenBlockToDesign()");
        while (OpenBlock.size() < CoveringDesign.getK()) {
            Debugger.log("\tFunctionCall_INFO \t~ AddOpenBlockToDesign() - size_check1 of open block: " + this.OpenBlock.size());
            int randomV = Rand.nextInt(this.CoveringDesign.getV());
            while(!isValidV(randomV)) randomV = (Rand.nextInt(this.CoveringDesign.getV()));
            this.OpenBlock.addElement(randomV);
            Debugger.log("\tFunctionCall_INFO \t~ AddOpenBlockToDesign() - size_check2 of open block: " + this.OpenBlock.size());
        }
        Debugger.log("\tFunctionCall_INFO \t~ AddOpenBlockToDesign() - add open block to design");
        this.CoveringDesign.addBlock(this.OpenBlock);
        Debugger.log("\tFunctionCall_INFO \t~ AddOpenBlockToDesign() - init new open block");
        this.OpenBlock = new Block(CoveringDesign.getV(), CoveringDesign.getT());
        Debugger.log("FunctionCall_END \t\t~ AddOpenBlockToDesign()");
    }


    private boolean isValidV(int v){
        /*BitSet clone = (BitSet)OpenBlock.getElements().clone();
        clone.set(v);
        for(Block blck : this.CoveringDesign.getBlocks()) if (blck.getElements().equals(clone)) return false;*/
        return true;
    }
}
