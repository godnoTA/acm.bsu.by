#include <fstream>
#include <cstring>
using namespace std;

int mark = 1;

void dfs(int vertex, int * arr, int** matrix, int size)
{
	arr[vertex] = mark++;
	for (int j = 0; j < size; j++)
	{
		if (matrix[vertex][j] == 1 && arr[j] == 0)
		{
			dfs(j, arr, matrix, size);
		}
	}
}

int main()
{
	ifstream fin("input.txt");
	int size = 0;
	fin >> size;
	
	int * arr = new int[size]();
	int ** matrix = new int*[size];
	for (int i = 0; i < size; i++)
	{
		matrix[i] = new int[size];
	}

	for(int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			fin >> matrix[i][j];
		}
	}
	fin.close();

	for (int i = 0; i < size; i++)
	{
		if (arr[i] == 0)
		{
			dfs(i,arr,matrix,size);
		}
	}

	ofstream fout("output.txt");
	for (int i = 0; i < size; i++)
	{
		fout << arr[i] << " ";
	}
	fout.close();
	delete[] arr;
	for (int i = 0; i < size; i++)
	{
		delete[] matrix[i];
	}
	delete[] matrix;
	return 0;
}