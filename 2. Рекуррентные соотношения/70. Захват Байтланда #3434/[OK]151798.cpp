#include <vector>
#include <algorithm>
#include <map>
#include <string>
#include <fstream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
#include <ctime>
#include <queue>
#include <set>
#include <iostream>
#include <stack>
#include <list>
#include <bitset>
#include <unordered_map>
using namespace std;
typedef long long ll;
const ll MAXN = 5000002;
int kolvost, kolvovzrv;
int lvl[300];
int otvet[300][300];
int rasst[300][300];


int main()
{
	int i = 0, j = 0, t = 0, z = 0, otrez = 0, vzryvmosta = 0;


	ifstream in("input.txt");
	ofstream out("output.txt");
	in >> kolvost >> kolvovzrv;
	while (i < kolvost)
	{
		in >> lvl[i];
		i++;
	}


	i = 0;
	while (i < kolvost) 
	{
		j = i+1;
		while (j < kolvost) 
		{
			rasst[i][j] = rasst[i][j - 1];
			t = i;
			while (t < j)
			{
				otrez = lvl[t] * lvl[j];
				rasst[i][j] += otrez;
				t++;
			}
			rasst[j][i] = rasst[i][j];
			j++;
		}
		i++;
	}

	j = 0;
	while (j < kolvost)
	{
		otvet[j][0] = rasst[0][j];
		j++;
	}

	i = 1;
	while (i <= kolvovzrv) 
	{
		j = 0;
		while (j < kolvost) 
		{
			otvet[j][i] = MAXN;
			z = 0;
			while (z < j) 
			{
				vzryvmosta = otvet[z][i - 1] + rasst[z + 1][j];
				if (otvet[j][i] > vzryvmosta)
				otvet[j][i] = vzryvmosta;
				z++;
			}
			j++;
		}
		i++;
	}
	out << otvet[kolvost - 1][kolvovzrv];
	return 0;
}