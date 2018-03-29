#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <algorithm>
#include <set>
#include <ctime>

using namespace std;

enum Color{B, P};

struct Node {
	Color color;
	int timeLeft;
	int time_b;
	int time_p;
};

void printNode(Node node) {
	if (node.color == 0) {
		cout << "B, ";
	}
	else
		cout << "P, ";
	cout << node.timeLeft << ", " << node.time_b << ", " << node.time_p << endl;
}

Color ChangeColor(Color color) {
	if (color == 0) {
		color = P;
	}
	else {
		color = B;
	}
		return color;
}

int getTimePOrB(Node node, Color color) {
	if (color == 0) {
		return node.time_b;
	}
	else
		return node.time_p;
}

int wait(Node n1, Node n2) {
	int wait_time = -1;

	if (n1.color == n2.color) {
		wait_time = 0;
	}
	else {
		if (n1.timeLeft != n2.timeLeft) {
			wait_time = min(n1.timeLeft, n2.timeLeft);
		}
		else {
			if (getTimePOrB(n1, ChangeColor(n1.color)) != getTimePOrB(n2, n1.color)) {
				wait_time = n1.timeLeft + min(getTimePOrB(n1, ChangeColor(n1.color)), getTimePOrB(n2, n1.color));
			}
			else {
				if(getTimePOrB(n1, n1.color) != getTimePOrB(n2, ChangeColor(n1.color))){}
			}
		}
	}

	return wait_time;
}

Node setNode(Node node, int t) {
	Node temp = { P, 0, node.time_b, node.time_p};
	if (node.timeLeft > t) {
		temp.color = node.color;
		temp.timeLeft = node.timeLeft - t;
	}
	else {
		int tt = (t - node.timeLeft) % (node.time_b + node.time_p);
		if (tt < getTimePOrB(node, ChangeColor(node.color))) {
			temp.color = ChangeColor(node.color);
			temp.timeLeft = getTimePOrB(node, ChangeColor(node.color)) - tt;
		}
		else {
			temp.color = node.color;
			temp.timeLeft = getTimePOrB(node, node.color) - tt;
		}
	}
	return temp;
}

int main() {

	FILE *pFile = fopen("lights.inp", "r");
	FILE *output = fopen("lights.out", "w");

	int startNode = 0, endNode = 0, n = 0, m = 0;
	fscanf(pFile, "%d", &startNode);
	fscanf(pFile, "%d", &endNode);
	fscanf(pFile, "%d", &n);
	fscanf(pFile, "%d", &m);

	vector<vector<pair<int, int>>> crossroadsRoads(n + 1);//список смежности
	set <pair<long long, int>> crossroadsDistance;//храним расстояние и номер перекрестка
	vector<Node> crossroadsInfo(n + 1);
	long long * d = new long long[n + 1];//D[]....??????
	vector<vector<int>> minPath(n + 1);
	bool * met = new bool[n + 1];// посещена ли вершина
	int metCounter = n;

	char color = ' ';
	int r0 = 0, t_b = 0, t_p = 0;
	Node temp;
	for (int i = 1; i <= n; ++i) {
		fscanf(pFile, "%c", &color);
		fscanf(pFile, "%c", &color);
		fscanf(pFile, "%d", &r0);
		fscanf(pFile, "%d", &t_b);
		fscanf(pFile, "%d", &t_p);
		if (color == 'B')
			temp = { B, r0, t_b, t_p };
		else
			temp = { P, r0, t_b, t_p }; 
		if (i != startNode) {
			crossroadsDistance.insert(make_pair(LLONG_MAX, i));
		}
		crossroadsInfo[i] = temp;
		met[i] = false;
		d[i] = LLONG_MAX;
	}

	d[startNode] = 0;
	crossroadsDistance.insert(make_pair(0, startNode));

	int crossroad1 = 0, crossroad2 = 0, distance = 0;
	for (int i = 0; i < m; ++i) {
		fscanf(pFile, "%d", &crossroad1);
		fscanf(pFile, "%d", &crossroad2);
		fscanf(pFile, "%d", &distance);
		crossroadsRoads[crossroad1].push_back(make_pair(crossroad2, distance));
		crossroadsRoads[crossroad2].push_back(make_pair(crossroad1, distance));
	}

	int cnt = 0;

	while (metCounter != 0) {
		std::set<pair<long long, int>>::iterator it = crossroadsDistance.begin();
		int minNode = (*it).second;
		////
		crossroadsDistance.erase(it);
		met[minNode] = true;

		if (crossroadsRoads[minNode].size() != 0 && d[minNode] != LLONG_MAX) {
			for (auto i : crossroadsRoads[minNode]) {
				if (!met[i.first]) {
					////
					++cnt;
					int w = wait(setNode(crossroadsInfo[minNode], d[minNode]), setNode(crossroadsInfo[i.first], d[minNode]));
				
					if (w != -1) {
						long long temp = d[minNode] + w + i.second;
						crossroadsDistance.erase(crossroadsDistance.find(make_pair(d[i.first], i.first)));

						if (d[i.first] > temp) {
							d[i.first] = temp;
							minPath[i.first] = minPath[minNode];
							vector <int>::iterator it = minPath[i.first].begin(); ///Итератор указывает на vec[0]
							minPath[i.first].insert(it, minNode);
						}


						temp = d[i.first];
						crossroadsDistance.insert(make_pair(d[i.first], i.first));
					}
				}
			}
		}
		--metCounter;
	}

	if (LLONG_MAX == d[endNode])
		fprintf(output, "%d", 0);
	else {
		fprintf(output, "%lld", d[endNode]);
		fprintf(output, "%s", "\n");
		for (int i = minPath[endNode].size() - 1; i >= 0; --i) {
			fprintf(output, "%d", minPath[endNode][i]);
			fprintf(output, "%s", " ");
		}

		fprintf(output, "%d", endNode);
	}
	
	return 0;
}