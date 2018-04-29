#pragma comment(linker, "/STACK:10000000000")
#include <iostream> 
#include <fstream> 
#include <string> 
#include <set> 
#include <map> 
using namespace std;

ofstream out("out.txt");
int a = 0, b = 0, n;

int funk(int col, int i, int j, int **mas, int **pas)
{
	if (i< 0 || i >= a || j<0 || j >= b)
		return 0;
	if (mas[i][j] == col)
	{
		mas[i][j] = -1;
		int t = 0;
		if (i - 1 >= 0 && j + 1 < b)
		{
			if ((pas[i][j] > pas[i - 1][j] && pas[i][j] > pas[i][j + 1]) || (pas[i - 1][j + 1] > pas[i - 1][j] && pas[i - 1][j + 1] > pas[i][j + 1]))
			{
				t += funk(col, i - 1, j + 1, mas, pas);
			}
		}
		if (i + 1 < a && j + 1 < b)
		{
			if ((pas[i][j] > pas[i + 1][j] && pas[i][j] > pas[i][j + 1]) || (pas[i + 1][j + 1] > pas[i + 1][j] && pas[i + 1][j + 1] > pas[i][j + 1]))
			{
				t += funk(col, i + 1, j + 1, mas, pas);
			}
		}
		if (i - 1 >= 0 && j - 1 >= 0)
		{
			if ((pas[i][j] > pas[i - 1][j] && pas[i][j] > pas[i][j - 1]) || (pas[i - 1][j - 1] > pas[i - 1][j] && pas[i - 1][j - 1] > pas[i][j - 1]))
			{
				t += funk(col, i - 1, j - 1, mas, pas);
			}
		}
		if (i + 1 < a && j - 1 >= 0)
		{
			if ((pas[i][j] > pas[i + 1][j] && pas[i][j] > pas[i][j - 1]) || (pas[i + 1][j - 1] > pas[i + 1][j] && pas[i + 1][j - 1] > pas[i][j - 1]))
			{
				t += funk(col, i + 1, j - 1, mas, pas);
			}
		}
		if (j + 1<b)
		{
			t += funk(col, i, j + 1, mas, pas);
		}
		if (i + 1<a)
		{
			t += funk(col, i + 1, j, mas, pas);
		}
		if (i - 1 >= 0)
		{
			t += funk(col, i - 1, j, mas, pas);
		}
		if (j - 1 >= 0)
		{
			t += funk(col, i, j - 1, mas, pas);
		}
		return 1 + t;
	}
	else return 0;
}

int compare(const void * x1, const void * x2)
{
	return (*(int*)x1 - *(int*)x2);
}


int main()
{
	int count = 0;
	ifstream in("in.txt");
	in >> b >> a >> n;
	int **mas = new int *[a];
	int **pas = new int *[a];
	for (int i = 0; i < a; i++)
	{
		pas[i] = new int[b];
		mas[i] = new int[b];
		for (int j = 0; j < b; j++)
		{
			pas[i][j] = 1;
			mas[i][j] = 1;
		}
	}
	int p = a*b + 1;
	int **en = new int *[p];
	for (int i = 0; i < p; i++)
	{
		en[i] = new int[2];
		for (int j = 0; j < 2; j++)
		{
			en[i][j] = 0;
		}
	}
	int x1, y1, x2, y2, color;
	int a2 = a / 2, b2 = b / 2;
	int seek = 1;
	for (int t = 0; t < n; t++)
	{
		in >> x1 >> y1 >> x2 >> y2 >> color;
		int minx, miny, maxx, maxy;
		if (x1 > x2)
		{
			maxx = x1;
			minx = x2;
		}
		if (x1 < x2)
		{
			maxx = x2;
			minx = x1;
		}
		if (y1 > y2)
		{
			maxy = y1;
			miny = y2;
		}
		if (y1 < y2)
		{
			maxy = y2;
			miny = y1;
		}
		seek++;
		for (int i = minx + a2; i < maxx + a2; i++)
		{
			for (int j = miny + b2; j < maxy + b2; j++)
			{
				pas[i][j] = seek;
				mas[i][j] = color;
			}
		}
	}

	for (int i = 0; i < a; i++)
	{
		for (int j = 0; j < b; j++)
		{
			if (mas[i][j] == -1)
				continue;
			en[count][0] = mas[i][j];
			en[count][1] = funk(mas[i][j], i, j, mas, pas);
			count++;
		}
	}
	for (int i = 0; i < count; i++)
	{
		for (int j = 0; j < count; j++)
		{
			if (en[i][0]<en[j][0])
			{
				int temp1 = en[i][0];
				int temp2 = en[i][1];
				en[i][0] = en[j][0];
				en[i][1] = en[j][1];
				en[j][0] = temp1;
				en[j][1] = temp2;
			}
			if (en[i][0] == en[j][0] && en[i][1] < en[j][1])
			{
				int temp1 = en[i][0];
				int temp2 = en[i][1];
				en[i][0] = en[j][0];
				en[i][1] = en[j][1];

				en[j][0] = temp1;
				en[j][1] = temp2;
			}
		}
	}
	for (int i = 0; i < count; i++)
	{
		out << en[i][0] << " " << en[i][1] << endl;
	}
	return 0;
}