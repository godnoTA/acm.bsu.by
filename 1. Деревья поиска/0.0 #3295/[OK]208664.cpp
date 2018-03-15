#include <iostream>
#include <fstream>
#include <set>
using namespace std;
int main(){
 set <long long> peaks;
 ifstream in;
 in.open("input.txt");
 long long peak;

 while(in>>peak){
	 peaks.insert(peak);
 }

 long long sum =0;
 for(set<long long>::iterator it=peaks.begin(); it!=peaks.end(); it++){
     sum+=*it;
 }
 in.close();

 ofstream out("output.txt");
 out<<sum;
 out.close();

 system("pause");
 return 0;
}