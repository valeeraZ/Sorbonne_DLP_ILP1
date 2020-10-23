package com.paracamplus.ilp1.ilp1tme2.ex2;

import antlr4.ILPMLgrammar1Parser;
import com.paracamplus.ilp1.interfaces.IASTfactory;

public class ILPMLListener extends com.paracamplus.ilp1.parser.ilpml.ILPMLListener {
    protected int nbConst = 0;

    public int getNbConst() {
        return nbConst;
    }

    public ILPMLListener(IASTfactory factory) {
        super(factory);
    }

    @Override
    public void exitConstFloat(ILPMLgrammar1Parser.ConstFloatContext ctx) {
        super.exitConstFloat(ctx);
        nbConst++;
    }

    @Override
    public void exitConstInteger(ILPMLgrammar1Parser.ConstIntegerContext ctx) {
        super.exitConstInteger(ctx);
        nbConst++;
    }

    @Override
    public void exitConstFalse(ILPMLgrammar1Parser.ConstFalseContext ctx) {
        super.exitConstFalse(ctx);
        nbConst++;
    }

    @Override
    public void exitConstTrue(ILPMLgrammar1Parser.ConstTrueContext ctx) {
        super.exitConstTrue(ctx);
        nbConst++;
    }

    @Override
    public void exitConstString(ILPMLgrammar1Parser.ConstStringContext ctx) {
        super.exitConstString(ctx);
        nbConst++;
    }
}
