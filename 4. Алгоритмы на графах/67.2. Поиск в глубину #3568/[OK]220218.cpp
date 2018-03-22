#include <iostream>
#include <fstream>
#include <queue>
#include <vector>
using namespace std;


int dfs(int x, bool* used, int* path, bool **matrix, int n, int c)
{
	used[x] = 1;
	path[x] = ++c;
	for (int i = 0; i < n; ++i)
	{
		if (matrix[x][i] == 1 && used[i] == 0) 
			c = dfs(i, used, path, matrix, n, c);
	}
	return c;
}


int main() {

	ifstream fi("input.txt");

	int number_of_vertexes = 0;
	fi >> number_of_vertexes;


	/*матрица смежности*/
	bool** matrix = new bool*[number_of_vertexes];
	for (int i = 0; i < number_of_vertexes; ++i)
		matrix[i] = new bool[number_of_vertexes];

	for (int i = 0; i < number_of_vertexes; ++i)
		for (int j = 0; j < number_of_vertexes; ++j)
			matrix[i][j] = 0;
	////////////////////////////////////////////////


	/*заполнение матрицы смежности*/
	bool x = 0;
	int c = 1;
	while (fi >> x)
	{
		if (x == 1)
		{
			if (c % number_of_vertexes == 0)
			{
				matrix[c / number_of_vertexes - 1][number_of_vertexes - 1] = 1;
			}
			else
			matrix[c / number_of_vertexes][c % number_of_vertexes - 1] = 1;
		}
		c++;
	}



	/*вывод матрицы*/
	for (int i = 0; i < number_of_vertexes; ++i)
		for (int j = 0; j < number_of_vertexes; ++j)
		{
			cout << matrix[i][j];
			if (j == number_of_vertexes - 1) cout << endl;
			else cout << " ";
		}



	/*посещение вершин*/
	bool* used = new bool[number_of_vertexes];
	for (int i = 0; i < number_of_vertexes; ++i)
		used[i] = 0;



	int* path = new int[number_of_vertexes];
	for (int i = 0; i < number_of_vertexes; ++i)
	path[i] = 0;


	int count = 0;
	for (int i = 0; i < number_of_vertexes; ++i)
	{
		if (used[i] == 0) count = dfs(i, used, path, matrix, number_of_vertexes, count);
	}


	cout << endl << endl;
	for (int i = 0; i < number_of_vertexes; ++i) cout << path[i] << " ";


	ofstream fo("output.txt");
	for (int i = 0; i < number_of_vertexes; ++i) fo << path[i] << " ";



	system("pause");
	return 0;
}