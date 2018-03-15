#include <iostream>
#include <fstream>
#include <ctime>
#include <vector>
using namespace std;


int function(int** arr, vector<pair<int, int>> vect, int i, int j)
{
	if (i == j + 1)
	{
		arr[i][j] = vect.at(j).first * vect.at(j).second * vect.at(j + 1).second;
		return vect.at(j).first * vect.at(j).second * vect.at(j + 1).second;
	}

	else if (i >= j) {
		arr[i][j] = 0;
		return 0;
	}

	else {
		int min_value = 0;
		int cur_value = 0;
		int k = i;
		if (arr[k+1][j] == -1)
		min_value = function(arr, vect, k + 1, j) + vect.at(k).first * vect.at(k).second * vect.at(j).second;
		else min_value = arr[k+1][j] + vect.at(k).first * vect.at(k).second * vect.at(j).second;
		k++;
		while (k < j)
		{
			if (arr[i][k] == -1 && arr[k+1][j] == -1)
			cur_value = function(arr, vect, i, k) + function(arr, vect, k + 1, j) + vect.at(i).first * vect.at(j).second *  vect.at(k).second;
			else if (arr[i][k] == -1)
			cur_value = function(arr, vect, i, k) +arr[k + 1][j] + vect.at(i).first * vect.at(j).second *  vect.at(k).second;
			else if (arr[k+1][j] == -1)
			cur_value = arr[i][k] + function(arr, vect, k + 1, j) + vect.at(i).first * vect.at(j).second *  vect.at(k).second;
			else cur_value = arr[i][k] + arr[k+1][j] + vect.at(i).first * vect.at(j).second *  vect.at(k).second;
			k++;

			if (cur_value < min_value) min_value = cur_value;
		}

		arr[i][j] = min_value;

		return min_value;

	}



}






int main() {

	int x;
	int y;
	int n;
	vector<pair<int, int>> dimen;

	ifstream fi("input.txt");

	fi >> n;

	while (!fi.eof())
	{
		fi >> x;
		fi >> y;
		dimen.push_back(make_pair(x, y));
	}


	int** array = new int*[n];
	for (int i = 0; i < n; i++) array[i] = new int[n];

	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
		{
			if (i >= j) array[i][j] = 0;
			else array[i][j] = -1;
		}
	

	

	ofstream fo("output.txt");



	fo << function(array, dimen, 0, n - 1);

	cout << endl << clock();

	system("pause");
	return 0;

}