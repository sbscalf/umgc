#ifndef EXCEPTIONS_H
#define EXCEPTIONS_H

#include <exception>

class InvalidChoice: public std::exception
{
	virtual const char* what() const throw()
	{
		return "Input was not a valid choice";
	}
} invalidChoice;

#endif
