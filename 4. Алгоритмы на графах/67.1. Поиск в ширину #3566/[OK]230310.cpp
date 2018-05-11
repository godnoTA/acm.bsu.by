#include <fstream>
#include <iostream>
#include <map> 
#include <list>

using namespace std;

int main()
{
	ifstream fin ("input.txt");
    ofstream fout ("output.txt");

	int n, x;

	fin >> n;

	int *answer = new int[n];
	for(int i = 0; i < n; i++)
		answer[i] = 0;

	int *answer1 = new int[n];
	for(int i = 0; i < n; i++)
		answer1[i] = 0;

	list<int> listEmpty;
	map<int, list<int>> myMap;
	map<int, list<int>>::iterator it;

	for (int i = 0; i < n; i++)
	{
		myMap[i] = listEmpty;
	}

	for(it = myMap.begin(); it != myMap.end(); ++it)
		for(int j = 0; j < n; j++)
		{
			fin >> x;
			if (x == 1)
				(it->second).push_back(j);
		}
	
	bool exit = false;
	int d, b, y;
	int order = 1;
	it = myMap.begin();
	listEmpty.push_back(0);
	answer1[0] = 1;
	while(order <= n)
	{	
		if (listEmpty.size() == 0)
		{
			for(int i = 0; i < n; i++)
				if (answer[i] == 0)
				{
					d = i;
					listEmpty.push_back(d);
					break;
				}
		}
		else
			d = listEmpty.front();
		it = myMap.find(d);
		answer[d] = order++;
		b = (it->second).size();
		for(int i = 0; i < b; i++)
		{
			y = (it->second).front();
			(it->second).pop_front();
			if (answer[y] == 0)
			{
				if(answer1[y] == 0)
				{
					listEmpty.push_back(y);
					answer1[y] = 1;
				}
			}
		}
		listEmpty.pop_front();
	}

	for(int i = 0; i < n; i++)
		if(i == n - 1)
			fout << answer[i];
		else
			fout << answer[i] <<" ";

	return 0;
}