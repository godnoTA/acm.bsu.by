#include<iostream>
#include<cstdlib>
#include<fstream>
#include<algorithm>
#include<vector>


int main() {

	std::ifstream fin("input.txt");
	if (!fin.is_open())
		std::cout << "Файл не может быть открыт!";
	int n,m;
	fin >> n;
	fin >> m;

	std::vector<std::vector<int>> matr(n, std::vector<int>());
	int* count = new int[n];
	for (int i = 0; i < n; i++) count[i] = 0;
	for (int it = 0; it < m; it++) {	
		int i;
		fin >>i ;
		int j;
		fin >> j;
		count[i - 1]++;
		count[j - 1]++;
		matr[i - 1].push_back(j); 
		matr[j - 1].push_back(i);
	}

	std::ofstream fout("output.txt");
	for (int i = 0; i < n; i++) {
		fout << count[i];
		fout << " ";
		for (int j = 0; j < matr[i].size(); j++) {
			fout << matr[i][j];
			fout << " ";
		}
		fout << std::endl;
	}

	return 0;
}