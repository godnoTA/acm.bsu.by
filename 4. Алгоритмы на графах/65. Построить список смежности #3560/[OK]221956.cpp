#include <fstream>
#include <iostream>
#include <list>
#include <vector>

using namespace std;

int main()
{
	int m, n;
	ifstream f("input.txt");
	f >> n >> m;
	vector<list<int>> arr;
	arr.resize(n);
	
	int a, b;
	for (int i = 0; i < m;i++)
	{
		f >> a;
		f >> b;
		arr[a - 1].push_back(b);
		arr[b - 1].push_back(a);
	}
	f.close();
	ofstream of("output.txt");
	list<int>::iterator it;
	for (int i = 0; i < n; i++)
	{
		of << arr[i].size()<<" ";
		for (it = arr[i].begin();it != arr[i].end();it++)
		of << *it << " ";
		of << '\n';
	}
	of.close();
	return 0;
}