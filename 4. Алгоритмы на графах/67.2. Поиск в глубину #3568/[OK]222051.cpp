#include <fstream>
#include<vector>
#include<iostream>

using namespace std;


struct MyStruct
{
	bool visit = false;
	int met;
	vector <int> vec;
};
int time = 0;
void dfs(MyStruct *arr, int k)
{
	arr[k].visit = true;
	time++;
	arr[k].met = time;
	for (int i = 0; i < arr[k].vec.size(); i++)
	{
		if (arr[arr[k].vec[i]].visit == false)
		{
			dfs(arr, arr[k].vec[i]);
		}

	}
}
int main()
{
	ifstream f("input.txt");
	int n, a;
	f >> n;
	MyStruct *arr = new MyStruct[n];
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
	for (int i = 0; i < n; i++)
	{
		if (arr[i].visit == false)
			dfs(arr, i);
	}
	f.close();
	ofstream of("output.txt");
	for (int i = 0; i < n; i++)
		of << arr[i].met << " ";
	of.close();
	return 0;
}