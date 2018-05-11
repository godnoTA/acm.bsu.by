#include <fstream>
#include <iostream>
#include <map> 
#include <list>
#include <vector>

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

	list<int> listEmpty;
	map<int, list<int>> myMap;
	map<int, list<int>>::iterator it;

	for (int i = 0; i < n; i++)
	{
		myMap[i] = listEmpty;
	}

	for(it = myMap.begin(); it != myMap.end(); ++it)
	{
		for(int j = 0; j < n; j++)
		{
			fin >> x;
			if (x == 1)
				(it->second).push_back(j);
		}
	}

	fin.close();
	ifstream fin1 ("input.txt");
	int o;
	fin1 >> o;
	vector<int> vector1;

	bool exit = false;
	int d, b, y, c, h;
	int order = 1;
	it = myMap.begin();
	bool exit1 = false;
	//vector1.push_back(0);
	for(int i = 0; i < o; i++)
	{
		exit1 = false;
		exit = false;
		d = it->first;
		b = d;
		answer[d] = order++;
		while( ((it->second).size() != 0) && (exit == false))
		{
			d = (it->second).front();
			if (answer[d] != 0)
				(it->second).pop_front();
			else
			{
				exit = true;
				if ((it->second).size() > 1)
				{
					(it->second).pop_front();
					vector1.push_back(it->first);
				}
			}
		}
		if((it->second).size() != 0)
			it = myMap.find(d);
		else
		{
			while((vector1.size() != 0) && (exit1 == false))
			{
				y = vector1.back();
				it = myMap.find(y);
				for(int i = 0; i < (it->second).size(); i++)
				{
					h = it->second.front();
					it->second.pop_front();
					if(answer[h] == 0)
					{
						exit1 = true;
						c = h;
						break;
					}
				}
				if ((it->second).size() == 0)
					vector1.pop_back();
				it = myMap.find(c);
			}
			if(vector1.size() == 0 && exit1 == false)
				for(int i = 0; i < n; i++)
					if (answer[i] == 0)
					{
						it = myMap.find(i);
						break;
					}
		}
	}
	
	for(int i = 0; i < o; i++)
		if(i == o - 1)
			fout << answer[i];
		else
			fout << answer[i] <<" ";

	return 0;
}
