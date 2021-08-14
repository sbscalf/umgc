#ifndef LITERAL_H_
#define LITERAL_H_

class Literal: public Operand
{
public:
	Literal(int value)
	{
		this->value = value;
	}
	virtual ~Literal() {};
	virtual int evaluate()
	{
		return value;
	}
private:
	int value;
};

#endif /* LITERAL_H_ */
