
#include <iostream>
#include  <alg.h>
#include <fstream>
#include <string>

using namespace std;

const int inf =101;
int a[inf][inf];

int main()
{
	int n, m,u,v,i = 1;
	ifstream fin("input.txt", ios_base::in);
	fin >> n;
	fin >> m;
	while (!fin.eof() && i <= m) {
		fin >> u; fin >> v;
		a[u][v] = 1;
		a[v][u] = 1;
		i++;
	}

	ofstream fout("output.txt", ios_base::out);
	for (int i = 1;i <= n;i++)
	{
		for (int j = 1;j <= n;j++) {
			fout << a[i][j] << " ";
		}
		fout << endl;
	}
	fout.close();
}

