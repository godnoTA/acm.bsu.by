#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main()
{
	long long n,k,count;
	ifstream f("input.txt");
	f >> n;
	f.close();
	vector<long long> vec;
	while (n>1)
	{
		k = 2;
		count = 0;
		while (n>=k*2)
		{
			k *= 2;
			count++;
		}
		count++;
		n -= k;
		vec.push_back(count);
	}
	if(n==1)
		vec.push_back(0);
	reverse(vec.begin(), vec.end());
	vector<long long>::iterator it;
	ofstream of("output.txt");
	for (it = vec.begin(); it != vec.end(); ++it)
	{
	of << *it<<'\n';
	}
	of.close();
	return 0;
}