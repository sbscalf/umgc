#ifndef FILEPARSER_H_
#define FILEPARSER_H_

#include "symboltable.h"
#include "expression.h"

class FileParser
{
public:
	int parse(string filename);
private:
	Expression* evaluateLine(stringstream& in);
	void parseAssignments(stringstream& in);
};

#endif /* FILEPARSER_H_ */
