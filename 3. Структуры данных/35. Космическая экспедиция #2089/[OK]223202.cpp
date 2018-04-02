#include <iostream>
#include <fstream>
#include <limits>
#include <queue>

using namespace std;
const int M = INT_MAX;

int min(int a, int b)
{
	return a > b ? b : a;
}



class DSU
{
public:
	int* arr;
	DSU(int n)
	{
		arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = -1;
	}

	int find_root(int v)
	{
		if (arr[v - 1] <= 0) { return v; }
		int t = find_root(arr[v - 1]);
		arr[v - 1] = t;
		return t;
	}

	void add(int v1, int v2)
	{
		int root1, root2;
		root1 = find_root(v1);
		root2 = find_root(v2);
		if (root1 == root2) { return; }
		if (arr[root1 - 1] < arr[root2 - 1])
		{
			arr[root1 - 1] += arr[root2 - 1];
			arr[root2 - 1] = root1;
		}
		else if (arr[root1 - 1] > arr[root2 - 1])
		{
			arr[root2 - 1] += arr[root1 - 1];
			arr[root1 - 1] = root2;
		}
		else
		{
			if (root1 > root2)
			{
				arr[root2 - 1] += arr[root1 - 1];
				arr[root1 - 1] = root2;
			}
			else
			{
				arr[root1 - 1] += arr[root2 - 1];
				arr[root2 - 1] = root1;
			}
		}
	}

};


class MyComp {
public:
	bool operator ()(const pair<int, int>& pr1, const pair<int, int>& pr2)
	{
		return pr1.first > pr2.first;
	}
};




int distance(int x1, int y1, int z1, int x2, int y2, int z2)
{
	return abs(x1 - x2) + abs(y1 - y2) + abs(z1 - z2);
}

/////////////////////////////////////////////////////////
int distance(int i, int j, int n, int m, int k)
{
	int a, b, c, x, y, z, xx, yy, zz;
	int a1, b1, c1, x1, y1, z1, xx1, yy1, zz1;

	x = (i) / (n*m); xx = (i) % (n*m);
	a = x + 1;
	if (xx == 0) { --a; xx = n*m; }
	y = xx / n; yy = xx % n;
	b = y + 1; c = yy;
	if (c == 0) { c = n; --b; }

	x1 = (j) / (n*m); xx1 = (j) % (n*m);
	a1 = x1 + 1;
	if (xx1 == 0) { --a1; xx1 = n*m; }
	y1 = xx1 / n; yy1 = xx1 % n;
	b1 = y1 + 1; c1 = yy1;
	if (c1 == 0) { c1 = n; --b1; }

	return distance(a, b, c, a1, b1, c1);

}





bool is_neighbour(int x1, int y1, int z1, int x2, int y2, int z2)
{
	return abs(x1 - x2) + abs(y1 - y2) + abs(z1 - z2) == 1;
}

bool is_neighbour(int i, int j, int n, int m, int k)
{
	return distance(i, j, n, m, k) == 1;
}











