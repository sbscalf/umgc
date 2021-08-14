#include "filemanager.h"
#include <iostream>
#include <fstream>
#include <regex>

using namespace std::filesystem;

FileManager::FileManager()
{
	this->directory = "";
}

void FileManager::setDirectory()
{
	string directory;
	cout << "Please provide the path for the directory: ";
	cin >> directory;
	if (exists(path(directory)))
	{
		if (is_directory(path(directory)))
		{
			path target = canonical(directory);
			this->directory = target;
			std::cout << "\nSet directory to " << target.string() << "\n";
		}
		else
		{
			string error = "'" + directory + "' is not a directory.";
			throw error;
		}

	} // end if exists
	else
	{
		string error = "'" + directory + "' does not exist.";
		throw error;
	}
}

void FileManager::listDirectory(int recursive)
{
	if (this->directory.empty())
	{
		string error = "No directory has been set!";
		throw error;
	}
	else
	{
		std::cout << std::endl << "Showing contents" << std::endl;
		path file;
		if (recursive == 1)
		{
			for (auto& p : recursive_directory_iterator(directory))
				std::cout << p.path() << std::endl;
		}
		else
			for (auto& p :directory_iterator(directory))
				std::cout << p.path() << std::endl;
	}
}

path FileManager::getFile()
{
	string file;
	std::cout << "Please provide the filename (no path): ";
	std::cin >> file;
	path filename = this->directory / file;
	if (exists(filename))
	{
		if (!is_directory(filename))
			return filename;
		else
		{
			string error = "'" + file + "' is a directory.";
			throw error;
		}
	}
	else
	{
		string error = "Could not find a file with the name '" + file + "'";
		throw error;
	}
}

path FileManager::getOutFile(string type, path source)
{
	string file;
	std::cout << "Please provide the name for the " << type << " file (no path): ";
	std::cin >> file;
	path filename = this->directory / file;
	if (exists(filename))
	{
		if (is_directory(filename))
		{
			string error = "'" + file + "' is a directory.";
			throw error;
		}
		if (filename.filename() == source.filename())
		{
			string error = "'" + file + "' is the same as the source!";
			throw error;
		}
		remove(filename);
	}
	return filename;
}

void FileManager::deleteFile()
{
	if (this->directory.empty())
	{
		string error = "No directory has been set!";
		throw error;
	}
	else
	{
		path target = getFile();
		regex confirm("[:space:]*[y|Y]{1}.*");
		string response, clear;
		std::cout << "Are you sure you want to delete " << target.filename() << " (y/n)? ";
		std::cin >> response;
		std::getline(std::cin, clear);
		std::cout << std::endl;
		if (regex_match(response, confirm))
		{
			remove(target);
			std::cout << "Removed " << target.string() << std::endl;
		}
		else
			std::cout << "Aborting removal of " << target.string() << std::endl;
	}
}

void FileManager::displayFile()
{
	if (this->directory.empty())
	{
		string error = "No directory has been set!";
		throw error;
	}
	else
	{
		path target = getFile();
		std::ifstream stream(target.string(), ios_base::binary);
		char c = stream.get();
		int offset = 0x0;
		do
		{
			if ( (offset % 16) == 0)
				std::printf("\n%08X: ", offset);
			printf("%02X ", c);
			offset++;
			c = stream.get();
		} while (c != EOF);
		std::cout << std::endl;
	}
}

void FileManager::endecryptFile(string type)
{
	if (this->directory.empty())
	{
		string error = "No directory has been set!";
		throw error;
	}

	path target = getFile();
	std::ifstream stream(target.string(), ios_base::binary);

	path output = getOutFile(type, target);
	std::ofstream outstream(output, ios_base::binary);

	string password;
	std::cout << "Please enter the password: ";
	std::cin >> password;

	char c = stream.get();
	char p, e; // p = current password char; e = endecrypted char
	int i = 0;
	int length = password.length();
	while (c != EOF)
	{
		p = password[i];
		e = (c ^ p);
		outstream.put(e);
		c = stream.get();
		i = (i + 1) % length;
	}
	outstream.close();
}
