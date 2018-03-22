#include <iostream>
#include <fstream>
#include <queue>
using namespace std;



int haszero(int* arr, int num)
{
	for (int i = 0; i < num; ++i)
		if (arr[i] == 0) return i + 1;
	return 0;
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
	///////////////////////////////////////////////////


	/*посещение вершин*/
	bool* used = new bool[number_of_vertexes];
	for (int i = 0; i < number_of_vertexes; ++i)
		used[i] = 0;
	////////////////////////////////////////////////

	/*метки вершин*/
	int* path = new int[number_of_vertexes];
	for (int i = 0; i < number_of_vertexes; ++i)
		path[i] = 0;


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


	for (int i = 0; i < number_of_vertexes; ++i)
		for (int j = 0; j < number_of_vertexes; ++j)
		{
			cout << matrix[i][j];
			if (j == number_of_vertexes - 1) cout << endl;
			else cout << " ";
		}



	queue<int> q;
	used[0] = 1;
	int cur = 1;
	int cnt = 0;
	
	do
	{
		q.push(cur);
		path[cur - 1] = ++cnt;
		while (!q.empty())
		{
			cur = q.front();
			q.pop();
			used[cur - 1] = 1;
			for (int i = 0; i < number_of_vertexes; ++i)
			{
				if (matrix[cur - 1][i] != 0)
				{
					if (used[i] == 0)
					{
						q.push(i + 1);
						used[i] = 1;
						path[i] = cnt + 1;
						cnt++;
					}
				}
			}
		}
	} while (cur = haszero(path, number_of_vertexes));
	





	ofstream fo("output.txt");
	for (int i = 0; i < number_of_vertexes; ++i)
		fo << path[i] << " ";


	

	system("pause");
	return 0;
}