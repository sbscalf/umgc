#include <iostream>
#include <sstream>
using namespace std;

#include "expression.h"
#include "subexpression.h"
#include "operand.h"
#include "literal.h"
#include "plus.h"
#include "minus.h"
#include "times.h"
#include "divide.h"
#include "comparison.h"
#include "negation.h"
#include "greaterthan.h"
#include "lessthan.h"
#include "equals.h"
#include "and.h"
#include "or.h"
#include "ternary.h"

SubExpression::SubExpression(Expression* left, Expression* right)
{
	this->left = left;
	this->right = right;

}

Expression* SubExpression::parse(stringstream& in)
{
	char operation, paren, question;
	Expression* left = Operand::parse(in);
	in >> operation;
	if (operation == '!')
	{
		in >> paren;
		return new Negation(left, new Literal(0));
	}
	else
	{
		Expression* right = Operand::parse(in);
		if (operation == ':')
		{
			in >> question;
			Expression* condition = Operand::parse(in);
			in >> paren;
			return new Ternary(left, right, condition);
		}
		in >> paren;
		switch (operation)
		{
			case '+':
				return new Plus(left, right);
			case '-':
				return new Minus(left, right);
			case '*':
				return new Times(left, right);
			case '/':
				return new Divide(left, right);
			case '>':
				return new GreaterThan(left, right);
			case '<':
				return new LessThan(left, right);
			case '=':
				return new Equals(left, right);
			case '&':
				return new And(left, right);
			case '|':
				return new Or(left, right);
		}
	}
	return 0;
}
