#ifndef MINUS_H_
#define MINUS_H_

class Minus: public SubExpression
{
public:
	Minus(Expression* left, Expression* right):
		SubExpression(left, right)
	{
	}
	virtual ~Minus() {};
	virtual int evaluate()
	{
		return left->evaluate() - right->evaluate();
	}
};

#endif /* MINUS_H_ */
