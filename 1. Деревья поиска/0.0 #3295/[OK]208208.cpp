#include <fstream>
#include <set>
#include <string>

using namespace std;

int main()
{
	long long sum = 0;
	long long a;
	string str;
	ofstream out("output.txt");
	set<long long> aSet;
	ifstream in("input.txt");
	if (in.is_open())
	{
		while (getline(in, str)) {
			a = atol(str.c_str());
			aSet.insert(a);
		}
		for (set<long long>::iterator it = aSet.begin(); it != aSet.end(); it++)
			sum += *it;
		in.close();
	}
		out << sum;
		out.close();
	return 0;
}