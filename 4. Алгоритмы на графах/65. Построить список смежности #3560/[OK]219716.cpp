#include <iostream>
#include <fstream>
#include <list>
using namespace std;

int main() {

	ifstream fi("input.txt");

	int number_of_vertexes = 0;
	int number_of_edges = 0;
	fi >> number_of_vertexes;
	fi >> number_of_edges;
	list<int>* array = new list<int>[number_of_vertexes];

	int x = 0;
	int y = 0;
	

	while (fi >> x)
	{
		fi >> y;
		array[x - 1].push_back(y);
		array[y - 1].push_back(x);
	}


	ofstream fo("output.txt");
	

	for (int i = 0; i < number_of_vertexes; ++i)
	{
		fo << array[i].size() << " ";
		while (!array[i].empty())
		{
			fo << array[i].front() << " ";
			array[i].pop_front();
		}
		fo << endl;
	}





	

	system("pause");
	return 0;
}