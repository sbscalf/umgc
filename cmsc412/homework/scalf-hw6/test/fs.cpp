#include <iostream>
#include <string>
#include <filesystem>

namespace fs = std::filesystem;
using namespace std;

int main()
{
	fs::path directory;

	cout << "Enter a directory: ";
	string input;
	cin >> input;

	if (fs::is_directory(fs::path(input)))
	{
		cout << "which file to remove? ";
		string file;
		cin >> file;

		string fullpath = input + "/" + file;
		fs::remove(fs::path(fullpath));
/*
		fs::path file;
		for (const auto &entry : fs::directory_iterator(input))
		{
			file = entry.path();
			cout << file.filename() << endl;
		}
*/
	}

	else
	{
		cerr << "'" << input << "' is not a directory!" << endl;
	}
}
