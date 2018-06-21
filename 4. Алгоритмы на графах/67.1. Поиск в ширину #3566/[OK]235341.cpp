#include <iostream>
#include <fstream>
#include <queue>

using namespace std;

int main(){
	ifstream in("input.txt");
	ofstream out("output.txt");

	int n;
	in >> n;
	n++;
	
	int k = 1;
	int** matr = new int*[n];
	for (int i = 1; i < n; i++)
		matr[i] = new int[n];
	int* marks = new int[n];
	bool* isDone = new bool[n];
	for (int i = 1; i < n; i++)
	{
		isDone[i] = false;
		marks[i] = 0;
		for (int j = 1; j < n; j++)
			in >> matr[i][j];
	}
	queue<int> q;

	for (int i = 1; i < n; i++)
	{
		if (marks[i] == 0 && !isDone[i])
		{
			marks[i] = k++;
			isDone[i] = true;
			q.push(i);
		}
		while (!q.empty())
		{
			int h = q.front();
			q.pop();
			for (int j = 1; j < n; j++)
				if (matr[h][j] == 1 && !isDone[j])
				{
					q.push(j);
					marks[j] = k++;
					isDone[j] = true;
				}
		}
	}

	for (int i = 1; i < n; i++)
		out << marks[i] << " ";


	delete[] isDone;
	delete[] marks;
	for (int i = 1; i < n; i++)
		delete[] matr[i];
	delete[] matr;
}