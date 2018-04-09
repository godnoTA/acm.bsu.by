#include <iostream>
#include <fstream>
#include <math.h>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

bool comp(long long a, long long b){
    return a>b;
}
int main(){

	long long n;

	ifstream in;
	in.open("input.txt");

	in>>n;
	vector<long long> arr(n);

	for(long long i=0; i<n; i++){
	    in>>arr[i];
	}

	ofstream out("output.txt");
	if(is_heap(arr.begin(), arr.end(),comp)){
	    out<<"Yes";
	}
	else{
	    out<<"No";
	}
	
	out.close();

	system("pause");
    return 0;
}