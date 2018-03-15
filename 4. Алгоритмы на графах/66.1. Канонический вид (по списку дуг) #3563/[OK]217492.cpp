
#include <iostream>
#include  <alg.h>
#include <fstream>
#include <string>
#include <list>
#include <iterator> 
using namespace std;

const int inf = 100001;
int arr[inf];

int main()
{
	int n, u, v, size, i = 1;
	ifstream fin("input.txt", ios_base::in);
	fin >> n;
	while (!fin.eof() && i <= n) {
		fin >> u;
		fin >> v;
		arr[v] = u;
		i++;
	}

	ofstream fout("output.txt", ios_base::out);
	for (int i = 1;i <= n;i++)
	{
		if(arr[i]!=0)
			fout << arr[i] << " ";
		else 
			fout << 0<< " ";
	}
	fout.close();
}

