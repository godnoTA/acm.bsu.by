#include <iostream>

#include <fstream>
#include <vector>
#include <list>
#include <iterator>
#include <math.h>
#include <vector>

using namespace std;

ifstream in("input.txt");
ofstream out("output.txt");

vector <int> mas;
int res = 0;
int i=0;
int j=0;

void func(long long n){
	while (n > 0) {
		if (n % 2 == 1)
			mas.push_back(res);
		n/=2;
		res++;
	}
}
int main()
{
	long long n;
	if(in == NULL )
        return 1;
	in >> n;
	in.close();

	if(n<1){
		out << -1;
		return 0;
	}
	func(n);
	for (i = 0; i<=mas.size() - 1; i++)
	{
		out << mas[i] << endl;
	}
	out.close();
    return 0;
}