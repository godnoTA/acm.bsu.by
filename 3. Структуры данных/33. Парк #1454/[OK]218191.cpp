#include <fstream>
#include <iostream>
#include <algorithm>
#include <vector>
#include <set>
#include<ctime>

using namespace std;

static int area(int left, int top, int right, int bottom) {
	return (right - left) * (top - bottom);
}

int main() {
	long startTime = clock();

	FILE *pFile = fopen("input.txt", "r");
	FILE *output = fopen("output.txt", "w");

	int n = 0, c = 0, r = 0;
	fscanf(pFile, "%d", &n);
	fscanf(pFile, "%d", &c);
	fscanf(pFile, "%d", &r);

	int maxArea = 0;

	vector<int> xPointCoordinate;
	vector<int> yPointCoordinate;

	set<int> yCoordinate;

		int x, y;
		for (int i = 0; i < n; ++i)
		{
			fscanf(pFile, "%d", &x);
			fscanf(pFile, "%d", &y);
			xPointCoordinate.push_back(x);
			yPointCoordinate.push_back(y);

			if (yCoordinate.find(y) == yCoordinate.end())
			{
				yCoordinate.insert(y);
				xPointCoordinate.push_back(0);
				yPointCoordinate.push_back(y);
				xPointCoordinate.push_back(c);
				yPointCoordinate.push_back(y);
			}
		}

		int size = xPointCoordinate.size();
		int * topCoordinate = new int[size];
		int * bottomCoordinate = new int[size];
		bool * theSameYCoordinatePoint = new bool[size];

		for (int i = 0; i < size; ++i)
		{
			topCoordinate[i] = r;
			bottomCoordinate[i] = 0;
			theSameYCoordinatePoint[i] = false;
		}


		int temp;
		//pointCoordinate.Sort((p1, p2) = > p1.X.CompareTo(p2.X));
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - i - 1; j++) {
				if (xPointCoordinate[j] > xPointCoordinate[j + 1]) {
					// меняем элементы местами
					temp = xPointCoordinate[j];
					xPointCoordinate[j] = xPointCoordinate[j + 1];
					xPointCoordinate[j + 1] = temp;

					temp = yPointCoordinate[j];
					yPointCoordinate[j] = yPointCoordinate[j + 1];
					yPointCoordinate[j + 1] = temp;
				}
			}
		}

		if (n == 0) {
			maxArea = r * c;
		}
	
		
		for (int i = (size - n) / 2; i < size; ++i)
		{
			maxArea = max(maxArea, area(xPointCoordinate[i - 1], topCoordinate[i], xPointCoordinate[i], bottomCoordinate[i]));
			for (int j = 0; j < i; ++j)
			{
				if (yPointCoordinate[i] > yPointCoordinate[j] && yPointCoordinate[i] <= topCoordinate[j])
				{
					if (theSameYCoordinatePoint[j])
					{
						maxArea = max(maxArea, area(xPointCoordinate[j], topCoordinate[j], xPointCoordinate[i], yPointCoordinate[j]));
					}
					else
					{
						maxArea = max(maxArea, area(xPointCoordinate[j], topCoordinate[j], xPointCoordinate[i], bottomCoordinate[j]));
					}
					topCoordinate[j] = yPointCoordinate[i];
				}
				if (yPointCoordinate[i] < yPointCoordinate[j] && yPointCoordinate[i] >= bottomCoordinate[j])
				{
					if (theSameYCoordinatePoint[j])
					{
						maxArea = max(maxArea, area(xPointCoordinate[j], yPointCoordinate[j], xPointCoordinate[i], bottomCoordinate[j]));
					}
					else
					{
						///
						maxArea = max(maxArea, area(xPointCoordinate[j], topCoordinate[j], xPointCoordinate[i], bottomCoordinate[j]));
					}
					bottomCoordinate[j] = yPointCoordinate[i];
				}
				else if (yPointCoordinate[i] == yPointCoordinate[j])
				{
					if (theSameYCoordinatePoint[j])
					{
						maxArea = max(maxArea, area(xPointCoordinate[j], yPointCoordinate[i], xPointCoordinate[i], bottomCoordinate[j]));
						maxArea = max(maxArea, area(xPointCoordinate[j], topCoordinate[j], xPointCoordinate[i], yPointCoordinate[i]));

					}
					else
					{
						maxArea = max(maxArea, area(xPointCoordinate[j], topCoordinate[j], xPointCoordinate[i], bottomCoordinate[j]));
					}
					theSameYCoordinatePoint[j] = true;
				}
			}
		}

	fprintf(output, "%d", maxArea);

	long endTime = clock();
	cout << endTime - startTime << endl;
	return 0;
}