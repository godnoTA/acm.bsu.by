#include <fstream>
#include <iostream>
#include <map> 
#include <list>
using namespace std;
list<int> emp;
ifstream fin("input.txt");
ofstream fout("output.txt");
map<int, list<int>> Map;
map<int, list<int>>::iterator it;
void print(int*answer, int n)
{
	for (int i = 0; i < n; i++)
			fout << answer[i] << " ";
}
void read(int&x, int n)
{
	for (int i = 0; i < n; i++)
	{Map[i] = emp;}	
	for (it = Map.begin(); it != Map.end(); ++it)
		for (int t = 0; t < n; t++)
		{
			fin >> x;
			if (x == 1)
				(it->second).push_back(t);
		}
}
void fill(int n, int*res, int&order)
{
	static int tmp;
	if (emp.size() == 0)
	{
		for (int i = 0; i < n; i++)
			if (res[i] == 0)
			{
				tmp = i;
				emp.push_back(tmp);
				break;
			}
	}
	else
		tmp = emp.front();
	it = Map.find(tmp);
	res[tmp] = order++;
}
int main()
{
	int n, x;
	fin >> n;
	int *res = new int[n];
	int *res1 = new int[n];
	for (int i = 0; i < n; i++) {
		res[i] = 0;
		if (i == 0)
			res1[i] = 1;
		else
		res1[i] = 0;
	}	
	read(x, n);
	int temp; 
	int y;
	int level = 1;
	it = Map.begin();
	emp.push_back(0);
	
	while (level <= n)
	{
		fill(n, res, level);
		temp = (it->second).size();
		for (int i = 0; i < temp; i++)
		{
			y = (it->second).front();
			(it->second).pop_front();
			if (res[y] == 0)
			{if (res1[y] == 0){emp.push_back(y);res1[y] = 1;}}
		}
		emp.pop_front();
	}

	print(res, n);
	return 0;
}