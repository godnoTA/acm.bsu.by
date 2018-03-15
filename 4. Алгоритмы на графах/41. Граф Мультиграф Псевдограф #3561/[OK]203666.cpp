#include <iostream>
#include <fstream>

using namespace std;


int main()
{
	ifstream in;
	ofstream out;
	in.open("input.txt");
	out.open("output.txt");

	int  m, x, y;
	int n;
	int matrix[501][501];
	bool g = true, ml = true, p = true;
	in >> n >> m;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			matrix[i][j] = 0;
	for (int i = 0; i < m; i++)
	{
		in >> x >> y;
		if (x == y)
		{
			g = false;
			ml = false;
			out << "No\nNo\nYes" << endl;
			return 0;
		}
		matrix[x][y]++;
		matrix[y][x]++;
	}
	for (int i = 1; i <= n; i++)
		for (int j = i; j <= n; j++)
			if (matrix[i][j] > 1)
			{
				out << "No\nYes\nYes" << endl;
				return 0;
			}

	out << "Yes\nYes\nYes" << endl;
	return 0;
}

