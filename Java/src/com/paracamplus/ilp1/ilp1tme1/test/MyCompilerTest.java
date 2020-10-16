package com.paracamplus.ilp1.ilp1tme1.test;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.test.CompilerRunner;
import com.paracamplus.ilp1.compiler.test.CompilerTest;
import com.paracamplus.ilp1.parser.ParseException;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class MyCompilerTest extends CompilerTest {
    protected static String[] samplesDirName = { "SamplesTME1" };
    public MyCompilerTest(File file) {
        super(file);
    }

    @Test
    public void test() throws CompilationException, ParseException, IOException {
        processFile();
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<File[]> data() throws Exception {
        return CompilerRunner.getFileList(samplesDirName, pattern);
    }
}
