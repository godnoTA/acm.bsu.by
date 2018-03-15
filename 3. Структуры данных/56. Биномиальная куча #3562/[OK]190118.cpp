#include <iostream>
#include <fstream>
#include <vector>
using namespace std;
int main()
{
	setlocale(LC_ALL, "rus");
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	long long N;
	fin >> N;
//	cin >> N;
	fin.close();
	vector <long long> V;
	while (N != 1)
	{
		int buff = N % 2;
		V.push_back(buff);
		N = N / 2;
	}
	V.push_back(1);
	int P = 0;
	for (int i = 0; i < V.size(); i++)
	{
		if (V[i])
		{
			fout << P << endl;
		}
		P++;
	}
	fout.close();
}