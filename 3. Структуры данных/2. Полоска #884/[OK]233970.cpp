#include<iostream>
#include<fstream>
using namespace std;
ifstream fin("in.txt");
ofstream fout("out.txt");

void Solution(int beg, int end, int i, int dim, int arr[])
{
	if (beg == end)
	{
		arr[i] = beg;
		return;
	}
	int el;
	el = beg < end ? 1 : -1;
	Solution(beg, (beg + end - el) / 2, i, 2 * dim, arr);
	Solution(end, (beg + end + el) / 2, dim - i - 1, 2 * dim, arr);
}

int main()
{
	int k, dim = 1;	
	fin >> k;
	fin.close();
	cout << k << endl << endl;
	for (int i = 0; i < k; i++)
	{
		dim *= 2;
	}
	int* arr = new int[dim];
	Solution(1, dim, 0, 2, arr);
	for (int i = 0; i < dim-1; i++)
		fout << arr[i] << " ";
	fout << arr[dim - 1];
	fout << endl;
	fout.close();
	return 0;
}