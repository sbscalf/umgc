#include <iostream>
#include <fstream>

using namespace std;

int main()
{
	ifstream test("readme", ios_base::binary);
	char c = test.get();
	int offset = 0x0;
	printf("%08X: ", offset);
	do
	{
		printf("%02X ", c);
		offset++;
		if ( (offset % 16) == 0)
			printf("\n%08X: ", offset);
		c = test.get();
	} while (c != EOF);
	cout << endl;
} // end main()
