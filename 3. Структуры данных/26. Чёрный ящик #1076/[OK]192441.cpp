#define _CRT_DISABLE_PERFCRIT_LOCKS
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <queue>
#include <vector>

using namespace std;

class CompareMax {
public:
	bool operator() (int a, int b) {
		return b > a;
	}
};

class CompareMin {
public:
	bool operator() (int a, int b) {
		return b < a;
	}
};

void search(int currentNum, priority_queue <int, vector<int>, CompareMin>& minHeap, priority_queue <int, vector<int>, CompareMax>& maxHeap) {
	if (maxHeap.top() > currentNum) {
		minHeap.push(maxHeap.top());
		maxHeap.pop();
		maxHeap.push(currentNum);
	}
	else
		minHeap.push(currentNum);
}

inline void writeInt(int x)
{
	if (x < 0) 	{
		putchar('-');
		x = -x;
	}

	char buf[10], *p = buf;
	do {
		*p++ = '0' + x % 10;
		x /= 10;
	} while (x);
	do
	{
		putchar(*--p);
	} while (p > buf);
}

inline int nextInt()
{
	char c;
	while (c = getchar(), c <= ' ');

	bool sign = c == '-';
	if (sign)
		c = getchar();

	int res = c - '0';
	while (c = getchar(), c >= '0' && c <= '9')
		res = res * 10 + (c - '0');

	return sign ? -res : res;

}

int main() {
	priority_queue <int, vector<int>, CompareMax> maxHeap;
	priority_queue <int, vector<int>, CompareMin> minHeap;
	int M = 0, N = 0;
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	N = nextInt();
	M = nextInt();

	int * numbers = new int[N];

	for (int i = 0; i < N; i++)
		numbers[i] = nextInt();

	int j = 0;
	for (int i = 0; i < M; i++)		{
		int currentAdd = nextInt();
		if (minHeap.size() != 0) {
			maxHeap.push(minHeap.top());
			minHeap.pop();
		}
		else {
			maxHeap.push(numbers[j]);
			j++;
		}
		while (j != currentAdd) {
			search(numbers[j], minHeap, maxHeap);
			j++;
		}
		if (i != 0)
			putchar(' ');
		writeInt(maxHeap.top());
	}
	delete[] numbers;
	return 0;
}