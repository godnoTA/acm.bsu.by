#include <iostream>
#include <fstream>
using namespace std;

const int N = 101;
int gMap[N][N], nMap[N][N],stack[N*N];
int mapNo[N], outC[N], inC[N], vst[N];
int connC, n, sn;


void Search1(int x)
{
	vst[x] = true;
	for (int i = 1;i <= n;++i)
	{
		if (gMap[x][i] && !vst[i])
			Search1(i);
	}
	stack[++sn] = x;
}

void Search2(int x, int mark)
{
	vst[x] = true;
	mapNo[x] = mark;
	for (int i = 1;i <= n;++i)
	{
		if (gMap[i][x] && !vst[i])
			Search2(i, mark);
	}
}

ifstream inStream("input.txt");
ofstream outStream("output.txt");
int main()
{
	
	inStream >> n;
	for (int i = 1, t;i <= n;++i)
	{
		while (inStream>>t , t)
			gMap[i][t] = true;
	}

		for (int i = 1;i <= n;++i)
		{
			if (!vst[i])
				Search1(i);
		}
		memset(vst, false, sizeof(vst));
		for (int i = sn;i >= 1;--i)
		{
			if (!vst[stack[i]])
				Search2(stack[i], ++connC);
		}
		for (int i = 1;i <= n;++i)
		{
			for (int j = 1;j <= n;++j)
			{
				if (gMap[i][j] && mapNo[i] != mapNo[j])
					nMap[mapNo[i]][mapNo[j]] = true;
			}
		}
	memset(inC, 0, sizeof(inC));
	memset(outC, 0, sizeof(outC));

	for (int i = 1;i <= connC;++i)
	{
		for (int j = 1;j <= connC;++j)
		{
			if (nMap[i][j])
				++outC[i], ++inC[j];
		}
	}

	int zeroIn = 0, zeroOut = 0;
	for (int i = 1;i <= connC;++i)
	{
		if (inC[i] == 0)
			++zeroIn;
		if (outC[i] == 0)
			++zeroOut;
	}
	outStream<< zeroIn<< "\n";
	if (connC == 1)
		outStream << "0";
	else
		outStream << ( zeroIn >zeroOut ? zeroIn : zeroOut) << "\n";
	return 0;
}
