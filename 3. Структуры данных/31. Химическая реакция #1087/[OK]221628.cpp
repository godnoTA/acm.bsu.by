#define _CRT_SECURE_NO_WARNINGS
#include <stack>
#include <fstream>
using namespace std;

int main()
{
	ifstream fin("in.txt");
	stack<int> vial;
	int N = 0;
	int M = 0;
	fin >> N >> M;
	int ** matrix = new int* [N];
	for (int i = 0; i < N; i++)
	{
		matrix[i] = new int[N];
	}
	
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			fin >> matrix[i][j];
		}
	}

	int element = 0;
	for (int i = 0; i < M; i++)
	{
		fin >> element;
		while (true)
		{
			if (vial.empty())
			{
				vial.push(element);
				break;
			}
			
			int row = element - 1;
			int col = vial.top() - 1;
			if (matrix[row][col] != 0)
			{
				vial.pop();
				element = matrix[row][col];
			}
			else
			{
				vial.push(element);
				break;
			}
		}
	}
	fin.close();

	ofstream fout("out.txt");
	
	while(vial.size() != 1)
	{
		fout << vial.top() << " ";
		vial.pop();
	}
	fout << vial.top();
	fout.close();

	for (int i = 0; i < N; i++)
	{
		delete [] matrix[i];
	}
	delete[] matrix;
	return 0;
}