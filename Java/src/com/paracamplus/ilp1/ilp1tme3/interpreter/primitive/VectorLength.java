package com.paracamplus.ilp1.ilp1tme3.interpreter.primitive;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.UnaryPrimitive;

import java.util.Vector;

public class VectorLength extends UnaryPrimitive {
    public VectorLength() {
        super("vectorLength");
    }

    @Override
    public Object apply(Object vector) throws EvaluationException {
        if(vector instanceof Vector<?>){
            return ((Vector<?>) vector).size();
        }else{
            throw new EvaluationException("Invalid argument, Vector expected");
        }
    }
}
