package com.paracamplus.ilp1.ilp1tme1.test;

import com.paracamplus.ilp1.interpreter.test.InterpreterRunner;
import com.paracamplus.ilp1.interpreter.test.InterpreterTest;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Collection;

public class MyInterpreterTest extends InterpreterTest {
    protected static String[] samplesDirName = { "SamplesTME1" };
    public MyInterpreterTest(File file) {
        super(file);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<File[]> data() throws Exception {
        return InterpreterRunner.getFileList(samplesDirName, pattern);
    }
}
