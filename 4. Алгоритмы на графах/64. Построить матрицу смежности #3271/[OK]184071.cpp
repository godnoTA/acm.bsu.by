#include <iostream>
#include <fstream>
#include <vector>
using namespace std;
ifstream fin("input.txt");
ofstream fout("output.txt");
int main()
{
	int n = 0, m = 0;
	fin >> n;
	fin >> m;
	vector<vector<int>> a(n);
	for (int i = 0; i < n; i++)
	for (int j = 0; j < n; j++)
	    {
                a[i].push_back(0);
            }
	for (int i = 0; i < m; i++)
	{
		int p,q;
		fin >> p>>q;
		a[p - 1][q - 1] = a[q - 1][p - 1] = 1;
	}
	for(int i=0;i<n;i++)
        {
                for(int j=0;j<n;j++){
                    fout<<a[i][j]<<" ";
                }
            fout<<endl;
        }

}