#include <fstream>
#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

void min_add(int a, vector<int> &min_heap) {
	min_heap.push_back(a);
	int index = min_heap.size() - 1;
	int parent = (index - 1) / 2;

	while (index > 0 && min_heap[parent] > min_heap[index]) {
		int temp = min_heap[index];
		min_heap[index] = min_heap[parent];
		min_heap[parent] = temp;

		index = parent;
		parent = (index - 1) / 2;
	}
}

void max_add(int a, vector<int> &max_heap) {
	max_heap.push_back(a);
	int index = max_heap.size() - 1;
	int parent = (index - 1) / 2;

	while (index > 0 && max_heap[parent] < max_heap[index]) {
		int temp = max_heap[index];
		max_heap[index] = max_heap[parent];
		max_heap[parent] = temp;

		index = parent;
		parent = (index - 1) / 2;
	}
}

void min_heapify(int i, vector<int> &min_heap) {
	int LeftChild;
	int RightChild;
	int SmallestChild;

	for (;;) {
		LeftChild = i * 2 + 1;
		RightChild = i * 2 + 2;
		SmallestChild = i;
		if (LeftChild < min_heap.size() && min_heap[LeftChild] < min_heap[SmallestChild])
			SmallestChild = LeftChild;

		if (RightChild < min_heap.size() && min_heap[RightChild] < min_heap[SmallestChild])
			SmallestChild = RightChild;

		if (SmallestChild == i)
			break;

		int temp = min_heap[i];
		min_heap[i] = min_heap[SmallestChild];
		min_heap[SmallestChild] = temp;
		i = SmallestChild;
	}
}

void max_heapify(int i, vector<int> &max_heap) {
	int LeftChild;
	int RightChild;
	int LargestChild;

	for (;;) {
		LeftChild = i * 2 + 1;
		RightChild = i * 2 + 2;
		LargestChild = i;
		if (LeftChild < max_heap.size() && max_heap[LeftChild] > max_heap[LargestChild])
			LargestChild = LeftChild;

		if (RightChild < max_heap.size() && max_heap[RightChild] > max_heap[LargestChild])
			LargestChild = RightChild;

		if (LargestChild == i)
			break;

		int temp = max_heap[i];
		max_heap[i] = max_heap[LargestChild];
		max_heap[LargestChild] = temp;
		i = LargestChild;
	}
}

void min_deleteElement(vector<int> &min_heap)
{
	int result = min_heap[0];
	min_heap[0] = min_heap[min_heap.size() - 1];
	min_heap.erase(min_heap.end() - 1);
}

void max_deleteElement(vector<int> &max_heap)
{
	int result = max_heap[0];
	max_heap[0] = max_heap[max_heap.size() - 1];
	max_heap.erase(max_heap.end() - 1);
}

int main() {

	int M, N, a;

	vector<int> arr;
	vector<int> data;
	vector<int> min_heap;
	vector<int> max_heap;
	vector<int> copy_heap;
	FILE *in;
	FILE *out;
	freopen_s(&in, "input.txt", "r", stdin);
	freopen_s(&out, "output.txt", "w", stdout);
	fscanf_s(in, "%d %d", &M, &N);

	for (int i = 0; i < M; i++)
	{
		fscanf_s(in, "%d", &a);
		data.push_back(a);
	}

	for (int i = 0; i < N; i++)
	{
		fscanf_s(in, "%d", &a);
		arr.push_back(a - 1);
	}

	int j = 0;

	for (int i = 0; i < M; i++) {
		if (max_heap.size() == 0) {
			min_add(data[i], min_heap);
			if (arr[j] == i) {
				max_add(min_heap[0], max_heap);
				min_deleteElement(min_heap);
				if (min_heap.size() != 0)
					min_heapify(0, min_heap);
				if (j == N - 1)
					fprintf_s(out, "%d", max_heap[0]);
				else
					fprintf_s(out, "%d ", max_heap[0]);
				j++;
			}
		}
		else {
			if (data[i] >= max_heap[0])
				min_add(data[i], min_heap);

			if (data[i] < max_heap[0]) {
				min_add(max_heap[0], min_heap);
				max_deleteElement(max_heap);
				if (max_heap.size() != 0)
					max_heapify(0, max_heap);
				max_add(data[i], max_heap);
			}
		}
		while (j < N && arr[j] == i) {
			max_add(min_heap[0], max_heap);
			min_deleteElement(min_heap);
			if (min_heap.size() != 0)
				min_heapify(0, min_heap);
			if (j == N - 1)
				fprintf_s(out, "%d", max_heap[0]);
			else
				fprintf_s(out, "%d ", max_heap[0]);
			j++;
		}
	}

	return 0;
}