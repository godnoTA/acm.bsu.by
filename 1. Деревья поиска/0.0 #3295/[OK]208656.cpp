#include <iostream>
#include <fstream>
#include <string>
#include <set>
using namespace std;

int main() 
{
	ifstream in ("input.txt");
	ofstream out ("output.txt");
	long long a = 0;
	int c;
	set <int> se; 
	while (in >> c) {    
    se.insert(c);
	}
	set< int>::iterator it;
	for (it=se.begin(); it!=se.end(); ++it)
	{
		a+=*it;	
	}
    out<<a;
	in.close();
	out.close();
}