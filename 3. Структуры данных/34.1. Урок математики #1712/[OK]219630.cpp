#include<iostream>
#include<cstdlib>
#include<fstream>
#include<algorithm>
#include<map>


int main() {

	std::ifstream fin("input.txt");
	if (!fin.is_open())
		std::cout << "Файл не может быть открыт!";
	int n;
	fin >> n;
	int numb;
	int* ans = new int[n];
	std::map<long long, int> sumNumbMap;
	while (fin >> numb) {
		sumNumbMap[numb]++;
	}

	ans[0] = (*sumNumbMap.begin()).first / 2;
	if ((*sumNumbMap.begin()).second == 1) {
		sumNumbMap.erase((*sumNumbMap.begin()).first);
	}
	else {
		sumNumbMap[(*sumNumbMap.begin()).first]--;
	}

	for (int i = 1; i < n; i++) {
		int nextMinNumb = (*sumNumbMap.begin()).first;
		ans[i] = nextMinNumb - ans[0];
		if (i == n - 1) break;
		int numbRemove = ans[i] * 2;
		if (sumNumbMap[numbRemove] == 1) {
			sumNumbMap.erase(numbRemove);
		}
		else {
			sumNumbMap[numbRemove]--;
		}
		for (int j = i - 1; j > -1; j--) {
				//int count = 2;
				int numbRemove = ans[i] + ans[j];
					if (sumNumbMap[numbRemove] == 2) {
						sumNumbMap.erase(numbRemove);
					}
					else {
						sumNumbMap[numbRemove]-=2;
					}
			}
	}

	std::ofstream fout("output.txt");
	for (int i = 0; i < n; i++) {
		fout << ans[i];
		fout << std::endl;
	}

	return 0;
}