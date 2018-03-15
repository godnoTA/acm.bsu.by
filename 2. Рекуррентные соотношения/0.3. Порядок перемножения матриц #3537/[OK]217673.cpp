#include<iostream>
#include <fstream>
#include <math.h>
using namespace std;

int main(){

	ifstream in;
	in.open("input.txt");
 
	long long length=0;
	int el;
	in>>length;

	long long** arr1= new long long*[length];
	for(int i=0 ;i<length; i++){
	     arr1[i]=new long long[2];
	}
	for(int i =0; i<length; i++){
         in>>arr1[i][0];
	     in>>arr1[i][1];
	}

	in.close();

	long long** arr = new long long*[length];
	for(int i=0 ;i<length; i++){
	     arr[i]=new long long[length];
		 for(int j=0; j<length; j++)
			 arr[i][j]=0;
	}
	for(int j=0; j<length; j++){
		
		for(int i=0; i<length; i++){
			if(i+j<length){
			if(j!=0) arr[i][i+j]=INT_MAX;
		    for(int k=i; k<j+i; k++){
		        arr[i][i+j] = min(arr[i][i+j], arr[i][k]+arr[k+1][i+j]+arr1[i][0]*arr1[k][1]*arr1[i+j][1]);
				
		    }
			}
		}
	}

	long long answer =arr[0][length-1];
	ofstream out("output.txt");
	out<<answer;
	out.close();

	system("pause");	
	return 0;
}