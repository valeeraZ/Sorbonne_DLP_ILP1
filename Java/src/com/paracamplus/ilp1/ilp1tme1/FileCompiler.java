package com.paracamplus.ilp1.ilp1tme1;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.test.CompilerTest;
import com.paracamplus.ilp1.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class FileCompiler extends CompilerTest {
    public FileCompiler(File file) {
        super(file);
    }

    public static void main (String[] args) throws ParseException, IOException, CompilationException {
        String fileName = args[0];
        File file = new File(fileName);
        FileCompiler fileCompiler = new FileCompiler(file);
        fileCompiler.processFile();
    }
}
