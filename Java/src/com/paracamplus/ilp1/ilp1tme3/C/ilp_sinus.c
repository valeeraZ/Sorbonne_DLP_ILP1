#include "ilp_sinus.h"
#include "ilp.h"

ILP_Object
ILP_sinus(ILP_Object o)
{
    if(ILP_isInteger(o)){
        double d = sin(o->_content.asInteger);
        return ILP_make_float(d);
    }
    if (ILP_isFloat(o)){
        double d = sin(o->_content.asFloat);
        return ILP_make_float(d);
    }
    return ILP_domain_error("Not a number", o);
}
