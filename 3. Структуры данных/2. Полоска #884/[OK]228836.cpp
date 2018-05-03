#include <iostream>
#include <fstream>
#include <stack>
#include <math.h>
#include <vector>

using namespace std;

void flop(stack<int>& a, stack<int>&b) {
	while (!b.empty()) {
		a.push(b.top());
		b.pop();
	}
}

int main()
{
	ifstream fin("in.txt");
	ofstream fout("out.txt");
	int n;
	fin >> n;

	long long size = pow(2, n);
	vector<stack<int>> mas(size);
	vector<int> ans(size);

	for (int i = 0; i < size; i++) {
		mas[i].push(i);
	}
	while (size) {
		for (int i = 0; i < size / 2; i++) {
			flop(mas[i], mas[size - i - 1]);
		}
		size /= 2;
	}
	int j = pow(2, n);
	while (!mas[0].empty()) {
		ans[mas[0].top()] = j;
		mas[0].pop();
		j--;
	}
	for (int i = 0; i < pow(2, n)-1; i++)
		fout << ans[i] << " ";
	fout << ans[pow(2, n) - 1];
	return 0;
}