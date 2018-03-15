#include <iostream>
#include <fstream>
#include <map>

using namespace std;

int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	int n;
	cin >> n;
	map<int, int> m;
	int vertice1;
	int vertice2;
	while(!cin.eof()){
		cin >> vertice1;
		cin >> vertice2;
		m.insert(pair<int, int>(vertice2-1, vertice1));		
	}
	for(int i = 0; i < n; i++)
		if(m.find(i) != m.end())
			cout << m.find(i)->second << " ";
		else
			cout << 0 << " ";

	return 0;
}