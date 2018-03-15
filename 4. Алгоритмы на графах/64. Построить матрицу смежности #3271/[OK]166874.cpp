#include <iostream>
#include <fstream>
#include <vector>
#include <list>

using namespace std;

ifstream inStream("input.txt", ios::in);
ofstream outStream("output.txt", ios::out);

int main()
{
	int N, M, a, b;
	inStream >> N >> M;

	int **tree = new int*[N];
	for (int i = 0; i < N; i++)
	{
		tree[i] = new int[N];
	}
	for (int i = 0;i<N;i++)
	{
		for (int j = 0; j<N; j++)
		{
			tree[i][j]=0;
		}
	}
	for (int i = 0;i<M;i++) 
	{
		inStream >> a >> b;
		tree[a - 1][b-1]++;
		tree[b - 1][a - 1]++;
	}
	inStream.close();
	for (int i = 0;i<N;i++) 
	{
		for (int j = 0; j<N; j++) 
		{
			outStream << tree[i][j] << " ";
		}
		outStream << "\n";
	}
	outStream << "\n";
	outStream.close();
	return 0;
}


