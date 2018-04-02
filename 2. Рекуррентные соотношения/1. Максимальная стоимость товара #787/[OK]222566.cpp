#include <iostream>
#include <fstream>
#include <cstdlib>

using namespace std;

int main(){
	int n = 0;
	int m = 0;
	ifstream fin("input.txt");
	fin >> n >> m;
	ofstream fout("output.txt");
	int index = 0;
	int size = n + m;
	int *array = new int[size];
	int element = 0;
	int sum = 0;

	for (int i = 0; i < n; i++){
		fin >> element;
		array[i] = element;
		sum += element;
		index = i + 1;
	}

	for (int j = index; j < size; j++){
		fin >> element;
		array[j] = element;
	}
		int tmp;
		for (int i = 0; i < size - 1; ++i)
		{
			for (int j = 0; j < size - 1; ++j)
			{
				if (array[j + 1] < array[j])
				{
					tmp = array[j + 1];
					array[j + 1] = array[j];
					array[j] = tmp;
				}
			}
		}
	index = 0;
	int s = 0;
	while ((index < size) && (array[index] <= s + 1))
	{
		s = s + array[index];
		index++;
	}
	if (s >= sum)
		fout << "YES";
	else
	{
		fout << "NO" << endl;
		fout << sum - (s + 1);
	}
	delete[]array;
	fin.close();
	fout.close();
	return 0;
}