#include <iostream>
#include <fstream>
using namespace std;

int main() {

	ifstream fi("input.txt");

	int number_of_vertexes = 0;
	int number_of_edges = 0;
	fi >> number_of_vertexes;
	fi >> number_of_edges;


	int ** array = new int*[number_of_vertexes];
	for (int i = 0; i < number_of_vertexes; ++i)
	{
		array[i] = new int[number_of_vertexes];
	}
	for (int i = 0; i < number_of_vertexes; ++i)
		for (int j = 0; j < number_of_vertexes; ++j)
		{
			array[i][j] = 0;
		}


	int x = 0;
	int y = 0;
	while (fi >> x && fi >> y)
	{
		array[x - 1][y - 1] = array[y - 1][x - 1] = 1;
	}
	

	ofstream fo("output.txt");

	for (int i = 0; i < number_of_vertexes; ++i)
		for (int j = 0; j < number_of_vertexes; ++j)
		{
			if (j == number_of_vertexes - 1)
				fo << array[i][j] << endl;
			else fo << array[i][j] << " ";
		}

	delete array;

	system("pause");
	return 0;
}