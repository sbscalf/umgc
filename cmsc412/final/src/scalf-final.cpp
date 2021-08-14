#include <iostream>
#include <string>
#include <regex>
#include <vector>
#include <stdlib.h>

/* CONSTANTS */
static const std::regex singleInteger("[0-9]{1}");
static const std::regex hasInteger(".*[0-9]+.*");
static const std::regex isInteger("[0-9]+");
static const int MAX_SIZE_OF_STRING = 20;
static const int MAX_NUM_OF_FRAMES = 7;

/* SIMULATION VARIABLES */
static std::vector<int> referenceString;
static int numFrames = MAX_NUM_OF_FRAMES;
static int pFrames[MAX_NUM_OF_FRAMES][MAX_SIZE_OF_STRING];
static char pageFaults[MAX_SIZE_OF_STRING];
static std::string victims[MAX_SIZE_OF_STRING];
static int priority[MAX_NUM_OF_FRAMES];

void setReferenceString()
{
	std::string input, ch;
	int good = 0;
	while (!good)
	{
		std::printf("\nPlease enter the reference string.\n");
		std::printf("Please note that only the first %d single-digit integers are accepted.\n",
					MAX_SIZE_OF_STRING);
		std::printf("Non-integer characters will be skipped/ignored.\n");
		std::cout << "> ";
		getline(std::cin >> std::ws, input);
		if(std::regex_match(input, hasInteger))
			good = 1;
		else
			std::printf("\nInput must have at least one integer.\n");
	}
	referenceString.clear();
	for (auto &c : input)
	{
		ch = c;
		if (std::regex_match(ch, singleInteger))
		{
			int temp = stoi(ch);
			referenceString.push_back(temp);
		}
		if (referenceString.size() >= MAX_SIZE_OF_STRING)
			break;
	}
}

void generateReferenceString()
{
	std::printf("Generating a reference string of %d integers.", MAX_SIZE_OF_STRING);
	referenceString.clear();
	for (int i = 0; i < MAX_SIZE_OF_STRING; i++)
		referenceString.push_back(rand() % 10);
}

void displayReferenceString()
{
	for (int i : referenceString)
		std::cout << i << " ";
	std::cout << "\n";
}

void printResults(std::string title)
{
	std::string pressEnter;
	std::cin.get();

	for (int page = 0; page < referenceString.size(); page++)
	{
		std::string refHead = "Reference String | ";
		int lineWidth = refHead.length() + (referenceString.size() * 3) - 1;
		std::string hLine = "\n-";
		for (int i = 0; i < lineWidth; i++)
			hLine.append("-");
		hLine.append("\n");
	
		std::cout << hLine << ' ' << title << hLine;	
	
		std::cout << refHead;
		for (int page : referenceString)
			std::printf("%2d ", page);
		std::cout << hLine;

		std::string frameLine;
		int value;
		for (int frame = 0; frame < numFrames; frame++)
		{
			frameLine = "Physical Frame ";
			frameLine.append(std::to_string(frame));
			frameLine.append(" | ");
			std::cout << frameLine;
			for (int i = 0; i < page + 1; i++)
			{
				value = pFrames[frame][i];
				if (value == -1)
					std::printf("%2s ", " ");
				else
					std::printf("%2d ", value);
			}
			std::cout << hLine;
		}

		int numFaults = 0;
		std::printf("%*s", refHead.length(), "Page Faults | ");
		for (int i = 0; i < page + 1; i++)
		{
			std::cout << ' ' << pageFaults[i] << ' ';
			if (pageFaults[i] == 'F')
				numFaults++;
		}
		std::cout << hLine;

		std::printf("%*s", refHead.length(), "Victim Frames | ");
		for (int i = 0; i < page + 1; i++)
		{
			std::cout << ' ';
			if (victims[i].empty())
				std::cout << ' ';
			else
				std::cout << victims[i];
			std::cout << ' ';
		}
		std::cout << hLine;

		std::printf("\nTotal Number of Page Faults: %d\n", numFaults);
		
		std::printf("\nPress enter to continue...");
		getline(std::cin, pressEnter);
		std::cout << '\n';
	} // end for each page
}

void resetArrays(int priorityBase)
{
	for (int i = 0; i < referenceString.size(); i++)
	{
		pageFaults[i] = ' ';
		victims[i].clear();
		for (int j = 0; j < numFrames; j++)
		{
			pFrames[j][i] = -1;
			priority[j] = priorityBase;
		}
	}
}

