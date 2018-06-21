// 31.cpp: определяет точку входа для консольного приложения.
//
//#include "stdafx.h"
#include<iostream>
#include<fstream>
#include<vector>
#include<algorithm>

using namespace std;

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n;
	fin >> n;
	vector<int> start(n);
	vector<int> duration(n);
	vector<int> numbers(n);
	for (int i = 0; i < n; ++i) {
		fin >> start[i];
		fin >> duration[i];
		numbers[i] = i + 1;
	}
	long long fine = 0;
	long long curr_time = 0;
	for (int i = 0; i < n; ++i) {
		curr_time = max(curr_time, (long long)start[i]);
		long long  old_f = fine;
		fine += curr_time - start[i];
		if (fine < old_f)
			fine = old_f;
		curr_time += duration[i];
	}
	long long min_fine = fine;
	vector< vector <int> > works;
	vector<int> work;
	for (int i = 0; i < n; i++)
		work.push_back(i + 1);
	works.push_back(work);
	while (next_permutation(numbers.begin(), numbers.end())) {
		/*for (int i = 0; i < n; ++i) {
			cout << numbers[i];
		}
		cout << endl;*/
		curr_time = 0;
		fine = 0;
		for (int i = 0; i < n; ++i) {
			curr_time = max(curr_time, (long long)start[numbers[i] - 1]);
			long long old_f = fine;
			fine += - start[numbers[i] - 1] + curr_time;
			if (fine < old_f)
				fine = old_f;
			curr_time += duration[numbers[i] - 1];
		}
		if (fine < min_fine) {
			min_fine = fine;
			works.clear();
			vector<int> work;
			for (int i = 0; i < n; i++)
				work.push_back(numbers[i]);
			works.push_back(work);
		}
		else if (fine == min_fine) {
			vector<int> work;
			for (int i = 0; i < n; i++)
				work.push_back(numbers[i]);
			works.push_back(work);
		}
	}
	fout << min_fine << endl;
	for (int i = 0; i < works.size(); ++i) {
		for (int j = 0; j < works[i].size() - 1; ++j) {
			fout << works[i][j] << " ";
		}
		fout << works[i][works[i].size() - 1];
		fout << endl;
	}
	system("pause");
	/*int *numbers = new int(n);
	for (int i = 0; i < n; ++i) {
		numbers[i] = i + 1;
	}
	next_permutation(numbers, numbers + n - 1);*/
	
	
	return 0;
}




