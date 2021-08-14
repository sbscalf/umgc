#ifndef NEGATION_H_
#define NEGATION_H_

class Negation: public Comparison
{
public:
	Negation(Expression* left, Expression* right):
		Comparison(left, right)
	{
	}
	virtual ~Negation() {};
	virtual int evaluate()
	{
		if (left->evaluate() == 0)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
};

#endif /* NEGATION_H_ */
