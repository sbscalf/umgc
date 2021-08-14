#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <vector>
#include <deque>

using namespace std;

fstream myFile;
int numberOfResources, numberOfProcesses;
vector<int> available;
vector< vector<int> > allocated, maximum, need;
vector< vector<int> > safeSequences;


void* printData()
{
	cout << "Number of Resources: " << numberOfResources << endl << endl;
	cout << "Number of Processes: " << numberOfProcesses << endl << endl;
	cout << "Available" << endl;
	for (int i = 0; i < available.size(); i++)
		cout << available[i] << " ";
	cout << endl << endl;
	cout << "Allocated" << endl;
	for (int i = 0; i < allocated.size(); i++)
	{
		for (int j = 0; j < allocated[i].size(); j++)
			cout << allocated[i][j] << " ";
		cout << endl;
	}
	cout << endl;
	cout << "Maximum" << endl;
	for (int i = 0; i < maximum.size(); i++)
	{
		for (int j = 0; j < maximum[i].size(); j++)
			cout << maximum[i][j] << " ";
		cout << endl;
	}
	cout << endl;
	cout << "Need" << endl;
	for (int i = 0; i < need.size(); i++)
	{
		for (int j = 0; j < need[i].size(); j++)
			cout << need[i][j] << " ";
		cout << endl;
	}
	cout << endl;
	return 0;
}

string getLine()
{
	string line;
	getline(myFile, line);
	if (line.length() > 0)
		return line;
	else
	{
		while (line.length() == 0)
		{
			getline(myFile, line);
		}
		return line;
	}
}

int parseMaximum()
{
	string line = getLine();
	if (line.compare("maximum") != 0) {
		cerr << "Expected 'maximum', but got " << line << endl;
		return -1;
	}
	else
	{
		for (int proc = 0; proc < numberOfProcesses; proc++)
		{
			vector<int> procAlloc;
			string line = getLine();
			stringstream ss(line);
			string temp;
			int value;
			for (int i = 0; i < numberOfResources; i++)
			{
				getline(ss, temp, ' ');
				value = stoi(temp);
				procAlloc.push_back(value);
			}
			maximum.push_back(procAlloc);
		}
		return 0;
	}
}

int parseAllocated()
{
	string line = getLine();
	if (line.compare("allocated") != 0)
	{
		cerr << "Expected 'allocated', but got " << line << endl;
		return -1;
	}
	else
	{
		for (int proc = 0; proc < numberOfProcesses; proc++)
		{
			vector<int> procAlloc;
			string line = getLine();
			stringstream ss(line);
			string temp;
			int value;
			for (int i = 0; i < numberOfResources; i++)
			{
				getline(ss, temp, ' ');
				value = stoi(temp);
				procAlloc.push_back(value);
			}
			allocated.push_back(procAlloc);
		}
		return parseMaximum();
	}
}

int parseAvailable()
{
	string line = getLine();
	if (line.compare("available") != 0)
	{
		cerr << "Expected 'available', but found " << line << endl;
		return -1;
	}
	else
	{
		line = getLine();
		stringstream ss(line);
		string temp;
		int value;
		for (int i = 0; i < numberOfResources; i++)
		{
			getline(ss, temp, ' ');
			value = stoi(temp);
			available.push_back(value);
		}
		return parseAllocated();
	}
}

int checkNumberOfProcesses()
{
	string line = getLine();
	if (line.compare("numberOfProcesses") != 0)
	{
		cerr << "Expected 'numberOfProcesses', but found " << line << endl;
		return -1;
	}
	else
	{
		line = getLine();
		numberOfProcesses = stoi(line);
		if (numberOfProcesses < 1 || numberOfProcesses > 9)
		{
			cerr << "Number of Processes must be ";
			cerr << "between 0 and 10, exclusively" << endl;
			return 0;
		}
		return parseAvailable();
	}
}

int checkNumberOfResources()
{
	string line = getLine();
	if (line.compare("numberOfResources") != 0)
	{
		cerr << "Expected 'numberOfResources', but found " << line << endl;
		return -1;
	}
	else
	{
		line = getLine();
		numberOfResources = stoi(line);
		if (numberOfResources < 1 || numberOfResources > 9)
		{
			cerr << "Number of Resources must be ";
			cerr << "between 0 and 10, exclusively" << endl;
			return 0;
		}
		return checkNumberOfProcesses();
	}
}

int parse(string& filename)
{
	int result;
	myFile.open(filename);
	if (myFile.is_open())
	{
		cout << endl << "Parsing file " << filename << " . . ." << endl;
		result = checkNumberOfResources();
	}
	else
	{
		cerr << endl << "Could not open file" << endl;
		result = -1;
	}
	myFile.close();
	return result;
}

