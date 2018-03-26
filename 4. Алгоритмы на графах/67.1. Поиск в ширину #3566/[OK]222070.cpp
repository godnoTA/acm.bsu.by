#include <fstream>
#include<vector>
#include<iostream>
#include<queue>

using namespace std;

struct MyStruct
{
	bool visit = false;
	int met = 0;
	vector <int> vec;
};
int time = 0;

int main()
{

	ifstream f("input.txt");
	int n, a;
	f >> n;
	MyStruct *arr = new MyStruct[n];
	queue<int> q;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			f >> a;
			if (a == 1)
			{
				arr[i].vec.push_back(j);
			}
		}
	}
	q.push(0);
	arr[0].visit = true;
	arr[0].met = 1;
	for (int i = 0; i < n; i++)
	{
		if (arr[i].visit == false)
		{
			arr[i].visit = true;
			q.push(i);
		}
		while (!q.empty())
		{
			int j = q.front();
			time++;
			arr[j].met = time;
			q.pop();
			for (int i = 0; i < arr[j].vec.size(); i++)
			{
				if (arr[arr[j].vec[i]].visit == false) {
					arr[arr[j].vec[i]].visit = true;
					q.push(arr[j].vec[i]);
				}
			}
		}
	}
	ofstream of("output.txt");
	for (int i = 0; i < n; i++)
		of << arr[i].met << " ";
	return 0;
}