#ifndef DIVIDE_H_
#define DIVIDE_H_

class Divide: public SubExpression
{
public:
	Divide(Expression* left, Expression* right):
		SubExpression(left, right)
	{
	}
	virtual ~Divide() {};
	virtual int evaluate()
	{
		double result = (double)left->evaluate() / (double)right->evaluate();
		return (int)(result + 0.5 - (result < 0));
	}
};

#endif /* DIVIDE_H_ */
