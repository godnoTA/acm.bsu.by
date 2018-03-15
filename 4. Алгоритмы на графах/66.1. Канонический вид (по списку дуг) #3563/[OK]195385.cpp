#include <iostream>
#include <fstream>
#include <vector>
#include <list>
using namespace std;
int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int N;
	fin >> N;
	vector <pair <int, int>> V;
	for (int i = 0; i < (N - 1); i++)
	{
		int I1, I2;
		fin >> I1 >> I2;
		V.push_back(make_pair(I1, I2));
	}
	fin.close();
	vector <int> Result;
	for (int i = 0; i < N; i++)
	{
		Result.push_back(0);
	}
	for (int i = 0; i < (N - 1); i++)
	{
		int I1 = V[i].first;
		int I2 = V[i].second;
		I2--;
		Result[I2] = I1;
	}
	for (int i = 0; i < N; i++)
	{
		fout << Result[i] << " ";
	}
	return 0;
}