int compareSequence(vector<int>& stored, vector<int>& comparison)
{
	int unique = 0;
	for (int i = 0; i < stored.size(); i++)
	{
		if (stored[i] != comparison[i])
			unique = 1;
	}
	return unique;
}

int compareSequences(vector<int>& sequence)
{
	vector<int> uniqueness;
	for (int i = 0; i < safeSequences.size(); i++)
	{
		int unique = compareSequence(safeSequences[i], sequence);
		uniqueness.push_back(unique);
	}
	int trulyUnique = 1;
	for (int i = 0; i < uniqueness.size(); i++)
	{
		if (uniqueness[i] == 0)
			trulyUnique = 0;
	}
	return trulyUnique;
}

void addUniqueSequence(vector<int>& sequence)
{
	if (safeSequences.size() == 0)
		safeSequences.push_back(sequence);
	else
	{
		if (compareSequences(sequence) == 1)
			safeSequences.push_back(sequence);
	}
}


int compareNeed(vector<int> work, vector<int> procNeed)
{
	int safe = 1;
	for (int i = 0; i < procNeed.size(); i++)
	{
		if (procNeed[i] > work[i])
		{
			safe = 0;
			break;
		}
	}
	return safe;
}

int findSafeSequences(vector<int> work, deque<int> processes, int current, vector<int> sequence)
{
	processes.pop_front();
	sequence.push_back(current);

	vector<int> procNeed = need.at(current);
	int safe = compareNeed(work, procNeed);
	if (safe == 0)
		return -1;

	for (int i = 0; i < numberOfResources; i++)
		work[i] += allocated[current][i];


	// CASES
	if (processes.size() == 0)
	{
		if (safe == 1)
			addUniqueSequence(sequence);
		return 1;
	}

	if (processes.size() == 1)
		findSafeSequences(work, processes, processes.front(), sequence);

	if (processes.size() > 1)
	{
		for (int i = 0; i < processes.size(); i++)
		{
			int index = i;
			deque<int> newOrder;
			for (int j = 0; j < processes.size(); j++)
			{
				newOrder.push_back(processes.at(index));
				index = (index + 1) % processes.size();
			}
			findSafeSequences(work, newOrder, newOrder.front(), sequence);
		}
	}

	return 0;
}

void calculateNeed()
{
	int value;
	for (int proc = 0; proc < numberOfProcesses; proc++)
	{
		vector<int> procNeed;
		for (int res = 0; res < numberOfResources; res++)
		{
			value = maximum[proc][res] - allocated[proc][res];
			procNeed.push_back(value);
		}
		need.push_back(procNeed);
	}
	return;
}

void printSafeSequences()
{
	cout << endl << "Safe Sequences" << endl;
	cout << "--------------" << endl;
	if (safeSequences.size() > 0)
	{
		for(int i = 0; i < safeSequences.size(); i++)
		{
			for (int j = 0; j < safeSequences[i].size(); j++)
				cout << "P" << safeSequences[i][j] << " ";
			cout << endl;
		}
	}
	cout << endl;
}

void resetVariables()
{
	numberOfResources = 0, numberOfProcesses = 0;
	available.clear();
	allocated.clear();
	maximum.clear();
	need.clear();
	safeSequences.clear();
}

int main(int argc, char** argv)
{
	int repeat = 1, valid = 0;
	while (repeat)
	{
		resetVariables();

		string filename, response;
		cout << endl << "Please enter a filename: ";
		cin >> filename;

		if (parse(filename) == 0)
		{
			calculateNeed();
			//printData();


			// Find all possible safe Sequences
			for (int p = 0; p < numberOfProcesses; p++)
			{
				int index = p;
				deque<int> processes;
				for (int c = 0; c < numberOfProcesses; c++)
				{
					processes.push_back(index);
					index = (index + 1) % numberOfProcesses;
				}

				vector<int> sequence;
				findSafeSequences(available, processes, processes.front(), sequence);
			}
			// End finding safe sequences

			printSafeSequences();

		} // end if (parse(filename) == 0)

		valid = 0;
		while (!valid)
		{
			cout << endl << "Would you like to continue (y or n)? ";
			cin >> response;
			if (response.compare("y") == 0
			  || response.compare("n") == 0
			  || response.compare("Y") == 0
			  || response.compare("N") == 0)
				valid = 1;
			else
			{
				cerr << "'" << response << "' is not a valid response." << endl;
			}
		} // end while (!valid)
		if (response.compare("n") == 0 || response.compare("N") == 0)
			repeat = 0;
	} // end while (repeat)
}