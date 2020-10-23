package com.paracamplus.ilp1.ilp1tme2.ex2;


import antlr4.ILPMLgrammar1Lexer;
import antlr4.ILPMLgrammar1Parser;
import com.paracamplus.ilp1.interfaces.IASTfactory;
import com.paracamplus.ilp1.interfaces.IASTprogram;
import com.paracamplus.ilp1.parser.ParseException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ILPMLParser extends com.paracamplus.ilp1.parser.ilpml.ILPMLParser {
    public ILPMLParser(IASTfactory factory) {
        super(factory);
    }

    @Override
    public IASTprogram getProgram() throws ParseException {
        try {
            ANTLRInputStream in = new ANTLRInputStream(input.getText());
            // flux de caractères -> analyseur lexical
            ILPMLgrammar1Lexer lexer = new ILPMLgrammar1Lexer(in);
            // analyseur lexical -> flux de tokens
            CommonTokenStream tokens =	new CommonTokenStream(lexer);
            // flux tokens -> analyseur syntaxique
            ILPMLgrammar1Parser parser = new ILPMLgrammar1Parser(tokens);
            // démarage de l'analyse syntaxique
            ILPMLgrammar1Parser.ProgContext tree = parser.prog();
            // parcours de l'arbre syntaxique et appels du Listener
            ParseTreeWalker walker = new ParseTreeWalker();
            ILPMLListener extractor = new ILPMLListener(factory);
            walker.walk(extractor, tree);
            System.out.println("Nombre de constantes : " + extractor.getNbConst());
            return tree.node;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }
}
