#include <iostream>
#include <fstream>
#include <cmath>

using namespace std;

int main()
{
	int matr[10][10][10] = { 0 };
	int people_count, sets_count, f, b, c, s, cost, x, y, z;
	ifstream input("input.txt");
	input >> people_count >> sets_count;
	for (int l = 0; l < sets_count; ++l)
	{
		input >> f >> b >> c >> s;
		if (f > 9) f = 9;
		if (b > 9) b = 9;
		if (c > 9) c = 9;
		if (!matr[f][b][c]) matr[f][b][c] = s;
		else
			matr[f][b][c] = matr[f][b][c] < s ? matr[f][b][c] : s;
		for (int i = 0; i<10; i++)
			for (int j = 0; j<10; j++)
				for (int k = 0; k<10; k++)
					if (matr[i][j][k])
					{
						x = i + f < 10 ? i + f : 9;
						y = j + b < 10 ? j + b : 9;
						z = k + c < 10 ? k + c : 9;
						if ((matr[x][y][z] > matr[i][j][k] + s) || (matr[x][y][z] == 0)) matr[x][y][z] = matr[i][j][k] + s;
					}
	}
	input >> f >> b >> c;
	cost = 2147483647;
	for (int i = f; i<10; i++)
		for (int j = b; j<10; j++)
			for (int k = c; k<10; k++)
				if (matr[i][j][k] != 0)
					if (matr[i][j][k] < cost) cost = matr[i][j][k];
	if (cost == 2147483647) cost = 0;
	cost = ceil(double(cost) / people_count);
	ofstream output("output.txt");
	output << cost << endl;
	return 0;
}