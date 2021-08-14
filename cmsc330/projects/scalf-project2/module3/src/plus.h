#ifndef PLUS_H_
#define PLUS_H_

class Plus: public SubExpression
{
public:
	Plus(Expression* left, Expression* right):
		SubExpression(left, right)
	{
	}
	virtual ~Plus() {};
	virtual int evaluate()
	{
		return left->evaluate() + right->evaluate();
	}
};

#endif /* PLUS_H_ */
