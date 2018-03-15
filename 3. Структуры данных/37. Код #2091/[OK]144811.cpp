#include <iostream>
#include <fstream>
#include <queue>
#include <vector>

using namespace std;

int find(int a,int* p) {
	int index = a;
	while (p[a] > 0) {
		if (p[a] < 0) {
			index = a;
			return index;
		}
		else {
			index = p[a];
			a = p[a];
		}
	}
	return index;
}

int main() {

	ifstream fin("input.txt");
	ofstream fout("output.txt");

	int N;
	int R;
	int count = 0;
	fin >> N >> R;

	int* p = new int[N+1];
	for (int i = 0; i < N + 1; i++) {
		p[i] = -1;
	}
	int a;
	int b;
	for (int i = 0; i < R; i++) {
		fin >> a >> b;
		int temp_min = min(a, b);
		int temp_max = max(a, b);
		a = temp_min;
		b = temp_max;
		int root_one = find(a , p);
		int root_two = find(b , p);
		if (root_one == root_two) {
			count++;
		}
		else {
			if (p[root_one] <= p[root_two]) {
				p[root_one] += p[root_two];
				p[root_two] = root_one;
			}
			if(p[root_one] > p[root_two]){
				p[root_two] += p[root_one];
				p[root_one] = root_two;
			}
		}
	}
	
	fin.close();

	fout << count;

	fout.close();
}