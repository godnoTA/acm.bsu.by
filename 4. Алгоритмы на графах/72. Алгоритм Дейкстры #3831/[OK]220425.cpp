#include <iostream>
#include <fstream>
#include <vector>
#include <set>
#include <limits.h>
#include <ctime>
using namespace std;
const long long UNLABELED = LLONG_MAX;


int main() {

	ifstream fi("input.txt");
	int num_of_vertexes = 0;
	int num_of_edges = 0;

	fi >> num_of_vertexes;
	fi >> num_of_edges;
	int x, y, length;


	////////////////////////////////////////////////////////
	vector<vector<pair<int, int>>> graph(num_of_vertexes);

	/*заполнение графа*/
	while (fi >> x && fi >> y && fi >> length)
	{
		graph[x - 1].push_back(make_pair(y, length));
		graph[y - 1].push_back(make_pair(x, length));
	}

	//////////////////////////////////////////////////////////
	
	/*пути*/
	long long int* path = new long long int[num_of_vertexes];
	for (int i = 0; i < num_of_vertexes; ++i) path[i] = UNLABELED;
	path[0] = 0;

	/*множество путь - вершина, ибо сразу сортирует по пути*/
	set<pair<long long int, int>> way_vertex;
	way_vertex.insert(make_pair(0, 1));
	///////////////////////////////////////////////////////////

	while (way_vertex.empty() == false)
	{	
		x = (*way_vertex.begin()).second;
		way_vertex.erase(way_vertex.begin());

		for (int i = 0; i < graph[x - 1].size(); ++i)
		{
			length = graph[x - 1][i].second;
			y = graph[x - 1][i].first;

			if (path[y - 1] > path[x - 1] + length)	{
				way_vertex.erase(make_pair(path[y - 1], y));
				path[y - 1] = path[x - 1] + length;
				way_vertex.insert(make_pair(path[y - 1], y));
			}

		}

	}

	cout << clock();
	ofstream fo("output.txt");
	fo << path[num_of_vertexes - 1];

	system("pause");
	return 0;
}