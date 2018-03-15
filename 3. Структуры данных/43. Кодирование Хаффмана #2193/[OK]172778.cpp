#include <iostream>
#include <queue>
#include <fstream>
#include <algorithm>
using namespace std;
bool min_heap(long long a, long long b) { return a < b ? true : false; }
class Heap
{
public:
	long long s = 0;

	long long parent(long long i)
	{
		return (i - 1) / 2;
	}

	long long left(long long i)
	{
		return 2 * i + 1;
	}

	long long right(long long i)
	{
		return 2 * i + 2;
	}
	

	void heapify(long long* A, long long heap_size, long long i, bool(*cmp)(long long , long long))
	{
		long long l = left(i);
		long long r = right(i);
		long long current = i;

		if (l < heap_size && cmp(A[l], A[current]))
			current = l;
		if (r < heap_size && cmp(A[r], A[current]))
			current = r;

		if (current != i)
		{
			swap(A[i], A[current]);
			heapify(A, heap_size, current, cmp);
		}
	}
	void build_heap(long long* A, bool(*cmp)(long long, long long))
	{
		for (long long i = size(A) / 2 - 1; i >= 0; i--)
			heapify(A, size(A), i, cmp);
	}
	long long heap_get(long long* A)
	{
		return A[0];
	}
	long long heap_extract(long long* A, bool(*cmp)(long long, long long))
	{
		long long e = heap_get(A);
		A[0] = A[ size(A) - 1];
		s--;
		heapify(A, size(A), 0, cmp);
		return e;
	}
	long long size(long long* a)
	{
		return s;
	}
	void heap_insert(long long* A,long long k, bool(*cmp)(long long,long long))
	{
		A[s]=k;
		s++;
		heap_shift_up(A, size(A) - 1, cmp);
	}

	void heap_shift_up(long long* A, long long i, bool(*cmp)(long long,long long))
	{
		while (i > 0 && cmp(A[ i], A[parent(i)]))
		{
			swap(A[i], A[parent(i)]);
			i = parent(i);
		}
	}
};

long long N, c;
ifstream inStream("huffman.in");
ofstream outStream("huffman.out");

int main()
{
	inStream >> N;
	Heap H;
	long long* A = new long long[N+1];

	for (long long i = 0; i < N; i++)
	{
		inStream >> c;
		H.heap_insert(A,c, min_heap);
	}

	long long length = 0;
	while (H.s > 1)
	{
		long long k  = H.heap_extract(A,min_heap) + H.heap_extract(A, min_heap);
		length+=k;
		H.heap_insert(A, k, min_heap);
	}
	outStream << length;
	outStream.close();
	return 0;
}