#include<iostream>
#include<fstream>
#include<string.h>
#include<set>
using namespace std;

int main() {
	set<long long> verticles;
	ifstream in("input.txt");
	ofstream out("output.txt");
	char*str = new char[100];
	while (in) {
		in.getline(str, 100);
		verticles.insert(atoi(str));
		
	}

	long long sum = 0;
	set<long long>::iterator it = verticles.begin();
	while (it != verticles.end()) {
		sum += *it;
		it++;
	}
	
	out<<sum;
	


		in.close();
		out.close();

}