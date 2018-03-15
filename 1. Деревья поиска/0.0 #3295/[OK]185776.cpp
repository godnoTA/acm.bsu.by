#include <iostream>
#include <fstream>
#include <set>

using namespace std;


typedef  set <long> :: iterator it_set;
int main()
{
	ifstream fin;
	fin.open("input.txt");
	ofstream fout;
	fout.open("output.txt");
	set<long> c;
	long long sum = 0;
	long temp;
	while (fin.peek() != EOF){
		fin >> temp;
		c.insert(temp);
	}
	for (it_set i = c.begin(); i != c.end(); i++){
		sum = sum + *i;
	}

	fout << sum;
	return 0;
}