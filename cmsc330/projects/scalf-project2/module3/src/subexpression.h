#ifndef SUBEXPRESSION_H_
#define SUBEXPRESSION_H_

#include "expression.h"

class SubExpression: public Expression
{
public:
	SubExpression(Expression* left, Expression* right);
	virtual ~SubExpression() {};
	virtual int evaluate() = 0;
	static Expression* parse(stringstream& in);
protected:
	Expression* left;
	Expression* right;
};

#endif /* SUBEXPRESSION_H_ */
