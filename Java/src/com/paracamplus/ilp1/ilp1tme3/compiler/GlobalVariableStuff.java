package com.paracamplus.ilp1.ilp1tme3.compiler;

import com.paracamplus.ilp1.compiler.Primitive;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;

public class GlobalVariableStuff {
    public static void fillGlobalVariables (IGlobalVariableEnvironment env) {
        env.addGlobalVariableValue("pi", "ILP_PI");
        env.addGlobalFunctionValue(
                new Primitive("print", "ILP_print", 1));
        env.addGlobalFunctionValue(
                new Primitive("newline", "ILP_newline", 0));
        env.addGlobalFunctionValue(
                new Primitive("throw", "ILP_throw", 1));
        env.addGlobalFunctionValue(
                new Primitive("sinus", "ILP_sinus", 1));
    }
}