void simulateFIFO()
{
	resetArrays(0);

	int targetFrame = 0; // Initialize variable for "queue"

	for (int i = 0; i < referenceString.size(); i ++)
	{
		int currentPage = referenceString[i];

		// Try to find currentPage in memory
		int index = -1;
		for (int frame = 0; frame < numFrames; frame++)
		{
			if (pFrames[frame][i] == currentPage)
				index = frame;
		}

		// Page does not exist in memory
		if (index == -1)
		{
			pageFaults[i] = 'F';

			// physical frame is free
			if (pFrames[targetFrame][i] == -1)
				pFrames[targetFrame][i] = currentPage;

			// physical frame is occupied
			else
			{
				int victim = pFrames[targetFrame][i];
				pFrames[targetFrame][i] = currentPage;
				victims[i] = std::to_string(victim);
			}

			// Move to next target frame in "queue"
			targetFrame = (targetFrame + 1) % numFrames;
		}

		// Load values from current frame into next frame (for checks)
		if (i < (referenceString.size() - 1))
		{
			int next = i + 1;
			for (int frame = 0; frame < numFrames; frame++)
				pFrames[frame][next] = pFrames[frame][i];
		}

	}

	printResults("FIFO SIMULATION");
}

void simulateOPT()
{
	resetArrays(99);

	int initial = numFrames;
	for (int i = 0; i < referenceString.size(); i ++)
	{
		for (int f = 0; f < numFrames; f++)
		{
			if ((initial > -1) || (priority[f] != 99))
				priority[f] -= 1;
		}

		int targetFrame = 0;
		int currentPage = referenceString[i];

		// Try to find currentPage in memory
		// also set highest priority
		int index = -1;
		for (int frame = 0; frame < numFrames; frame++)
		{
			if (pFrames[frame][i] == currentPage)
				index = frame;
			if (priority[frame] > priority[targetFrame])
				targetFrame = frame;
		}


		// Page does not exist in memory
		if (index == -1)
		{
			pageFaults[i] = 'F';

			// physical frame is free
			if (pFrames[targetFrame][i] == -1)
				pFrames[targetFrame][i] = currentPage;

			// physical frame is occupied
			else
			{
				int victim = pFrames[targetFrame][i];
				pFrames[targetFrame][i] = currentPage;
				victims[i] = std::to_string(victim);
			}

		} // end page not exist

		if (initial > -1)
			initial--;

		// Set new priority
		int count = 0, nextOccurence = 99;
		for (int j = i + 1; j < referenceString.size(); j++)
		{
			count++;
			if (referenceString[j] == currentPage)
			{
				nextOccurence = count;
				break;
			}
		}

		// A check for all physical frames full prevents victim frames
		// being targeted before filling remaining physical frames
		if (index == -1)
		{
			if ((initial > -1) && (nextOccurence == 99))
				priority[targetFrame] -= 1;
			else
				priority[targetFrame] = nextOccurence;
		}
		else
		{
			if ((initial > -1) && (nextOccurence == 99))
				priority[index] -= 1;
			else
				priority[index] = nextOccurence;
		}

		if (i < (referenceString.size() - 1))
		{
			int next = i + 1;
			for (int frame = 0; frame < numFrames; frame++)
				pFrames[frame][next] = pFrames[frame][i];
		}

	}

	printResults("OPT SIMULATION");
}

void simulateLRU()
{
	resetArrays(0);

	for (int i = 0; i < referenceString.size(); i ++)
	{
		for (int f = 0; f < numFrames; f++)
			priority[f] += 1;

		int targetFrame = 0;
		int currentPage = referenceString[i];

		// Try to find currentPage in memory
		// also set highest priority
		int index = -1;
		for (int frame = 0; frame < numFrames; frame++)
		{
			if (pFrames[frame][i] == currentPage)
				index = frame;
			if (priority[frame] > priority[targetFrame])
				targetFrame = frame;
		}


		// Page does not exist in memory
		if (index == -1)
		{
			pageFaults[i] = 'F';

			// physical frame is free
			if (pFrames[targetFrame][i] == -1)
				pFrames[targetFrame][i] = currentPage;

			// physical frame is occupied
			else
			{
				int victim = pFrames[targetFrame][i];
				pFrames[targetFrame][i] = currentPage;
				victims[i] = std::to_string(victim);
			}

			priority[targetFrame] = 0;
		} // end page not exist

		// Page exists in memory
		else
			priority[index] = 0;

		// Fill next set
		if (i < (referenceString.size() - 1))
		{
			int next = i + 1;
			for (int frame = 0; frame < numFrames; frame++)
				pFrames[frame][next] = pFrames[frame][i];
		}

	}

	printResults("LRU SIMULATION");
}

