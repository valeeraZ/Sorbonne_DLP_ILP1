package com.paracamplus.ilp1.ilp1tme3.interpreter;

import com.paracamplus.ilp1.ilp1tme3.interpreter.primitive.MakeVector;
import com.paracamplus.ilp1.ilp1tme3.interpreter.primitive.Sinus;
import com.paracamplus.ilp1.ilp1tme3.interpreter.primitive.VectorGet;
import com.paracamplus.ilp1.ilp1tme3.interpreter.primitive.VectorLength;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.primitive.Newline;
import com.paracamplus.ilp1.interpreter.primitive.Print;

import java.io.Writer;
import java.math.BigDecimal;

public class GlobalVariableStuff {

    public static void fillGlobalVariables (
            IGlobalVariableEnvironment env,
            Writer out ) {
        env.addGlobalVariableValue("pi", new BigDecimal("3.1415926535"));
        env.addGlobalVariableValue(new Print(out));
        env.addGlobalVariableValue(new Newline(out));
        env.addGlobalVariableValue(new Sinus());
        env.addGlobalVariableValue(new MakeVector());
        env.addGlobalVariableValue(new VectorLength());
        env.addGlobalVariableValue(new VectorGet());
    }
}
