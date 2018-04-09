#include<vector>
#include<iostream>
#include <fstream>
using namespace std;

int main(){
long long n;

ifstream in;
in.open("input.txt");
in>>n;

vector<long long> arr(n);
long long x, y;
for(int i=0; i<n-1; i++){
    in>>x>>y;
    arr[y-1]=x;
}

ofstream out("output.txt");
for(int i=0; i<n; i++){
    out<<arr[i]<<" ";
}
    return 0;
}