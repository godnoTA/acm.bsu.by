#include <fstream>
#include <cstring>
using namespace std;

int main()
{
	ifstream fin("input.txt");
	int size = 0;
	int parent = 0;
	int child = 0;
	fin >> size;

	int * arr = new int[size];
	memset(arr, 0, size * sizeof(int));
	for(int i = 1; i < size; i++)
	{
		fin >> parent;
		fin >> child;
		arr[child - 1] = parent;
	}
	fin.close();
	ofstream fout("output.txt");
	for (int i = 0; i < size; i++)
	{
		fout << arr[i] << " ";
	}
	fout.close();
	delete[] arr;
	return 0;
}