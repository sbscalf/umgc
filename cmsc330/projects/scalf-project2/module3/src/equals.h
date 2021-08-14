#ifndef EQUALS_H_
#define EQUALS_H_

class Equals: public Comparison
{
public:
	Equals(Expression* left, Expression* right):
		Comparison(left, right)
	{
	}
	virtual ~Equals() {};
	virtual int evaluate()
	{
		if (left->evaluate() == right->evaluate())
			return 1;
		else
			return 0;
	}
};

#endif /* EQUALS_H_ */
