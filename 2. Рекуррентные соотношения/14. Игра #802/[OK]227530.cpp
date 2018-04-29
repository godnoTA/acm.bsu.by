#include <iostream>
#include <fstream>
using namespace std;

int mas[13][13][13][13][13][13][13];
ifstream in("input.txt");
ofstream out("output.txt");

int main()
{
	for (int i1 = 10; i1 < 13; i1++)
	{
		for (int i2 = 10; i2 < 13; i2++)
		{
			for (int i3 = 10; i3 < 13; i3++)
			{
				for (int i4 = 10; i4 < 13; i4++)
				{
					for (int i5 = 10; i5 < 13; i5++)
					{
						for (int i6 = 10; i6 < 13; i6++)
						{
							for (int i7 = 10; i7 < 13; i7++)
							{
								mas[i1][i2][i3][i4][i5][i6][i7] = 0;
							}
						}
					}
				}
			}
		}
	}
	mas[9][9][9][9][9][9][9] = 0;
	mas[9][9][9][9][9][9][12] = 1;
	for (int i1 = 9; i1 >= 0; i1--)
	{
		for (int i2 = 9; i2 >= 0; i2--)
		{
			for (int i3 = 9; i3 >= 0; i3--)
			{
				for (int i4 = 9; i4 >= 0; i4--)
				{
					for (int i5 = 9; i5 >= 0; i5--)
					{
						for (int i6 = 9; i6 >= 0; i6--)
						{
							for (int i7 = 9; i7 >= 0; i7--)
							{
								int t = 0;
								for (int i = 1; i < 4; i++)
								{
									t += mas[i1 + i][i2][i3][i4][i5][i6][i7];
									t += mas[i1][i2 + i][i3][i4][i5][i6][i7];
									t += mas[i1][i2][i3 + i][i4][i5][i6][i7];
									t += mas[i1][i2][i3][i4 + i][i5][i6][i7];
									t += mas[i1][i2][i3][i4][i5 + i][i6][i7];
									t += mas[i1][i2][i3][i4][i5][i6 + i][i7];
									t += mas[i1][i2][i3][i4][i5][i6][i7 + i];
								}
								if (t == 0)
								{
									mas[9][9][9][9][9][9][9] = 0;
									mas[i1][i2][i3][i4][i5][i6][i7] = 1;
								}
								else
								{
									mas[i1][i2][i3][i4][i5][i6][i7] = 0;
								}
							}
						}
					}
				}
			}
		}
	}
	int n;
	in >> n;
	int a1, a2, a3, a4, a5, a6, a7;
	a1 = n % 10;
	a2 = n % 100 / 10;
	a3 = n % 1000 / 100;
	a4 = n % 10000 / 1000;
	a5 = n % 100000 / 10000;
	a6 = n % 1000000 / 100000;
	a7 = n % 10000000 / 1000000;
	if (mas[a7][a6][a5][a4][a3][a2][a1] == 0)
	{
		out << "The first wins"<<endl;
		for (int i = 1; i < 4; i++)
		{
			if (mas[a7][a6][a5][a4][a3][a2][a1 + i] == 1)
				out << 1000000*a7 + 100000*a6+10000*a5+1000*a4+100*a3+10*a2+(a1+i) << " ";
		}
		for (int i = 1; i < 4; i++)
		{
			if (mas[a7][a6][a5][a4][a3][a2 + i][a1 ] == 1)
				out << 1000000 * a7 + 100000 * a6 + 10000 * a5 + 1000 * a4 + 100 * a3 + 10 * (a2+i) + a1 << " ";
		}
		for (int i = 1; i < 4; i++)
		{
			if (mas[a7][a6][a5][a4][a3 + i][a2][a1] == 1)
				out << 1000000 * a7 + 100000 * a6 + 10000 * a5 + 1000 * a4 + 100 * (a3+i) + 10 * a2 + a1 << " ";
		}
		for (int i = 1; i < 4; i++)
		{
			if (mas[a7][a6][a5][a4 + i][a3][a2][a1] == 1)
				out << 1000000 * a7 + 100000 * a6 + 10000 * a5 + 1000 * (a4+i) + 100 * a3 + 10 * a2 + a1 << " ";
		}
		for (int i = 1; i < 4; i++)
		{
			if (mas[a7][a6][a5 + i][a4][a3][a2][a1] == 1)
				out << 1000000 * a7 + 100000 * a6 + 10000 * (a5+i) + 1000 * a4 + 100 * a3 + 10 * a2 + a1 << " ";
		}
		for (int i = 1; i < 4; i++)
		{
			if (mas[a7][a6 + i][a5][a4][a3][a2][a1] == 1)
				out << 1000000 * a7 + 100000 * (a6+i) + 10000 * a5 + 1000 * a4 + 100 * a3 + 10 * a2 + a1 << " ";
		}
		for (int i = 1; i < 4; i++)
		{
			if (mas[a7 + i][a6][a5][a4][a3][a2][a1] == 1)
				out << 1000000 * (a7+i) + 100000 * a6 + 10000 * a5 + 1000 * a4 + 100 * a3 + 10 * a2 + a1 << " ";
		}
		return 0;
	}
	out << "The second wins";
	return 0;
}