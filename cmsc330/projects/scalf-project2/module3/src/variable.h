#ifndef VARIABLE_H_
#define VARIABLE_H_

class Variable: public Operand
{
public:
	Variable(string name)
	{
		this->name = name;
	}
	virtual ~Variable() {};
	virtual int evaluate();
private:
	string name;
};

#endif /* VARIABLE_H_ */
