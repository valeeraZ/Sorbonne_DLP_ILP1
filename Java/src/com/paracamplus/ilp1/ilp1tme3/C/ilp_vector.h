#ifndef ILP_VECTOR_H
#define ILP_VECTOR_H

#include "ilp.h"

extern ILP_Object ILP_make_vector(ILP_Object size, ILP_Object value);
extern ILP_Object ILP_vector_get(ILP_Object vector, ILP_Object index);
extern ILP_Object ILP_vector_length(ILP_Object vector);

#endif
