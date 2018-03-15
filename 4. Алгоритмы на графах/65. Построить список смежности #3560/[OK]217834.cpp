#include <iostream>
#include <fstream>

#include <vector>
using namespace std;

int main()
{
	
	vector<vector<int>> my_list;
	vector<int> vect;
	ofstream fout("output.txt");
	ifstream fin("input.txt");
	int n, m;
	fin >> n>>m;
	vect.push_back(0);

	for (int i = 0; i < n; i++)
	{	
		my_list.push_back(vect);
	}

	while (m>0)
	{
		m--;
		int a, b;
		fin >> a >> b;
		my_list[a - 1][0]++;
		my_list[a - 1].push_back(b);
		my_list[b - 1][0]++;
		my_list[b - 1].push_back(a);

	}
	
	for (int i = 0; i<n; i++) {
		if (my_list[i][0] == 0)
			fout<<0<<endl;
		else {
			for (int j = 0; j < my_list[i].size(); j++) {
				
					fout << my_list[i][j] << " ";
				
			}
			fout << endl;
		}
	}
	fout.close();
}