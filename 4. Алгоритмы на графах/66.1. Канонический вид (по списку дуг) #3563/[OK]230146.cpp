#include <iostream> 
#include <fstream> 
#include <vector> 
using namespace std;
int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	int n;
	cin >> n;
	int a, b;
	vector<int> ancestor(n);
	for (int i = 0; i < n-1; i++) cin >> a >> b,
	ancestor[b - 1] = a;

	for (int i = 0; i < n; i++) cout << ancestor[i] << " ";
	return 0;
}