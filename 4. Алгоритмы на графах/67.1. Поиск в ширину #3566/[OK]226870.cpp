#include <iostream>
#include <fstream>
#include <queue>

using namespace std;

struct node {
	bool visited;
	int mark;
	queue<int> sons;

	node() {
		sons = queue<int>();
		visited = false;
	}
};

void bfs(int start_node, node* array, int size, int mark) {
	queue<int> queueFunc;

	queueFunc.push(start_node);              
	array[start_node].visited = true;
	mark++;
	array[start_node].mark = mark;
	while (!queueFunc.empty()) {
		int nodeFunc = queueFunc.front();      
		queueFunc.pop();

		int sizeFunc = array[nodeFunc].sons.size();

		for(int i=0;i<sizeFunc;i++) {    
			int a = array[nodeFunc].sons.front();
			if (array[array[nodeFunc].sons.front()].visited == false) {     
				queueFunc.push(array[nodeFunc].sons.front());     
				array[array[nodeFunc].sons.front()].visited = true;
				mark++;
				array[array[nodeFunc].sons.front()].mark = mark;
				array[nodeFunc].sons.pop();
			}
			else {
				array[nodeFunc].sons.pop();
			}
		}
	}

	for (int i = 0; i < size; i++) {
		if (abs(array[i].mark) >100|| array[i].mark==0|| array[i].mark<0) {
			int start_nodeOne = i;
			queue<int> queueFuncOne;

			queueFuncOne.push(i);
			array[start_nodeOne].visited = true;
			mark++;
			array[start_nodeOne].mark = mark;
			while (!queueFuncOne.empty()) {
				int nodeFuncOne = queueFuncOne.front();
				queueFuncOne.pop();

				int sizeFuncOne = array[nodeFuncOne].sons.size();

				for (int i = 0; i<sizeFuncOne; i++) {
					int a = array[nodeFuncOne].sons.front();
					if (array[array[nodeFuncOne].sons.front()].visited == false) {
						queueFuncOne.push(array[nodeFuncOne].sons.front());
						array[array[nodeFuncOne].sons.front()].visited = true;
						mark++;
						array[array[nodeFuncOne].sons.front()].mark = mark;
						array[nodeFuncOne].sons.pop();
					}
					else {
						array[nodeFuncOne].sons.pop();
					}
				}
			}
		}
	}

	ofstream out("output.txt");
	for (int i = 0; i < size; i++) {
		if (i != 0) {
			out << " ";
		}
		out << array[i].mark;
	}
}

void main() {
	ifstream in("input.txt");

	int dimension;
	in >> dimension;

	node *array = new node[dimension];

	int x;
	int counter = -1;

	for (int j = 0; j < dimension; j++) {
		for (int i = 0; i < dimension; i++) {
			in >> x;
			if (x != 0) {
				array[j].sons.push(i);
			}
		}
	}

	bfs(0, array, dimension, 0);

	delete[] array;
	in.close();
}