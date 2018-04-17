#include <iostream>
#include <fstream>
#include <queue>

using namespace std;

#define MAX 2520

int nod(int a, int b)
{
	while (a != b)
	{
		if (a>b)
			a = a - b;
		else
			b = b - a;
	}
	return a;
}

int nok(int a, int b)
{
	return (a*b / nod(a, b));
}


class tag
{
public:
	int island, time;
	tag(int isl = -1, int t = 0/*, tag *n=NULL*/) : island(isl), time(t)/*, next(n)*/ {};
};


int main()
{
	ifstream in("input.txt");
	int n;
	in >> n;
	int *a = new int[n], *b = new int[n], *c = new int[n], MAX_TIME = 1;
	bool *vis = new bool[n];
	for (int i = 0; i<n; i++)
	{
		in >> a[i] >> b[i];
		c[i] = a[i] + b[i];
		if (MAX_TIME < MAX)
			MAX_TIME = nok(MAX_TIME, c[i]);

	}
	in.close();
	int time;
	bool found = false;
	queue<tag> q;
	//queue q;
	int curtime = 0;
	q.push(tag(-1, 0));
	while ((!q.empty()) && (!found))
	{
		tag cur;
		cur = q.front();
		q.pop();
		if (curtime < cur.time + 1)
		{
			curtime++;
			for (int i = 0; i<n; i++)
				vis[i] = false;
		}
		for (int i = cur.island - 5; i <= (cur.island + 5); i++)
		{
			if ((i>-1) && (i<n) && (vis[i] == false) &&
				(cur.time + 1<MAX_TIME) && (((cur.time) % c[i]) < a[i]))
			{
				q.push(tag(i, cur.time + 1));
				vis[i] = true;
			}
			else
			{
				if ((i == -1) && ((cur.time + 1)<MAX_TIME))
					q.push(tag(-1, cur.time + 1));
				else
					if ((i == n) && ((cur.time + 1) <= MAX_TIME + 1))
					{
						time = cur.time + 1;
						found = true;
					}
			}
		}
	}
	delete[] a;
	delete[] b;
	delete[] c;
	a = b = c = NULL;
	ofstream out("output.txt");
	if (found)
		out << time;
	else
		out << "No";
	out.close();
	return 0;
}