void simulateLFU()
{
	resetArrays(-1);

	for (int i = 0; i < referenceString.size(); i ++)
	{
		int targetFrame = 0;
		int currentPage = referenceString[i];

		// Try to find currentPage in memory
		// also set highest priority
		int index = -1;
		for (int frame = 0; frame < numFrames; frame++)
		{
			if (pFrames[frame][i] == currentPage)
				index = frame;
			if (priority[frame] < priority[targetFrame])
				targetFrame = frame;
		}


		// Page does not exist in memory
		if (index == -1)
		{
			pageFaults[i] = 'F';

			// physical frame is free
			if (pFrames[targetFrame][i] == -1)
				pFrames[targetFrame][i] = currentPage;

			// physical frame is occupied
			else
			{
				int victim = pFrames[targetFrame][i];
				pFrames[targetFrame][i] = currentPage;
				victims[i] = std::to_string(victim);
			}

			priority[targetFrame] = 0;;
		} // end page not exist

		// Page exists in memory
		else
			priority[index] += 1;

		// Fill next set
		if (i < (referenceString.size() - 1))
		{
			int next = i + 1;
			for (int frame = 0; frame < numFrames; frame++)
				pFrames[frame][next] = pFrames[frame][i];
		}

	}

	printResults("LFU SIMULATION");
}

void getNumberOfFrames()
{
	int validInput = 0;
	int frames = MAX_NUM_OF_FRAMES;
	std::string input;
	while (!validInput)
	{
		std::printf("Please enter the number of physical frames [1-%d]: ",
			MAX_NUM_OF_FRAMES);
		std::cin >> input;
		std::cout << '\n';

		if ( std::regex_match(input, singleInteger) )
		{
			frames = std::stoi(input);
			if (frames > MAX_NUM_OF_FRAMES)
				std::cerr << "'" << input << "' is not within the range!\n";
			else
				validInput = 1;
		}
		else
		{
			std::cerr << "'" << input << "' is not a valid integer!\n";
		}
	}
	numFrames = frames;
}

void evaluateChoice(int choice)
{
	if (choice == 1)
		setReferenceString();
	else if (choice == 2)
		generateReferenceString();
	else
	{
		if (referenceString.empty())
			std::cerr << "No reference string exists!\n";
		else // referenceString.empty() == false
		{
			if (choice > 3)
				getNumberOfFrames();
			switch (choice)
			{
				case 3:
					displayReferenceString();
					break;
				case 4:
					simulateFIFO();
					break;
				case 5:
					simulateOPT();
					break;
				case 6:
					simulateLRU();
					break;
				case 7:
					simulateLFU();
			}
		} // end referenceString.empty() == false
	}
}

int main(int argc, char** argv)
{
	const std::string menu =
		"-----------------------\n"
		"Demand Paging Simulator\n"
		"-----------------------\n\n"
		"0. Exit\n"
		"1. Set Reference String\n"
		"2. Generate Reference String\n"
		"3. Display Current Reference String\n"
		"4. Simulate FIFO\n"
		"5. Simulate OPT\n"
		"6. Simulate LRU\n"
		"7. Simulate LFU\n";
	const std::regex validChoice{"[0-7]{1}"};

	int doAgain = 1;

	while (doAgain)
	{
		int validInput = 0;
		int choice;

		// Loop menu until input is valid selection
		while (!validInput)
		{
			std::cout << "\n\n" << menu;
			std::cout << "\nPlease enter your selection: ";
			std::string input;
			std::cin >> input;
			std::cout << "\n";

			if ( std::regex_match(input, validChoice) )
			{
				validInput = 1;
				int choice = stoi(input);
				if (choice == 0)
					doAgain = 0;
				else
					evaluateChoice(choice);
			}
			else
			{
				std::cerr << "'" << input << "' is not a valid selection!\n";
			}
		} // end while (!validInput)

	} // end while (doAgain)

	return 0;
}
