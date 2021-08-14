#include "horizontal.h"
#include "fifo.h"
#include "opt.h"
#include "lru.h"
#include "lfu.h"

int main()
{
	std::vector<int> refStr{2, 4, 5, 6, 1, 5, 3, 4, 5, 2, 3, 6, 5, 3, 4, 7, 3, 5, 6};
//	simFIFO(refStr, 4);
//	simOPT(refStr, 4);
//	simLRU(refStr, 4);
	simLFU(refStr, 4);
	return 0;
}
