#include<vector>
#include<iostream>
#include <fstream>
using namespace std;

int main(){
long long n;

ifstream in;
in.open("input.txt");
in>>n;

vector<vector<long long>> arr(n);
vector<long long> answer(n);
long long x;
for(int i=0; i<n; i++){
	arr[i] = vector<long long>(n);
	for(int j=0; j<n; j++){
    in>>x;
	if(x)
        answer[j]=i+1;
	}
}

ofstream out("output.txt");
for(int i=0; i<n; i++){
    out<<answer[i]<<" ";
}
    return 0;
}