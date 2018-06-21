#include<iostream>
#include <set>
#include<fstream>
using namespace std;
class Point
{
public:
	int x;
	int y;

	Point(int x1, int y1)
	{
		x = x1; y = y1;
	}
	const Point operator +(const int arr[2])
	{
		return Point (x + arr[0], y + arr[1]);
	}

	
	friend bool operator < (const Point& point, const Point& point1) 
	{
		if (point.x != point1.x)		
			return (point.x < point1.x);
		else
			return (point.y < point1.y);
	}
		
};



int main()
{
	ifstream fin("in.txt");
	ofstream fout("out.txt");
	long n;
	int key,el,arr[2];
	fin >> n;
	int sqrs[8][2] =
	{
		{ 0, 1 },
		{ 1, 1 },
		{ 1, 0 },
		{ 1, -1 },
		{ 0, -1 },
		{ -1, -1 },
		{ -1, 0 },
		{ -1, 1 }
	};
	Point curr(0, 0);
	typedef set<Point> mySet;
	mySet S;
	mySet::const_iterator it;
	S.insert(curr);
	for (int i = 0; i < n; i++)
	{
		fin >> el;
		curr = curr + sqrs[el];
		//it = S.find(curr);
		if ((S.count(curr))==1)
		{
			fout << "Yes";
			cout << "Yes\n";
			return 0;
		}
		S.insert(curr);
	}
	fout << "No";
	cout << "No\n";
	return 0;
}