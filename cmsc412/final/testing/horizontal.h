#ifndef HORIZONTAL_H_
#define HORIZONTAL_H_

#include <iostream>
#include <iomanip>

void horizontalLine(int hWidth, int colWidth)
{
	std::cout << std::setw(hWidth) << std::cout.fill('-') << '+';
	std::cout << std::setw(colWidth) << std::cout.fill('-') << '\n';
}

#endif /* HORIZONTAL_H_ */
