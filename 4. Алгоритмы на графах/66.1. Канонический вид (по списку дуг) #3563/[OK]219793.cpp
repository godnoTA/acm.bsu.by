#include <iostream>
#include <fstream>
#include <list>
using namespace std;

int main() {

	ifstream fi("input.txt");

	int number_of_vertexes = 0;
	fi >> number_of_vertexes;
	
	int* array = new int[number_of_vertexes];
	for (int i = 0; i < number_of_vertexes; ++i)
		array[i] = 0;

	int x = 0;
	int y = 0;
	while (fi >> x)
	{
		fi >> y;
		array[y - 1] = x;
	}

	ofstream fo("output.txt");
	for (int i = 0; i < number_of_vertexes; ++i) fo << array[i] << " ";


	

	system("pause");
	return 0;
}