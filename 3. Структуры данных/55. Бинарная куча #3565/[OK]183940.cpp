#include <iostream>
#include <fstream>
#include <math.h>
#include <vector>

using namespace std;
ifstream in("input.txt");
ofstream out("output.txt");


int main() {
	if (in.is_open())
	{
		int N;
		bool b = true;
		in >> N;
		int * A = new int[N];
		for (int i = 0; i < N; i++)
			in >> A[i];
		for (int i = 0; i <= N / 2; i++) {
			if (2 * (i + 1)-1 > N-1)
				break;
			if ((2 * (i + 1) > N-1)){
				if((A[2 * i + 1] >= A[i])) {continue;}
				else if((A[2 * i + 1] < A[i])) {
					b = false;
					break;
				}
			}
			if ((A[2 * (i + 1)-1] >= A[i])&&(A[2 * (i + 1)] >= A[i])){continue;}
			else {
				b = false;
				break;
			}
		}
		out << ((b)? "Yes" : "No")<<endl;
	}
	else
		cout << "file is not opened!!!"<<endl;
	in.close();
	out.close();
	return 0;
}