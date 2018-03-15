#include<iostream>
#include<fstream>
#include<algorithm>
#include<ctime>
using namespace std;

static int prevStars(int * xCounter, int prevStar, int curStar) {

	int counter = 0;
	for (int i = prevStar + 1; i <= curStar; ++i) {
		counter += xCounter[i];
	}
	return counter;
}


int main() {

	FILE *pFile = fopen("input.txt", "r");
	FILE *output = fopen("output.txt", "w");
	//long startTime = clock();
	
	int n = 0; 
	fscanf(pFile, "%d", &n);

	int * yCoordinate = new int[n];
	int * xCoordinate = new int[n];
	int * level = new int[n];
	int * levelOutput = new int[n];

	int maxX = 0;
	
	int x = 0, y = 0;

	for (int i = 0; i < n; ++i) {
		fscanf(pFile, "%d", &x);
		fscanf(pFile, "%d", &y);
		xCoordinate[i] = x;
		yCoordinate[i] = y;
		maxX = max(maxX, xCoordinate[i] + 1);
		level[i] = 0;
		levelOutput[i] = 0;
	}

	int * xCounter = new int[maxX];

	for (int i = 0; i < maxX; ++i) {
		xCounter[i] = 0;
	}

	xCounter[xCoordinate[0]]++;
	int prevYPosition = 0;
	levelOutput[0] ++;

	for (int i = 1; i < n; ++i) {
		if (yCoordinate[i - 1] == yCoordinate[i]) {
			level[i] += level[i - 1];
			level[i] += prevStars(xCounter, xCoordinate[i - 1], xCoordinate[i]);
			level[i]++;
		}
		else {
			level[i] += level[prevYPosition] + 1;
			if (xCoordinate[i] > xCoordinate[prevYPosition]) {
				level[i] += prevStars(xCounter, xCoordinate[prevYPosition], xCoordinate[i]);

			}
			else if (xCoordinate[i] < xCoordinate[prevYPosition]) {

				level[i] -= prevStars(xCounter, xCoordinate[i], xCoordinate[prevYPosition]);
			}
			prevYPosition = i;
		}
		xCounter[xCoordinate[i]]++;
		levelOutput[level[i]]++;
	}
	
	for (int i = 0; i < n; ++i) {
		fprintf(output, "%d", levelOutput[i] );
		fprintf(output, "%s", "\n");
		//+ "\n"
	}
	//long endTime = clock();
	//fprintf(output, "%d", (endTime - startTime));
	return 0;
}