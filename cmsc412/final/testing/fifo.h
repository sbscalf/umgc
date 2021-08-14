#ifndef FIFO_H_
#define FIFO_H_

#include "horizontal.h"

#include <vector>
#include <cstring>
#include <string>

void simFIFO(std::vector<int> refStr, int numFrames)
{
	/* SET UP INITIAL VARIABLES */
	int pFrames[numFrames][refStr.size()];
	memset(pFrames, -1, sizeof(pFrames)); // Set all frames to -1;
	char pageFaults[refStr.size()];
	memset(pageFaults, ' ', sizeof(pageFaults));
	std::string victims[refStr.size()];
	memset(pageFaults, ' ', sizeof(pageFaults));


	/* SIMULATE FIFO  */
	int targetFrame = 0; // Initialize variable for "queue"

	for (int i = 0; i < refStr.size(); i ++)
	{

		int currentPage = refStr[i];

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

		if (i < (refStr.size() - 1))
		{
			int next = i + 1;
			for (int frame = 0; frame < numFrames; frame++)
				pFrames[frame][next] = pFrames[frame][i];
		}

	} // end iteration of refStr


	std::string first = "Reference String ";
	int width = first.length();
	int line = refStr.size() * 3;
	std::cout << first << "| ";
	for (int page : refStr)
		std::printf("%2d ", page);
	std::cout << '\n';
	horizontalLine(width,line);

	int rows = sizeof pFrames / sizeof pFrames[0];
	int cols = sizeof pFrames[0] / sizeof(int);
	for (int i = 0; i < rows; i++)
	{
		std::printf("%*s%1d | ", width - 2, "Physical Frame ", i);
		for (int j = 0; j < cols; j++)
		{
			if (pFrames[i][j] == -1)
				std::printf("%2s ", " ");
			else
				std::printf("%2d ", pFrames[i][j]);
		}
		std::cout << '\n';
		horizontalLine(width,line);
	}
	std::printf("%*s| ", width, "Page Faults ");
	for (char f : pageFaults)
		std::cout << ' ' << f << ' ';
	std::cout << '\n';
	horizontalLine(width, line);
	std::printf("%*s| ", width, "Victim Frame ");
	for (std::string f : victims)
	{
		std::cout << ' ';
		if (f.empty())
			std::cout << ' ';
		else
			std::cout << f;
		std::cout << ' ';
	}
	std::cout << '\n';
	std::cout << std::endl;
}

#endif /* FIFO_H_ */
