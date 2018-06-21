#include <iostream> 
#include <fstream> 
using namespace std;
void main(){
	ifstream in("input.txt");
	ofstream out("output.txt");
	long long n, count = 0;
	in >> n;
	while (n){
		if (n % 2 == 1)
		{
			out << count << endl;
		}
		n = n / 2;
		count = count + 1;
	}	
}