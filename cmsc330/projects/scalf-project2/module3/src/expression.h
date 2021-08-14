#ifndef EXPRESSION_H_
#define EXPRESSION_H_

class Expression
{
public:
	virtual ~Expression() {};
	virtual int evaluate() = 0;
	bool is_comparison() const
	{
		return isComparison;
	}
protected:
	bool isComparison = false;
};

#endif /* EXPRESSION_H_ */
