#include <iostream>
#include <fstream>
#include <string>
#include <map>

using namespace std;

int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	map<int, int> tree;
	string s;
	long long sum = 0;
	do{
		cin >> s;
		

		if(tree.insert(pair<int, int>(atoi(s.c_str()), atoi(s.c_str()))).second == true)
			sum += atoi(s.c_str());
	}
	while(!cin.eof());

	cout << sum;



}