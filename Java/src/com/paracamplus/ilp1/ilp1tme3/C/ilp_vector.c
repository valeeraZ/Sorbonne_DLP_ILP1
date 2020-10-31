#include "ilp_vector.h"
#include "ilp.h"

ILP_Object
ILP_make_vector(ILP_Object size, ILP_Object value)
{
    if(ILP_isInteger(size)){
        int length = size -> _content.asInteger;
        ILP_Object vector = ILP_AllocateVector(length);
        vector->_content.asVector._size = length;
        for(int i = 0; i < length; i++){
            vector->_content.asVector.asVectorItem[i] = value;
        }
        return vector;
    }else{
        return ILP_domain_error("Invalid argument, number expected",size);
    }
}

ILP_Object
ILP_vector_get(ILP_Object vector, ILP_Object index)
{
    if(ILP_isVector(vector)){
        if(ILP_isInteger(index)){
            int i = index -> _content.asInteger;
            return vector->_content.asVector.asVectorItem[i];
        }else{
            return ILP_domain_error("Invalid argument, number expected",index);
        }   
    }else{
        return ILP_domain_error("Invalid argument, vector expected", vector);
    }
}

ILP_Object
ILP_vector_length(ILP_Object vector)
{
    if(ILP_isVector(vector)){
        return ILP_make_integer(vector->_content.asVector._size);        
    }else{
        return ILP_domain_error("Invalid argument, vector expected", vector);
    }
}