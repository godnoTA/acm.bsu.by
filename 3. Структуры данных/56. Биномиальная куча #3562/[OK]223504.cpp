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

	ofstream out("output.txt");
	vector<long long>arr;

	long long col =1;

	
	while(n>1){
	    long long g = 2;
	    long long col=0;
	    while(true){
	      	if(g>n) {break;}
		    else {
				g*=2;
		        col++;
			}
	     }

	    g/=2;

	    n-=g;
	    arr.push_back(col);
	}

	if(n==1){
	    arr.push_back(0);
	}

	for(long long i=arr.size()-1; i>=0; i--)
		out<<arr[i]<<endl;

	out.close();
	system("pause");
    return 0;
}