#include <iostream>
#include <fstream>

void input(int &n, int &m, int* &A, int* &B, int &s) {
	std::ifstream istr("input.txt");
	istr>>n>>m>>s;
	A= new int[n];
	B= new int[m];
	for (int i=0;i<n;i++)
		istr>>A[i];
	for (int i=0;i<m;i++)
		istr>>B[i];
	istr.close();
};
void output(bool sat){
	std::ofstream ostr("output.txt");
	if (sat) ostr<<"Yes";
	else ostr<<"No";
	ostr.close();
};

int main() {
	int n,m,s;
	int *A, *B;
	int* sat;
	bool sated = false;
	input(n,m,A,B,s);
	int sum=-s;
	for (int i=0;i<n;i++)
		sum+=A[i];
	if (sum<0) {output(false); return 0;};
	if (sum==0) {output(true); return 0;}
	sat=new int[sum+1];
	for (int i=0;i<sum+1;i++)
		sat[i]=0;
	sat[0]=1;
	for (int i=0;(i<n&&!sated); i++) 
		for (int j=sum-1;j>=0;j--){
			if (sat[j]==1) {
				if (j+A[i]==sum) {
					sated=true;
					output(sated);
				}
				else if (j+A[i]<sum) {
						sat[j+A[i]]=1;
					};
			};
		};

	if (sated) return 0;

	for (int i=0;(i<m&&!sated); i++) 
		for (int j=sum-1;j>=0;j--){
			if (sat[j]==1) {
				if (j+B[i]==sum) {
					sated=true;
					output(sated);
				}
				else if (j+B[i]<sum) {
						sat[j+B[i]]=1;
					};
			};
		};

if (!sated)	output(false);
	return 0;
}