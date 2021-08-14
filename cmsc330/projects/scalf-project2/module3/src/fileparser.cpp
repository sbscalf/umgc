#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
using namespace std;

#include "fileparser.h"
#include "expression.h"
#include "subexpression.h"
#include "symboltable.h"
#include "parse.h"

extern SymbolTable symbolTable;

int FileParser::parse(string filename)
{
	fstream myFile;
	myFile.open(filename);
	if (myFile.is_open())
	{
		string line;
		while (getline(myFile, line))
		{
			symbolTable.clear();
			stringstream in(line);
			Expression* expression = evaluateLine(in);
			int result = expression->evaluate();
			cout << line << " Value = " << result << endl;
		}
		return 0;
	}
	else
	{
		cerr << "Could not open file";
		return -1;
	}
}

Expression* FileParser::evaluateLine(stringstream& in)
{
	Expression* expression;
	char paren, comma;
	in >> paren;
	expression = SubExpression::parse(in);
	in >> comma;
	parseAssignments(in);
	return expression;
}

void FileParser::parseAssignments(stringstream& in)
{
	char assignop, delimiter;
	string variable;
	double value;
	do
	{
		variable = parseName(in);
		in >> ws >> assignop >> value >> delimiter;
		symbolTable.insert(variable, value);
	}
	while (delimiter == ',');
}
