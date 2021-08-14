#ifndef LESSTHAN_H_
#define LESSTHAN_H_

#include <math.h>

class LessThan: public Comparison
{
public:
	LessThan(Expression* left, Expression* right):
		Comparison(left, right)
	{
	}
	virtual ~LessThan() {};
	virtual int evaluate()
	{
		return isless(left->evaluate(),right->evaluate());
	}
};

#endif /* LESSTHAN_H_ */
