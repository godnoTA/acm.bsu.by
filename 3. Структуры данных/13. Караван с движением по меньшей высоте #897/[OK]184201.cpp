#include <iostream>
#include <fstream>
#include <queue> 
using namespace std;

struct coord{
	int oX;
	int oY;
	coord(int x, int y)
	{
		oX = x;
		oY = y;
	}
	coord()
	{
		oX = -1;
		oY = -1;
	}
};

int main()
{
	int size;
	coord startNode, finishNode;
	ifstream in1("in.txt");
	in1 >> size >> startNode.oX >> startNode.oY >> finishNode.oX >> finishNode.oY;
	int**landscape;
	int**routes;
	queue<coord> myQueue;

	landscape = new int*[size];
	routes = new int*[size];

	for (int i = 0; i < size; i++){
		landscape[i] = new int[size];
		routes[i] = new int[size];

		for (int j = 0; j < size; j++){
			in1 >> landscape[i][j];
			routes[i][j] = 0;
		}

	}
	in1.close();

	startNode.oX--;
	startNode.oY--;
	finishNode.oX--;
	finishNode.oY--;

	routes[startNode.oX][startNode.oY] = landscape[startNode.oX][startNode.oY];

	coord nextnode, node;

	myQueue.push(startNode);

	while (myQueue.size() != 0){
		node = myQueue.front();
		myQueue.pop();

		if (node.oX + 1 > -1 && node.oX + 1 < size){
			if (landscape[node.oX + 1][node.oY] < landscape[node.oX][node.oY] && routes[node.oX + 1][node.oY]==0)
			{
				routes[node.oX + 1][node.oY] = landscape[node.oX + 1][node.oY];
				nextnode.oX = node.oX + 1;
				nextnode.oY = node.oY;
				myQueue.push(nextnode);
			}
		}
		if (node.oX - 1 > -1 && node.oX - 1 < size){
			if (landscape[node.oX - 1][node.oY] < landscape[node.oX][node.oY] && routes[node.oX - 1][node.oY] == 0)
			{
				routes[node.oX - 1][node.oY] = landscape[node.oX - 1][node.oY];
				nextnode.oX = node.oX - 1;
				nextnode.oY = node.oY;
				myQueue.push(nextnode);
			}
		}
		if (node.oY + 1 > -1 && node.oY + 1 < size){
			if (landscape[node.oX][node.oY + 1] < landscape[node.oX][node.oY] && routes[node.oX][node.oY + 1] == 0)
			{
				routes[node.oX][node.oY + 1] = landscape[node.oX][node.oY + 1];
				nextnode.oX = node.oX;
				nextnode.oY = node.oY + 1;
				myQueue.push(nextnode);
			}
		}
		if (node.oY - 1 > -1 && node.oY - 1 < size){
			if (landscape[node.oX][node.oY - 1] < landscape[node.oX][node.oY] && routes[node.oX][node.oY - 1] == 0)
			{
				routes[node.oX][node.oY - 1] = landscape[node.oX][node.oY - 1];
				nextnode.oX = node.oX;
				nextnode.oY = node.oY - 1;
				myQueue.push(nextnode);
			}
		}
	}

	ofstream out1("out.txt");
	for (int i = 0; i < size - 1; i++){
		for (int j = 0; j < size - 1; j++){
			out1 << routes[i][j] << " ";
		}out1 << routes[i][size - 1] << endl;
	}

	for (int j = 0; j < size - 1; j++){
		out1 << routes[size - 1][j] << " ";
	}out1 << routes[size - 1][size - 1];

	return 0;
}