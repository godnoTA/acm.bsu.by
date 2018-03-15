#include <iostream>
#include <fstream>
#include <vector>
#include <list>
using namespace std;
int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int N, M;
	fin >> N >> M;
	vector <list <int>> Mat(N);
	typedef list <int>::iterator Lit;
	for (int i = 0; i < M; i++)
	{
		int I, J;
		fin >> I >> J;
		I--;
		J--;
		Mat[I].push_back(J);
		Mat[J].push_back(I);
	}
	fin.close();
	for (int i = 0; i < N; i++)
	{
		fout << Mat[i].size() << " ";
		for (Lit j = Mat[i].begin(); j != Mat[i].end(); j++)
		{
			fout << *j + 1 << " ";
		}
		fout << endl;
	}
	fout.close();
}