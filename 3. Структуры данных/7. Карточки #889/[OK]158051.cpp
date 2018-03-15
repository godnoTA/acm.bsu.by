#include <iostream>
#include <fstream>
#include <deque>
#include <iterator>

using namespace std;

int main()
{
	ifstream fin("in.txt");
	ofstream fout("out.txt");
	if (fin.is_open())
	{
		int N = 0;
		fin >> N;
		int* mas = new int[N];
		deque<int> res;
		for (int i = 0, j = 1; (j < N) || (i < N); i += 2, j += 2)
		{
			mas[i] = 0;
			mas[j] = 1;
		}
		for (int i = N - 1; i >= 1; i--)
		{
			res.push_front(mas[i]);
			res.push_front(res.back());
			res.pop_back();
		}
		res.push_front(0);
		copy(res.begin(), res.end() - 1, ostream_iterator<int>(fout, " "));
		fout << res.back();
	}
	else
		cout << "File can't be opened" << endl;
	fin.close();
	fout.close();
	system("pause");
}