#include<iostream>
#include<fstream>
#include<stack>
#include<set>
using namespace std;
static bool* used;
static int* result;
static int Number = 0;
static int** mas;
static int n;
void recurse(int index) {
	if (used[index])
		return;
	used[index] = true;
	result[Number++] = index;
	for (int i = 0; i < n; i++)
	{
		if (mas[index][i] == 1)
		{
			if (used[i])
				continue;
			recurse(i);
		}
	}
}
void main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	in >> n;
	mas = new int*[n];
	for (int i = 0; i < n; i++)
	{
		mas[i] = new int[n];
		for (int j = 0; j < n; j++)
			mas[i][j] = 0;
	}
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			in >> mas[i][j];
	used = new bool[n];
	result = new int[n];
	for (int i = 0; i < n; i++)
		used[i] = false;
	for (int i = 0; i < n; i++)
		recurse(i);
	
	int* finish = new int[n];
	for (int i = 0; i < n; i++)
		finish[result[i]] = i + 1;
	for (int i = 0; i < n; i++)
		out << finish[i] << " ";
	out << endl;
	in.close();
	out.close();
}