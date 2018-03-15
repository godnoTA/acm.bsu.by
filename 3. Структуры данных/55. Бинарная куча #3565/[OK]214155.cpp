#include <iostream>
#include <fstream>
using namespace std;
void main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n;
	in >> n;
	int* MaxHeap = new int[n];
	for (int i = 0; i < n; i++) {
		in >> MaxHeap[i];
	}
	for (int i = 0; i < n; i++) {
		if ((2 * i + 1) < n && (2 * i + 2) < n) {
			if (MaxHeap[i] > MaxHeap[2 * i + 1] || MaxHeap[i] > MaxHeap[2 * i + 2]) {
				out << "No";
				return;
			}
		}
		else if ((2 * i + 1) < n) {
			if (MaxHeap[i] > MaxHeap[2 * i + 1]) {
				out << "No";
				return;
			}
		}
		else {
			out << "Yes";
			return;
		}
	}
}