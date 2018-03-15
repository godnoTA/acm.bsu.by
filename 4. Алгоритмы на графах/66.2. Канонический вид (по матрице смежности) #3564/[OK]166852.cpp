#include <iostream>
#include <fstream>
using namespace std;

ifstream inStream("input.txt", ios::in);
ofstream outStream("output.txt", ios::out);


int main()
{
	int N, a;
	inStream >> N;
	int* tree = new int[N];
	for (int i = 0;i<N;tree[i] = 0,i++) 
	{}
	for (int i = 0;i < N;i++)
	{
		for (int j = 0;j < N;j++) 
		{
			inStream >> a;
			if (a == 1)
			{
				tree[j] = i + 1;
			}
		}
	}

	inStream.close();
	for (int i = 0;i<N;i++) 
	{
		outStream << tree[i] << " ";
	}
	outStream << "\n";
	outStream.close();
	return 0;
}
