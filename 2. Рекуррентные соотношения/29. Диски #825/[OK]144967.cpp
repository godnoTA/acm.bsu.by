#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;

double countNeibSpace(double h1, double h2)
{
	double distance;
	if (h1 == h2)
		distance = h1 * 2;
	else
	{
		double gipot = h1 + h2;
		double katet = h1 - h2;
		distance = sqrt(gipot*gipot - katet*katet);
	}
	
	return distance;
}

double countSpace(double *mas, int len)
{
	double maxR = mas[0];
	double *koordinats = new double[len];
	for (int i = 0; i < len; i++)
	{
		koordinats[i] = 0;
	}
	koordinats[0] = mas[0];
	double dist;
	for (int i = 1; i < len; i++)
	{
		dist = countNeibSpace(mas[i - 1], mas[i]);
		koordinats[i] = koordinats[i - 1] + dist ;
		for (int x = 2; x <= i; x++)
		{
			if (koordinats[i] - mas[i] < koordinats[i - x] + mas[i - x] &&
				koordinats[i] + mas[i] > koordinats[i - x] + mas[i - x] &&
				mas[i - x] >= mas[i] &&
				koordinats[i] < koordinats[i - x] + countNeibSpace(mas[i - x], mas[i]))
			
				koordinats[i] = koordinats[i - x] + countNeibSpace(mas[i - x], mas[i]);
		}
	}
	double count = koordinats[len-1]+mas[len-1];
	return count;
}

void main()
{
	ifstream in("in.txt");
	ofstream out("out.txt");
	
	int number;
	in >> number;

	double *arr = new double[number];
	for (int i = 0; i < number; i++)
		in >> arr[i];
	
	double result = countSpace(arr, number);
	out << fixed << setprecision(5) << result;

	out.close();
	in.close();
}