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
	int c = 1;
	while (fi >> x)
	{
		if (x == 1)
		{
			if (c % number_of_vertexes == 0)
			{
				array[number_of_vertexes - 1] = c / number_of_vertexes;
			}
			else
			array[(c) % number_of_vertexes - 1] = c / number_of_vertexes + 1;
		}
		c++;
	}

	ofstream fo("output.txt");
	for (int i = 0; i < number_of_vertexes; ++i) fo << array[i] << " ";


	

	system("pause");
	return 0;
}