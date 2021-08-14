#ifndef OR_H_
#define OR_H_

class Or: public Comparison
{
public:
	Or(Expression* left, Expression* right):
		Comparison(left, right)
	{
	}
	virtual ~Or() {};
	virtual int evaluate()
	{
		return left->evaluate() || right->evaluate();
	}
};

#endif /* OR_H_ */
