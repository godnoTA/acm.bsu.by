#include <fstream>
#include <iostream>

using namespace std;



int main()
{
	int n, **arr, *arr2, a;
	ifstream f("input.txt");
	f >> n ;
	arr2 = new int[n];
	for (int j = 0; j < n; j++)
	{
		arr2[j]=0;
	}
	arr = new int *[n];
	for (int i = 0; i < n; i++)
	{
		arr[i] = new int[n];
	}
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			f >> a;
			arr[i][j]=a;
			arr2[j] += a;
		}
	}

	f.close();
	ofstream of("output.txt");
	for (int i = 0; i < n;i++)
	{
		if (arr2[i] != 0) {
			for (int j = 0; j < n;j++)
			{
				if (arr[j][i] == 1)
				{
					a = j + 1;
					of << a << " ";
					break;
				}
			}
		}
		else
		{
			of << 0 << " ";
		}
	}
	of.close();
	system("pause");
	return 0;
}