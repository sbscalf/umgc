#ifndef FILEMANAGER_H_
#define FILEMANAGER_H_

#include <string>
#include <filesystem>

using namespace std;

class FileManager
{
public:
	FileManager();
	void setDirectory();
	void listDirectory(int recursive);
	void deleteFile();
	void displayFile();
	void endecryptFile(string type);
private:
	filesystem::path directory;
	filesystem::path getFile();
	filesystem::path getOutFile(string type, filesystem::path source);
};


#endif /* FILEMANAGER_H_ */
