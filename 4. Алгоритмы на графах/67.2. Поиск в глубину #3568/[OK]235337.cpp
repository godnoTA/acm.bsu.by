#include <iostream>
#include <fstream>
#include <queue>

using namespace std;

int* marks;
int** matr;
bool* isDone;
int n;
int k = 1;

void getMarks(int i)
{
	for (int j = 1; j < n; j++)
		if (matr[i][j] == 1 && !isDone[j])
		{
			marks[j] = k++;
			isDone[j] = true;
			getMarks(j);
		}
}

int main(){
	ifstream in("input.txt");
	ofstream out("output.txt");

	in >> n;
	n++;

	matr = new int*[n];
	for (int i = 1; i < n; i++)
		matr[i] = new int[n];
	marks = new int[n];
	isDone = new bool[n];
	for (int i = 1; i < n; i++)
	{
		isDone[i] = false;
		marks[i] = 0;
		for (int j = 1; j < n; j++)
			in >> matr[i][j];
	}

	for (int i = 1; i < n; i++)
	{
		if (marks[i] == 0 && !isDone[i])
		{
			marks[i] = k++;
			isDone[i] = true;
		}
		getMarks(i);
	}

	for (int i = 1; i < n; i++)
		out << marks[i] << " ";

	delete[] isDone;
	delete[] marks;
	for (int i = 1; i < n; i++)
		delete[] matr[i];
	delete[] matr;
}