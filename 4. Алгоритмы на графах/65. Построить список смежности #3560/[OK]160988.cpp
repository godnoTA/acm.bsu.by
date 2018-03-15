#include <iostream>
#include <fstream>
#include <list>

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	if (fin.is_open())
	{
		int N = 0, M = 0;
		fin >> N;
		fin >> M;
		list<int>* A = new list<int>[N];

		for (int i = 0; i < M; i++)
		{
			int k, l;
			fin >> k;
			fin >> l;
			A[k - 1].push_front(l);
			A[l - 1].push_front(k);
		}

		for (int i = 0; i < N; i++)
		{
			fout << A[i].size();
			for (int x : A[i])
				fout << " " << x;
			fout << endl;
		}
	}

	fin.close();
	fout.close();
}