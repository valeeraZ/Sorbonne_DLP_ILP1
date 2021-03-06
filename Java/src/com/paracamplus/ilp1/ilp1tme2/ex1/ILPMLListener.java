package com.paracamplus.ilp1.ilp1tme2.ex1;

import antlr4.ILPMLgrammar1tme2Listener;
import antlr4.ILPMLgrammar1tme2Parser;
import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTfactory;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class ILPMLListener implements ILPMLgrammar1tme2Listener {
    protected IASTfactory factory;

    public ILPMLListener(IASTfactory factory) {
        super();
        this.factory = factory;
    }

    /*
     * ANTLRGrammarBaseListener, automatiquement généré, fournit
     * un squelette d'objet "Listener".
     * Il suffit de redéinir les méthodes qui nous intéressent : celles de la
     * forme "exit<règle>".
     */

    @Override
    public void exitVariable(ILPMLgrammar1tme2Parser.VariableContext ctx) {
        ctx.node = factory.newVariable(ctx.getText());
    }

    @Override
    public void exitInvocation(
            ILPMLgrammar1tme2Parser.InvocationContext ctx) {
        ctx.node = factory.newInvocation(
                ctx.fun.node,
                toExpressions(ctx.args));
    }

    @Override
    public void enterVariableAssignment(ILPMLgrammar1tme2Parser.VariableAssignmentContext ctx) {

    }

    @Override
    public void exitVariableAssignment(ILPMLgrammar1tme2Parser.VariableAssignmentContext ctx) {

    }

    @Override
    public void exitConstFloat(
            ILPMLgrammar1tme2Parser.ConstFloatContext ctx) {
        ctx.node = factory.newFloatConstant(ctx.floatConst.getText());
    }

    @Override
    public void	exitConstInteger(
            ILPMLgrammar1tme2Parser.ConstIntegerContext ctx) {
        ctx.node = factory.newIntegerConstant(ctx.intConst.getText());
    }

    @Override
    public void enterFundef(ILPMLgrammar1tme2Parser.FundefContext ctx) {

    }

    @Override
    public void exitFundef(ILPMLgrammar1tme2Parser.FundefContext ctx) {

    }

    @Override
    public void exitBinding(ILPMLgrammar1tme2Parser.BindingContext ctx) {
        ctx.node = factory.newBlock(
                toBindings(ctx.vars, ctx.vals),
                ctx.body.node);
    }

    @Override
    public void exitAlternative(
            ILPMLgrammar1tme2Parser.AlternativeContext ctx) {
        ctx.node = factory.newAlternative(
                ctx.condition.node,
                ctx.consequence.node,
                (ctx.alternant == null ? null : ctx.alternant.node));
    }

    @Override
    public void exitSequence(
            ILPMLgrammar1tme2Parser.SequenceContext ctx) {
        ctx.node = factory.newSequence(toExpressions(ctx.exprs));
    }

    @Override
    public void exitConstFalse(
            ILPMLgrammar1tme2Parser.ConstFalseContext ctx) {
        ctx.node = factory.newBooleanConstant("false");
    }

    @Override
    public void exitProg(ILPMLgrammar1tme2Parser.ProgContext ctx) {
        IASTexpression e = factory.newSequence(toExpressions(ctx.exprs));
        ctx.node = factory.newProgram(e);
    }

    @Override
    public void exitUnary(ILPMLgrammar1tme2Parser.UnaryContext ctx) {
        ctx.node = factory.newUnaryOperation(
                factory.newOperator(ctx.op.getText()),
                ctx.arg.node);
    }

    @Override
    public void exitConstTrue(
            ILPMLgrammar1tme2Parser.ConstTrueContext ctx) {
        ctx.node = factory.newBooleanConstant("true");
    }

    @Override
    public void exitConstString(
            ILPMLgrammar1tme2Parser.ConstStringContext ctx) {
        /*
         * On enlève le " initial et final, et on interprète les codes
         * d'échapement \n, \r, \t, \"
         */
        String s = ctx.getText();
        StringBuilder buf = new StringBuilder();
        for (int i = 1; i < s.length() - 1; i++) {
            if (s.charAt(i) == '\\' && i < s.length() - 2) {
                switch (s.charAt(i+1)) {
                    case 'n': buf.append('\n'); i++; break;
                    case 'r': buf.append('\r'); i++; break;
                    case 't': buf.append('\t'); i++; break;
                    case '"': buf.append('\"'); i++; break;
                    default: buf.append(s.charAt(i));
                }
            }
            else buf.append(s.charAt(i));
        }
        ctx.node = factory.newStringConstant(buf.toString());
    }

    @Override
    public void exitBinary(ILPMLgrammar1tme2Parser.BinaryContext ctx) {
        ctx.node = factory.newBinaryOperation(
                factory.newOperator(ctx.op.getText()),
                ctx.arg1.node,
                ctx.arg2.node);
    }

    @Override
    public void enterWhileLoop(ILPMLgrammar1tme2Parser.WhileLoopContext ctx) {

    }

    @Override
    public void exitWhileLoop(ILPMLgrammar1tme2Parser.WhileLoopContext ctx) {

    }



    /* Utilitaires de conversion ANTLR vers AST */

    protected IASTexpression[] toExpressions(
            List<ILPMLgrammar1tme2Parser.ExprContext> ctxs) {
        if (ctxs == null) return new IASTexpression[0];
        IASTexpression[] r = new IASTexpression[ctxs.size()];
        int pos = 0;
        for (ILPMLgrammar1tme2Parser.ExprContext e : ctxs) {
            r[pos++] = e.node;
        }
        return r;
    }

    protected IASTvariable[] toVariables(
            List<Token> vars, boolean addSelf) {
        if (vars == null) return new IASTvariable[0];
        IASTvariable[] r = new IASTvariable[vars.size() + (addSelf ? 1 : 0)];
        int pos = 0;
        if (addSelf) {
            // Les déclarations de méthodes ont une variable "self" implicite
            r[pos++] = factory.newVariable("self");
        }
        for (Token v : vars) {
            r[pos++] = factory.newVariable(v.getText());
        }
        return r;
    }

    protected String[] toStrings(List<Token> vars) {
        if (vars == null) return new String[0];
        String[] r = new String[vars.size()];
        int pos = 0;
        for (Token v : vars) {
            r[pos++] = v.getText();
        }
        return r;
    }

    protected IASTblock.IASTbinding[] toBindings(
            List<Token> vars,
            List<ILPMLgrammar1tme2Parser.ExprContext> exprs) {
        if (vars == null) return new IASTblock.IASTbinding[0];
        /* par construction, vars et ctxs ont la même taille */
        assert(vars.size() == exprs.size());
        IASTblock.IASTbinding[] r = new IASTblock.IASTbinding[exprs.size()];
        int pos = 0;
        for (Token v : vars) {
            r[pos] = factory.newBinding(
                    factory.newVariable(v.getText()),
                    exprs.get(pos).node
            );
            pos++;
        }
        return r;
    }

    @Override	public void enterEveryRule(ParserRuleContext arg0) {}
    @Override	public void exitEveryRule(ParserRuleContext arg0) {}
    @Override	public void visitErrorNode(ErrorNode arg0) {}
    @Override	public void visitTerminal(TerminalNode arg0) {}
    @Override	public void enterConstInteger(ILPMLgrammar1tme2Parser.ConstIntegerContext ctx) {}
    @Override	public void enterProg(ILPMLgrammar1tme2Parser.ProgContext ctx) {}
    @Override	public void enterConstFloat(ILPMLgrammar1tme2Parser.ConstFloatContext ctx) {}
    @Override	public void enterVariable(ILPMLgrammar1tme2Parser.VariableContext ctx) {}
    @Override	public void enterBinary(ILPMLgrammar1tme2Parser.BinaryContext ctx) {}
    @Override	public void enterAlternative(ILPMLgrammar1tme2Parser.AlternativeContext ctx) {}
    @Override	public void enterConstFalse(ILPMLgrammar1tme2Parser.ConstFalseContext ctx) {}
    @Override	public void enterSequence(ILPMLgrammar1tme2Parser.SequenceContext ctx) {}
    @Override	public void enterConstTrue(ILPMLgrammar1tme2Parser.ConstTrueContext ctx) {}
    @Override	public void enterBinding(ILPMLgrammar1tme2Parser.BindingContext ctx) {}
    @Override	public void enterConstString(ILPMLgrammar1tme2Parser.ConstStringContext ctx) {}
    @Override	public void enterUnary(ILPMLgrammar1tme2Parser.UnaryContext ctx) {}
    @Override	public void enterInvocation(ILPMLgrammar1tme2Parser.InvocationContext ctx) {}

}
