#ifndef SYMBOLTABLE_H_
#define SYMBOLTABLE_H_

#include <vector>

class SymbolTable
{
public:
	SymbolTable() {}
	void insert(string variable, double value);
	double lookUp(string variable) const;
	void clear();
private:
	struct Symbol
	{
		Symbol(string variable, double value)
		{
			this->variable = variable;
			this->value = value;
		}
		string variable;
		double value;
	};
	vector <Symbol> elements;
};

#endif /* SYMBOLTABLE_H_ */
