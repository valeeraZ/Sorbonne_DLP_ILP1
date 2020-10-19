package com.paracamplus.ilp1.ilp1tme1.test;

import com.paracamplus.ilp1.ast.ASTboolean;
import com.paracamplus.ilp1.ast.ASTfloat;
import com.paracamplus.ilp1.ast.ASTinteger;
import com.paracamplus.ilp1.ilp1tme1.sequence.ASTsequence;
import com.paracamplus.ilp1.ilp1tme1.sequence.IASTsequence;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * tester la méthode getAllButLastInstructions
 */
public class ASTsequenceTest {
    IASTexpression[] expr = {
            new ASTinteger("12"),
            new ASTboolean("true"),
            new ASTfloat("1.1")
    };
    IASTsequence seq = new ASTsequence(expr);

    @Test
    public void test() {
        try {
            IASTexpression[] result = seq.getAllButLastInstructions();

            //tester que le result a moins d'un element que l'orginale expr
            assertEquals(result.length, seq.getExpressions().length - 1);

            //tester que le dernier element dans result est ASTboolean(true)
            assertTrue((result[result.length - 1] instanceof ASTboolean));

            //tester que le reste sont identiques
            for(int i = 0; i < result.length; i++){
                assertEquals(result[i],seq.getExpressions()[i]);
            }

        } catch (EvaluationException e) {
            e.getStackTrace();
        }
    }
}
