#include <iostream>
#include <string>
#include <regex>
#include "filemanager.h"

using namespace std;

static FileManager filemanager;

// Simple method to print the menu of options
void printMenu()
{
	cout << endl << "FILE MANAGER (BETA)" << endl << endl;
	cout << "0 - Exit" << endl;
	cout << "1 - Select directory" << endl;
	cout << "2 - List directory content (first level)" << endl;
	cout << "3 - List directory content (all levels)" << endl;
	cout << "4 - Delete file" << endl;
	cout << "5 - Display file (hexadecimal view)" << endl;
	cout << "6 - Encrypt file (XOR with password)" << endl;
	cout << "7 - Decrypt file (XOR with password)" << endl;
	cout << endl << "Select option: ";
	return;
} // end printMenu()

void evaluateOption(int option)
{
	string target;
	switch (option)
	{
		case 1:
			filemanager.setDirectory();
			break;
		case 2:
			filemanager.listDirectory(0);
			break;
		case 3:
			filemanager.listDirectory(1);
			break;
		case 4:
			filemanager.deleteFile();
			break;
		case 5:
			filemanager.displayFile();
			break;
		case 6:
			filemanager.endecryptFile("encrypt");
			break;
		case 7:
			filemanager.endecryptFile("decrypt");
		default:
			return;
	}
	return;
}

// Starting point of program.
// Controls I/O interface for user.
int main()
{
	int doAgain = 1;
	string input;
	regex valid("[0-7]{1}");

	while (doAgain == 1)
	{
		printMenu();
		cin >> input;
		cout << "\n\n\n\n";
		if (regex_match(input, valid))
		{
			int option = stoi(input);
			if (option == 0)
				doAgain = 0;
			else
			{
				try
				{
					evaluateOption(option);
				}
				catch (string e)
				{
					cerr << e << "\nPlease try again." << endl;
				}
			} // end option != 0
		}
		else
			cout << "'" << input << "' is not a valid option.\nPlease try again." << endl;
	} // end while (doAgain == 1)

} // end int main()
