#include <stdio.h>
#include <stdlib.h>
#include "ilp.h"

/* Global variables */


ILP_Object 
ilp_program()
{
	{
		ILP_Object	ilptmp3;
		ILP_Object	ilptmp4;
		ilptmp3 = ILP_Integer2ILP(44);
		ilptmp4 = ILP_Integer2ILP(33);
		return ILP_GreaterThan(ilptmp3, ilptmp4);
	}

}

static ILP_Object 
ilp_caught_program()
{
	struct ILP_catcher *current_catcher = ILP_current_catcher;
	struct ILP_catcher new_catcher;

	if (0 == setjmp(new_catcher._jmp_buf)) {
		ILP_establish_catcher(&new_catcher);
		return ilp_program();
	};
	return ILP_current_exception;
}

int 
main(int argc, char *argv[])
{
	ILP_START_GC;
	ILP_print(ilp_caught_program());
	ILP_newline();
	return EXIT_SUCCESS;
}
