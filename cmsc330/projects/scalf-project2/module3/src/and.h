#ifndef AND_H_
#define AND_H_

class And: public Comparison
{
public:
	And(Expression* left, Expression* right):
		Comparison(left, right)
	{
	}
	virtual ~And() {};
	virtual int evaluate()
	{
		return left->evaluate() && right->evaluate();
	}
};

#endif /* AND_H_ */
