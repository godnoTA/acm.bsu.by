#include <stdio.h>
#include <iostream>
#include <vector>
#include <algorithm>
#include <ctime>
using namespace std;

int main()
{
	FILE *pFile = fopen("in.txt", "r");
	FILE *output = fopen("out.txt", "w");
	int numberOfDominoes = 0;
	fscanf(pFile, "%d", &numberOfDominoes);
	int *dominoDifference = new int[numberOfDominoes];
	int difference = 0;
	int *values = new int[13]{ 0,0,0,0,0,0,0,0,0,0,0,0,0 };
	const int MAX_VALUE = 10000000;

	int size = numberOfDominoes * 12 + 1;
	int shift_ = numberOfDominoes * 6;

	vector<int> weight;
	vector <int> price;
	weight.reserve(numberOfDominoes);
	price.reserve(numberOfDominoes);

	int leftVal;
	int rightVal;
	for (int i = 0; i < numberOfDominoes; ++i) {
		fscanf(pFile, "%d", &leftVal);
		fscanf(pFile, "%d", &rightVal);
		dominoDifference[i] = leftVal - rightVal;

		difference += dominoDifference[i];
		++values[dominoDifference[i] + 6];
	}

	for (int i = 0; i < 13; ++i) {
		for (int j = 1; j <= values[i]; j *= 2) {
			values[i] -= j;
			weight.push_back(j * (i - 6));
			price.push_back(j);
		}
		if (values[i] != 0) {
			weight.push_back(values[i] * (i - 6));
			price.push_back(values[i]);
			values[i] = 0;
		}
	}

	int *prev = new int[size];
	int *cur = new int[size];
	fill(prev, prev + size, MAX_VALUE);
	fill(cur, cur + size, MAX_VALUE);

	prev[difference + shift_] = 0;

	for (int i = 1; i < weight.size() + 1; ++i) {
		for (int j = 0; j < size; ++j) {
			if (prev[j] != MAX_VALUE) {
				cur[j] = min(prev[j], cur[j]);
				cur[j - 2 * weight[i - 1]] = min(price[i - 1] + prev[j],
					cur[j - 2 * weight[i - 1]]);

			}
		}
		for (int k = 0; k < size; ++k) {
			prev[k] = cur[k];
		}


	}

	if (prev[shift_] != MAX_VALUE)
		fprintf(output, "%d", prev[shift_]);
	else {
		for (int i = 1; i < shift_ + 1; ++i) {
			if (prev[shift_ - i] != MAX_VALUE) {
				fprintf(output, "%d", min(prev[shift_ - i], prev[shift_ + i]));
				break;
			}
		}

	}
}