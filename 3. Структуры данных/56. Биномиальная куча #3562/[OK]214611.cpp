#include <iostream>
#include  <alg.h>
#include <queue>
#include <fstream>

using namespace std;

int main()
{
	long long number;
	queue<long long> st;

	ifstream fin("input.txt", ios_base::in);
	ofstream fout("output.txt", ios_base::out);


	fin >> number;
	while (number != 0)
	{
		st.push(number % 2);
		number =(long long)number/ 2;
	}
	long long j= st.size(),i=0,k=0;
	while (i != j)
	{
		k = st.front();
		st.pop();
		if (k == 1)
			fout << i << "\n";
		i++;
	}
	fout.close();
}