#include <iostream>
#include <fstream>
#include <list>

using namespace std;

int main()
{
	ifstream input("input.txt");
	ofstream output("output.txt");


		int N = 0, M = 0;
		input >> N;
		input >> M;
		list<int>* A = new list<int>[N];
		for (int i = 0; i < M; i++)
		{
			int k, l;
			input >> k;
			input >> l;
			A[k - 1].push_front(l);
			A[l - 1].push_front(k);
		}
		for (int i = 0; i < N; i++)
		{
			output << A[i].size();
			for (int x : A[i])
				output << " " << x;
			output << endl;
		}

}