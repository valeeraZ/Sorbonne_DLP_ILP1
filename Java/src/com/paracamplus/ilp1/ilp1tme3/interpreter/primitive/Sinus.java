package com.paracamplus.ilp1.ilp1tme3.interpreter.primitive;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.UnaryPrimitive;

import java.math.BigInteger;

public class Sinus extends UnaryPrimitive {
    public Sinus() {
        super("sinus");
    }

    @Override
    public Object apply(Object value) throws EvaluationException {
        if(value instanceof Double){
            return Math.sin((Double)value);
        }else if(value instanceof BigInteger){
            return Math.sin( ((BigInteger)value).doubleValue());
        }else throw new EvaluationException("Invalid argument, number expected");

    }
}
