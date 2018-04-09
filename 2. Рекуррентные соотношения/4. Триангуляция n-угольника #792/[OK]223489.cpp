#include <iostream>
#include <fstream>
#include <math.h>
#include <vector>
using namespace std;


double dl(int x1, int y1, int x2, int y2){
	return sqrt((x1-x2)*(x1-x2)*1.0 + (y1-y2)*(y1-y2)*1.0);
}


int main(){
	int n;

	ifstream in;
	in.open("input.txt");

	in>>n;

	int* x =  new int[n];
	int* y = new int[n];
	for(int i=0; i<n; i++){
	    in>>x[i]>>y[i];
	}


	vector<vector<double>> S(n);
	for(int i=0; i<n; i++){
	    S[i]= vector<double>(n);
		S[i][i]=0;
		S[i][(i+1)%n]=0;
		S[i][(i+2)%n]=dl(x[i],y[i], x[(i+2)%n], y[(i+2)%n]);

	}

	for(long long k=3; k<n; k++){

	    for(int p=0; p<n; p++){

		    S[p][(p+k)%n] = dl(x[p], y[p], x[(p+k)%n], y[(p+k)%n]);

			double min=INT_MAX;
			for(int i=p+1; i<p+k; i++){
			    min = min<(S[p][i%n]+S[i%n][(p+k)%n])?min:(S[p][i%n]+S[i%n][(p+k)%n]);
			}
			S[p][(p+k)%n]+=min;
		}
	}


	double answer =INT_MAX;
	for(int i=0; i<n; i++){
	    answer = answer>S[i][(i+n-2)%n]?S[i][(i+n-2)%n]:answer;
	}


	ofstream out("output.txt");
	out<<answer;
	out.close();
	delete x, y;

	system("pause");
    return 0;
}