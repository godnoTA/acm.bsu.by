#include <iostream> 
#include <fstream> 
using namespace std;
int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	long long n, counter=0;
	cin >> n;
	while (n){
		if (n % 2 == 1) cout << counter << endl;
		n /= 2;
		counter++;
	}	return 0;
}