#include <iostream>
#include <string>

int main()
{
	std::string phony;
	for (int i = 0; i < 5; i++)
	{
		std::printf("This is iteration %d!\n", i);
		std::cout << "Press enter to continue...\n";
		getline(std::cin, phony);
	}
}
