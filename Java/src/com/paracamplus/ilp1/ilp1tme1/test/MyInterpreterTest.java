package com.paracamplus.ilp1.ilp1tme1.test;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.test.InterpreterRunner;
import com.paracamplus.ilp1.interpreter.test.InterpreterTest;
import com.paracamplus.ilp1.parser.ParseException;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class MyInterpreterTest extends InterpreterTest {
    protected static String[] samplesDirName = { "SamplesTME1" };
    public MyInterpreterTest(File file) {
        super(file);
    }

    @Test
    public void test() throws ParseException, IOException, EvaluationException {
        processFile();
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<File[]> data() throws Exception {
        return InterpreterRunner.getFileList(samplesDirName, pattern);
    }
}
