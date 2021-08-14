#ifndef TERNARY_H_
#define TERNARY_H_

class Ternary: public Comparison
{
public:
	Ternary(Expression* left, Expression* right, Expression* condition):
		Comparison(left, right), condition(condition)
	{
	}
	virtual ~Ternary() {};
	virtual int evaluate()
	{
		if (condition->evaluate() == 0)
			return right->evaluate();
		else
			return left->evaluate();
	}
private:
	Expression* condition;
};

#endif /* TERNARY_H_ */
