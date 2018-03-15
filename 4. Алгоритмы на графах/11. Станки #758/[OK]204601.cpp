#define _CRT_DISABLE_PERFCRIT_LOCKS
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <iostream>
#include <algorithm>
#include <queue>
#include <ctime>
using namespace std;

char* visited;
char* visitedNew;
bool anotherUnsaturated = false;

inline void writeInt(int x)
{
	if (x < 0) {
		putchar('-');
		x = -x;
	}

	char buf[10], *p = buf;
	do {
		*p++ = '0' + x % 10;
		x /= 10;
	} while (x);
	do
	{
		putchar(*--p);
	} while (p > buf);
}

inline int nextInt()
{
	char c;
	while (c = getchar(), c <= ' ');

	bool sign = c == '-';
	if (sign)
		c = getchar();

	int res = c - '0';
	while (c = getchar(), c >= '0' && c <= '9')
		res = res * 10 + (c - '0');

	return sign ? -res : res;

}

class Graph {
public:
	int**ribs;
	int* pair;
	int N;
	int ribsCount = 0;
	Graph(int N) {
		ribs = new int*[N];
		this->N = N;
		pair = new int[N];
		for (int i = 0; i < N; i++) {
			ribs[i] = new int [N];
			for (int j = 0; j < N; j++)
				ribs[i][j] = 0;
			pair[i] = -1;
		}
			
	}

	void addRib(int node, int nodesecond) {
		ribs[node][nodesecond-N] = 1;
	}
};

void reading(int* productivity, int** productivityMatrix, int N) {
	int index = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			int prod = nextInt();;
			productivity[index] = prod;
			index++;
			productivityMatrix[i][j] = prod;
		}
	}
	sort(productivity, productivity + N*N);
}

Graph* createGraph(int prod, int** prodictivityMatrix, int N, char * visited) {
	Graph* graph = new Graph(N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (prodictivityMatrix[i][j] >= prod) {
				if (visited[i + N] == false && visited[j] == false) {
					visited[i + N] = true;
					visited[j] = true;
					graph->pair[i] = j;
					graph->ribsCount++;
				}
				else
					graph->addRib(j, i + N);
			}
		}
	}
	return graph;
}

bool findLargerOne(int N, Graph* graph, int v) {
	if (visitedNew[v])
		return false;
	visitedNew[v] = true;
	for (int i = 0; i < N; i++) {
		if (graph->ribs[v][i] == 1) {
			if (graph->pair[i] == -1 || findLargerOne(N, graph, graph->pair[i])) {
				if (graph->pair[i] != -1) {
					graph->ribs[graph->pair[i]][i] = 1;
					anotherUnsaturated = true;
				}
				graph->pair[i] = v;
				graph->ribs[v][i] = 0;
				return true;
			}
		}
	}
	return false;
}

int kyhn(Graph* graph, int N) {
	for (int i = 0; i < N; i++)
		visitedNew[i] = false;
	for (int i = 0; i < N; i++) {
		if (visited[i])
			continue;
		if (anotherUnsaturated) {
			for (int i = 0; i < N; i++)
				visitedNew[i] = 0;
		}
		anotherUnsaturated = false;
		if (!findLargerOne(N, graph, i))
			break;
		graph->ribsCount++;
	}
	return graph->ribsCount;
}

int findMin(Graph * graph, queue<int> queue, int N, char* blocked, int* path, int** productivity, int prod, int node) {
	int  min = -1;
	for (int i = 0; i < N << 1; i++)
		visited[i] = false;
	visited[node] = true;
	int currentNode;
	while (!queue.empty()) {
		currentNode = queue.front();
		queue.pop();
		if (!visited[graph->pair[currentNode]] && !blocked[graph->pair[currentNode]]) {
			path[graph->pair[currentNode]] = currentNode + N;
			visited[graph->pair[currentNode]] = true;
			if (productivity[node - N][graph->pair[currentNode]] >= prod && (min == -1 || min > graph->pair[currentNode]))
				min = graph->pair[currentNode];
			for (int i = 0; i < N; i++) {
				if (graph->ribs[graph->pair[currentNode]][i] == 1) {
					if (!blocked[i + N] && !visited[i + N]) {
						visited[i + N] = true;
						path[i + N] = graph->pair[currentNode];
						queue.push(i);
					}
				}
			}
		}
	}
	return min;
}

void findLexicographical(Graph * graph, int N, int** productivity, int prod) {
	cout << prod << '\n';
	char* blocked = new char[N << 1];
	for (int i = 0; i < N << 1; i++)
		blocked[i] = false;
	int* path = new int[N << 1];
	for (int u = N; u < (N << 1) - 1; u++) {
		if (blocked[u] == false) {
			queue<int> queue;
			queue.push(u - N);
			int min = findMin(graph, queue, N, blocked, path, productivity, prod, u);
			if (min != -1) {
				int current = min;
				while (current != u) {
					int nodeWorker;
					if (current >= N) {
						nodeWorker = current - N;
						graph->pair[nodeWorker] = path[current];
						graph->ribs[path[current]][nodeWorker] = 0;
					}
					else
						graph->ribs[current][path[current] - N] = 1;
					current = path[current];
				}
				int nodeMachine = min;
				int nodeWorker = u - N;
				graph->pair[nodeWorker] = nodeMachine;
				graph->ribs[nodeMachine][nodeWorker] = 0;
				blocked[min] = true;
				blocked[u] = true;
			}
			if (u != N)
				cout << " ";
			writeInt(graph->pair[u - N] + 1);
		}
	}
	cout << " ";
	writeInt(graph->pair[N - 1] + 1);
}

void search(int* productivity, int** productivityMatrix, int N) {
	int lastProductivity = -1;
	int first = 0;
	Graph* lastResultgr = NULL;
	int last = N*N - 1;
	while (true) {
		int medium = (first + last) >> 1;
		if (first > last) {
			findLexicographical(lastResultgr, N, productivityMatrix, productivity[medium]);
			break;
		}
		if (productivity[medium] != lastProductivity) {
			if (productivity[medium] < lastProductivity) {
				findLexicographical(lastResultgr, N, productivityMatrix, productivity[medium]);
				break;
			}
			for (int i = 0; i < N << 1; i++)
				visited[i] = false;
			Graph* graph = createGraph(productivity[medium], productivityMatrix, N, visited);
			if ((lastProductivity == -1 || lastProductivity < productivity[medium]) && kyhn(graph, N) == N) {
				lastProductivity = productivity[medium];
				lastResultgr = graph;
				first = medium + 1;
			}
			else
				last = medium - 1;
		}
		else
			first = medium + 1;
	}
}

int main() {
	//unsigned int time = clock();
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int N = nextInt();
	visited = new char[N << 1];
	visitedNew = new char[N];
	int* productivity = new int[N*N];
	int** productivityMatrix = new int *[N];
	for (int i = 0; i < N; i++)
		productivityMatrix[i] = new int[N];
	reading(productivity, productivityMatrix, N);
	search(productivity, productivityMatrix, N);
	//cout << '\n'<< clock() - time;
	return 0;
}