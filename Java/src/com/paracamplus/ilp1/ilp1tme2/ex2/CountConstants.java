package com.paracamplus.ilp1.ilp1tme2.ex2;

import com.paracamplus.ilp1.interfaces.*;

public class CountConstants implements IASTvisitor<Integer, Integer, Exception> {
    public Integer visit(IASTprogram iast, Integer data) throws Exception{
        return iast.getBody().accept(this,data);
    }

    @Override
    public Integer visit(IASTalternative iast, Integer data) throws Exception {
        data += iast.getCondition().accept(this,data);
        data += iast.getConsequence().accept(this,data);
        if(iast.isTernary()){
            data += iast.getAlternant().accept(this,data);
        }
        return data;
    }

    @Override
    public Integer visit(IASTbinaryOperation iast, Integer data) throws Exception {
        data += iast.getLeftOperand().accept(this,data);
        data += iast.getRightOperand().accept(this,data);
        System.out.println("a binary operation data: " + data);
        return data;
    }

    @Override
    public Integer visit(IASTblock iast, Integer data) throws Exception {
        System.out.println("before block data: "+data);
        IASTblock.IASTbinding[] bindings = iast.getBindings();
        for(IASTblock.IASTbinding binding : bindings){
            data += binding.getInitialisation().accept(this,data);
        }
        //data += iast.getBody().accept(this,data);
        System.out.println("after block data: "+data);
        return data;
    }

    @Override
    public Integer visit(IASTboolean iast, Integer data) throws Exception {
        System.out.println("a boolean");
        return 1;
    }

    @Override
    public Integer visit(IASTfloat iast, Integer data) throws Exception {
        System.out.println("a float");
        return 1;
    }

    @Override
    public Integer visit(IASTinteger iast, Integer data) throws Exception {
        System.out.println("a integer");
        return 1;
    }

    @Override
    public Integer visit(IASTinvocation iast, Integer data) throws Exception {
        IASTexpression[] arguments = iast.getArguments();
        for(IASTexpression argument : arguments){
            argument.accept(this,data);
        }
        System.out.println("invocation data: "+data);
        return data;
    }

    @Override
    public Integer visit(IASTsequence iast, Integer data) throws Exception {
        IASTexpression[] exprs = iast.getExpressions();
        for(IASTexpression expr : exprs){
            data += expr.accept(this,data);
        }
        return data;
    }

    @Override
    public Integer visit(IASTstring iast, Integer data) throws Exception {
        System.out.println("a string data: " + data);
        return 1;
    }

    @Override
    public Integer visit(IASTunaryOperation iast, Integer data) throws Exception {
        data += iast.getOperand().accept(this,data);
        System.out.println("an unary operation data: " + data);
        return data;
    }

    @Override
    public Integer visit(IASTvariable iast, Integer data) throws Exception {
        System.out.println("a variable data:" + data);
        return 0;
    }
}
