#include <iostream>
#include <fstream> 
#include <map> 
using namespace std;
struct step
{
	int x;
	int y;
	step(int x1 = 0, int y1 = 0) : x(x1), y(y1) {}
	bool operator ==(const step &s)
	{
		return ((s.x == x) && (s.y == y));
	}
	step operator+ (const int a[2])
	{
		return step(x + a[0], y + a[1]);
	}
	bool operator< (const step& b) const
	{
		if (x != b.x)
			return x < b.x;
		else if (y != b.y)
			return y < b.y;
		else return false;
	}
};
const int points[][2] = { { -1, 0 },{ -1, 1 },{ 0,  1 },{ 1,   1 },
{ 1,  0 },{ 1, -1 },{ 0, -1 },{ -1, -1 } };
ifstream f_in("in.txt");
ofstream f_out("out.txt");
typedef  map <step, int> mapStep;
typedef pair <step, int> mapPair;
int main(int argc, char* argv[])
{
	mapStep::const_iterator it;
	mapStep ms;
	int n;
	f_in >> n;
	step now(0, 0);
	ms.insert(mapPair(now, 1));
	int d;
	for (int i = 0; i < n; i++) {
		f_in >> d;
		now = now + points[d];
		it = ms.find(now);
		if (it != ms.end()) {
			f_out << "Yes";
			return 0;
		}
		ms.insert(mapPair(now, 1));
	}

	f_out << "No";
	return 0;
}

