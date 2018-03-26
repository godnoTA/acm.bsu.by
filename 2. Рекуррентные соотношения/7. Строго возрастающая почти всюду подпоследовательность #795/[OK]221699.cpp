#include<iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <math.h>
using namespace std;
bool Comp(long long a, long long b){
	return a>b;
}
int main(){

	ifstream in;
	in.open("input.txt");

	
 
	long long length = 0;
	in>>length;
	vector<long long> arr(length);

	vector<long long> chit1(length);
	vector<long long> chit2(length);

	for(long long i=0; i<length; i++){
	    in>>arr[i];
	}
	in.close();

	vector<long long> db1(length+1);
    db1[0]=INT_MIN;
	for(long long i=1; i<length+1; i++){
	    db1[i]=INT_MAX;
	}

	long long maxlength1 = 0;

	long long pos1=length-1, pos2=0;

	for(long long i=0; i<length; i++){
		long long j = int(upper_bound(db1.begin(), db1.end(), arr[i])-db1.begin());
			if(db1[j-1]<arr[i] && arr[i]<db1[j]){
			    db1[j] = arr[i];
				if(j>maxlength1){ 
					maxlength1=j;
				}
				chit1[i]=j;
				
			}
			else{
				chit1[i]=(int)(lower_bound(db1.begin(), db1.end(), arr[i])-db1.begin());
			}
		
	}
	reverse(arr.begin(), arr.end());

	vector<long long> db2(length+1);
    db2[0]=INT_MAX;
	for(long long i=1; i<length+1; i++){
	    db2[i]=INT_MIN;
	}

	int maxlength2 = 0;

	for(long long i=0; i<length; i++){
		vector <long long>::iterator tmp =lower_bound(db2.begin(), db2.end(), arr[i] ,Comp);
		if(tmp==db2.end()) {
			cout<<"1";
		}
	    long long j = int(tmp-db2.begin());
		if(db2[j-1]>arr[i] && arr[i]>db2[j]){
			    db2[j] = arr[i];
				if(j>maxlength2) {
					maxlength2=j;
				}
				chit2[i]=j;
				
			}
			else{
				vector <long long>::iterator tmp1 = lower_bound(db2.begin(), db2.end(), arr[i] ,Comp);
				if(*tmp){
				}
				chit2[i]=(int)(tmp1-db2.begin());
			}
		
	}

	long long sum =1;
	ofstream out("output.txt");

	vector<long long> maximums(length);
	reverse(chit2.begin(), chit2.end());
	maximums[length-1]=chit2[length-1];

	for(long long j=length-2; j>=1; j--){
		maximums[j]=max(chit2[j], maximums[j+1]);
	}

    for(long long i=0; i<length-1; i++)
        sum = (sum<(chit1[i]+maximums[i+1]))?chit1[i]+maximums[i+1]:sum;

    out<<sum;
	
	out.close();
	system("pause");	
	return 0;
}


/*тесты
9
1 2 12 7 3 8 14 13 9

= 6

1
1

=1

3
1 2 3

=3

3
3 1 2

=3

21
10 11 12 13 13 12 15 1 2 3 4 2 11 20 20 1 20 21 22 2 23

=14

*/