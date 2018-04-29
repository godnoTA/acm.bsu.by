#include<iostream>
#include<fstream>
#include<queue>
#include<set>
using namespace std;
int check(int* mas, int n) {
	for (int i = 0; i < n; i++)
		if (mas[i] == 1)
			return i + 1;
	return 0;
}
void main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n;
	in >> n;
	int** mas = new int*[n];
	for (int i = 0; i < n; i++)
	{
		mas[i] = new int[n];
		for (int j = 0; j<n; j++)
			mas[i][j] = 0;
	}
	for (int i = 0; i<n; i++)
		for (int j = 0; j < n; j++)
			in >> mas[i][j];
	queue<int> myQueue;
	set<int> mySet;
	int* array = new int[n];
	bool* used = new bool[n];
	for (int i = 0; i < n; i++)
		used[i] = false;
	myQueue.push(0);
	int Number = 0;
	while (!myQueue.empty()) {
		int k = myQueue.front();
		myQueue.pop();
		mySet.erase(k);
		used[k] = true;
		array[Number++] = k;
		for (int i = 0; i < n; i++)
		{
			if (mas[k][i] == 1 && used[i] == false&&mySet.find(i)==mySet.end())
			{
				myQueue.push(i);
				mySet.insert(i);
			}
		}
		if (myQueue.empty()) {
			for (int i = 0; i < n; i++)
			{
				if (used[i] != true)
				{
					myQueue.push(i);
					mySet.insert(i);
					break;
				}
			}
		}
	}
	int* finish = new int[n];
	for (int i = 0; i < n; i++)
		finish[array[i]] = i+1;
	for (int i = 0; i < n; i++)
		out << finish[i] << " ";
	out << endl;
	out.close();
	in.close();
}