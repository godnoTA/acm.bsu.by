#include <iostream>
#include <fstream>
#include <algorithm>
#include <list>
#include <vector>

using namespace std;

int main()
{
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	int n, m,a,b;
	f1 >> n >> m;
	vector <list <int> > mas(n, list <int>(0));
	for (int i = 0; i < m; i++)
	{
		f1 >> a >> b;
		mas[a - 1].push_back(b);
		mas[b - 1].push_back(a);
	}
	std::list<int>::iterator it;
	for (int i = 0; i < n; i++)
	{
		if (i != 0) f2 << endl;
		std::list<int>::iterator it = mas[i].begin();
		f2 << mas[i].size();
		for (int j = 0; j < mas[i].size(); j++)
		{
			f2 << ' ';
			f2 << *it;
			it++;
		}
	}
	f1.close();
	f2.close();
	return 0;
}