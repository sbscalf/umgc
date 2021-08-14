#include <iostream>
#include <fstream>

using namespace std;

static const char* WS = " \t\n\r\f\v";

int main()
{
	fstream test;
	test.open("readme");
	if (test.is_open())
	{
		int offset = 0x0;
		string line;
		while (getline(test, line))
		{
			printf("%08X: ", offset);
			for (int i = 0; i < line.length(); i++)
			{
				char c = line[i];
				for (const char &a : WS)
				{
					if (c == a)
						cout << "WHITESPACE";
				}
				offset++;
				printf("%02X ", c);
				if (offset % 16 == 0)
					printf("\n%08X: ", offset);
			} // end line loop

		} // end read file
	} // end if file is_open()

	cout << endl;

} // end main()
