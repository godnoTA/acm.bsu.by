#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	long long n;
	cin >> n;
	vector<int> v;
	while(n != 0){
		if(n % 2 == 1)
			v.push_back(1);
		else
			v.push_back(0);
		n = n / 2;
	}
	//vector<int>::iterator i;
	bool w = true;
	for(int i = 0; i < v.size(); i++)
		if( v[i] == 1){
			w = false;
			cout << i << "\n";
		}
	if(w)
		cout << -1;
	return 0;
}