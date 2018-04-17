#include <fstream>
#include <cstring>
using namespace std;

int main()
{
	ifstream fin("input.txt");
	int size = 0;
	int element = 0;
	fin >> size;

	int * arr = new int[size];
	memset(arr, 0, size * sizeof(int));
	for(int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			fin >> element;
			if (element == 1)
			{
				arr[j] = i + 1;
			}
			
		}
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