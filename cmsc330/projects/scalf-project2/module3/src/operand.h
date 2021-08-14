#ifndef OPERAND_H_
#define OPERAND_H_

#include "expression.h"

class Operand: public Expression
{
public:
	static Expression* parse(stringstream& in);
	virtual ~Operand() {};
	virtual int evaluate() = 0;
};

#endif /* OPERAND_H_ */
