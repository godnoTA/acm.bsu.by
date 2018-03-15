#include <iostream>
#include <fstream>

using namespace std;


int main() {

	int n = 0;
	int x = 0;

	ifstream fi("input.txt");
	fi >> n;
	
	int* arr = new int[n];

	for (int i = 0; i < n; ++i)
	{
		fi >> x;
		arr[i] = x;
	}


	bool c = true;


	for (int i = 1; i < n; ++i)
	{
		if (arr[i] < arr[(i - 1) / 2]) c = false;
	}


	ofstream fo("output.txt");
	if (c == true) fo << "Yes";
	else fo << "No";



	system("pause");
	return 0;
}