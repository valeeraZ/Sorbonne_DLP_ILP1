package com.paracamplus.ilp1.ilp1tme1.test;

import com.paracamplus.ilp1.ilp1tme1.sequence.ASTfactory;
import com.paracamplus.ilp1.interfaces.IASTfactory;
import com.paracamplus.ilp1.interpreter.*;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interpreter.test.InterpreterRunner;
import com.paracamplus.ilp1.interpreter.test.InterpreterTest;
import com.paracamplus.ilp1.parser.ParseException;
import com.paracamplus.ilp1.parser.ilpml.ILPMLParser;
import com.paracamplus.ilp1.parser.xml.IXMLParser;
import com.paracamplus.ilp1.parser.xml.XMLParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;

/**
 * test, avec la nouvelle fabrique, que l'exécution des programmes ILP est inchangée
 */
@RunWith(Parameterized.class)
public class NewInterpreterTest extends InterpreterTest {
    protected static String[] samplesDirName = { "SamplesTME1" };
    public NewInterpreterTest(File file) {
        super(file);
    }

    @Override
    public void configureRunner(InterpreterRunner run) throws EvaluationException {
        // configuration du parseur
        IASTfactory factory = new ASTfactory();
        IXMLParser xmlParser = new XMLParser(factory);
        xmlParser.setGrammar(new File(XMLgrammarFile));
        run.setXMLParser(xmlParser);
        run.setILPMLParser(new ILPMLParser(factory));

        // configuration de l'interprète
        StringWriter stdout = new StringWriter();
        run.setStdout(stdout);
        IGlobalVariableEnvironment gve = new GlobalVariableEnvironment();
        GlobalVariableStuff.fillGlobalVariables(gve, stdout);
        IOperatorEnvironment oe = new OperatorEnvironment();
        OperatorStuff.fillUnaryOperators(oe);
        OperatorStuff.fillBinaryOperators(oe);
        Interpreter interpreter = new Interpreter(gve, oe);
        run.setInterpreter(interpreter);
    }

    @Test
    public void test() throws EvaluationException, ParseException, IOException {
        super.processFile();
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<File[]> data() throws Exception {
        return InterpreterRunner.getFileList(samplesDirName, pattern);
    }
}
