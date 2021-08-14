#include <iostream>
#include <thread>
#include <unistd.h>

using namespace std;

#define NUMBER_OF_ITERATIONS 5
#define NUMBER_OF_THREADS 3

void printIteration(int threadId) {
	int i;

	sleep(threadId);
	for (i = 0; i < NUMBER_OF_ITERATIONS; i++) {
		cout << "Thread " << threadId << " - iteration no. " << i+1 << endl;
		sleep(NUMBER_OF_THREADS);
	}
	pthread_exit(NULL);
}

int main() {
	int i;
	thread threads[NUMBER_OF_THREADS];

	for (i = 0; i < NUMBER_OF_THREADS; i++) {
		threads[i] = thread(printIteration, i+1);
	}

	for (i = 0; i < NUMBER_OF_THREADS; i++) {
		threads[i].join();
	}
}
