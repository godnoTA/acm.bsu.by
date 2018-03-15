#include <iostream>
#include <fstream>
#include <string>

using namespace std;



int main()
{
	ifstream in;
	ofstream out;
	in.open("input.txt");
	out.open("output.txt");
	int n, m, x, y;
	int matrix[500][500];
    string g, ml , p;
	in >> n >> m;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			matrix[i][j] = 0;
	for (int i = 1; i <= m; i++)
	{
		in >> x >> y;
		matrix[x][y]++;
		matrix[y][x]++;
	}
	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= n; j++)
		{
			out << matrix[i][j] << " ";
		}
		out << endl;
	}
    return 0;
}

