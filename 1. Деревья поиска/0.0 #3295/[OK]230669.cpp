#include<iostream>
#include<fstream>
#include<set>
using namespace std;


int main(){


	ifstream cin("input.txt");
	ofstream cout("output.txt");



	int buf;
	set<int> a;
	while (!cin.eof())
		cin >> buf, a.insert(buf);


	long long sum = 0;
	for (int i : a) sum += i;

	cout << sum;






	return 0;
}