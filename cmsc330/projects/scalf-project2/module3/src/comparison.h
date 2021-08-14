#ifndef COMPARISON_H_
#define COMPARISON_H_

class Comparison: public SubExpression
{
public:
	Comparison(Expression* left, Expression* right):
		SubExpression(left, right)
	{
		this->isComparison = true;
	}
	virtual ~Comparison() {};
	virtual int evaluate() = 0;
};

#endif /* COMPARISON_H_ */