int main() {

	ifstream fi("input.txt");
	int n, m, k, t;
	fi >> n >> m >> k >> t;
	int v = n * m * k;
	int a, b, c, x, y, z, xx, yy, zz;

	int* rooms = new int[v];

	for (int i = 0; i < v; ++i)
	{
		x = (i + 1) / (n*m); xx = (i + 1) % (n*m);
		a = x + 1;
		if (xx == 0) { --a; xx = n*m; }
		y = xx / n; yy = xx % n;
		b = y + 1; c = yy;
		if (c == 0) { c = n; --b; }
		rooms[i] = 100 * c + 10 * b + a;
	}




	DSU dsu(v);

	
	int d1 = M, d2 = M, d3 = M, d4 = M, d5 = M, d6 = M, min1 = M, min2 = M, min3 = M, min4 = M, min5 = M, min6 = M, minC = M, dmin = M;
	int p = 0;
	int temp = 0;
	cout << endl;
	ofstream fo("output.txt");


	for (int i = 0; i < t; ++i)
	{
		fi >> x >> y >> z >> xx >> yy >> zz;
		b = (z - 1)*(n*m) + (y - 1)*n + x;
		c = (zz - 1)*(n*m) + (yy - 1)*n + xx;
		temp = b;



			if (is_neighbour(x, y, z, xx, yy, zz))
			{
				if (dsu.find_root(b) == dsu.find_root(c))
				{
					cout << x << " " << y << " " << z << " " << xx << " " << yy << " " << zz << endl;
					fo << x << " " << y << " " << z << " " << xx << " " << yy << " " << zz << endl;
				}
				else dsu.add(b, c);
			}


			else {
				while (temp != c)
				{
					d1 = d2 = d3 = d4 = d5 = d6 = M;

					if (is_neighbour(temp, c, n, m, k))
					{
						if (dsu.find_root(temp) == dsu.find_root(c))
						{
							z = (temp) / (n*m); a = (temp) % (n*m);
							z++;
							if (a == 0) { --z; a = n*m; }
							y = a / n; b = a % n;
							y++; x = b;
							if (x == 0) { x = n; --y; }
							cout << x << " " << y << " " << z << " " << xx << " " << yy << " " << zz << endl;
							fo << x << " " << y << " " << z << " " << xx << " " << yy << " " << zz << endl;
						}
						else dsu.add(temp, c);
						break;
					}


					else {
						z = (temp) / (n*m); a = (temp) % (n*m);
						z++;
						if (a == 0) { --z; a = n*m; }
						y = a / n; b = a % n;
						y++; x = b;
						if (x == 0) { x = n; --y; }



						if (x + 1 <= n)
						{
							d1 = distance(x + 1, y, z, xx, yy, zz);
							min1 = (z - 1)*(n*m) + (y - 1)*n + x + 1;
							minC = min1;
							dmin = d1;
						}
						if (x - 1 > 0)
						{
							d2 = distance(x - 1, y, z, xx, yy, zz);
							min2 = (z - 1)*(n*m) + (y - 1)*n + x - 1;
							if (d2 < dmin) { minC = min2; dmin = d2; }
						}
						if (y + 1 <= m)
						{
							d3 = distance(x, y + 1, z, xx, yy, zz);
							min3 = (z - 1)*(n*m) + (y)*n + x;
							if (d3 < dmin) { minC = min3; dmin = d3; }
						}
						if (y - 1 > 0)
						{
							d4 = distance(x, y - 1, z, xx, yy, zz);
							min4 = (z - 1)*(n*m) + (y - 2)*n + x;
							if (d4 < dmin) { minC = min4; dmin = d4; }
						}
						if (z + 1 <= k)
						{
							d5 = distance(x, y, z + 1, xx, yy, zz);
							min5 = (z)*(n*m) + (y - 1)*n + x;
							if (d5 < dmin) { minC = min5; dmin = d5; }
						}
						if (z - 1 > 0)
						{
							d6 = distance(x, y, z - 1, xx, yy, zz);
							min6 = (z - 2)*(n*m) + (y - 1)*n + x;
							if (d6 < dmin) { minC = min6; dmin = d6; }
						}


						
						p = minC;
						minC = dmin = d1 = d2 = d3 = d4 = d5 = d6 = min1 = min2 = min3 = min4 = min5 = min6 = M;
						if (dsu.find_root(temp) == dsu.find_root(p))
						{
							cout << x << " " << y << " " << z << " ";
							fo << x << " " << y << " " << z << " ";

							z = (p) / (n*m); a = (p) % (n*m);
							z++;
							if (a == 0) { --z; a = n*m; }
							y = a / n; b = a % n;
							y++; x = b;
							if (x == 0) { x = n; --y; }

							cout << x << " " << y << " " << z << endl;
							fo << x << " " << y << " " << z << endl;
							temp = p;
						}
						else {
							dsu.add(temp, p);
							temp = p;
						}
					}
				}
			}
		}



	system("pause");
	return 0;
}