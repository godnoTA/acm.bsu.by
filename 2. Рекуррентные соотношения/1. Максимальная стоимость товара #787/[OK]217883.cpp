#include<fstream>
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
int main()
{
	fstream fin("input.txt");
	ofstream fout("output.txt");
	long n, m, sum = 0, S = 0;
	fin >> n >> m;
	cout << n << endl << m << endl;
	long nm = n + m;
	vector<long> A(n);
	vector<long> B(m);
	vector<long> C(nm);
//	cout << "\nA:" << endl;
	for (int i = 0; i < n; i++)
	{
		fin >> A[i];
		sum += A[i];
		//cout << A[i] << endl;
	}
	cout << endl;
	//cout << "\nB:" << endl;
	for (int i = 0; i < m; i++)
	{
		fin >> B[i];
		//cout << B[i] << endl;
	}
	C = A;
	C.insert(C.end(), B.begin(), B.end());
	cout << endl;
	/*cout << "\nC:" << endl;
	for (int i = 0; i < nm; i++)
	{
		cout << C[i] << endl;
	}*/
	sort(C.begin(), C.end());
	cout << endl;
	/*for (int i = 0; i < nm; i++)
	{
		cout << C[i] << endl;
	}*/
	for (int i = 0; i < nm; i++)
	{		
			if (C[i] <= S + 1 /*&& S<sum*/)
				S += C[i];
			else
				break;
	}
	if (S >= sum)
	{
		cout << "\nYES" << endl;
		fout << "YES";
	}
	else
	{
		cout << "\nNO\nAnswer: " << sum - S - 1 << endl;
		fout << "NO\n" << sum - S - 1;
	}
	fin.close();
	fout.close();
	return 0;
}