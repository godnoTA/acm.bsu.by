#include <fstream>
//#include <map>
#include <vector>
//#include <cmath>
using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

//const int deg = 19;
const int LEAF = 524287; // 2^19 - 1
const int SIZE = 1048575; // 2^20
int Tree[SIZE]; 

void modify(int x)
{
	for (int i = LEAF + x; i >= 0; i = (i + 1) / 2 - 1)
		Tree[i]++;
}

int countSum(int idx, int intvl_l, int intvl_r, int l, int r)
{
	if (l >= intvl_r || r <= intvl_l)
		return 0;
	if (l <= intvl_l && r >= intvl_r)
		return Tree[idx];
	int k = (intvl_l + intvl_r) / 2;
	return countSum(2 * idx + 1, intvl_l, k, l, r) + countSum(2 * idx + 2, k, intvl_r, l, r);
}

int sum(int l, int r)
{
	return countSum(0, 0, LEAF + 1, l, r);
}

int main()
{
	int n;
	fin >> n;
	vector <int> levels(n);
	levels[0] = 1;
	int x, y;
	fin >> x >> y;
	int currY = y, lowerBound = x + 1, currLvl = 1;
	modify(x); 

	for (int i = 1; i < n; i++)
	{
		fin >> x >> y;
		if (y != currY)
		{
			currY = y;
			lowerBound = 0;
			currLvl = 0;
		}

		currLvl += sum(lowerBound, x + 1);
		lowerBound = x + 1;
		levels[currLvl++]++;
		modify(x);
		
	}
	
	for (int i = 0; i < n; i++)
		fout << levels[i] << endl;
}