#include <algorithm>
#include<iostream>
#include <fstream>
#include <ctime>
using namespace std;
int n, m;
int stations[301];
int res[300][300];
int power[300][300];

void fillPower(){
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			power[i][j] = power[i][j - 1];
			for (int t = i; t < j; t++) {
				power[i][j] += stations[t] * stations[j];
			}
			power[j][i] = power[i][j];
		}
	}
}

int main()
{
	unsigned int start_time = clock();
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	fin >> n;
	fin >> m;
	for (int i = 0; i < n; i++)
		fin >> stations[i];

	fillPower();

	for (int i = 0; i < n; i++)
		res[i][0] = power[0][i];
	for (int j = 1; j <= m; j++) {
		for (int i = 0; i < n; i++) {
			res[i][j] = 1121250;
			for (int t = 0; t < i; t++) {
				res[i][j] = min(res[i][j], res[t][j - 1] + power[t + 1][i]);
			}
		}
	}
	fout << res[n - 1][m];
	return 0;
}