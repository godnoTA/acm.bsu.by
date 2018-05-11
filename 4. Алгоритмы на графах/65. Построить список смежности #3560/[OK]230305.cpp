#include <fstream>
#include <iostream>
#include <map> 
#include <vector>

using namespace std;
 
int main ()
{
    ifstream fin ("input.txt");
    ofstream fout ("output.txt");

	int m, n, d, t;

	fin >> n;
	fin >> m;

	d = m;
	t = n;
	
	vector<int> vectorEmpty;

	map <int, vector<int>> myFirstMap;

	int T;

	for (int i = 0; i < n; i++)
	{
		myFirstMap[i] = vectorEmpty;
	}
	
	map<int, vector<int>>::iterator it;


	while (fin >> n)
	{
		fin >> m;
		for (it = myFirstMap.begin(); it != myFirstMap.end(); ++it)
		{
			it = myFirstMap.find(n-1);
			(it->second).push_back(m);
			it = myFirstMap.find(m-1);
			(it->second).push_back(n);
			break;
		}
	}

	for (it = myFirstMap.begin(); it != myFirstMap.end(); ++it)
		{
			fout << (it->second).size();
			for(int i = 0; i < (it->second).size(); i++)
				fout << ' ' << (it->second)[i] ;
			fout << endl;
		}
	fin.close();
	fout.close();
	return 0;
}