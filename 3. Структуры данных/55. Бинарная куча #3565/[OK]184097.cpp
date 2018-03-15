#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

ifstream fin("input.txt");	
ofstream fout("output.txt");

int main()
{
	int n= 0;
	fin >> n;
	vector<int> a(n+1);
	int p, t = 1;
	for(int i=0;i<n;i++){
             fin>>p;
             a[t++]=p;
        }
	for (int i = 1; i < n + 1; i++)
	{
		if (2 * i + 1 <= n)
		{
			if (a[i] > a[2 * i + 1] || a[i] > a[2 * i])
			{
				fout << "No";
				return 0;
			}
		}
		else if (2 * i <= n)
			if (a[i] > a[2 * i])
			{
				fout << "No";
				return 0;
			}
	}
	fout << "Yes";
        return 0;
}