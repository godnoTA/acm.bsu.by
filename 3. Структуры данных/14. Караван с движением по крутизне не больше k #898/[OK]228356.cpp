#include <iostream>
#include <fstream>
#include <math.h>
using namespace std;
int n, m;
int k,N=0;
ifstream fin("in.txt");
ofstream fout("out.txt");

bool granici(int x, int y)
{
	if ((x >= 0) && (x<n) && (y >= 0) && (y<m))
		return true;
	return false;
}


int main()
{
	int x1, y1, x2, y2;
	fin >> n;
	fin >> m;
	int **a = new int*[n];
	for (int c= 0; c <n; c++)
		a[c] = new int[m];
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			fin >> a[i][j];
	fin >> k >> x1 >> y1 >> x2 >> y2;
	fin.close();
	/*
	for (int i = 0; i < n; i++) 
	{
		for (int j = 0; j < m; j++)
			cout << a[i][j]<<" ";
		cout << endl;
	}*/
	
	cout << k << " " << x1 << " " << y1 << " " << x2<<" " << y2;
	x1--;
	y1--;
	x2--;
	y2--;
	
	int **b = new int*[n];
	for (int c = 0; c <n; c++)
		b[c] = new int[m];
	for (int i = 0; i < n; i++)
	{ 
		for (int j = 0; j < m; j++)
		{
			b[i][j]=-1;
		}
	}
	b[x1][y1] = 0;


	bool tf = true;
	for(;;)
	{
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{

				if (b[i][j] == N)
				{
					int x, y;
					x = i;
					y = j;
					int mas[8];
					mas[0] = x - 1;
					mas[1] = y;
					mas[2] = x + 1;
					mas[3] = y;
					mas[4] = x;
					mas[5] = y - 1;
					mas[6] = x;
					mas[7] = y + 1;
					int ch = 0;
					do
					{
						if ((granici(mas[ch], mas[ch + 1]) == true))
						{

							if ((b[mas[ch]][mas[ch + 1]] == -1) && (abs(a[mas[ch]][mas[ch + 1]] - a[x][y]) <= k))
							{
								b[mas[ch]][mas[ch + 1]] = N+1;
							}

						}
						ch = ch + 2;
					} while (ch < 8);
					tf = false;
				}
					
			}

		}
		if (tf == true)
			goto loop;
		else
			N++;
		tf = true;
		
	} 
loop:

	if (b[x2][y2] == -1)
		fout << "No";
	else
		fout << "Yes" << endl<< b[x2][y2];
	fout.close();
	/*cout << endl;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
			cout << b[i][j] << " ";
		cout << endl;
	}
	system("pause");*/
	return 0;
}
