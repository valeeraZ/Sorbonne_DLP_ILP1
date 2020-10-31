package com.paracamplus.ilp1.ilp1tme3.interpreter.primitive;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.Primitive;

import java.math.BigInteger;
import java.util.Vector;

public class MakeVector extends Primitive {
    public MakeVector() {
        super("makeVector");
    }

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public Object apply(Object size, Object value) throws EvaluationException {
        if(size instanceof BigInteger){
            int length = ((BigInteger) size).intValue();
            Vector<Object> vector = new Vector<>();
            vector.setSize(length);
            for(int i = 0; i < length; i++){
                vector.set(i, value);
            }
            return vector;
        }else {
            throw new EvaluationException("Invalid argument 0, number expected");
        }
    }
}
