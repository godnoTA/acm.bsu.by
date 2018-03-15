// tree.cpp : Defines the entry point for the console application.
//

#include <iostream>
#include <fstream>

using namespace std;

ifstream in("input.txt");
ofstream out("output.txt");

int A[500][500];

int main()
{
	bool flag_graph = true;
	bool pseudograph_flag = true;
	bool multigraph = true;
	int ver_num, reb_num;
	in >> ver_num;
	in >> reb_num;
	int ver1, ver2;

	for (int i = 0; i < 500; i++)
		for (int j = 0; j < 500; j++)
			A[i][j] = 0;

	for (int i = 0; i < reb_num; i++)
	{
		in >> ver1;
		in >> ver2;
		if (A[ver1 - 1][ver2 - 1]>0)
			flag_graph = false;
		A[ver1 - 1][ver2 - 1]++;
		A[ver2 - 1][ver1 - 1]++;
		if (ver1==ver2)
		{
			flag_graph = false;
			multigraph = false;
		}
	}


	
	if (flag_graph)
		out << "Yes" << endl;
	else
		out << "No" << endl;

	if (multigraph)
		out << "Yes" << endl;
	else
		out << "No" << endl;

	if (pseudograph_flag)
		out << "Yes" << endl;
	else
		out << "No" << endl;


	return 0;
}