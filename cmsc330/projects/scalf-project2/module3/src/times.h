#ifndef TIMES_H_
#define TIMES_H_

class Times: public SubExpression
{
public:
	Times(Expression* left, Expression* right):
		SubExpression(left, right)
	{
	}
	virtual ~Times() {};
	virtual int evaluate()
	{
		return left->evaluate() * right->evaluate();
	}
};

#endif /* TIMES_H_ */
