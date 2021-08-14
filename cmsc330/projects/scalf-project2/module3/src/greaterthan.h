#ifndef GREATERTHAN_H_
#define GREATERTHAN_H_

class GreaterThan: public Comparison
{
public:
	GreaterThan(Expression* left, Expression* right):
		Comparison(left, right)
	{
	}
	virtual ~GreaterThan() {};
	virtual int evaluate()
	{
		return left->evaluate() > right->evaluate();
	}
};

#endif /* GREATERTHAN_H_ */
