#include <iostream>
using namespace std;

#include "fileparser.h"
#include "symboltable.h"

SymbolTable symbolTable;

int main(int argc, char** argv)
{
    string filename;

    if (argc > 1)
    	filename = argv[1];
    else
    	filename = "default.txt";

    FileParser fp;
    return fp.parse(filename);

}
