package com.paracamplus.ilp1.ilp1tme1.sequence;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public class ASTsequence extends com.paracamplus.ilp1.ast.ASTsequence implements IASTsequence {

    public ASTsequence(IASTexpression[] expressions) {
        super(expressions);
    }

    @Override
    public IASTexpression[] getAllButLastInstructions() throws EvaluationException {
        IASTexpression[] expr = new ASTexpression[this.expressions.length - 1];
        //from 0 to length - 1
        System.arraycopy(this.expressions, 0, expr, 0, this.expressions.length-1);
        return expr;
    }
}
