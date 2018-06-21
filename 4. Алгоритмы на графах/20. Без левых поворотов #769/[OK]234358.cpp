#include <iostream>
#include <fstream>
#include <queue>
#include <map>
#include <list>

using namespace std;

struct node {
	int x;
	int y;
	int value;
};

struct keeper {
public:
	long long from;
	long long to;
	int hint;

	keeper() {
		hint = 0;
	}

	friend const bool operator < (const keeper& a, const keeper&b)
	{
		if (a.from == b.from) {
			return  (a.to<b.to);
		}
		return (a.from < b.from);
	}
};

bool determinant(long long x1, long long x2, long long x3, long long y1, long long y2, long long y3) {
	return (x1*(y2 - y3) - y1*(x2 - x3) + x2*y3 - x3*y2) <= 0;
}

struct fromTo {
	int from;
	int to;
};

void main() {
	ifstream in("input.txt");
	ofstream of("output.txt");

	int n;
	int m;

	in >> n;
	in >> m;

	node *nodes = new node[n + 1];

	queue<fromTo> way = queue<fromTo>();

	//vector<keeper> writer = vector<keeper>();
	map<keeper, keeper> writer = map<keeper, keeper>();

	int **matrix = new int*[n];
	for (int i = 0; i < n; i++) {
		matrix[i] = new int[n];
		for (int j = 0; j < n; j++) {
			matrix[i][j] = 0;
		}
	}

	int i;
	int j;
	int x1;
	int x2;
	int y1;
	int y2;

	for (int q = 0; q < m; q++) {
		in >> x1;
		in >> y1;
		in >> x2;
		in >> y2;
		in >> i;
		in >> j;

		nodes[i].x = x1;
		nodes[i].y = y1;
		nodes[j].x = x2;
		nodes[j].y = y2;

		matrix[i - 1][j - 1] = 1;
		matrix[j - 1][i - 1] = 1;
	}

	int begin;
	int end;

	in >> begin;
	in >> end;

	nodes[0].x = nodes[begin].x;
	nodes[0].y = nodes[begin].y-1;

	int kept = 0;

	for (int i = 1; i <= n; i++) {
		cout << nodes[i].x << ' ' << nodes[i].y << endl;
	}

	for (int i = 1; i <= n; i++) {
		if (nodes[0].x == nodes[i].x && nodes[0].y == nodes[i].y) {
			kept = i;
			break;
		}
	}

	fromTo example;
	example.from = kept;
	example.to = begin;
	way.push(example);

	keeper beginner;
	beginner.from = kept;
	beginner.to = begin;


	keeper starter;
	starter.from = 10000000000000;
	starter.to = kept;
	writer[beginner] = starter;

	bool print = true;

	while (way.size() != 0) {
		if (way.front().to == end) {
			of << "Yes" << endl;
			print = false;
			int first = 0;
			
			list<int> finalWay = list<int>();

			beginner.to = way.front().to;
			beginner.from = way.front().from;

			finalWay.push_front(beginner.to);

			while (writer[beginner].from != 10000000000000) {
				finalWay.push_front(writer[beginner].to);
				starter.to = writer[beginner].to;
				starter.from = writer[beginner].from;
				beginner.to = starter.to;
				beginner.from = starter.from;
			}
			while (finalWay.size() > 0) {
				if (!first) {
					of << finalWay.front();
					first++;
				}
				else {
					of << ' ' << finalWay.front();
				}
				finalWay.pop_front();
			}

			break;
		}
		
		for (int q = 0; q < n; q++) {
			if (matrix[way.front().to - 1][q] == 1 && q!=way.front().from-1/*(nodes[q].x != nodes[way.front().from].x || nodes[q].y != nodes[way.front().from].y)*/) {
				if (determinant(nodes[way.front().from].x, nodes[way.front().to].x,  nodes[q+1].x, nodes[way.front().from].y, nodes[way.front().to].y ,  nodes[q+1].y)) {
					example.from = way.front().to;
					example.to = q+1;
					way.push(example);

					beginner.from = way.front().to;
					beginner.to = q + 1;

					starter.from = way.front().from;
					starter.to = way.front().to;
					const auto found = writer.find(beginner);
					if (found == writer.cend()){
					writer[beginner] = starter;

					matrix[way.front().to - 1][q] = 0;
					}
				}
			}
		}
		way.pop();
	}

	if (print) {
		of << "No";
	}

	in.close();
	of.close();
}