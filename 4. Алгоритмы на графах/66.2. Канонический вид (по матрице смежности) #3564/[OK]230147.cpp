#include <iostream> 
#include <fstream> 
#include <vector> 
using namespace std;
int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	int n;
	cin >> n;
	int a;
	vector<int> ancestor(n);
	for (int i = 0; i < n; i++)
	for (int j = 0; j < n; j++){
		cin >> a;
		if (a)
		ancestor[j] = i + 1;
	}

	for (int i = 0; i < n; i++) cout << ancestor[i] << " ";
	return 0;
}