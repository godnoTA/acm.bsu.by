#include <fstream>
#include <set>

using namespace std;


int main()
{
	ifstream  in("input.txt");
	ofstream out("output.txt");
	set <long long> s;
	long long ss= 0;
	long long i;
	while (in >> i)
	{
		auto it = s.find(i);
		if (!(it != s.end()))
	    ss += i;
		s.insert(i);

	}
	out << ss;




    return 0;
}

