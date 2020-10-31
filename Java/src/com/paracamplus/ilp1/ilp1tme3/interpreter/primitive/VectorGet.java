package com.paracamplus.ilp1.ilp1tme3.interpreter.primitive;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.Primitive;

import java.math.BigInteger;
import java.util.Vector;

public class VectorGet extends Primitive {
    public VectorGet() {
        super("vectorGet");
    }

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public Object apply(Object vector, Object index) throws EvaluationException {
        if(vector instanceof Vector<?> && index instanceof BigInteger){
            return ((Vector<?>) vector).elementAt(((BigInteger) index).intValue());
        }else{
            throw new EvaluationException("Invalid arguments, vector and number expected");
        }
    }
}
