#include <iostream>
#include <fstream>
using namespace std;

ifstream inStream("input.txt", ios::in);
ofstream outStream("output.txt", ios::out);


int main()
{
	int N, a, b;
	inStream >> N;
	int* tree = new int[N];
	for (int i = 0;i<N;tree[i] = 0,i++) 
	{}
	for (int i = 0;i<N ;i++) 
	{
		inStream >> a >> b;
		tree[b - 1] = a;
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
