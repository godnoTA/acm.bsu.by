#include <fstream>
#include <iostream>
#include <map> 
#include <vector>

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	int m, N;

	fin >> N;
	fin >> m;

	int s = m;
	int t = N;

	vector<int> vec;
	map <int, vector<int>> Map;


	for (int i = 0; i < N; i++)
	{
		Map[i] = vec;
	}


	while (fin >> N)
	{
		fin >> m;
		for (map<int, vector<int>>::iterator iter = Map.begin(); iter != Map.end(); ++iter)///вывод на экран
		{
			iter = Map.find(N - 1);
			(iter->second).push_back(m);
			iter = Map.find(m - 1);
			(iter->second).push_back(N);
			break;
		}
	}

	fin.close();

	for (map<int, vector<int>>::iterator it = Map.begin(); it != Map.end(); ++it)///вывод на экран
	{
		fout << (it->second).size();
		for (int i = 0; i < (it->second).size(); i++)
			fout << ' ' << (it->second)[i];
		fout << endl;
	}
	
	fout.close();
	return 0;
}